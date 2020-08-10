package com.yrazlik.lol.request;

public class RequestGetSummonerByName extends BaseRequest {

	private String summonerName;

	public RequestGetSummonerByName() {
		super();
	}
	
	public RequestGetSummonerByName(String language, String region, String summonerName) {
		super(language, region);
		this.summonerName = summonerName;
	}

	public String getSummonerName() {
		return summonerName;
	}

	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}
	
	
}
