package com.yrazlik.lol.response;

import java.util.List;

import com.yrazlik.lol.pojo.LeagueEntryDto;

public class SummonerLeagueInfoResponse {

	private List<LeagueEntryDto> leagues;

	public List<LeagueEntryDto> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<LeagueEntryDto> leagues) {
		this.leagues = leagues;
	}
	
	
}
