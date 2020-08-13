package com.yrazlik.lol.pojo;

public class SummonerAveragesDto {
	
	private double averageKills;
	private double averageDeaths;
	private double averageAssits;
	private double averageMinions;
	private double winRate;
	
	public SummonerAveragesDto() {
		super();
	}
	
	public SummonerAveragesDto(double averageKills, double averageDeaths, double averageAssits, double averageMinions,
			double winRate) {
		super();
		this.averageKills = averageKills;
		this.averageDeaths = averageDeaths;
		this.averageAssits = averageAssits;
		this.averageMinions = averageMinions;
		this.winRate = winRate;
	}
	public double getAverageKills() {
		return averageKills;
	}
	public void setAverageKills(double averageKills) {
		this.averageKills = averageKills;
	}
	public double getAverageDeaths() {
		return averageDeaths;
	}
	public void setAverageDeaths(double averageDeaths) {
		this.averageDeaths = averageDeaths;
	}
	public double getAverageAssits() {
		return averageAssits;
	}
	public void setAverageAssits(double averageAssits) {
		this.averageAssits = averageAssits;
	}
	public double getAverageMinions() {
		return averageMinions;
	}
	public void setAverageMinions(double averageMinions) {
		this.averageMinions = averageMinions;
	}
	public double getWinRate() {
		return winRate;
	}
	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}
	
	

}
