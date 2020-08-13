package com.yrazlik.lol.pojo;

public class SummonerAveragesDto {
	
	private int averageKills;
	private int averageDeaths;
	private int averageAssists;
	private int averageMinions;
	private int winRate;
	
	public SummonerAveragesDto() {
		super();
	}
	
	public SummonerAveragesDto(int averageKills, int averageDeaths, int averageAssists, int averageMinions,
			int winRate) {
		super();
		this.averageKills = averageKills;
		this.averageDeaths = averageDeaths;
		this.averageAssists = averageAssists;
		this.averageMinions = averageMinions;
		this.winRate = winRate;
	}

	public int getAverageKills() {
		return averageKills;
	}

	public void setAverageKills(int averageKills) {
		this.averageKills = averageKills;
	}

	public int getAverageDeaths() {
		return averageDeaths;
	}

	public void setAverageDeaths(int averageDeaths) {
		this.averageDeaths = averageDeaths;
	}

	public int getAverageAssists() {
		return averageAssists;
	}

	public void setAverageAssists(int averageAssists) {
		this.averageAssists = averageAssists;
	}

	public int getAverageMinions() {
		return averageMinions;
	}

	public void setAverageMinions(int averageMinions) {
		this.averageMinions = averageMinions;
	}

	public int getWinRate() {
		return winRate;
	}

	public void setWinRate(int winRate) {
		this.winRate = winRate;
	}
	
	

}
