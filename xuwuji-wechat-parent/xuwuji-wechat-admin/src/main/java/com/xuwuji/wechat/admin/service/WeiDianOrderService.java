package com.xuwuji.wechat.admin.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.xuwuji.wechat.admin.model.weidian.Order;
import com.xuwuji.wechat.admin.util.WeiDianConstants;
import com.xuwuji.wechat.admin.util.WeiDianUtil;
import com.xuwuji.wechat.common.util.HttpUtil;
import com.xuwuji.wechat.common.util.TimeUtil;

@Service

public class WeiDianOrderService {

	public String getOrderResponse(String startDate, String endDate) throws Exception {
		BufferedReader in = null;
		String result = "";
		try {
			String host = WeiDianConstants.weidianHOST;
			String minutes = TimeUtil.getCurrentMinutesDateTime();
			String param1 = ""
					+ "{\"page_num\":1,\"order_type\":\"\", \"add_start\":\"{startDate}%2000:00:00\",\"add_end\":\"{endDate}%20{now}\"}";
			param1 = param1.replace("{startDate}", startDate).replace("{endDate}", endDate).replace("{now}", minutes);
			String param2 = "{\"method\":\"vdian.order.list.get\",\"access_token\":\"{token}\", \"version\":\"1.1\",\"format\":\"json\"}";
			param2 = param2.replace("{token}", WeiDianUtil.getAccessToken());
			param1 = HttpUtil.encode(param1.toString(), "ISO-8859-1");
			param2 = HttpUtil.encode(param2.toString(), "ISO-8859-1");
			String url = host + param1 + "&public=" + param2;
			System.out.println(url);
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection = WeiDianUtil.setRequestProperty(connection);
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public String getOrderDetail(String order_id) throws Exception {
		BufferedReader in = null;
		String result = "";
		String host = WeiDianConstants.weidianHOST;
		String param1 = "{\"order_id\":\"{order_id}\"}";
		param1 = param1.replace("{order_id}", order_id);
		String param2 = "{\"method\":\"vdian.order.get\",\"access_token\":\"{token}\",\"version\":\"1.0\",\"format\":\"json\"}";
		param2 = param2.replace("{token}", WeiDianUtil.getAccessToken());
		param1 = HttpUtil.encode(param1.toString(), "ISO-8859-1");
		param2 = HttpUtil.encode(param2.toString(), "ISO-8859-1");
		String url = host + param1 + "&public=" + param2;
		URL realUrl = new URL(url);
		URLConnection connection = realUrl.openConnection();
		connection = WeiDianUtil.setRequestProperty(connection);
		connection.connect();
		in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			result += line;
		}
		return result;
	}

	public List<Order> parseOrderList(String input) throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(input);
		JSONObject result = (JSONObject) obj.get("result");
		JSONArray orders = (JSONArray) result.get("orders");
		List<Order> list = new ArrayList<Order>();
		for (int i = 0; i < orders.size(); i++) {
			JSONObject inode = (JSONObject) orders.get(i);
			String img = (String) inode.get("img").toString();
			String time = (String) inode.get("time").toString();
			String order_id = (String) inode.get("order_id").toString();
			JSONObject buyInfo = (JSONObject) inode.get("buyer_info");
			String address = (String) buyInfo.get("address");
			String city = (String) buyInfo.get("city");
			String province = (String) buyInfo.get("province");
			Order order = new Order.Builder().orderId(order_id).time(time).address(address).city(city)
					.province(province).img(img).build();
			System.out.println(order.toString());
			// System.out.println(getOrderDetail(order_id));
			list.add(order);
		}
		return list;
	}

	public Order parseOrderDetail(String input) throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(input);
		JSONObject result = (JSONObject) obj.get("result");
		JSONArray orders = (JSONArray) result.get("orders");
		List<Order> list = new ArrayList<Order>();
		for (int i = 0; i < orders.size(); i++) {
			JSONObject inode = (JSONObject) orders.get(i);
			String img = (String) inode.get("img").toString();
			String time = (String) inode.get("time").toString();
			String order_id = (String) inode.get("order_id").toString();
			JSONObject buyInfo = (JSONObject) inode.get("buyer_info");
			String address = (String) buyInfo.get("address");
			String city = (String) buyInfo.get("city");
			String province = (String) buyInfo.get("province");
			Order order = new Order.Builder().orderId(order_id).time(time).address(address).city(city)
					.province(province).img(img).build();
			System.out.println(order.toString());
			System.out.println(order.toString());
			// System.out.println(getOrderDetail(order_id));
			// list.add(order);
			return order;
		}
		return null;
	}

}
