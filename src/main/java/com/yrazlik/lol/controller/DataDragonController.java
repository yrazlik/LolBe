package com.yrazlik.lol.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.JsonSyntaxException;
import com.yrazlik.lol.pojo.ChampionDetailResponse;
import com.yrazlik.lol.request.ChampionDetailRequest;
import com.yrazlik.lol.response.AllChampionsResponse;
import com.yrazlik.lol.response.AllItemsResponse;
import com.yrazlik.lol.response.BaseResponse;
import com.yrazlik.lol.service.DataDragonService;
import com.yrazlik.lol.util.PlatformConstants;

@RestController
@RequestMapping("/datadragon")
public class DataDragonController {

	@Autowired
	private DataDragonService dataDragonService;

	@RequestMapping(method = RequestMethod.GET, value = "/champions/{id}.json")
	public BaseResponse<ChampionDetailResponse> getWeeklyFreeRotation(HttpServletRequest request, @PathVariable String id) throws JsonSyntaxException, IOException {
		String language = request.getHeader(PlatformConstants.HEADER_LANGUAGE);
		String region = request.getHeader(PlatformConstants.HEADER_REGION);
		ChampionDetailRequest req = new ChampionDetailRequest(id);
		req.setLanguage(language);
		req.setRegion(region);
		ChampionDetailResponse championDetailResponse = dataDragonService.getChampionDetailById(req);
		return new BaseResponse<ChampionDetailResponse>(championDetailResponse);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/allChampions.json")
	public BaseResponse<AllChampionsResponse> getAllChampions(HttpServletRequest request) throws JsonSyntaxException, IOException {
		String language = request.getHeader(PlatformConstants.HEADER_LANGUAGE);
		//String region = request.getHeader(PlatformConstants.HEADER_REGION);
		AllChampionsResponse response = dataDragonService.getAllChampions(language);
		return new BaseResponse<AllChampionsResponse>(response);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/allItems.json")
	public BaseResponse<AllItemsResponse> getAllItems(HttpServletRequest request) throws JsonSyntaxException, IOException {
		String language = request.getHeader(PlatformConstants.HEADER_LANGUAGE);
		//String region = request.getHeader(PlatformConstants.HEADER_REGION);
		AllItemsResponse response = dataDragonService.getAllItems(language);
		return new BaseResponse<AllItemsResponse>(response);
	}
}
