package com.yrazlik.lol.pojo;

public class MatchReferenceDto {
	
	private long gameId;
	private String role;
	private int season;
	private String platformId;
	private int champion;
	private int queue;
	private String queueName;
	private String lane;
	private long timestamp;
	private MatchDto matchDto;
	public long getGameId() {
		return gameId;
	}
	public void setGameId(long gameId) {
		this.gameId = gameId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public int getChampion() {
		return champion;
	}
	public void setChampion(int champion) {
		this.champion = champion;
	}
	public int getQueue() {
		return queue;
	}
	public void setQueue(int queue) {
		this.queue = queue;
	}
	public String getLane() {
		return lane;
	}
	public void setLane(String lane) {
		this.lane = lane;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public MatchDto getMatchDto() {
		return matchDto;
	}
	public void setMatchDto(MatchDto matchDto) {
		this.matchDto = matchDto;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	
	

}
