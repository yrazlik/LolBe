package com.yrazlik.lol.pojo;

public class CurrentGameParticipantDto {
	private long championId;
	private PerkDto perks;
	private long profileIconId;
	private boolean bot;
	private long teamId;
	private String summonerName;
	private String summonerId;
	private long spell1Id;
	private long spell2Id;
	private String summonerIconUrl;
	private String championIconUrl;
	private String championName;
	//gameCustomizationObjects	List[GameCustomizationObject]	List of Game Customizations
	public long getChampionId() {
		return championId;
	}
	public void setChampionId(long championId) {
		this.championId = championId;
	}
	public PerkDto getPerks() {
		return perks;
	}
	public void setPerks(PerkDto perks) {
		this.perks = perks;
	}
	public long getProfileIconId() {
		return profileIconId;
	}
	public void setProfileIconId(long profileIconId) {
		this.profileIconId = profileIconId;
	}
	public boolean isBot() {
		return bot;
	}
	public void setBot(boolean bot) {
		this.bot = bot;
	}
	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}
	public String getSummonerName() {
		return summonerName;
	}
	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}
	public String getSummonerId() {
		return summonerId;
	}
	public void setSummonerId(String summonerId) {
		this.summonerId = summonerId;
	}
	public long getSpell1Id() {
		return spell1Id;
	}
	public void setSpell1Id(long spell1Id) {
		this.spell1Id = spell1Id;
	}
	public long getSpell2Id() {
		return spell2Id;
	}
	public void setSpell2Id(long spell2Id) {
		this.spell2Id = spell2Id;
	}
	public String getSummonerIconUrl() {
		return summonerIconUrl;
	}
	public void setSummonerIconUrl(String summonerIconUrl) {
		this.summonerIconUrl = summonerIconUrl;
	}
	public String getChampionIconUrl() {
		return championIconUrl;
	}
	public void setChampionIconUrl(String championIconUrl) {
		this.championIconUrl = championIconUrl;
	}
	public String getChampionName() {
		return championName;
	}
	public void setChampionName(String championName) {
		this.championName = championName;
	}
	
	
}
