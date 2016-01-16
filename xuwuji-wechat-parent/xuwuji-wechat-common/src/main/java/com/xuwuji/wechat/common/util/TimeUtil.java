package com.xuwuji.wechat.common.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeUtil {

	public static DateTimeFormatter getDateFormatter() {
		return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
	}

	public static DateTimeFormatter getDateFormatter1() {
		return DateTimeFormat.forPattern("yyyy-MM-dd-HH:mm");
	}

	public static DateTimeFormatter getSimpleDateFormatter() {
		return DateTimeFormat.forPattern("yyyy-MM-dd");
	}

	public static DateTimeFormatter getMinutesDateFormatter() {
		return DateTimeFormat.forPattern("HH:mm:ss");
	}

	public static String getDateTime(DateTime time) {
		String result = time.toString(getDateFormatter());
		return result;
	}

	public static String getDateTime1(DateTime time) {
		String result = time.toString(getDateFormatter1());
		return result;
	}

	public static String getSimpleDateTime(DateTime time) {
		String result = time.toString(getSimpleDateFormatter());
		return result;
	}

	public static String getCurrentMinutesDateTime() {
		String result = DateTime.now().toString(getMinutesDateFormatter());
		return result;
	}

	public static String converUnix(long unixTime) {
		DateTime time = new DateTime(unixTime * 1000L);
		return getDateTime(time);
	}

	public static String converSimpleUnix(long unixTime) {
		DateTime time = new DateTime(unixTime * 1000L);
		return getSimpleDateTime(time);
	}

	public static String currentTime() {
		// TODO Auto-generated method stub
		return getDateTime(DateTime.now());
	}

	public static String recurrentTime() {
		// TODO Auto-generated method stub
		return getDateTime1(DateTime.now());
	}
}
