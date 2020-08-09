package com.yrazlik.lol.response;

public class GenericErrorResponse {
	
	private String errorMessage;
	private String path;

	public GenericErrorResponse(String errorMessage, String path) {
		this.errorMessage = errorMessage;
		this.path = path;
	}
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
