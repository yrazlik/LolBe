package com.yrazlik.lol.request;

public class ChampionDetailRequest extends BaseRequest {
	
	private String id;
	
	public ChampionDetailRequest() {
		super();
	}

	public ChampionDetailRequest(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
