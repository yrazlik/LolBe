package com.yrazlik.lol.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.JsonSyntaxException;
import com.yrazlik.lol.request.WeeklyFreeRotationRequest;
import com.yrazlik.lol.response.BaseResponse;
import com.yrazlik.lol.response.WeeklyFreeRotationResponse;
import com.yrazlik.lol.service.WeeklyFreeRotationService;
import com.yrazlik.lol.util.PlatformConstants;

@RestController
@RequestMapping("/weekly-rotation")
public class WeeklyFreeChampionsController {

	@Autowired
	private WeeklyFreeRotationService weeklyFreeRotationService;

	@RequestMapping(method = RequestMethod.GET, value = "/weeklyFreeRotation.json")
	public BaseResponse<WeeklyFreeRotationResponse> getWeeklyFreeRotation(HttpServletRequest request) throws JsonSyntaxException, IOException {
		WeeklyFreeRotationRequest weeklyFreeRotationRequest = new WeeklyFreeRotationRequest(
				request.getHeader(PlatformConstants.HEADER_LANGUAGE),
				request.getHeader(PlatformConstants.HEADER_REGION));
		WeeklyFreeRotationResponse weeklyFreeRotationsResponse = weeklyFreeRotationService.getWeeklyFreeRotation(weeklyFreeRotationRequest);
		return new BaseResponse<WeeklyFreeRotationResponse>(weeklyFreeRotationsResponse);
	}

}
