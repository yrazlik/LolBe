package com.yrazlik.lol.service;

import java.util.Map;

import com.yrazlik.lol.pojo.ChampionDetailResponse;
import com.yrazlik.lol.pojo.ChampionDto;
import com.yrazlik.lol.pojo.ChampionImageDto;
import com.yrazlik.lol.pojo.ChampionInfo;
import com.yrazlik.lol.pojo.QueueDto;
import com.yrazlik.lol.pojo.SpellDto;
import com.yrazlik.lol.pojo.StaticDataDto;
import com.yrazlik.lol.request.ChampionDetailRequest;
import com.yrazlik.lol.request.ChampionImageRequest;
import com.yrazlik.lol.response.AllChampionsResponse;
import com.yrazlik.lol.response.AllItemsResponse;
import com.yrazlik.lol.response.AllSpellsResponse;

public interface DataDragonService {

	public StaticDataDto getAllChampionInfoStaticData(String locale);
	public ChampionImageDto getChampionImages(ChampionImageRequest championImageRequest);
	public ChampionDetailResponse getChampionDetailById(ChampionDetailRequest championDetailRequest);
	public AllChampionsResponse getAllChampions(String locale);
	public Map<Long, ChampionDto> getAllChampionsMap(String locale);
	public AllItemsResponse getAllItems(String locale);
	public AllSpellsResponse getAllSpells(String locale);
	public Map<Integer, SpellDto> getAllSpellsMap(String locale);
	public Map<Integer, QueueDto> getQueueTypes(String locale);
	public ChampionInfo getWeeklyFreeRotationsFromFile();
}
