package com.yrazlik.lol.pojo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ChampionDetailResponse {
	
	private String type;
	private String format;
	private String version;
	@JsonIgnore
	private Map<String, ChampionDto> data;
	private ChampionDto champion;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Map<String, ChampionDto> getData() {
		return data;
	}
	public void setData(Map<String, ChampionDto> data) {
		this.data = data;
	}
	public ChampionDto getChampion() {
		return champion;
	}
	public void setChampion(ChampionDto champion) {
		this.champion = champion;
	}

}
