package com.yrazlik.lol.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yrazlik.lol.httpclient.LolHttpClient;
import com.yrazlik.lol.pojo.AllItemsDto;
import com.yrazlik.lol.pojo.AllSpellsDto;
import com.yrazlik.lol.pojo.BlockDto;
import com.yrazlik.lol.pojo.BlockItemDto;
import com.yrazlik.lol.pojo.ChampionDetailResponse;
import com.yrazlik.lol.pojo.ChampionDto;
import com.yrazlik.lol.pojo.ChampionImageDto;
import com.yrazlik.lol.pojo.ChampionRpIpDto;
import com.yrazlik.lol.pojo.ChampionRpIpResponse;
import com.yrazlik.lol.pojo.ImageDto;
import com.yrazlik.lol.pojo.ItemDto;
import com.yrazlik.lol.pojo.LeagueEntryDto;
import com.yrazlik.lol.pojo.PassiveDto;
import com.yrazlik.lol.pojo.QueueDto;
import com.yrazlik.lol.pojo.RecommendedDto;
import com.yrazlik.lol.pojo.SkinDto;
import com.yrazlik.lol.pojo.SpellDto;
import com.yrazlik.lol.pojo.StaticDataDto;
import com.yrazlik.lol.request.ChampionDetailRequest;
import com.yrazlik.lol.request.ChampionImageRequest;
import com.yrazlik.lol.response.AllChampionsResponse;
import com.yrazlik.lol.response.AllItemsResponse;
import com.yrazlik.lol.response.AllSpellsResponse;
import com.yrazlik.lol.response.RiotApiResponse;
import com.yrazlik.lol.service.DataDragonService;
import com.yrazlik.lol.util.DateUtils;
import com.yrazlik.lol.util.ServicePaths;
import com.yrazlik.lol.util.UrlUtil;
import com.yrazlik.lol.util.Utils;

@Service
public class DataDragonServiceImpl implements DataDragonService {
	
	@Value("${ddragon.staticdata.base.url}")
	private String staticDataBaseUrl;
	
	@Value("${ddragon.img.base.url}")
	private String imageBaseUrl;
	
	@Value("${ddragon.img.version}")
	private String imageVersion;
	
	@Value("${ddragon.champion.data.version}")
	private String championDataVersion;
	
	@Value("${ddragon.profile.icon.version}")
	private String profileIconVersion;
	
	@Autowired
	private LolHttpClient lolHttpClient;
	
	@Autowired
	private DataDragonService self;
	
	@Autowired
	private ResourceLoader resourceLoader;

	private Map<String, ChampionRpIpDto> championCosts = new HashMap<>();
	
