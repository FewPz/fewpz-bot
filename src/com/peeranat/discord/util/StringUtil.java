package com.peeranat.discord.util;

import java.util.Calendar;
import java.util.TimeZone;

public class StringUtil {

	public static String capitalize(String string) {
		char[] chars = string.toLowerCase().toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; i++) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other																		// chars here
				found = false;
			}
		}
		return String.valueOf(chars);
	}

	public static String date() {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		int days = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		return days + "/" + month + "/" + (year+543);
	}
}
