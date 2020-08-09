package com.yrazlik.lol.request;

public class BaseRequest {
	
	protected String language;
	protected String region;
	
	public BaseRequest() {
		super();
	}
	public BaseRequest(String language, String region) {
		super();
		this.language = language;
		this.region = region;
	}

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	

}
