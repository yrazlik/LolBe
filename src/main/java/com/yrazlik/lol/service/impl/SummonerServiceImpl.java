package com.yrazlik.lol.service.impl;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yrazlik.lol.callable.MatchDetailExecutorTask;
import com.yrazlik.lol.httpclient.LolHttpClient;
import com.yrazlik.lol.pojo.ChampionDto;
import com.yrazlik.lol.pojo.ChampionMasteryDTO;
import com.yrazlik.lol.pojo.CurrentGameParticipantDto;
import com.yrazlik.lol.pojo.ImageDto;
import com.yrazlik.lol.pojo.LeagueEntryDto;
import com.yrazlik.lol.pojo.LeagueItemDto;
import com.yrazlik.lol.pojo.LeagueListDto;
import com.yrazlik.lol.pojo.MatchDto;
import com.yrazlik.lol.pojo.MatchListDto;
import com.yrazlik.lol.pojo.MatchReferenceDto;
import com.yrazlik.lol.pojo.ParticipantDto;
import com.yrazlik.lol.pojo.ParticipantIdentityDto;
import com.yrazlik.lol.pojo.ParticipantStatsDto;
import com.yrazlik.lol.pojo.QueueDto;
import com.yrazlik.lol.pojo.RequestGetMatchDetail;
import com.yrazlik.lol.pojo.SingleParticipantMatchDto;
import com.yrazlik.lol.pojo.SingleParticipantMatchListDto;
import com.yrazlik.lol.pojo.SingleParticipantMatchReferenceDto;
import com.yrazlik.lol.pojo.SpellDto;
import com.yrazlik.lol.pojo.SummonerAveragesDto;
import com.yrazlik.lol.request.RequestChampionMasteries;
import com.yrazlik.lol.request.RequestGetActiveGame;
import com.yrazlik.lol.request.RequestGetLeagueInfoByLeagueId;
import com.yrazlik.lol.request.RequestGetMatchListByAccountId;
import com.yrazlik.lol.request.RequestGetSummonerByName;
import com.yrazlik.lol.request.RequestGetSummonerLeague;
import com.yrazlik.lol.request.RequestSearchSummonerByName;
import com.yrazlik.lol.response.AllChampionsResponse;
import com.yrazlik.lol.response.AllSpellsResponse;
import com.yrazlik.lol.response.GetActiveGameInfoResponse;
import com.yrazlik.lol.response.GetSummonerByNameResponse;
import com.yrazlik.lol.response.RiotApiResponse;
import com.yrazlik.lol.response.SearchSummonerByNameResponse;
import com.yrazlik.lol.response.SummonerLeagueInfoResponse;
import com.yrazlik.lol.service.DataDragonService;
import com.yrazlik.lol.service.LeagueService;
import com.yrazlik.lol.service.MatchService;
import com.yrazlik.lol.service.SummonerService;
import com.yrazlik.lol.util.ServicePaths;
import com.yrazlik.lol.util.UrlUtil;

@Service
public class SummonerServiceImpl implements SummonerService {
	
	@Autowired
	private LolHttpClient lolHttpClient;
	
	@Autowired
	private DataDragonService dataDragonService;
	
	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private SummonerService self;
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolExecutor;
	
	@Cacheable(value = "summonerByName", key="{#request?.language, #request?.region, #request?.summonerName}", unless="#result == null")
	@SuppressWarnings("deprecation")
	@Override
	public GetSummonerByNameResponse findSummonerByName(RequestGetSummonerByName request) {
		String url = UrlUtil.buildSummonerSearchUrl(request.getRegion(), request.getSummonerName());
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		GetSummonerByNameResponse response =  new Gson().fromJson(responseBody, GetSummonerByNameResponse.class);
		if(response.getId() == null || response.getId().equals("")) {
			return null;
		}
		response.setProfileIconUrl(ServicePaths.PROFILE_ICON_BASE_URL + response.getProfileIconId() + ".png");
		return response;
	}
	
	@Override
	public GetActiveGameInfoResponse getActiveGameInfo(RequestGetActiveGame request) {
		GetActiveGameInfoResponse response = new GetActiveGameInfoResponse();
		RequestGetSummonerByName req = new RequestGetSummonerByName();
		req.setLanguage(request.getLanguage());
		req.setRegion(request.getRegion());
		req.setSummonerName(request.getSummonerName());
		GetSummonerByNameResponse resp = findSummonerByName(req);
		
		if(resp != null && resp.getAccountId() != null && resp.getAccountId().length() > 0) {
			AllChampionsResponse allChampionsResponse = dataDragonService.getAllChampions(request.getLanguage());
			List<ChampionDto> allChampions = allChampionsResponse.getChampions();
			Map<Long, ChampionDto> allChampionsMap = new HashMap<Long, ChampionDto>();
			for(ChampionDto dto : allChampions) {
				allChampionsMap.put((long)dto.getId(), dto);
			}
			
			String url = UrlUtil.buildActiveGameInfoUrl(request.getRegion(), resp.getId());
			RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
			String responseBody = riotApiResponse.getBody();
			response =  new Gson().fromJson(responseBody, GetActiveGameInfoResponse.class);
			if(response != null && response.getParticipants() != null) {
				for(CurrentGameParticipantDto participant : response.getParticipants()) {
					String summonerIconUrl = ServicePaths.PROFILE_ICON_BASE_URL + participant.getProfileIconId() + ".png";
					participant.setSummonerIconUrl(summonerIconUrl);
					ChampionDto champ = allChampionsMap.containsKey(participant.getChampionId()) ? allChampionsMap.get(participant.getChampionId()) : null;
					if(champ != null) {
						participant.setChampionName(champ == null ? "" : champ.getName());
						String champNameId = champ.getChampId();
						participant.setChampionIconUrl(ServicePaths.DATA_DRAGON_CHAMPION_IMG_BASE_PATH + champNameId + ".png");
					}
				}
			}
		} else {
			
		}

		return response;
	}

