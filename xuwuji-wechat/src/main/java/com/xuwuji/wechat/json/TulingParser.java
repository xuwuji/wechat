package com.xuwuji.wechat.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * parse result from tuling
 * 
 * @author xuwuji
 *
 */
public class TulingParser {

	public static String check(String input) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(input);
		String code = (String) obj.get("code").toString();
		System.out.println("code: " + code);
		return code;
	}

	public static String getNormalText(String input) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(input);
		if (check(input).equals("100000")) {
			String text = (String) obj.get("text").toString();
			System.out.println("text: " + text);
			return text;
		} else {
			return input;
		}
	}

}
