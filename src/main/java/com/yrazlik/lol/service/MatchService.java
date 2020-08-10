package com.yrazlik.lol.service;

import com.yrazlik.lol.pojo.MatchDto;
import com.yrazlik.lol.pojo.MatchListDto;
import com.yrazlik.lol.pojo.RequestGetMatchDetail;
import com.yrazlik.lol.request.RequestGetMatchListByAccountId;

public interface MatchService {

    public MatchListDto getMatchListByAccountId(RequestGetMatchListByAccountId requestModel);

	public MatchDto getMatchDetail(RequestGetMatchDetail requestModel);
	

}
