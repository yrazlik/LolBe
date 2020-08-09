package com.yrazlik.lol.service.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.yrazlik.lol.httpclient.LolHttpClient;
import com.yrazlik.lol.pojo.ChampionDto;
import com.yrazlik.lol.pojo.CurrentGameParticipantDto;
import com.yrazlik.lol.request.RequestGetActiveGame;
import com.yrazlik.lol.request.RequestGetSummonerByName;
import com.yrazlik.lol.response.AllChampionsResponse;
import com.yrazlik.lol.response.GetActiveGameInfoResponse;
import com.yrazlik.lol.response.GetSummonerByNameResponse;
import com.yrazlik.lol.service.DataDragonService;
import com.yrazlik.lol.service.SummonerService;
import com.yrazlik.lol.util.ServicePaths;
import com.yrazlik.lol.util.UrlUtil;

@Service
public class SummonerServiceImpl implements SummonerService {
	
	@Autowired
	private LolHttpClient lolHttpClient;
	
	@Autowired
	private DataDragonService dataDragonService;
	
	@Override
	public GetSummonerByNameResponse findSummonerByName(RequestGetSummonerByName request) {
		String url = UrlUtil.buildSummonerSearchUrl(request.getRegion(), URLEncoder.encode(request.getSummonerName()));
		String responseStr = lolHttpClient.makeGetRequest(url);
		GetSummonerByNameResponse response =  new Gson().fromJson(responseStr, GetSummonerByNameResponse.class);
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
			String responseStr = lolHttpClient.makeGetRequest(url);
			response =  new Gson().fromJson(responseStr, GetActiveGameInfoResponse.class);
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

}
