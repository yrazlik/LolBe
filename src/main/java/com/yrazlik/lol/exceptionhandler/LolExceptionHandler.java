package com.yrazlik.lol.exceptionhandler;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yrazlik.lol.response.BaseResponse;
import com.yrazlik.lol.response.GenericErrorResponse;

@ControllerAdvice
public class LolExceptionHandler {

	private static final Logger LOGGER = Logger.getLogger(LolExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public BaseResponse<GenericErrorResponse> handleException(Exception ex, HttpServletRequest request) {
		String path = request.getServletPath();
		LOGGER.error("Exception for the request: " + path, ex);
		GenericErrorResponse errorResponse = new GenericErrorResponse("Generic Exception", path);
		BaseResponse<GenericErrorResponse> response = new BaseResponse<GenericErrorResponse>(errorResponse);
		response.setStatus("NOK");
		return response ;
	}
	
}
