package com.yrazlik.lol.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.JsonSyntaxException;
import com.yrazlik.lol.pojo.MatchDto;
import com.yrazlik.lol.pojo.MatchListDto;
import com.yrazlik.lol.pojo.RequestGetMatchDetail;
import com.yrazlik.lol.request.RequestGetMatchListByAccountId;
import com.yrazlik.lol.response.BaseResponse;
import com.yrazlik.lol.service.MatchService;
import com.yrazlik.lol.util.PlatformConstants;
import com.yrazlik.lol.util.Utils;

@RestController
@RequestMapping("/match")
public class MatchController {

	@Autowired
	private MatchService matchService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/matchlists/by-account/{region}/{accountId}.json")
	public BaseResponse<MatchListDto> getMatchListByAccountId(HttpServletRequest request, @PathVariable String accountId, @PathVariable String region) throws JsonSyntaxException, IOException {
		String language = request.getHeader(PlatformConstants.HEADER_LANGUAGE);
		region = Utils.getRegion(request, region);
		RequestGetMatchListByAccountId requestModel = new RequestGetMatchListByAccountId(language, region, accountId);
		MatchListDto response = matchService.getMatchListByAccountId(requestModel);
		return new BaseResponse<MatchListDto>(response);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{region}/{matchId}.json")
	public BaseResponse<MatchDto> getMatchDetail(HttpServletRequest request, @PathVariable long matchId, @PathVariable String region) throws JsonSyntaxException, IOException {
		String language = request.getHeader(PlatformConstants.HEADER_LANGUAGE);
		region = Utils.getRegion(request, region);
		RequestGetMatchDetail requestModel = new RequestGetMatchDetail(language, region, matchId);
		MatchDto response = matchService.getMatchDetail(requestModel);
		return new BaseResponse<MatchDto>(response);
	}
	
	
}
