package com.yrazlik.lol.pojo;

import com.yrazlik.lol.request.BaseRequest;

public class RequestGetMatchDetail extends BaseRequest {

	private long matchId;

	public RequestGetMatchDetail() {
		super();
	}
	
	public RequestGetMatchDetail(String language, String region, long matchId) {
		super(language, region);
		this.matchId = matchId;
	}

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	
	
}
