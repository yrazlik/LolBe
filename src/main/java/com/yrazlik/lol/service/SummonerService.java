package com.yrazlik.lol.service;

import java.util.List;

import com.yrazlik.lol.pojo.ChampionMasteryDTO;
import com.yrazlik.lol.request.RequestChampionMasteries;
import com.yrazlik.lol.request.RequestGetActiveGame;
import com.yrazlik.lol.request.RequestGetSummonerByName;
import com.yrazlik.lol.request.RequestSearchSummonerByName;
import com.yrazlik.lol.response.GetActiveGameInfoResponse;
import com.yrazlik.lol.response.GetSummonerByNameResponse;
import com.yrazlik.lol.response.SearchSummonerByNameResponse;

public interface SummonerService {
	public GetSummonerByNameResponse findSummonerByName(RequestGetSummonerByName request);
	public GetActiveGameInfoResponse getActiveGameInfo(RequestGetActiveGame request);
	public SearchSummonerByNameResponse searchSummonerByName(RequestSearchSummonerByName requestModel);
	public List<ChampionMasteryDTO> getSummonerChampionMasteries(RequestChampionMasteries requestModel);
}
