package com.yrazlik.lol.pojo;

public class ChampionMasteryDTO {

	private long championPointsUntilNextLevel;
	private boolean chestGranted;
	private long championId;
	private String championName;
	private long lastPlayTime;
	private int championLevel;
	private String summonerId;
	private int championPoints;
	private long championPointsSinceLastLevel;
	private int tokensEarned;
	private String championImageUrl;
	
	public long getChampionPointsUntilNextLevel() {
		return championPointsUntilNextLevel;
	}
	public void setChampionPointsUntilNextLevel(long championPointsUntilNextLevel) {
		this.championPointsUntilNextLevel = championPointsUntilNextLevel;
	}
	public boolean isChestGranted() {
		return chestGranted;
	}
	public void setChestGranted(boolean chestGranted) {
		this.chestGranted = chestGranted;
	}
	public long getChampionId() {
		return championId;
	}
	public void setChampionId(long championId) {
		this.championId = championId;
	}
	public long getLastPlayTime() {
		return lastPlayTime;
	}
	public void setLastPlayTime(long lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
	}
	public int getChampionLevel() {
		return championLevel;
	}
	public void setChampionLevel(int championLevel) {
		this.championLevel = championLevel;
	}
	public String getSummonerId() {
		return summonerId;
	}
	public void setSummonerId(String summonerId) {
		this.summonerId = summonerId;
	}
	public int getChampionPoints() {
		return championPoints;
	}
	public void setChampionPoints(int championPoints) {
		this.championPoints = championPoints;
	}
	public long getChampionPointsSinceLastLevel() {
		return championPointsSinceLastLevel;
	}
	public void setChampionPointsSinceLastLevel(long championPointsSinceLastLevel) {
		this.championPointsSinceLastLevel = championPointsSinceLastLevel;
	}
	public int getTokensEarned() {
		return tokensEarned;
	}
	public void setTokensEarned(int tokensEarned) {
		this.tokensEarned = tokensEarned;
	}
	public String getChampionImageUrl() {
		return championImageUrl;
	}
	public void setChampionImageUrl(String championImageUrl) {
		this.championImageUrl = championImageUrl;
	}
	public String getChampionName() {
		return championName;
	}
	public void setChampionName(String championName) {
		this.championName = championName;
	}
	
	
}
