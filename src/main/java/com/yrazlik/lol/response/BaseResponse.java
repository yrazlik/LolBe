package com.yrazlik.lol.response;

public class BaseResponse<T> {
	
	private String status = "OK";
	private T body;
	
	public BaseResponse(T body) {
		super();
		this.body = body;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	
	
}
