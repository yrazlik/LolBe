package com.yrazlik.lol.pojo;

public class LeagueEntryDto {

	private String leagueId;
	private String queueType;
	private String tier;
	private String rank;
	private String summonerId;
	private String summonerName;
	private long leaguePoints;
	private long wins;
	private long losses;
	private boolean veteran;
	private boolean inactive;
	private boolean freshBlood;
	private boolean hotStreak;
	private MiniSeriesDto miniSeries;
	public String getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}
	public String getQueueType() {
		return queueType;
	}
	public void setQueueType(String queueType) {
		this.queueType = queueType;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getSummonerId() {
		return summonerId;
	}
	public void setSummonerId(String summonerId) {
		this.summonerId = summonerId;
	}
	public String getSummonerName() {
		return summonerName;
	}
	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}
	public long getLeaguePoints() {
		return leaguePoints;
	}
	public void setLeaguePoints(long leaguePoints) {
		this.leaguePoints = leaguePoints;
	}
	public long getWins() {
		return wins;
	}
	public void setWins(long wins) {
		this.wins = wins;
	}
	public long getLosses() {
		return losses;
	}
	public void setLosses(long losses) {
		this.losses = losses;
	}
	public boolean isVeteran() {
		return veteran;
	}
	public void setVeteran(boolean veteran) {
		this.veteran = veteran;
	}
	public boolean isInactive() {
		return inactive;
	}
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	public boolean isFreshBlood() {
		return freshBlood;
	}
	public void setFreshBlood(boolean freshBlood) {
		this.freshBlood = freshBlood;
	}
	public boolean isHotStreak() {
		return hotStreak;
	}
	public void setHotStreak(boolean hotStreak) {
		this.hotStreak = hotStreak;
	}
	public MiniSeriesDto getMiniSeries() {
		return miniSeries;
	}
	public void setMiniSeries(MiniSeriesDto miniSeries) {
		this.miniSeries = miniSeries;
	}
	
	
}
