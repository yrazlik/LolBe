package com.yrazlik.lol.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.JsonSyntaxException;
import com.yrazlik.lol.pojo.LeagueEntryDto;
import com.yrazlik.lol.pojo.LeagueListDto;
import com.yrazlik.lol.request.RequestGetLeagueInfo;
import com.yrazlik.lol.request.RequestGetLeagueInfoByLeagueId;
import com.yrazlik.lol.request.RequestGetSummonerLeague;
import com.yrazlik.lol.response.BaseResponse;
import com.yrazlik.lol.response.SummonerLeagueInfoResponse;
import com.yrazlik.lol.service.LeagueService;
import com.yrazlik.lol.util.PlatformConstants;
import com.yrazlik.lol.util.Utils;

@RestController
@RequestMapping("/league")
public class LeagueController {

	@Autowired
	private LeagueService leagueService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/by-summoner/{region}/{summonerId}.json")
	public BaseResponse<SummonerLeagueInfoResponse> getSummonerLeague(HttpServletRequest request, @PathVariable String summonerId, @PathVariable String region) throws JsonSyntaxException, IOException {
		String language = request.getHeader(PlatformConstants.HEADER_LANGUAGE);
		region = Utils.getRegion(request, region);
		RequestGetSummonerLeague requestModel = new RequestGetSummonerLeague(language, region, summonerId);
		SummonerLeagueInfoResponse response = leagueService.getSummonerLeagueInfo(requestModel);
		return new BaseResponse<SummonerLeagueInfoResponse>(response);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{region}/{queue}/{tier}/{division}.json")
	public BaseResponse<LeagueEntryDto> getLeagueInfo(HttpServletRequest request, @PathVariable String queue, @PathVariable String tier, @PathVariable String division, @PathVariable String region) throws JsonSyntaxException, IOException {
		String language = request.getHeader(PlatformConstants.HEADER_LANGUAGE);
		region = Utils.getRegion(request, region);
		RequestGetLeagueInfo requestModel = new RequestGetLeagueInfo(language, region, queue, tier, division);
		LeagueEntryDto response = leagueService.getLeagueInfo(requestModel);
		return new BaseResponse<LeagueEntryDto>(response);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{region}/{leagueId}.json")
	public BaseResponse<LeagueListDto> getLeagueInfoByLeagueId(HttpServletRequest request, @PathVariable String leagueId, @PathVariable String region) throws JsonSyntaxException, IOException {
		String language = request.getHeader(PlatformConstants.HEADER_LANGUAGE);
		region = Utils.getRegion(request, region);
		RequestGetLeagueInfoByLeagueId requestModel = new RequestGetLeagueInfoByLeagueId(language, region, leagueId);
		LeagueListDto response = leagueService.getLeagueInfoByLeagueId(requestModel);
		return new BaseResponse<LeagueListDto>(response);
	}
	
	
}
