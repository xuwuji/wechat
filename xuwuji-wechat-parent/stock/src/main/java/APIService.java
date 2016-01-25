import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Service for common api service
 * 
 * @author wuxu
 *
 *         Jan 22, 2016
 */

public class APIService {
	private static String STOCK_KEY = "39db628ef6cb48385695ee018e927d67";

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
		// System.out.println(response);
		String time = TimeUtil.currentTimewithMinutes();
		System.out.println("-------------   " + time + "   ----------------");
		System.out.println("shanghai:" + shanghai_dot);
		System.out.println("shenzhen:" + shenzhen_dot);
		System.out.println("DJI:" + DJI_dot);
		System.out.println("HSI:" + HSI_dot);
		System.out.println("-------------------------------------------");
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("\n");
	}

	public static void main(String[] args) throws IOException, ParseException {
		APIService a = new APIService();
		a.stock();

	}

}
