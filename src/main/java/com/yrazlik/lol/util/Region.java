package com.yrazlik.lol.util;

public enum Region {
	
	RU("RU", "ru", "Russia"),
	KR("KR", "kr", "Korea"),
	BR1("BR", "br1", "Brazil"),
	OC1("OCE", "oc1", "Oceania"),
	JP1("JP", "jp1", "Japan"),
	NA1("NA", "na1", "North America"),
	EUN1("EUNE", "eun1", "Europe North East"),
	EUW1("EUW", "euw1", "Europe West"),
	TR1("TR", "tr1", "Turkey"),
	LA1("LAN", "la1", "Latin America North"),
	LA2("LAS", "la2", "Latin America South");
	
	private String serviceRegion;
	private String regionCode;
	private String regionName;
	
	public static Region createRegionFromRegionCode(String code) {
		for(Region region : Region.values()) {
			if(code != null && code.equalsIgnoreCase(region.getRegionCode())) {
				return region;
			}
		}
		return Region.TR1;
	}
	
	Region(String serviceRegion, String regionCode, String regionName) {
		this.serviceRegion = serviceRegion;
		this.regionCode = regionCode;
		this.regionName = regionName;
	}
	
	public String getServiceRegion() {
		return serviceRegion;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public String getRegionName() {
		return regionName;
	}
}
