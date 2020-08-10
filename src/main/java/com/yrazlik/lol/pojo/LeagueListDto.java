package com.yrazlik.lol.pojo;

import java.util.List;

public class LeagueListDto {

	private String leagueId;
	private List<LeagueItemDto> entries;
	private String tier;
	private String name;
	private String queue;
	public String getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}
	public List<LeagueItemDto> getEntries() {
		return entries;
	}
	public void setEntries(List<LeagueItemDto> entries) {
		this.entries = entries;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	
	
}
