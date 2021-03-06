package com.yrazlik.lol.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GetSummonerByNameResponse {

	private String id;
	private String accountId;
	@JsonIgnore
	private String puuid;
	private String name;
	private long profileIconId;
	@JsonIgnore
	private long revisionDate;
	private int summonerLevel;
	private String profileIconUrl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getPuuid() {
		return puuid;
	}
	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getProfileIconId() {
		return profileIconId;
	}
	public void setProfileIconId(long profileIconId) {
		this.profileIconId = profileIconId;
	}
	public long getRevisionDate() {
		return revisionDate;
	}
	public void setRevisionDate(long revisionDate) {
		this.revisionDate = revisionDate;
	}
	public int getSummonerLevel() {
		return summonerLevel;
	}
	public void setSummonerLevel(int summonerLevel) {
		this.summonerLevel = summonerLevel;
	}
	public String getProfileIconUrl() {
		return profileIconUrl;
	}
	public void setProfileIconUrl(String profileIconUrl) {
		this.profileIconUrl = profileIconUrl;
	}
	
	
}
