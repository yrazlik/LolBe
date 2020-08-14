package com.yrazlik.lol.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.JsonSyntaxException;
import com.yrazlik.lol.request.RequestGetActiveGame;
import com.yrazlik.lol.request.RequestGetSummonerByName;
import com.yrazlik.lol.request.RequestSearchSummonerByName;
import com.yrazlik.lol.response.BaseResponse;
import com.yrazlik.lol.response.GetActiveGameInfoResponse;
import com.yrazlik.lol.response.GetSummonerByNameResponse;
import com.yrazlik.lol.response.SearchSummonerByNameResponse;
import com.yrazlik.lol.service.SummonerService;
import com.yrazlik.lol.util.PlatformConstants;
import com.yrazlik.lol.util.Utils;

@RestController
@RequestMapping("/summoner")
public class SummonerController {

	@Autowired
	private SummonerService summonerService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/by-name/{region}/{summonerName}.json")
	public BaseResponse<GetSummonerByNameResponse> getWeeklyFreeRotation(HttpServletRequest request, @PathVariable String summonerName, @PathVariable String region) throws JsonSyntaxException, IOException {
		String language = request.getHeader(PlatformConstants.HEADER_LANGUAGE);
		region = Utils.getRegion(request, region);
		RequestGetSummonerByName requestModel = new RequestGetSummonerByName(language, region, summonerName);
		GetSummonerByNameResponse response = summonerService.findSummonerByName(requestModel);
		return new BaseResponse<GetSummonerByNameResponse>(response);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/by-name/{region}/search/{summonerName}.json")
	public BaseResponse<SearchSummonerByNameResponse> searchSummonerByName(HttpServletRequest request, @PathVariable String summonerName, @PathVariable String region) throws JsonSyntaxException, IOException {
		String language = request.getHeader(PlatformConstants.HEADER_LANGUAGE);
		region = Utils.getRegion(request, region);
		RequestSearchSummonerByName requestModel = new RequestSearchSummonerByName(language, region, summonerName);
		SearchSummonerByNameResponse response = summonerService.searchSummonerByName(requestModel);
		return new BaseResponse<SearchSummonerByNameResponse>(response);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/active-games/by-summoner/{region}/{summonerName}.json")
	public BaseResponse<GetActiveGameInfoResponse> getActiveGameInfo(HttpServletRequest request, @PathVariable String summonerName, @PathVariable String region) throws JsonSyntaxException, IOException {
		String language = request.getHeader(PlatformConstants.HEADER_LANGUAGE);
		if(region == null || region.equals("")) {
			region = request.getHeader(PlatformConstants.HEADER_REGION);
		}
		RequestGetActiveGame requestModel = new RequestGetActiveGame();
		requestModel.setLanguage(language);
		requestModel.setRegion(region);
		requestModel.setSummonerName(summonerName);
		GetActiveGameInfoResponse response = summonerService.getActiveGameInfo(requestModel);
		BaseResponse<GetActiveGameInfoResponse> result = new BaseResponse<GetActiveGameInfoResponse>(response);
		if(response.getGameId() == 0) {
			result.setStatus("NOK");
		}
		return result;
	}
	
}
