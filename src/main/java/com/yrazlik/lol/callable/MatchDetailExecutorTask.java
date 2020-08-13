package com.yrazlik.lol.callable;

import com.yrazlik.lol.ApplicationContextAccessor;
import com.yrazlik.lol.pojo.MatchDto;
import com.yrazlik.lol.pojo.RequestGetMatchDetail;
import com.yrazlik.lol.service.MatchService;

public class MatchDetailExecutorTask extends RequestScopedCallable<MatchDto> {
	
	private MatchService matchService = ApplicationContextAccessor.get().getBean(MatchService.class);
	
	private RequestGetMatchDetail request;
	
	public MatchDetailExecutorTask(RequestGetMatchDetail request) {
		this.request = request;
	}

	@Override
	public MatchDto execute() {
		try {
			return matchService.getMatchDetail(request);
		} catch (Exception e) {
			return null;
		}
	}

}
