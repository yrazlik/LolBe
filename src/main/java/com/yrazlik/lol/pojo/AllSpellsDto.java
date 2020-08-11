package com.yrazlik.lol.pojo;

import java.util.Map;

public class AllSpellsDto {
	private Map<String, SpellDto> data;

	public Map<String, SpellDto> getData() {
		return data;
	}

	public void setData(Map<String, SpellDto> data) {
		this.data = data;
	}
}
