package com.yrazlik.lol.util;

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
}
