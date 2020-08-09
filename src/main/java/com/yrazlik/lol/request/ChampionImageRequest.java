package com.yrazlik.lol.request;

import com.yrazlik.lol.pojo.ChampionDto;

public class ChampionImageRequest extends BaseRequest {
	
	private String locale;
	private ChampionDto champion;

	public ChampionImageRequest() {
		super();
	}
	public ChampionImageRequest(String locale, ChampionDto champion) {
		super();
		this.locale = locale;
		this.champion = champion;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public ChampionDto getChampion() {
		return champion;
	}
	public void setChampion(ChampionDto champion) {
		this.champion = champion;
	}
}
