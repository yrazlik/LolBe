package com.yrazlik.lol.request;

public class RequestGetLeagueInfoByLeagueId extends BaseRequest {

	private String leagueId;

	public RequestGetLeagueInfoByLeagueId() {
		super();
	}
	
	public RequestGetLeagueInfoByLeagueId(String language, String region, String leagueId) {
		super(language, region);
		this.leagueId = leagueId;
	}

	public String getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}
	
	
}
