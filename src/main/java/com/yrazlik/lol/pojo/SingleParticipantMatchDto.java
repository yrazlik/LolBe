package com.yrazlik.lol.pojo;

public class SingleParticipantMatchDto {

	private long gameId;
	private ParticipantIdentityDto participantIdentitiy;
	private int queueId;
	private String gameType;
	private String platformId;
	private long gameCreation;
	private long gameDuration;
	private int seasonId;
	private String gameVersion;
	private int mapId;
	private String gameMode;
	private ParticipantDto participant;
	
	
	public SingleParticipantMatchDto() {
		super();
	}
	public SingleParticipantMatchDto(long gameId, int queueId, String gameType, String platformId, long gameCreation,
			int seasonId, String gameVersion, int mapId, String gameMode, long gameDuration) {
		super();
		this.gameId = gameId;
		this.queueId = queueId;
		this.gameType = gameType;
		this.platformId = platformId;
		this.gameCreation = gameCreation;
		this.seasonId = seasonId;
		this.gameVersion = gameVersion;
		this.mapId = mapId;
		this.gameMode = gameMode;
		this.gameDuration = gameDuration;
	}
	public long getGameId() {
		return gameId;
	}
	public void setGameId(long gameId) {
		this.gameId = gameId;
	}
	public ParticipantIdentityDto getParticipantIdentitiy() {
		return participantIdentitiy;
	}
	public void setParticipantIdentitiy(ParticipantIdentityDto participantIdentitiy) {
		this.participantIdentitiy = participantIdentitiy;
	}
	public int getQueueId() {
		return queueId;
	}
	public void setQueueId(int queueId) {
		this.queueId = queueId;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public long getGameCreation() {
		return gameCreation;
	}
	public void setGameCreation(long gameCreation) {
		this.gameCreation = gameCreation;
	}
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	public String getGameVersion() {
		return gameVersion;
	}
	public void setGameVersion(String gameVersion) {
		this.gameVersion = gameVersion;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public String getGameMode() {
		return gameMode;
	}
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	public ParticipantDto getParticipant() {
		return participant;
	}
	public void setParticipant(ParticipantDto participant) {
		this.participant = participant;
	}
	public long getGameDuration() {
		return gameDuration;
	}
	public void setGameDuration(long gameDuration) {
		this.gameDuration = gameDuration;
	}
	
	
}
