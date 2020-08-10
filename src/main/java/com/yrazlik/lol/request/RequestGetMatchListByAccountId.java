package com.yrazlik.lol.request;

public class RequestGetMatchListByAccountId extends BaseRequest {
	
	private String accountId;
	
	public RequestGetMatchListByAccountId() {
		super();
	}

	public RequestGetMatchListByAccountId(String language, String region, String accountId) {
		super(language, region);
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	

}