	@Override
	public SearchSummonerByNameResponse searchSummonerByName(RequestSearchSummonerByName requestModel) {
		RequestGetSummonerByName requestSummoner = new RequestGetSummonerByName(requestModel.getLanguage(), requestModel.getRegion(), requestModel.getSummonerName());
		GetSummonerByNameResponse respSummoner = self.findSummonerByName(requestSummoner);
		String summonerId = respSummoner.getId();
        int totalWin = 0, totalKills = 0, totalDeaths = 0, totalMinionsKilled = 0, totatlAssists = 0, totalMatches = 0;

		if(summonerId != null && summonerId.length() > 0) {
			String accountId = respSummoner.getAccountId();
			RequestGetMatchListByAccountId requestMatchList = new RequestGetMatchListByAccountId(requestModel.getLanguage(), requestModel.getRegion(), accountId, 0, 10);
			MatchListDto matchListResponse = matchService.getMatchListByAccountId(requestMatchList);
			SingleParticipantMatchListDto singleParticipantMatchListDto = new SingleParticipantMatchListDto(matchListResponse.getStartIndex(), matchListResponse.getTotalGames(), matchListResponse.getEndIndex(), null, matchListResponse.isEnd());

			if(matchListResponse != null) {
				List<MatchReferenceDto> matches = matchListResponse.getMatches();
				if(matches != null && matches.size() > 0) {
					matchListResponse.setEnd(false);
					
					Map<Long, ChampionDto> championsMap = dataDragonService.getAllChampionsMap(requestModel.getLanguage());
					Map<Integer, SpellDto> spellsMap = dataDragonService.getAllSpellsMap(requestModel.getLanguage());

					
					Map<Integer, QueueDto> queueTypes = dataDragonService.getQueueTypes(requestModel.getLanguage());
					
					List<MatchDetailExecutorTask> taskList = new ArrayList<>();
					
					for(MatchReferenceDto match : matches) {
						RequestGetMatchDetail reqMatchDetail = new RequestGetMatchDetail(requestModel.getLanguage(), requestModel.getRegion(), match.getGameId());
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
									e.printStackTrace();
								} catch (TimeoutException e) {
									e.printStackTrace();
								}
							}
						}
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					//List<MatchReferenceDto> matchesWithMatchDetail = new ArrayList<>();
					List<SingleParticipantMatchReferenceDto> matchesWithMatchDetail = new ArrayList<>();

					
					for(MatchReferenceDto match : matches) {
						ParticipantIdentityDto currentParticipantIdentity = null;
						ParticipantDto currentParticipantDto = null;
						
						
						long gameId = match.getGameId();
						if(matchList.containsKey(gameId)) {
							MatchDto matchDto = matchList.get(gameId);
							
							
							if(matchDto != null) {
								
								if(queueTypes != null && queueTypes.containsKey(match.getQueue())) {
									QueueDto queue = queueTypes.get(match.getQueue());
									match.setQueueName(queue == null || queue.getDescription() == null ? "-" : queue.getDescription());
								}
							
								List<ParticipantDto> participants = matchDto.getParticipants();
								
								List<ParticipantIdentityDto> participantIdentities = matchDto.getParticipantIdentities();
								for(ParticipantIdentityDto participantIdentityDto : participantIdentities) {
									if(participantIdentityDto.getPlayer() != null && summonerId.equalsIgnoreCase(participantIdentityDto.getPlayer().getSummonerId())) {
										currentParticipantIdentity = participantIdentityDto;
										break;
									}
								}
								
								
								if(participants != null) {
									for(ParticipantDto participant : participants) {
										if(participant != null && (participant.getParticipantId() == currentParticipantIdentity.getParticipantId())) {
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
											currentParticipantDto = participant;
											
                                             totalKills += stats.getKills();
                                             totalDeaths += stats.getDeaths();
                                             totatlAssists += stats.getAssists();
                                             totalMinionsKilled += stats.getTotalMinionsKilled();
                                             totalMatches++;
                                             if(stats.isWin()) {
                                                 totalWin++;
                                             } 
										}
									}
								}
								
								match.setMatchDto(matchDto);
								
								SingleParticipantMatchReferenceDto singleParticipantMatchReferenceDto = new SingleParticipantMatchReferenceDto(match.getGameId(),
										match.getRole(), match.getSeason(), match.getPlatformId(), match.getChampion(), match.getQueue(), match.getQueueName(), match.getLane(), match.getTimestamp());
								
								SingleParticipantMatchDto singleParticipantMatchDto = new SingleParticipantMatchDto(matchDto.getGameId(), matchDto.getQueueId(), matchDto.getGameType(), matchDto.getPlatformId(), matchDto.getGameCreation(), matchDto.getSeasonId(), matchDto.getGameVersion(), matchDto.getMapId(), matchDto.getGameMode(), matchDto.getGameDuration());
								singleParticipantMatchDto.setParticipantIdentitiy(currentParticipantIdentity);;
								singleParticipantMatchDto.setParticipant(currentParticipantDto);
								singleParticipantMatchReferenceDto.setMatchDto(singleParticipantMatchDto);
								matchesWithMatchDetail.add(singleParticipantMatchReferenceDto);
							}
							
						}
					}
					
					singleParticipantMatchListDto.setMatches(matchesWithMatchDetail);

				} else {
					singleParticipantMatchListDto.setEnd(true);
				}
			}
			
			
			
