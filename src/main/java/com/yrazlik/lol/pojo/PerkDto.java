package com.yrazlik.lol.pojo;

import java.util.List;

public class PerkDto {

	private List<Long> perkIds;
	private long perkStyle;
	private long perkSubStyle;
	public List<Long> getPerkIds() {
		return perkIds;
	}
	public void setPerkIds(List<Long> perkIds) {
		this.perkIds = perkIds;
	}
	public long getPerkStyle() {
		return perkStyle;
	}
	public void setPerkStyle(long perkStyle) {
		this.perkStyle = perkStyle;
	}
	public long getPerkSubStyle() {
		return perkSubStyle;
	}
	public void setPerkSubStyle(long perkSubStyle) {
		this.perkSubStyle = perkSubStyle;
	}
	
	
}
