package com.yrazlik.lol.pojo;

import java.util.Map;

public class StaticDataDto {
	
	private String type;
	private String format;
	private String version;
	private Map<String, ChampionDto> data;
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
}
