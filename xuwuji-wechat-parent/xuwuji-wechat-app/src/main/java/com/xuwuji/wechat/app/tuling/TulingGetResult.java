package com.xuwuji.wechat.app.tuling;

import com.xuwuji.wechat.app.util.HttpUtil;

/**
 * Get the result from tuling rebort based on the input key
 * 
 * @author xuwuji
 *
 */
public class TulingGetResult {
	private static String URL = "http://www.tuling123.com/openapi/api?key=e99e29fe48c3c8c1c6f1f342c0362761&info=";

	public static String getResult(String key) throws Exception {
		return HttpUtil.GetTuLing(key);
	}

}
