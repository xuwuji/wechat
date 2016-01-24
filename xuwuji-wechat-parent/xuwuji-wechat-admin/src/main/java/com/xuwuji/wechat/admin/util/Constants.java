package com.xuwuji.wechat.admin.util;

import java.util.HashMap;

public class Constants {
	public static final String LOGIN_TOKEN = "login_key";
	public static final HashMap<String, String> stock_table = new HashMap();

	static {
		stock_table.put("shanghai", "shanghai_stock");
	}
}
