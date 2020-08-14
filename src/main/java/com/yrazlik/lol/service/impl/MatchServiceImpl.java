package com.yrazlik.lol.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.yrazlik.lol.callable.MatchDetailExecutorTask;
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
import com.yrazlik.lol.pojo.SingleParticipantMatchDto;
import com.yrazlik.lol.pojo.SingleParticipantMatchReferenceDto;
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
		return response;
	}
	
	/*
	 * 
	- String url = UrlUtil.buildMatchListByAccountIdUrl(request.getRegion(), request.getAccountId(), request.getStartIndex(), request.getEndIndex());
	-	RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
	-	String responseBody = riotApiResponse.getBody();
	-	MatchListDto response =  new Gson().fromJson(responseBody, MatchListDto.class);
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
				List<MatchDetailExecutorTask> taskList = new ArrayList<>();
				
				for(MatchReferenceDto match : matches) {
					RequestGetMatchDetail reqMatchDetail = new RequestGetMatchDetail(request.getLanguage(), request.getRegion(), match.getGameId());
					taskList.add(new MatchDetailExecutorTask(reqMatchDetail));
				}
				
				
				
				Map<Long, MatchDto> matchList = new HashMap<>();
				try {
					List<Future<MatchDto>> matchFutures = threadPoolExecutor.getThreadPoolExecutor().invokeAll(taskList, 10000, TimeUnit.MILLISECONDS);
					if(matchFutures != null) {
						for(Future<MatchDto> future : matchFutures) {
							try {
								MatchDto match = future.get(70000, TimeUnit.MILLISECONDS);
								if(match != null && match.getGameId() != 0) {
									matchList.put(match.getGameId(), match);
								}
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (TimeoutException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				List<MatchReferenceDto> matchesWithMatchDetail = new ArrayList<>();
				
				for(MatchReferenceDto match : matches) {
					long gameId = match.getGameId();
					if(matchList.containsKey(gameId)) {
						MatchDto matchDto = matchList.get(gameId);
						if(matchDto != null) {
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
							matchesWithMatchDetail.add(match);
						}
						
					}
				}
				response.setMatches(matchesWithMatchDetail);
			} else {
				response.setEnd(true);
			}
		}
		return response;
	 * 
	 * 
	 * */

	@Override
	public MatchDto getMatchDetail(RequestGetMatchDetail request) {
		String url = UrlUtil.buildMatchDetailUrl(request.getRegion(), request.getMatchId());
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		MatchDto response =  new Gson().fromJson(responseBody, MatchDto.class);
		
		if(response != null) {
			Map<Long, ChampionDto> championsMap = dataDragonService.getAllChampionsMap(request.getLanguage());
			Map<Integer, SpellDto> spellsMap = dataDragonService.getAllSpellsMap(request.getLanguage());
			Map<Integer, QueueDto> queueTypes = dataDragonService.getQueueTypes(request.getLanguage());
			List<ParticipantDto> participants = response.getParticipants();
			
			if(participants != null) {
				for(ParticipantDto participant : participants) {
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
		return response;
	}
	
	

}
