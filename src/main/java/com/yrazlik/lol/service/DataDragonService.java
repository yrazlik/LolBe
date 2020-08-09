package com.yrazlik.lol.service;

import com.yrazlik.lol.pojo.ChampionDetailResponse;
import com.yrazlik.lol.pojo.ChampionImageDto;
import com.yrazlik.lol.pojo.StaticDataDto;
import com.yrazlik.lol.request.ChampionDetailRequest;
import com.yrazlik.lol.request.ChampionImageRequest;
import com.yrazlik.lol.response.AllChampionsResponse;
import com.yrazlik.lol.response.AllItemsResponse;

public interface DataDragonService {

	public StaticDataDto getAllChampionInfoStaticData(String locale);
	public ChampionImageDto getChampionImages(ChampionImageRequest championImageRequest);
	public ChampionDetailResponse getChampionDetailById(ChampionDetailRequest championDetailRequest);
	public AllChampionsResponse getAllChampions(String locale);
	public AllItemsResponse getAllItems(String locale);
}
