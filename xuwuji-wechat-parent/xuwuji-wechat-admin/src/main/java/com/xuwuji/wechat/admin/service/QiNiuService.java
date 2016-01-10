package com.xuwuji.wechat.admin.service;

import com.qiniu.util.Auth;

/**
 * Service for using QiNiu Server
 * 
 * @author xuwuji
 *
 *         Jan 9, 2016
 */
public class QiNiuService {
	Auth auth = Auth.create("LVLzJr-cSFr0fC9AQpqXh_USzy3fwNs4yzOcOb7F", "z4pgHYpQvJ8CYqoOxNiG9s_v8pQzql6drvbwNfT6");

	// 简单上传，使用默认策略
	private String getUpToken0() {
		return auth.uploadToken("wechat");
	}
}
