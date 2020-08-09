package com.yrazlik.lol.service;

import com.yrazlik.lol.request.WeeklyFreeRotationRequest;
import com.yrazlik.lol.response.WeeklyFreeRotationResponse;

public interface WeeklyFreeRotationService {

	public WeeklyFreeRotationResponse getWeeklyFreeRotation(WeeklyFreeRotationRequest request);
	
}
