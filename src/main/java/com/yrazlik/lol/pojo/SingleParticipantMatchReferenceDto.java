package com.yrazlik.lol.pojo;

public class SingleParticipantMatchReferenceDto {
	
	private long gameId;
	private String role;
	private int season;
	private String platformId;
	private int champion;
	private int queue;
	private String queueName;
	private String lane;
	private long timestamp;
	private SingleParticipantMatchDto matchDto;
	
	public SingleParticipantMatchReferenceDto() {
		super();
	}
	public SingleParticipantMatchReferenceDto(long gameId, String role, int season, String platformId, int champion,
			int queue, String queueName, String lane, long timestamp) {
		super();
		this.gameId = gameId;
		this.role = role;
		this.season = season;
		this.platformId = platformId;
		this.champion = champion;
		this.queue = queue;
		this.queueName = queueName;
		this.lane = lane;
		this.timestamp = timestamp;
	}
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
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
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
	public SingleParticipantMatchDto getMatchDto() {
		return matchDto;
	}
	public void setMatchDto(SingleParticipantMatchDto matchDto) {
		this.matchDto = matchDto;
	}
	
	
	

}
