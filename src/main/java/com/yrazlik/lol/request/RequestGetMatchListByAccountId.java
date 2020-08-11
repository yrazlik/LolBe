package com.yrazlik.lol.request;

public class RequestGetMatchListByAccountId extends BaseRequest {
	
	private String accountId;
	private int startIndex;
	private int endIndex;
	
	public RequestGetMatchListByAccountId() {
		super();
	}

	public RequestGetMatchListByAccountId(String language, String region, String accountId, int startIndex, int endIndex) {
		super(language, region);
		this.accountId = accountId;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	
	

}
