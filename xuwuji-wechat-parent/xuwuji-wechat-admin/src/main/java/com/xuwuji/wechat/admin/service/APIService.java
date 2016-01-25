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
	private static String STOCK_KEY = "39db628ef6cb48385695ee018e927d67";

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
	 * stock service api
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public void stock() throws IOException, ParseException {
		String url = "http://a.apix.cn/apixmoney/stockdata/stock?stockid=50";
		URL realUrl = new URL(url);
		URLConnection connection = realUrl.openConnection();
		connection = APIUtil.setRequestHeader(connection);
		connection.setRequestProperty("apix-key", STOCK_KEY);
		connection.connect();
		String response = HttpUtil.outputResponse(connection);
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(response);
		JSONObject data = (JSONObject) obj.get("data");
		JSONObject market = (JSONObject) data.get("market");
		JSONObject shanghai = (JSONObject) market.get("shanghai");
		JSONObject shenzhen = (JSONObject) market.get("shenzhen");
		JSONObject DJI = (JSONObject) market.get("DJI");
		JSONObject HSI = (JSONObject) market.get("HSI");
		Double shanghai_dot = Double.valueOf(shanghai.get("curdot").toString());
		String shenzhen_dot = shenzhen.get("curdot").toString();
		String DJI_dot = DJI.get("curdot").toString();
		String HSI_dot = HSI.get("curdot").toString();
		StockService.addRecord(shanghai_dot, "shanghai");
		System.out.println(response);
		System.out.println("shanghai:" + shanghai_dot);
		System.out.println("shenzhen:" + shenzhen_dot);
		System.out.println("DJI:" + DJI_dot);
		System.out.println("HSI:" + HSI_dot);
	}

	public HashMap<String, Double> getStocks(String market) {
		StockService service = new StockService();
		List<HashMap<String, Object>> list = service.get(market);
		HashMap<String, Double> result = new HashMap();
		for (HashMap<String, Object> map : list) {
			long time = 0;
			Double dot = 0.0;
			for (Entry<String, Object> entry : map.entrySet()) {
				String metric = entry.getKey();
				if (metric.equals("time")) {
					time = ((Timestamp) entry.getValue()).getTime() / 1000;
					System.out.println(entry.getKey() + ":" + time);
				} else {
					dot = (Double) entry.getValue();
					System.out.println(entry.getKey() + ":" + dot);
				}
			}
			System.out.println(time + ":" + dot);
			result.put(TimeUtil.converUnix(time), dot);
		}
		return result;
	}

	/**
	 * return the maximum dot of every day
	 * 
	 * @param market
	 * @return
	 */
	public HashMap<String, Double> getMaxStocksGroupByDate(String market) {
		StockService service = new StockService();
		List<HashMap<String, Object>> list = service.getMax(market);
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
		StockService service = new StockService();
		List<HashMap<String, Object>> list = service.getOpen(market);
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
		StockService service = new StockService();
		List<HashMap<String, Object>> list = service.getClose(market);
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
		StockService service = new StockService();
		List<HashMap<String, Object>> list = service.getMin(market);
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
					// System.out.println(entry.getKey() + ":" + time);
				} else {
					dot = (Double) entry.getValue();
					// System.out.println(entry.getKey() + ":" + dot);
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
			System.out.println("!!!!!" + dateString);
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
		a.stock();
		a.getStocks("shanghai");
	}

}
