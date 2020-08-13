package com.yrazlik.lol.response;

import com.yrazlik.lol.pojo.SingleParticipantMatchListDto;
import com.yrazlik.lol.pojo.SummonerAveragesDto;

import java.util.List;

import com.yrazlik.lol.pojo.ChampionMasteryDTO;

public class SearchSummonerByNameResponse {
	
	private GetSummonerByNameResponse summonerInfo;
	private SummonerAveragesDto averages;
	private SingleParticipantMatchListDto matchList;
	private SummonerLeagueInfoResponse leagueInfo;
	private List<ChampionMasteryDTO> masteries;
	
	public GetSummonerByNameResponse getSummonerInfo() {
		return summonerInfo;
	}
	public void setSummonerInfo(GetSummonerByNameResponse summonerInfo) {
		this.summonerInfo = summonerInfo;
	}
	
	public SummonerAveragesDto getAverages() {
		return averages;
	}
	public void setAverages(SummonerAveragesDto averages) {
		this.averages = averages;
	}
	public SingleParticipantMatchListDto getMatchList() {
		return matchList;
	}
	public void setMatchList(SingleParticipantMatchListDto matchList) {
		this.matchList = matchList;
	}
	public SummonerLeagueInfoResponse getLeagueInfo() {
		return leagueInfo;
	}
	public void setLeagueInfo(SummonerLeagueInfoResponse leagueInfo) {
		this.leagueInfo = leagueInfo;
	}
	public List<ChampionMasteryDTO> getMasteries() {
		return masteries;
	}
	public void setMasteries(List<ChampionMasteryDTO> masteries) {
		this.masteries = masteries;
	}
	
	
}
