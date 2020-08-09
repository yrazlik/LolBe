package com.yrazlik.lol.response;

import java.util.List;

import com.yrazlik.lol.pojo.ChampionDto;

public class WeeklyFreeRotationResponse {

	private List<ChampionDto> champions;
	private List<ChampionDto> championsForNewPlayers;
	private int maxNewPlayerLevel;

	public WeeklyFreeRotationResponse() {
		super();
	}
	public WeeklyFreeRotationResponse(List<ChampionDto> champions, List<ChampionDto> championsForNewPlayers,
			int maxNewPlayerLevel) {
		super();
		this.champions = champions;
		this.championsForNewPlayers = championsForNewPlayers;
		this.maxNewPlayerLevel = maxNewPlayerLevel;
	}
	public List<ChampionDto> getChampions() {
		return champions;
	}
	public void setChampions(List<ChampionDto> champions) {
		this.champions = champions;
	}
	public List<ChampionDto> getChampionsForNewPlayers() {
		return championsForNewPlayers;
	}
	public void setChampionsForNewPlayers(List<ChampionDto> championsForNewPlayers) {
		this.championsForNewPlayers = championsForNewPlayers;
	}
	public int getMaxNewPlayerLevel() {
		return maxNewPlayerLevel;
	}
	public void setMaxNewPlayerLevel(int maxNewPlayerLevel) {
		this.maxNewPlayerLevel = maxNewPlayerLevel;
	}
	
	
}
