package com.yrazlik.lol.service.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yrazlik.lol.httpclient.LolHttpClient;
import com.yrazlik.lol.pojo.LeagueEntryDto;
import com.yrazlik.lol.pojo.LeagueListDto;
import com.yrazlik.lol.request.RequestGetLeagueInfo;
import com.yrazlik.lol.request.RequestGetLeagueInfoByLeagueId;
import com.yrazlik.lol.request.RequestGetSummonerLeague;
import com.yrazlik.lol.response.RiotApiResponse;
import com.yrazlik.lol.response.SummonerLeagueInfoResponse;
import com.yrazlik.lol.service.LeagueService;
import com.yrazlik.lol.util.UrlUtil;

@Service
public class LeagueServiceImpl implements LeagueService {

	@Autowired
	private LolHttpClient lolHttpClient;

	@Override
	public SummonerLeagueInfoResponse getSummonerLeagueInfo(RequestGetSummonerLeague request) {
		String url = UrlUtil.buildSummonerLeagueUrl(request.getRegion(), request.getSummonerId());
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();

		Type listType = new TypeToken<List<LeagueEntryDto>>() {}.getType();
		List<LeagueEntryDto> leagues = new Gson().fromJson(responseBody, listType);
		SummonerLeagueInfoResponse response = new SummonerLeagueInfoResponse();
		response.setLeagues(leagues);
		return response;
	}

	@Override
	public LeagueEntryDto getLeagueInfo(RequestGetLeagueInfo request) {
		String url = UrlUtil.buildLeagueInfoUrl(request.getRegion(), request.getQueue(), request.getTier(),
				request.getDivision());
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		LeagueEntryDto response = new Gson().fromJson(responseBody, LeagueEntryDto.class);
		return response;
	}

	@Override
	public LeagueListDto getLeagueInfoByLeagueId(RequestGetLeagueInfoByLeagueId request) {
		String url = UrlUtil.buildLeagueInfoByLeagueIdUrl(request.getRegion(), request.getLeagueId());
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		LeagueListDto response = new Gson().fromJson(responseBody, LeagueListDto.class);
		return response;
	}

}
