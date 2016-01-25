package com.xuwuji.wechat.admin.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuwuji.wechat.admin.model.Stock;
import com.xuwuji.wechat.admin.util.APIUtil;
import com.xuwuji.wechat.common.util.HttpUtil;
import com.xuwuji.wechat.common.util.TimeUtil;

/**
 * Service for common api service
 * 
 * @author wuxu
 *
 *         Jan 22, 2016
 */

@Service
public class APIService {

	@Autowired
	private StockService stockService;
	private static String EXCHANGE_KEY = "8bcacfa2a22d40de644ff364a56b6c31";

	/**
	 * exchange service api
	 * 
	 * @param source
	 * @param goal
	 * @param amount
	 * @throws IOException
	 * @throws ParseException
	 */
	public String exchange(String source, String goal, String amount) throws IOException, ParseException {
		String url = "http://a.apix.cn/apixmoney/exchangerate/exchange?source=" + source + "&goal=" + goal + "&money="
				+ amount;
		URL realUrl = new URL(url);
		URLConnection connection = realUrl.openConnection();
		connection = APIUtil.setRequestHeader(connection);
		connection.setRequestProperty("apix-key", EXCHANGE_KEY);
		connection.connect();
		String response = HttpUtil.outputResponse(connection);
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(response);
		JSONObject data = (JSONObject) obj.get("data");
		String result = data.get("amount").toString();
		return result;
	}

	/**
	 * return the maximum dot of every day
	 * 
	 * @param market
	 * @return
	 */
	public HashMap<String, Double> getMaxStocksGroupByDate(String market) {
		List<HashMap<String, Object>> list = stockService.getMax(market);
		HashMap<String, Double> result = this.parseStock(list);
		return result;
	}

	/**
	 * return the open dot of every day
	 * 
	 * @param market
	 * @return
	 */
	public HashMap<String, Double> getOpenStocksGroupByDate(String market) {
		List<HashMap<String, Object>> list = stockService.getOpen(market);
		HashMap<String, Double> result = this.parseStock(list);
		return result;
	}

	/**
	 * return the close dot of every day
	 * 
	 * @param market
	 * @return
	 */
	public HashMap<String, Double> getCloseStocksGroupByDate(String market) {
		List<HashMap<String, Object>> list = stockService.getClose(market);
		HashMap<String, Double> result = this.parseStock(list);
		return result;
	}

	/**
	 * return the minimum dot of every day
	 * 
	 * @param market
	 * @return
	 */
	public HashMap<String, Double> getMinStocksGroupByDate(String market) {
		List<HashMap<String, Object>> list = stockService.getMin(market);
		HashMap<String, Double> result = this.parseStock(list);
		return result;
	}

	private HashMap<String, Double> parseStock(List<HashMap<String, Object>> list) {
		HashMap<String, Double> result = new HashMap();
		for (HashMap<String, Object> map : list) {
			String time = "";
			Double dot = 0.0;
			for (Entry<String, Object> entry : map.entrySet()) {
				String metric = entry.getKey();
				if (metric.equals("date")) {
					time = (String) entry.getValue();
				} else {
					dot = (Double) entry.getValue();
				}
			}
			System.out.println(time + ":" + dot);
			result.put(time, dot);
		}
		return result;
	}

	/**
	 * get the info of a market
	 * 
	 * @param market
	 */
	public ArrayList<Stock> getStockInfo(String startDate, String endDate, String market) {
		HashMap<String, Double> open = this.getOpenStocksGroupByDate(market);
		HashMap<String, Double> close = this.getCloseStocksGroupByDate(market);
		HashMap<String, Double> max = this.getMaxStocksGroupByDate(market);
		HashMap<String, Double> min = this.getMinStocksGroupByDate(market);
		int length = TimeUtil.dateLength(startDate, endDate);
		DateTime date = TimeUtil.convertString(startDate);
		ArrayList<Stock> list = new ArrayList<Stock>();
		for (int i = 0; i <= length; i++) {
			DateTime d = date.plusDays(i);
			String dateString = TimeUtil.getSimpleDateTime(d);
			Double open_dot = open.get(dateString);
			Double close_dot = close.get(dateString);
			Double max_dot = max.get(dateString);
			Double min_dot = min.get(dateString);
			Stock stock = new Stock.Builder().date(dateString).max(max_dot).min(min_dot).open(open_dot).close(close_dot)
					.build();
			System.out.println(stock.toString());
			list.add(stock);
		}
		return list;
	}

	public static void main(String[] args) throws IOException, ParseException {
		APIService a = new APIService();
		// a.exchange("CNY", "USD", "100");
		// a.stock();
		// a.getStocks("shanghai");
	}

}
