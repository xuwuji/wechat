package com.xuwuji.wechat.admin.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.rs.EntryPath;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;
import com.qiniu.api.rsf.ListItem;
import com.qiniu.api.rsf.ListPrefixRet;
import com.qiniu.api.rsf.RSFClient;
import com.qiniu.api.rsf.RSFEofException;

/**
 * Service for using QiNiu Server
 * 
 * @author xuwuji
 *
 *         Jan 9, 2016
 */
public class QiNiuService {

	private static final String ACCESS_KEY = "LVLzJr-cSFr0fC9AQpqXh_USzy3fwNs4yzOcOb7F";
	private static final String SECRET_KEY = "z4pgHYpQvJ8CYqoOxNiG9s_v8pQzql6drvbwNfT6";
	private static final String BUCKET = "wechat";
	private static HashSet<String> set = new HashSet<String>();

	/**
	 * ÉÏ´«Í¼Æ¬µ½ÆßÅ£ÔÆ´æ´¢
	 * 
	 * @param reader
	 * @param fileName
	 *            Name of the image to upload
	 */
	public static void uploadImage(InputStream reader, String fileName) {
		String uptoken;
		try {
			Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
			PutPolicy putPolicy = new PutPolicy(BUCKET);
			uptoken = putPolicy.token(mac);
			IoApi.Put(uptoken, fileName, reader, null);
		} catch (AuthException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static HashSet<String> getSet() {
		Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
		RSFClient client = new RSFClient(mac);
		String marker = "";
		// HashSet<String> set = new HashSet<String>();
		List<ListItem> all = new ArrayList<ListItem>();
		ListPrefixRet ret = null;
		while (true) {
			ret = client.listPrifix(BUCKET, "", marker, 10);
			marker = ret.marker;
			all.addAll(ret.results);
			if (!ret.ok()) {
				// no more items or error occurs
				break;
			}
		}
		if (ret.exception.getClass() != RSFEofException.class) {
			// error handler
		}

		for (ListItem i : all) {
			System.out.println(i.key);
			set.add(i.key);
		}
		return set;
	}

	/**
	 * check if the currently uploading image is in the space
	 * 
	 * @param ImageName
	 */
	public static boolean contains(String ImageName) {
		set = getSet();
		if (set.contains(ImageName)) {
			return true;
		} else {
			return false;
		}
	}
}
