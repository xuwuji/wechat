package com.xuwuji.wechat.admin.util;

import java.util.HashMap;

/**
 * Contants for weidian service
 * 
 * Make the constructor to be private which makes it cannot be instanced and
 * Inheritance
 * 
 * @author wuxu
 *
 *         Jan 19, 2016
 */
public class WeiDianConstants {

	public static String GetAllOrderUrl = "http://api.vdian.com/api?param={\"page_num\":1,\"page_size\":100,\"order_type\":\"\", \"add_start\":\"{startDate} 16:36:08\",\"add_end\":\"{endDate} 16:36:08\"}&public={\"method\":\"vdian.order.list.get\",\"access_token\":\"{token}\", \"version\":\"1.1\",\"format\":\"json\"}";
	public static String weidianHOST = "http://api.vdian.com/api?param=";
	public static HashMap<String, String> Connection_Property = new HashMap<>();

	static {
		Connection_Property.put("accept", "*/*");
		Connection_Property.put("connection", "Keep-Alive");
		Connection_Property.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	}

	private WeiDianConstants() {

	}
}
