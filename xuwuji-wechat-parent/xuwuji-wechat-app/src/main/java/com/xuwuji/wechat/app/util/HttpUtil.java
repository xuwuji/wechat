package com.xuwuji.wechat.app.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http Util
 * 
 * get method
 * 
 * post method -application/json
 * 
 * @author xuwuji
 *
 */
public class HttpUtil {

	private static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);

	public static String getHttpResponse(HttpMethod request) throws Exception {
		HttpClient client = new HttpClient();

		String response = "";
		int status = -1;
		try {
			LOG.info(request.getURI().toString());
			status = client.executeMethod(request);
			if (status != 200) {
				throw new RuntimeException("Got unexpected response code " + status);
			}
			response = request.getResponseBodyAsString();
		} catch (HttpException e) {
			LOG.error("Fatal protocol violation: " + e.getMessage());
			throw e;
		} catch (IOException e) {
			LOG.error("Fatal transport error: " + e.getMessage());
			throw e;
		} finally {
			// Release the connection.
			request.releaseConnection();
		}
		return response;
	}

	/**
	 * get the response from a get method
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String Get(String url) throws Exception {
		GetMethod request = null;
		request = new GetMethod(url);
		return getHttpResponse(request);
	}

	public static String GetTuLing(String key) throws Exception {
		String url = "/openapi/api";
		String host = "www.tuling123.com";
		String param = "info=" + URLEncoder.encode(key, "utf-8") + "&key=e99e29fe48c3c8c1c6f1f342c0362761";
		HttpClient httpClient = new HttpClient();
		httpClient.getHostConfiguration().setHost(host, 80, "http");
		HttpMethod method = new GetMethod(url + "?" + param);
		httpClient.executeMethod(method);
		String response = method.getResponseBodyAsString();
		return response;
	}

	/**
	 * get the response from a post method, based on json
	 * 
	 * @param url
	 * @param payload
	 * @return
	 * @throws Exception
	 */
	public static String postJson(String url, String payload) throws Exception {
		StringRequestEntity requestEntity = new StringRequestEntity(payload, "application/json", "UTF-8");
		PostMethod request = new PostMethod(url);
		request.setRequestEntity(requestEntity);
		String response = HttpUtil.getHttpResponse(request);
		System.out.println(response);
		return response;
	}
}
