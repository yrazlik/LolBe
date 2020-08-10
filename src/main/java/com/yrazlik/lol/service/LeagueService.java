package com.yrazlik.lol.service;

import com.yrazlik.lol.pojo.LeagueEntryDto;
import com.yrazlik.lol.pojo.LeagueListDto;
import com.yrazlik.lol.request.RequestGetLeagueInfo;
import com.yrazlik.lol.request.RequestGetLeagueInfoByLeagueId;
import com.yrazlik.lol.request.RequestGetSummonerLeague;

public interface LeagueService {
	public LeagueEntryDto getSummonerLeagueInfo(RequestGetSummonerLeague request);

	public LeagueEntryDto getLeagueInfo(RequestGetLeagueInfo requestModel);
	
	public LeagueListDto getLeagueInfoByLeagueId(RequestGetLeagueInfoByLeagueId requestModel);

}
