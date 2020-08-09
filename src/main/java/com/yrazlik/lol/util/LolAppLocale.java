package com.yrazlik.lol.util;

public enum LolAppLocale {

	LOCALE_TR("tr_TR"),
	LOCALE_EN("tr_TR");
	
	private String locale;
	
	private LolAppLocale(String locale) {
		this.locale = locale;
	}

	public String getLocale() {
		return locale;
	}
}
