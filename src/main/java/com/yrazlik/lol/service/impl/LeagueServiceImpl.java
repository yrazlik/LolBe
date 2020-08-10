package com.yrazlik.lol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yrazlik.lol.httpclient.LolHttpClient;
import com.yrazlik.lol.pojo.LeagueEntryDto;
import com.yrazlik.lol.pojo.LeagueListDto;
import com.yrazlik.lol.request.RequestGetLeagueInfo;
import com.yrazlik.lol.request.RequestGetLeagueInfoByLeagueId;
import com.yrazlik.lol.request.RequestGetSummonerLeague;
import com.yrazlik.lol.service.LeagueService;
import com.yrazlik.lol.util.UrlUtil;

@Service
public class LeagueServiceImpl implements LeagueService {
	
	@Autowired
	private LolHttpClient lolHttpClient;
	
	@Override
	public LeagueEntryDto getSummonerLeagueInfo(RequestGetSummonerLeague request) {
		String url = UrlUtil.buildSummonerLeagueUrl(request.getRegion(), request.getSummonerId());
		String responseStr = lolHttpClient.makeGetRequest(url);
		LeagueEntryDto response =  new Gson().fromJson(responseStr, LeagueEntryDto.class);
		return response;
	}

	@Override
	public LeagueEntryDto getLeagueInfo(RequestGetLeagueInfo request) {
		String url = UrlUtil.buildLeagueInfoUrl(request.getRegion(), request.getQueue(), request.getTier(), request.getDivision());
		String responseStr = lolHttpClient.makeGetRequest(url);
		LeagueEntryDto response =  new Gson().fromJson(responseStr, LeagueEntryDto.class);
		return response;
	}
	
	@Override
	public LeagueListDto getLeagueInfoByLeagueId(RequestGetLeagueInfoByLeagueId request) {
		String url = UrlUtil.buildLeagueInfoByLeagueIdUrl(request.getRegion(), request.getLeagueId());
		String responseStr = lolHttpClient.makeGetRequest(url);
		LeagueListDto response =  new Gson().fromJson(responseStr, LeagueListDto.class);
		return response;
	}

}
