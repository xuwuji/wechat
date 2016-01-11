package com.xuwuji.wechat.admin.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeUtil {

	public static DateTimeFormatter getDateFormatter() {
		return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
	}

	public static DateTimeFormatter getSimpleDateFormatter() {
		return DateTimeFormat.forPattern("yyyy-MM-dd");
	}

	public static String getDateTime(DateTime time) {
		String result = time.toString(getDateFormatter());
		return result;
	}

	public static String getSimpleDateTime(DateTime time) {
		String result = time.toString(getSimpleDateFormatter());
		return result;
	}

	public static String converUnix(long unixTime) {
		DateTime time = new DateTime(unixTime * 1000L);
		return getDateTime(time);
	}

	public static String currentTime() {
		return getDateTime(DateTime.now());
	}
}
