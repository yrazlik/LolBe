package com.yrazlik.lol.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.yrazlik.lol.service.DataDragonService;

@Component
@EnableScheduling
public class CacheEvictManager {
	
	private static final Logger LOGGER = Logger.getLogger(CacheEvictManager.class);
	
	@Autowired
	private DataDragonService dataDragonService;
	
	@CacheEvict(allEntries = true, cacheNames = { "staticData" })
	@Scheduled(fixedDelay = 10800000)
	public void evictAndReCacheStaticData() {
		LOGGER.error("Evicting staticData cache.");
		List<String> locales = new ArrayList<String>();
		locales.add("tr_TR");locales.add("en_US");locales.add("pt_BR");locales.add("es_ES");
		for(String locale : locales) {
			dataDragonService.getAllChampionInfoStaticData(locale);
		}
	}
	
	@CacheEvict(allEntries = true, cacheNames = { "weeklyFreeRotation" })
	@Scheduled(fixedDelay = 10800000)
	public void evictWeeklyFreeRotationCache() {
		LOGGER.error("Evicting weeklyFreeRotation cache.");
	}
	
	@CacheEvict(allEntries = true, cacheNames = { "championDetail" })
	@Scheduled(fixedDelay = 10800000)
	public void evictChampionDetailCache() {
		LOGGER.error("Evicting championDetail cache.");
	}
	
	@CacheEvict(allEntries = true, cacheNames = { "allChampions" })
	@Scheduled(fixedDelay = 3600000)
	public void evictAllChampionsCache() {
		LOGGER.error("Evicting allChampions cache.");
	}
	
	@CacheEvict(allEntries = true, cacheNames = { "allItems" })
	@Scheduled(fixedDelay = 10800000)
	public void evictAllItemsCache() {
		LOGGER.error("Evicting allItems cache.");
	}
}
