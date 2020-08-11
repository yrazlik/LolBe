package com.yrazlik.lol.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.yrazlik.lol.httpclient.LolHttpClient;
import com.yrazlik.lol.pojo.ChampionDto;
import com.yrazlik.lol.pojo.ChampionMasteryDTO;
import com.yrazlik.lol.pojo.ImageDto;
import com.yrazlik.lol.pojo.MatchDto;
import com.yrazlik.lol.pojo.MatchListDto;
import com.yrazlik.lol.pojo.MatchReferenceDto;
import com.yrazlik.lol.pojo.ParticipantDto;
import com.yrazlik.lol.pojo.ParticipantIdentityDto;
import com.yrazlik.lol.pojo.ParticipantStatsDto;
import com.yrazlik.lol.pojo.PlayerDto;
import com.yrazlik.lol.pojo.QueueDto;
import com.yrazlik.lol.pojo.RequestGetMatchDetail;
import com.yrazlik.lol.pojo.SpellDto;
import com.yrazlik.lol.request.RequestGetMatchListByAccountId;
import com.yrazlik.lol.response.AllChampionsResponse;
import com.yrazlik.lol.response.AllSpellsResponse;
import com.yrazlik.lol.response.RiotApiResponse;
import com.yrazlik.lol.service.DataDragonService;
import com.yrazlik.lol.service.MatchService;
import com.yrazlik.lol.util.ServicePaths;
import com.yrazlik.lol.util.UrlUtil;

@Service
public class MatchServiceImpl implements MatchService {
	
	@Autowired
	private LolHttpClient lolHttpClient;
	
	@Autowired
	private DataDragonService dataDragonService;

	@Override
	public MatchListDto getMatchListByAccountId(RequestGetMatchListByAccountId request) {
		String url = UrlUtil.buildMatchListByAccountIdUrl(request.getRegion(), request.getAccountId(), request.getStartIndex(), request.getEndIndex());
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		MatchListDto response =  new Gson().fromJson(responseBody, MatchListDto.class);
		if(response != null) {
			List<MatchReferenceDto> matches = response.getMatches();
			if(matches != null && matches.size() > 0) {
				response.setEnd(false);
				
				Map<Long, ChampionDto> championsMap = new HashMap<>();
				AllChampionsResponse allChampions = dataDragonService.getAllChampions(request.getLanguage());
				if(allChampions != null) {
					List<ChampionDto> championList = allChampions.getChampions();
					if (championList != null) {
						for(ChampionDto champ : championList) {
							championsMap.put(champ.getId(), champ);
						}
					}
				}
				
				AllSpellsResponse spellsResponse = dataDragonService.getAllSpells(request.getLanguage());
				List<SpellDto> allSpels = new ArrayList<SpellDto>();
				if(spellsResponse != null) {
					allSpels = spellsResponse.getSpells();
				}
				
				Map<Integer, SpellDto> spellsMap = new HashMap<>();
				for(SpellDto spell : allSpels) {
					String key = spell.getKey();
					try {
						int keyInt = Integer.parseInt(key);
						spellsMap.put(keyInt, spell);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				Map<Integer, QueueDto> queueTypes = dataDragonService.getQueueTypes(request.getLanguage());
				
				for(MatchReferenceDto match : matches) {
					try {
						RequestGetMatchDetail reqMatchDetail = new RequestGetMatchDetail(request.getLanguage(), request.getRegion(), match.getGameId());
						MatchDto matchDto = getMatchDetail(reqMatchDetail);
						if(queueTypes != null && queueTypes.containsKey(match.getQueue())) {
							QueueDto queue = queueTypes.get(match.getQueue());
							match.setQueueName(queue == null || queue.getDescription() == null ? "-" : queue.getDescription());
						}
					
						if(matchDto != null) {
							List<ParticipantDto> participants = matchDto.getParticipants();
							if(participants != null) {
								for(ParticipantDto participant : participants) {
									if(participant != null) {
										Long championId = (long) participant.getChampionId();
										String champNameId = championsMap.containsKey(championId) ? championsMap.get(championId).getChampId() : "";
										participant.setChampionImageUrl(ServicePaths.DATA_DRAGON_CHAMPION_IMG_BASE_PATH + champNameId + ".png");
										int spell1Id = participant.getSpell1Id(), spell2Id = participant.getSpell2Id();
										if(spellsMap.containsKey(spell1Id)) {
											SpellDto spell = spellsMap.get(spell1Id);
											if(spell != null) {
												ImageDto img = spell.getImage();
												if(img != null) {
													participant.setSpell1Url(img.getFull());
												}
											}
										}
										
										if(spellsMap.containsKey(spell2Id)) {
											SpellDto spell = spellsMap.get(spell2Id);
											if(spell != null) {
												ImageDto img = spell.getImage();
												if(img != null) {
													participant.setSpell2Url(img.getFull());
												}
											}
										}
										ParticipantStatsDto stats = participant.getStats();
										if(stats != null) {
											stats.setItem0ImageUrl(ServicePaths.ITEM_IMAGES_BASE_URL + stats.getItem0() + ".png");
											stats.setItem1ImageUrl(ServicePaths.ITEM_IMAGES_BASE_URL + stats.getItem1() + ".png");
											stats.setItem2ImageUrl(ServicePaths.ITEM_IMAGES_BASE_URL + stats.getItem2() + ".png");
											stats.setItem3ImageUrl(ServicePaths.ITEM_IMAGES_BASE_URL + stats.getItem3() + ".png");
											stats.setItem4ImageUrl(ServicePaths.ITEM_IMAGES_BASE_URL + stats.getItem4() + ".png");
											stats.setItem5ImageUrl(ServicePaths.ITEM_IMAGES_BASE_URL + stats.getItem5() + ".png");
											stats.setItem6ImageUrl(ServicePaths.ITEM_IMAGES_BASE_URL + stats.getItem6() + ".png");
										}
									}
								}
							}
						}
						match.setMatchDto(matchDto);
					} catch (Exception e) {
						match.setMatchDto(null);
					}
					
				}
			} else {
				response.setEnd(true);
			}
		}
		return response;
	}

	@Override
	public MatchDto getMatchDetail(RequestGetMatchDetail request) {
		String url = UrlUtil.buildMatchDetailUrl(request.getRegion(), request.getMatchId());
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		MatchDto response =  new Gson().fromJson(responseBody, MatchDto.class);
		return response;
	}
	
	

}
