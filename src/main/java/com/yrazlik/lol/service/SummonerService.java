package com.yrazlik.lol.service;

import com.yrazlik.lol.request.RequestGetActiveGame;
import com.yrazlik.lol.request.RequestGetSummonerByName;
import com.yrazlik.lol.response.GetActiveGameInfoResponse;
import com.yrazlik.lol.response.GetSummonerByNameResponse;

public interface SummonerService {
	public GetSummonerByNameResponse findSummonerByName(RequestGetSummonerByName request);
	public GetActiveGameInfoResponse getActiveGameInfo(RequestGetActiveGame request);
}
