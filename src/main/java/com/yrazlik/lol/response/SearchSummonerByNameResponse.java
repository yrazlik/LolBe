package com.yrazlik.lol.response;

import com.yrazlik.lol.pojo.MatchListDto;

public class SearchSummonerByNameResponse {
	
	private GetSummonerByNameResponse summonerInfo;
	private MatchListDto matchList;
	private SummonerLeagueInfoResponse leagueInfo;
	
	public GetSummonerByNameResponse getSummonerInfo() {
		return summonerInfo;
	}
	public void setSummonerInfo(GetSummonerByNameResponse summonerInfo) {
		this.summonerInfo = summonerInfo;
	}
	public MatchListDto getMatchList() {
		return matchList;
	}
	public void setMatchList(MatchListDto matchList) {
		this.matchList = matchList;
	}
	public SummonerLeagueInfoResponse getLeagueInfo() {
		return leagueInfo;
	}
	public void setLeagueInfo(SummonerLeagueInfoResponse leagueInfo) {
		this.leagueInfo = leagueInfo;
	}
	
	
}
