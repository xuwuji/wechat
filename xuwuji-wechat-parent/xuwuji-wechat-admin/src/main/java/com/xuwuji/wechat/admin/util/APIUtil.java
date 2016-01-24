package com.xuwuji.wechat.admin.util;

import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map.Entry;

public class APIUtil {

	private static HashMap<String, String> map = new HashMap();

	static {
		map.put("accept", "application/json");
		map.put("content-type", "application/json");
		//exchange.put("apix-key", "8bcacfa2a22d40de644ff364a56b6c31");
	}

	public static URLConnection setRequestHeader(URLConnection conn) {
		for (Entry<String, String> entry : map.entrySet()) {
			conn.setRequestProperty(entry.getKey(), entry.getValue());
		}
		return conn;
	}

}