	@PostConstruct
	private void loadChampionCosts() {
		Resource resource = resourceLoader.getResource("classpath:lol-guide-champion-costs.json");
		try {
			InputStream inputStream = resource.getInputStream();
			
			InputStreamReader isReader = new InputStreamReader(inputStream);
		    BufferedReader reader = new BufferedReader(isReader);
		    StringBuffer sb = new StringBuffer();
		    String line, costsStr;
		    while((line = reader.readLine())!= null){
		       sb.append(line);
		    }
		    costsStr = sb.toString();

		    ChampionRpIpResponse response = new Gson().fromJson(costsStr, ChampionRpIpResponse.class);
		    championCosts = response.getCosts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	@Cacheable(value = "staticData", key="#locale", unless="#result == null")
	public StaticDataDto getAllChampionInfoStaticData(String locale) {
		String url = UrlUtil.buildDataDragonUrl(staticDataBaseUrl, championDataVersion, ServicePaths.DATA_DRAGON_CHAMPION_STATIC_DATA_BASE_PATH, locale, ServicePaths.DATA_DRAGON_CHAMPION_STATIC_DATA);
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		StaticDataDto staticDataResponse =  new Gson().fromJson(responseBody, StaticDataDto.class);
		Map<String, ChampionDto> dto = staticDataResponse.getData();
		Map<String, ChampionDto> dtoAsKeyMap = new HashMap<>();
		for (Map.Entry<String, ChampionDto> entry : dto.entrySet()) {
			ChampionDto champion = entry.getValue();
			if(champion != null) {
				String key = champion.getKey();
				champion.setId(Integer.parseInt(key));
				String id = String.valueOf(champion.getId());
				champion.setChampionRp(championCosts.containsKey(id) ? championCosts.get(id).getRp() : 0);
				champion.setChampionIp(championCosts.containsKey(id) ? championCosts.get(id).getIp() : 0);
				ChampionImageDto images = getChampionImages(new ChampionImageRequest(locale, champion));
				ImageDto image = champion.getImage();
				if(image != null && image.getFull() != null) {
					image.setFull(ServicePaths.DATA_DRAGON_CHAMPION_IMG_BASE_PATH + image.getFull());
				}
				List<SpellDto> spells = champion.getSpells();
				if(spells != null && spells.size() > 0) {
					for(int i = 0; i < spells.size(); i++) {
						SpellDto spell = spells.get(i);
						spell.setVideoUrl(URI.create(ServicePaths.CHAMPION_ABILITIES_VIDEOS_BASE_URL
				                + Utils.makeFourDigit(String.valueOf(champion.getId())) + "_" + "0" + (i+1) + ".mp4").toString());
						ImageDto spellImg = spell.getImage();
						if(spellImg != null && spellImg.getFull() != null) {
							spellImg.setFull(ServicePaths.CHAMPION_SPELL_IMAGE_BASE_URL + spellImg.getFull());
						}
					}
				}
				PassiveDto passive = champion.getPassive();
				if(passive != null) {
					passive.setVideoUrl(URI.create(ServicePaths.CHAMPION_ABILITIES_VIDEOS_BASE_URL
				                + Utils.makeFourDigit(String.valueOf(champion.getId())) + "_" + "05.mp4").toString());
					ImageDto passiveImg = passive.getImage();
					if(passiveImg != null && passiveImg.getFull() != null) {
						passiveImg.setFull(ServicePaths.CHAMPION_PASSIVE_IMAGE_BASE_URL + passiveImg.getFull());
					}
				}
				List<RecommendedDto> recommendedList = champion.getRecommended();
				if(recommendedList != null && recommendedList.size() > 0) {
					for(int i = 0; i < recommendedList.size(); i++) {
						RecommendedDto recommended = recommendedList.get(i);
						List<BlockDto> blocks = recommended.getBlocks();
						if(blocks != null && blocks.size() > 0) {
							for(int j = 0; j < blocks.size(); j++) {
								BlockDto block = blocks.get(j);
								if(block != null) {
									List<BlockItemDto> blockItems = block.getItems();
									if(blockItems != null && blockItems.size() > 0) {
										for(int k = 0; k < blockItems.size(); k++) {
											BlockItemDto blockItem = blockItems.get(k);
											String imageUrl = ServicePaths.ITEM_IMAGES_BASE_URL + String.valueOf(blockItem.getId()) + ".png";
											blockItem.setImageUrl(imageUrl);
										}
										
									}
								}
							}
						}
					}
				}
				champion.setDateInterval(DateUtils.getTuesday(locale));
				champion.setImages(images);
			}
			dtoAsKeyMap.put(champion.getKey(), champion);
		}
		staticDataResponse.setData(dtoAsKeyMap);
		if(dtoAsKeyMap.size() == 0) {
			return null;
		}
		return staticDataResponse;
	}

	@Override
	public ChampionImageDto getChampionImages(ChampionImageRequest request) {
		ChampionImageDto championImageDto = new ChampionImageDto();
		ChampionDto champion = request.getChampion();
		championImageDto.setSquare(imageBaseUrl + "/" + imageVersion + "/img/champion/" + champion.getImage().getFull());
		championImageDto.setLoading(imageBaseUrl + "/img/champion/loading/" + champion.getId() + "_0.jpg");
		return championImageDto;
	}
	
	@Cacheable(value = "championDetail", key="{#championDetailRequest?.language, #championDetailRequest?.region, #championDetailRequest?.id}", unless="#result == null")
	@Override
	public ChampionDetailResponse getChampionDetailById(ChampionDetailRequest championDetailRequest) {
		//http://ddragon.leagueoflegends.com/cdn/10.16.1/data/en_US/champion/Aatrox.json
		String locale = championDetailRequest.getLanguage();
		String championDetailPath = ServicePaths.DATA_DRAGON_CHAMPION_DETAIL_STATIC_DATA;
		String[] values = {championDetailRequest.getId()};
		championDetailPath = (MessageFormat.format(championDetailPath, values));
		
		String url = UrlUtil.buildDataDragonUrl(staticDataBaseUrl, championDataVersion, ServicePaths.DATA_DRAGON_CHAMPION_STATIC_DATA_BASE_PATH, locale, championDetailPath);
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		ChampionDetailResponse championDetailResponse =  new Gson().fromJson(responseBody, ChampionDetailResponse.class);
		Map<String, ChampionDto> dto = championDetailResponse.getData();

		ChampionDto champion = dto.containsKey(championDetailRequest.getId()) ? dto.get(championDetailRequest.getId()) : null;
		
		if(champion != null) {
			String key = champion.getKey();
			champion.setId(Integer.parseInt(key));
			String id = String.valueOf(champion.getId());
			champion.setChampionRp(championCosts.containsKey(id) ? championCosts.get(id).getRp() : 0);
			champion.setChampionIp(championCosts.containsKey(id) ? championCosts.get(id).getIp() : 0);
			ImageDto image = champion.getImage();
			if(image != null && image.getFull() != null) {
				image.setFull(ServicePaths.DATA_DRAGON_CHAMPION_IMG_BASE_PATH + image.getFull());
			}
			List<SpellDto> spells = champion.getSpells();
			if(spells != null && spells.size() > 0) {
				for(int i = 0; i < spells.size(); i++) {
					SpellDto spell = spells.get(i);
					spell.setVideoUrl(URI.create(ServicePaths.CHAMPION_ABILITIES_VIDEOS_BASE_URL
			                + Utils.makeFourDigit(String.valueOf(champion.getId())) + "_" + "0" + (i+2) + ".mp4").toString());
					ImageDto spellImg = spell.getImage();
					if(spellImg != null && spellImg.getFull() != null) {
						spellImg.setFull(ServicePaths.CHAMPION_SPELL_IMAGE_BASE_URL + spellImg.getFull());
					}
				}
			}
			PassiveDto passive = champion.getPassive();
			if(passive != null) {
				passive.setVideoUrl(URI.create(ServicePaths.CHAMPION_ABILITIES_VIDEOS_BASE_URL
			                + Utils.makeFourDigit(String.valueOf(champion.getId())) + "_" + "01.mp4").toString());
				ImageDto passiveImg = passive.getImage();
				if(passiveImg != null && passiveImg.getFull() != null) {
					passiveImg.setFull(ServicePaths.CHAMPION_PASSIVE_IMAGE_BASE_URL + passiveImg.getFull());
				}
			}
			
			List<RecommendedDto> recommendedList = champion.getRecommended();
			if(recommendedList != null && recommendedList.size() > 0) {
				for(int i = 0; i < recommendedList.size(); i++) {
					RecommendedDto recommended = recommendedList.get(i);
					List<BlockDto> blocks = recommended.getBlocks();
					if(blocks != null && blocks.size() > 0) {
						for(int j = 0; j < blocks.size(); j++) {
							BlockDto block = blocks.get(j);
							if(block != null) {
								List<BlockItemDto> blockItems = block.getItems();
								if(blockItems != null && blockItems.size() > 0) {
									for(int k = 0; k < blockItems.size(); k++) {
										BlockItemDto blockItem = blockItems.get(k);
										String imageUrl = ServicePaths.ITEM_IMAGES_BASE_URL + String.valueOf(blockItem.getId()) + ".png";
										blockItem.setImageUrl(imageUrl);
									}
									
								}
							}
						}
					}
				}
			}
			
			List<SkinDto> skins = champion.getSkins();
			if(skins != null && skins.size() > 0) {
				for(int i = 0; i < skins.size(); i++) {
					SkinDto skin = skins.get(i);
					skin.setImageUrl(ServicePaths.URL_CHAMPION_SKIN_BASE + champion.getChampId() + "_" + i + ".jpg");
				}
			}
			
			champion.setDateInterval(DateUtils.getTuesday(locale));
			ChampionImageDto images = getChampionImages(new ChampionImageRequest(locale, champion));
			champion.setImages(images);
		}

		if(champion == null || champion.getChampId() == null) {
			return null;
		}
		championDetailResponse.setChampion(champion);
		return championDetailResponse;
	}
	
	@Cacheable(value = "allChampions", key="#locale", unless="#result == null")
	@Override
	public AllChampionsResponse getAllChampions(String locale) {
		StaticDataDto staticData = self.getAllChampionInfoStaticData(locale);
		Map<String, ChampionDto> dataMap = staticData.getData() == null ? new HashMap<String, ChampionDto>() : staticData.getData();
		
		List<ChampionDto> champions = new ArrayList<>();

		for (Map.Entry<String, ChampionDto> entry : dataMap.entrySet()) {
			ChampionDto champion = entry.getValue();
			champions.add(champion);
		}
		
		champions.sort(Comparator.comparingDouble(ChampionDto::getId));
		
		AllChampionsResponse response = new AllChampionsResponse();
		response.setChampions(champions);
		response.setFormat(staticData.getFormat());
		response.setType(staticData.getType());
		response.setVersion(staticData.getVersion());
		return response;
	}
	
	@Cacheable(value = "allChampionsMap", key="#locale", unless="#result == null")
	@Override
	public Map<Long, ChampionDto> getAllChampionsMap(String locale) {
		Map<Long, ChampionDto> allChampionsMap = null;
		AllChampionsResponse allChampionsResponse = self.getAllChampions(locale);
		if(allChampionsResponse != null) {
			List<ChampionDto> championList = allChampionsResponse.getChampions();
			if (championList != null) {
				allChampionsMap = new HashMap<>();
				for(ChampionDto champ : championList) {
					if(champ != null && champ.getId() != 0 && champ.getChampId() != null && champ.getChampId().length() > 0) {
						allChampionsMap.put(champ.getId(), champ);
					}
				}
			}
		}
		return allChampionsMap;
	}
	
	@Cacheable(value = "allItems", key="#locale", unless="#result == null")
	@Override
	public AllItemsResponse getAllItems(String locale) {
		String url = UrlUtil.buildDataDragonItemsUrl(ServicePaths.ALL_ITEMS_DATA, locale);
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		AllItemsDto allItemsDto =  new Gson().fromJson(responseBody, AllItemsDto.class);
		Map<String, ItemDto> dto = allItemsDto.getData();

		List<ItemDto> items = new ArrayList<>();
		for (Map.Entry<String, ItemDto> entry : dto.entrySet()) {
			ItemDto item = entry.getValue();
			if(item != null) {
				String key = entry.getKey();
				item.setId(key);
				ImageDto image = item.getImage();
				if(image != null && image.getFull() != null) {
					image.setFull(ServicePaths.ITEM_IMAGE_BASE_URL + image.getFull());
				}
				items.add(item);
			}
		}
		AllItemsResponse response = new AllItemsResponse();
		response.setItems(items);
		if(items == null || items.size() == 0) {
			return null;
		}
		return response;
	}
	
	@Cacheable(value = "allSpells", key="#locale", unless="#result == null")
	@Override
	public AllSpellsResponse getAllSpells(String locale) {
		String url = UrlUtil.buildDataDragonSpellsUrl(locale);
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		AllSpellsDto AllSpellsDto =  new Gson().fromJson(responseBody, AllSpellsDto.class);
		Map<String, SpellDto> dto = AllSpellsDto.getData();

		List<SpellDto> spells = new ArrayList<>();
		for (Map.Entry<String, SpellDto> entry : dto.entrySet()) {
			SpellDto spell = entry.getValue();
			if(spell != null) {
				String key = entry.getKey();
				spell.setId(key);
				ImageDto image = spell.getImage();
				if(image != null && image.getFull() != null) {
					image.setFull(ServicePaths.CHAMPION_SPELL_IMAGE_BASE_URL + image.getFull());
				}
				spells.add(spell);
			}
		}
		AllSpellsResponse response = new AllSpellsResponse();
		response.setSpells(spells);
		if(spells == null || spells.size() == 0) {
			return null;
		}
		return response;
	}
	
	@Cacheable(value = "allSpellsMap", key="#locale", unless="#result == null")
	@Override
	public Map<Integer, SpellDto> getAllSpellsMap(String locale) {
		Map<Integer, SpellDto> allSpellsMap = null;
		AllSpellsResponse allSpellsResponse = self.getAllSpells(locale);
		
		if(allSpellsResponse != null) {
			List<SpellDto> allSpells = allSpellsResponse.getSpells();
			if(allSpells != null && allSpells.size() > 0) {
				allSpellsMap = new HashMap<>();
				for(SpellDto spell : allSpells) {
					String key = spell.getKey();
					try {
						if(spell != null && spell.getId() != null && spell.getId().length() > 0) {
							int keyInt = Integer.parseInt(key);
							allSpellsMap.put(keyInt, spell);
						}
					} catch (Exception ignored) {
						// spoiled data is ignored
					}
				}
			}
		}
		
		return allSpellsMap;
	}
	
	@Cacheable(value = "queuesMap", key="#locale", unless="#result == null")
	@Override
	public Map<Integer, QueueDto> getQueueTypes(String locale) {
		String url = UrlUtil.buildDataDragonQueueUrl();
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		Type listType = new TypeToken<List<QueueDto>>() {}.getType();
		List<QueueDto> queues =  new Gson().fromJson(responseBody, listType);
		
		Map<Integer, QueueDto> queuesMap = null;
		if(queues != null) {
			queuesMap = new HashMap<>();
			for(QueueDto dto : queues) {
				if(locale != null && locale.contains("tr")) {
					String map = dto.getMap() == null ? "" : dto.getMap().toLowerCase();
					String description = dto.getDescription() == null ? "" : dto.getDescription().toLowerCase();
					if(map.contains("custom")) {
						dto.setDescription("Özel Oyun");
					} else if(description.contains("normal")) {
						dto.setDescription("Normal");
					} else if(description.contains("dominion")) {
						dto.setDescription("Dominion");
					} else if(description.contains("aram")) {
						dto.setDescription("Sonsuz Uçurum");
					} else if(description.contains("doom")) {
						dto.setDescription("Ölümcül Felaket Botları");
					} else if(description.contains("bot")) {
						dto.setDescription("Yapay Zekaya Karşı");
					} else if(description.contains("one for all")) {
						dto.setDescription("Birimiz Hepimiz İçin");
					} else if(description.contains("6v6") || description.contains("hexa")) {
						dto.setDescription("6 vs. 6");
					} else if(description.contains("urf") || description.contains("rapid")) {
						dto.setDescription("Ultra Rekabet Faktörü");
					} else if(description.contains("ascension")) {
						dto.setDescription("Yükseliş");
					} else if(description.contains("poro")) {
						dto.setDescription("Poro Kralı");
					} else if(description.contains("ranked")) {
						dto.setDescription("Dereceli Oyun");
					} else if(description.contains("tutorial")) {
						dto.setDescription("Eğitim");
					} 
				}
				
				if(dto.getMap() == null) {
					dto.setDescription("Custom");
				}
				
				
				queuesMap.put(dto.getQueueId(), dto);
			}
		}
		
		return queuesMap;
	}
	
}