			RequestGetSummonerLeague requestLeague = new RequestGetSummonerLeague(requestModel.getLanguage(), requestModel.getRegion(), summonerId);
			SummonerLeagueInfoResponse respLeague = leagueService.getSummonerLeagueInfo(requestLeague);
			if(respLeague != null && respLeague.getLeagues() != null) {
				List<LeagueEntryDto> leagues = respLeague.getLeagues();
				for(LeagueEntryDto league : leagues) {
					RequestGetLeagueInfoByLeagueId reqLeagueDetail = new RequestGetLeagueInfoByLeagueId(requestModel.getLanguage(), requestModel.getRegion(), league.getLeagueId());
					LeagueListDto response = leagueService.getLeagueInfoByLeagueId(reqLeagueDetail);
					if(response != null) {
						league.setLeagueName(response.getName());
						List<LeagueItemDto> entries = response.getEntries();
						league.setEntries(entries);
					}
				}
			}
			
			RequestChampionMasteries requestMasteries = new RequestChampionMasteries(requestModel.getLanguage(), requestModel.getRegion(), summonerId);
			List<ChampionMasteryDTO> respMasteries = self.getSummonerChampionMasteries(requestMasteries);
			//leagueService.get
			
			SearchSummonerByNameResponse response = new SearchSummonerByNameResponse();
			response.setLeagueInfo(respLeague);
			response.setMatchList(singleParticipantMatchListDto);
			
			SummonerAveragesDto averages = new SummonerAveragesDto();
			if(totalMatches != 0) {
                int averageKills = (int) Math.round((double) totalKills / (double) (totalMatches));
                int averageDeaths = (int) Math.round((double) totalDeaths / (double) (totalMatches));
                int averageAssists = (int) Math.round((double) totatlAssists / (double) (totalMatches));
                int averageMinionsKilled = (int) Math.round((double) totalMinionsKilled / (double) totalMatches);
                int averageWinRate = (int) ((double) totalWin / (double) (totalMatches) * 100);
                
                averages.setAverageKills(averageKills);
                averages.setAverageDeaths(averageDeaths);
                averages.setAverageAssists(averageAssists);;
                averages.setAverageMinions(averageMinionsKilled);
                averages.setWinRate(averageWinRate);
            }
			response.setAverages(averages);
			
			response.setSummonerInfo(respSummoner);
			response.setMasteries(respMasteries);
			return response;
		}
		
		return null;
	}

	@Override
	public List<ChampionMasteryDTO> getSummonerChampionMasteries(RequestChampionMasteries requestModel) {
		AllChampionsResponse allChampions = dataDragonService.getAllChampions(requestModel.getLanguage());
		String url = UrlUtil.buildSummonerChampionMasteriesUrl(requestModel.getRegion(), requestModel.getSummonerId());
		RiotApiResponse riotApiResponse = lolHttpClient.makeGetRequest(url);
		String responseBody = riotApiResponse.getBody();
		Type listType = new TypeToken<List<ChampionMasteryDTO>>() {}.getType();
		List<ChampionMasteryDTO> response =  new Gson().fromJson(responseBody, listType);
		if(allChampions != null) {
			List<ChampionDto> championList = allChampions.getChampions();
			if (championList != null) {
				Map<Long, ChampionDto> championsMap = new HashMap<>();
				for(ChampionDto champ : championList) {
					championsMap.put(champ.getId(), champ);
				}
				if(response != null) {
					for(ChampionMasteryDTO mastery : response) {
						ChampionDto dto = championsMap.get(mastery.getChampionId());
						if(dto != null) {
							mastery.setChampionImageUrl(ServicePaths.DATA_DRAGON_CHAMPION_IMG_BASE_PATH + dto.getChampId() + ".png");
							mastery.setChampionName(dto.getChampId());
						}
						
					}
				}
			}
		}
		
		return response;
	}

}
