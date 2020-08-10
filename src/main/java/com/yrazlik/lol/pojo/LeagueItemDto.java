package com.yrazlik.lol.pojo;

public class LeagueItemDto {
	
	private boolean freshBlood;
	private long wins;
	private String summonerName;
	private MiniSeriesDto miniSeries;
	private boolean inactive;
	private boolean veteran;
	private boolean hotStreak;
	private String rank;
	private long leaguePoints;
	private long losses;
	private String summonerId;
	public boolean isFreshBlood() {
		return freshBlood;
	}
	public void setFreshBlood(boolean freshBlood) {
		this.freshBlood = freshBlood;
	}
	public long getWins() {
		return wins;
	}
	public void setWins(long wins) {
		this.wins = wins;
	}
	public String getSummonerName() {
		return summonerName;
	}
	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}
	public MiniSeriesDto getMiniSeries() {
		return miniSeries;
	}
	public void setMiniSeries(MiniSeriesDto miniSeries) {
		this.miniSeries = miniSeries;
	}
	public boolean isInactive() {
		return inactive;
	}
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	public boolean isVeteran() {
		return veteran;
	}
	public void setVeteran(boolean veteran) {
		this.veteran = veteran;
	}
	public boolean isHotStreak() {
		return hotStreak;
	}
	public void setHotStreak(boolean hotStreak) {
		this.hotStreak = hotStreak;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public long getLeaguePoints() {
		return leaguePoints;
	}
	public void setLeaguePoints(long leaguePoints) {
		this.leaguePoints = leaguePoints;
	}
	public long getLosses() {
		return losses;
	}
	public void setLosses(long losses) {
		this.losses = losses;
	}
	public String getSummonerId() {
		return summonerId;
	}
	public void setSummonerId(String summonerId) {
		this.summonerId = summonerId;
	}
	
	

}
