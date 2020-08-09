package com.yrazlik.lol.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static String getTuesday(String locale) {
		Calendar c = Calendar.getInstance();

		if (c.get(Calendar.DAY_OF_WEEK) < 3) {
			c.add(Calendar.DATE, -7);
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM", new Locale(getLocaleForMonthName(locale)));
			Date d = c.getTime();
			String start = sdf.format(d);
			c.add(Calendar.DATE, 7);
			Date d2 = c.getTime();
			String end = sdf.format(d2);
			return start + " - " + end;
		} else {
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM", new Locale(getLocaleForMonthName(locale)));
			Date d = c.getTime();
			String start = sdf.format(d);
			c.add(Calendar.DATE, 7);
			Date d2 = c.getTime();
			String end = sdf.format(d2);
			return start + " - " + end;
		}
	}

	public static String getLocaleForMonthName(String locale) {
		if (locale != null) {
			if (locale.toLowerCase().startsWith("en")) {
				return "en";
			} else if (locale.toLowerCase().startsWith("tr")) {
				return "tr";
			} else if (locale.toLowerCase().startsWith("pt")) {
				return "pt";
			}
		}
		return "en";
	}

}
