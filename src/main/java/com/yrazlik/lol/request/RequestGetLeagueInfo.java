package com.yrazlik.lol.request;

public class RequestGetLeagueInfo extends BaseRequest {

	private String queue;
	private String tier;
	private String division;
	
	public RequestGetLeagueInfo() {
		super();
	}
	public RequestGetLeagueInfo(String language, String region, String queue, String tier, String division) {
		super(language, region);
		this.queue = queue;
		this.tier = tier;
		this.division = division;
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}

	
	
	
}
