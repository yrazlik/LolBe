package com.yrazlik.lol.service.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yrazlik.lol.httpclient.LolHttpClient;
import com.yrazlik.lol.pojo.ChampionDto;
import com.yrazlik.lol.pojo.CurrentGameParticipantDto;
import com.yrazlik.lol.pojo.MatchListDto;
import com.yrazlik.lol.request.RequestGetActiveGame;
import com.yrazlik.lol.request.RequestGetMatchListByAccountId;
import com.yrazlik.lol.request.RequestGetSummonerByName;
import com.yrazlik.lol.request.RequestGetSummonerLeague;
import com.yrazlik.lol.request.RequestSearchSummonerByName;
import com.yrazlik.lol.response.AllChampionsResponse;
import com.yrazlik.lol.response.GetActiveGameInfoResponse;
import com.yrazlik.lol.response.GetSummonerByNameResponse;
import com.yrazlik.lol.response.RiotApiResponse;
import com.yrazlik.lol.response.SearchSummonerByNameResponse;
import com.yrazlik.lol.response.SummonerLeagueInfoResponse;
import com.yrazlik.lol.service.DataDragonService;
import com.yrazlik.lol.service.LeagueService;
import com.yrazlik.lol.service.MatchService;
import com.yrazlik.lol.service.SummonerService;
import com.yrazlik.lol.util.ServicePaths;
import com.yrazlik.lol.util.UrlUtil;

@Service
public class SummonerServiceImpl implements SummonerService {
	
	@Autowired
	private LolHttpClient lolHttpClient;
	
	@Autowired
	private DataDragonService dataDragonService;
	
	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private MatchService matchService;
	
	@Cacheable(value = "summonerByName", key="{#request?.language, #request?.region, #request?.summonerName}", unless="#result == null")
	@SuppressWarnings("deprecation")
	@Override
	public GetSummonerByNameResponse findSummonerByName(RequestGetSummonerByName request) {
		String url = UrlUtil.buildSummonerSearchUrl(request.getRegion(), URLEncoder.encode(request.getSummonerName()));
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		GetSummonerByNameResponse response =  new Gson().fromJson(responseBody, GetSummonerByNameResponse.class);
		if(response.getId() == null || response.getId().equals("")) {
			return null;
		}
		return response;
	}
	
	@Override
	public GetActiveGameInfoResponse getActiveGameInfo(RequestGetActiveGame request) {
		GetActiveGameInfoResponse response = new GetActiveGameInfoResponse();
		RequestGetSummonerByName req = new RequestGetSummonerByName();
		req.setLanguage(request.getLanguage());
		req.setRegion(request.getRegion());
		req.setSummonerName(request.getSummonerName());
		GetSummonerByNameResponse resp = findSummonerByName(req);
		
		if(resp != null && resp.getAccountId() != null && resp.getAccountId().length() > 0) {
			AllChampionsResponse allChampionsResponse = dataDragonService.getAllChampions(request.getLanguage());
			List<ChampionDto> allChampions = allChampionsResponse.getChampions();
			Map<Long, ChampionDto> allChampionsMap = new HashMap<Long, ChampionDto>();
			for(ChampionDto dto : allChampions) {
				allChampionsMap.put((long)dto.getId(), dto);
			}
			
			String url = UrlUtil.buildActiveGameInfoUrl(request.getRegion(), resp.getId());
			RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
			String responseBody = riotApiResponse.getBody();
			response =  new Gson().fromJson(responseBody, GetActiveGameInfoResponse.class);
			if(response != null && response.getParticipants() != null) {
				for(CurrentGameParticipantDto participant : response.getParticipants()) {
					String summonerIconUrl = ServicePaths.PROFILE_ICON_BASE_URL + participant.getProfileIconId() + ".png";
					participant.setSummonerIconUrl(summonerIconUrl);
					ChampionDto champ = allChampionsMap.containsKey(participant.getChampionId()) ? allChampionsMap.get(participant.getChampionId()) : null;
					if(champ != null) {
						participant.setChampionName(champ == null ? "" : champ.getName());
						String champNameId = champ.getChampId();
						participant.setChampionIconUrl(ServicePaths.DATA_DRAGON_CHAMPION_IMG_BASE_PATH + champNameId + ".png");
					}
				}
			}
		} else {
			
		}

		return response;
	}

	@Override
	public SearchSummonerByNameResponse searchSummonerByName(RequestSearchSummonerByName requestModel) {
		RequestGetSummonerByName requestSummoner = new RequestGetSummonerByName(requestModel.getLanguage(), requestModel.getRegion(), requestModel.getSummonerName());
		GetSummonerByNameResponse respSummoner = findSummonerByName(requestSummoner);
		String summonerId = respSummoner.getId();
		String accountId = respSummoner.getAccountId();
		RequestGetMatchListByAccountId requestMatchList = new RequestGetMatchListByAccountId(requestModel.getLanguage(), requestModel.getRegion(), accountId);
		MatchListDto matchList = matchService.getMatchListByAccountId(requestMatchList);
		
		RequestGetSummonerLeague requestLeague = new RequestGetSummonerLeague(requestModel.getLanguage(), requestModel.getRegion(), summonerId);
		SummonerLeagueInfoResponse respLeague = leagueService.getSummonerLeagueInfo(requestLeague);
		
		//leagueService.get
		
		SearchSummonerByNameResponse response = new SearchSummonerByNameResponse();
		response.setLeagueInfo(respLeague);
		response.setMatchList(matchList);
		response.setSummonerInfo(respSummoner);
		return response;
	}

}
