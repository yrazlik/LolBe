package com.yrazlik.lol.request;

public class RequestSearchSummonerByName extends BaseRequest {

	private String summonerName;

	public  RequestSearchSummonerByName() {
		super();
	}
	
	public  RequestSearchSummonerByName(String language, String region, String summonerName) {
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
