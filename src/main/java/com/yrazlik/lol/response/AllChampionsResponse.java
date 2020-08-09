package com.yrazlik.lol.response;

import java.util.List;

import com.yrazlik.lol.pojo.ChampionDto;

public class AllChampionsResponse {
	
	private String type;
	private String format;
	private String version;

	private List<ChampionDto> champions;

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

	public List<ChampionDto> getChampions() {
		return champions;
	}

	public void setChampions(List<ChampionDto> champions) {
		this.champions = champions;
	}
	
	
	
}
