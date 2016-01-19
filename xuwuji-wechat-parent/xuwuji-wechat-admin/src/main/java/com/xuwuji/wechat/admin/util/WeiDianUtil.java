package com.xuwuji.wechat.admin.util;

import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.xuwuji.wechat.common.util.HttpUtil;

/**
 * Util for some operations using weidian api
 * 
 * @author wuxu
 *
 */
public class WeiDianUtil {
	private static final String appKay = "644828";
	private static final String secret = "04d3251fd808bd65c2190982ffb41a40";
	private static final String accessUrl = "https://api.vdian.com/token?grant_type=client_credential&appkey=644828&secret=04d3251fd808bd65c2190982ffb41a40";

	private WeiDianUtil() {

	}

	public static String getAccessToken() throws Exception {
		String response = HttpUtil.Get(accessUrl);
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(response);
		JSONObject result = (JSONObject) obj.get("result");
		String text = result.get("access_token").toString();
		return text;
	}

	public static URLConnection setRequestProperty(URLConnection connection) {
		for (Entry<String, String> entry : WeiDianConstants.Connection_Property.entrySet()) {
			connection.setRequestProperty(entry.getKey(), entry.getValue());
		}
		return connection;
	}

}
