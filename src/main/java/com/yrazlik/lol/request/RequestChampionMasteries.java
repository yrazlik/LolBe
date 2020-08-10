package com.yrazlik.lol.request;

public class RequestChampionMasteries extends BaseRequest {
	
	private String summonerId;
	
	public RequestChampionMasteries() {
		super();
	}

	public RequestChampionMasteries(String language, String region, String summonerId) {
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
