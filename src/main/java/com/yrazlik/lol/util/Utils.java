package com.yrazlik.lol.util;

import javax.servlet.http.HttpServletRequest;

public class Utils {

	public static String makeFourDigit(String s) {
		if (s.length() == 1) {
			s = "000" + s;
		} else if (s.length() == 2) {
			s = "00" + s;
		} else if (s.length() == 3) {
			s = "0" + s;
		}

		return s;
	}
	
	public static String getRegion(HttpServletRequest request, String region) {
		if(region == null || region.equals("")) {
			region = request.getHeader(PlatformConstants.HEADER_REGION);
		}
		return region;
	}
}
