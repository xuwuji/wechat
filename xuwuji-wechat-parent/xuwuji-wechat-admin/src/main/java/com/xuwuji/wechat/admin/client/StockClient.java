package com.xuwuji.wechat.admin.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.xuwuji.wechat.admin.service.StockService;
import com.xuwuji.wechat.common.algorithm.MaxPQStarts1;

public class StockClient {

	public static void main(String[] args) {
		MaxPQStarts1<Double> pq = new MaxPQStarts1(10);
		List<HashMap<String, Object>> list = StockService.getAllDots("shanghai");
		ArrayList<Object> a = new ArrayList();

		for (HashMap<String, Object> map : list) {
			for (Entry<String, Object> entry : map.entrySet()) {
				Double dot = (Double) entry.getValue();
				a.add(dot);
			}
		}
		for (Object o : a) {
			pq.insert((Double) o);
		}

		for (Comparable key : pq.getArray()) {
			System.out.println(key);
		}
	}

}
