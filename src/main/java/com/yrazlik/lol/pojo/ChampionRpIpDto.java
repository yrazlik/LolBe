package com.yrazlik.lol.pojo;

import com.google.gson.annotations.SerializedName;

public class ChampionRpIpDto {
	
	@SerializedName("ip_cost")
	private int ip;
	@SerializedName("rp_cost")
	private int rp;
	public int getIp() {
		return ip;
	}
	public void setIp(int ip) {
		this.ip = ip;
	}
	public int getRp() {
		return rp;
	}
	public void setRp(int rp) {
		this.rp = rp;
	}
	
	

}
