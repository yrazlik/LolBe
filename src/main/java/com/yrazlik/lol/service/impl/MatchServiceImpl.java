package com.yrazlik.lol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.yrazlik.lol.httpclient.LolHttpClient;
import com.yrazlik.lol.pojo.MatchDto;
import com.yrazlik.lol.pojo.MatchListDto;
import com.yrazlik.lol.pojo.RequestGetMatchDetail;
import com.yrazlik.lol.request.RequestGetMatchListByAccountId;
import com.yrazlik.lol.response.RiotApiResponse;
import com.yrazlik.lol.service.MatchService;
import com.yrazlik.lol.util.UrlUtil;

@Service
public class MatchServiceImpl implements MatchService {
	
	@Autowired
	private LolHttpClient lolHttpClient;

	@Override
	public MatchListDto getMatchListByAccountId(RequestGetMatchListByAccountId request) {
		String url = UrlUtil.buildMatchListByAccountIdUrl(request.getRegion(), request.getAccountId());
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		MatchListDto response =  new Gson().fromJson(responseBody, MatchListDto.class);
		return response;
	}

	@Override
	public MatchDto getMatchDetail(RequestGetMatchDetail request) {
		String url = UrlUtil.buildMatchDetailUrl(request.getRegion(), request.getMatchId());
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		MatchDto response =  new Gson().fromJson(responseBody, MatchDto.class);
		return response;
	}
	
	

}
