package com.yrazlik.lol.service;

import com.yrazlik.lol.pojo.LeagueEntryDto;
import com.yrazlik.lol.pojo.LeagueListDto;
import com.yrazlik.lol.request.RequestGetLeagueInfo;
import com.yrazlik.lol.request.RequestGetLeagueInfoByLeagueId;
import com.yrazlik.lol.request.RequestGetSummonerLeague;
import com.yrazlik.lol.response.SummonerLeagueInfoResponse;

public interface LeagueService {
	public SummonerLeagueInfoResponse getSummonerLeagueInfo(RequestGetSummonerLeague request);

	public LeagueEntryDto getLeagueInfo(RequestGetLeagueInfo requestModel);
	
	public LeagueListDto getLeagueInfoByLeagueId(RequestGetLeagueInfoByLeagueId requestModel);

}
