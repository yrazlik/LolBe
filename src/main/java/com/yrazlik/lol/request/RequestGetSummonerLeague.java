package com.yrazlik.lol.request;

public class RequestGetSummonerLeague extends BaseRequest {

	private String summonerId;

	public RequestGetSummonerLeague() {
		super();
	}

	public RequestGetSummonerLeague(String language, String region, String summonerId) {
		super(language, region);
		this.summonerId = summonerId;
	}

	public String getSummonerId() {
		return summonerId;
	}

	public void setSummonerId(String summonerId) {
		this.summonerId = summonerId;
	}
	
	
}
