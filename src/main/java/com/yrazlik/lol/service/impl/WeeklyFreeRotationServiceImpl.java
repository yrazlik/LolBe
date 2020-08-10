package com.yrazlik.lol.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.yrazlik.lol.httpclient.LolHttpClient;
import com.yrazlik.lol.pojo.ChampionDto;
import com.yrazlik.lol.pojo.ChampionInfo;
import com.yrazlik.lol.pojo.StaticDataDto;
import com.yrazlik.lol.request.WeeklyFreeRotationRequest;
import com.yrazlik.lol.response.RiotApiResponse;
import com.yrazlik.lol.response.WeeklyFreeRotationResponse;
import com.yrazlik.lol.service.DataDragonService;
import com.yrazlik.lol.service.WeeklyFreeRotationService;
import com.yrazlik.lol.util.ServicePaths;
import com.yrazlik.lol.util.UrlUtil;

@Service
public class WeeklyFreeRotationServiceImpl implements WeeklyFreeRotationService {
	
	@Autowired
	private LolHttpClient lolHttpClient;
	
	@Autowired
	private DataDragonService dataDragonService;
	
	@Override
	@Cacheable(value="weeklyFreeRotation", key="{#request?.language, #request?.region}", unless="#result == null")
	public WeeklyFreeRotationResponse getWeeklyFreeRotation(WeeklyFreeRotationRequest request) {
		String url = UrlUtil.buildWeeklyRotationsUrl(request.getRegion(), ServicePaths.PATH_WEEKLY_FREE_ROTATION);
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		ChampionInfo championInfo = new Gson().fromJson(responseBody, ChampionInfo.class);
		StaticDataDto staticData = dataDragonService.getAllChampionInfoStaticData(request.getLanguage());
		Map<String, ChampionDto> dataMap = staticData.getData() == null ? new HashMap<String, ChampionDto>() : staticData.getData();
		List<Integer> freeChampions = championInfo.getFreeChampionIds();
		List<Integer> freeChampionsForNewPlayers = championInfo.getFreeChampionIds();
		int maxNewPlayerLevel = championInfo.getMaxNewPlayerLevel();
		
		List<ChampionDto> champions = new ArrayList<>();
		List<ChampionDto> championsForNewPlayers = new ArrayList<>();
		
		if(freeChampions != null) {
			for(Integer i : freeChampions) {
				String key = String.valueOf(i);
				if(dataMap.containsKey(key)) {
					champions.add(dataMap.get(key));
				}
			}
		}
		
		if(freeChampionsForNewPlayers != null) {
			for(Integer i : freeChampionsForNewPlayers) {
				String key = String.valueOf(i);
				if(dataMap.containsKey(key)) {
					championsForNewPlayers.add(dataMap.get(key));
				}
			}
		}
		
		WeeklyFreeRotationResponse response = new WeeklyFreeRotationResponse(champions, championsForNewPlayers, maxNewPlayerLevel);
		return response;
	}
}
