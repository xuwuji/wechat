package com.xuwuji.wechat.app.service.output;

import com.xuwuji.wechat.app.model.ResultMessage;
import com.xuwuji.wechat.app.model.UserMessage;

/**
 * Service for Output based on Users' input
 * 
 * @author wuxu
 * @time 2016年1月12日
 */
public class UserOutPutService {

	public static void process(UserMessage message) {
		if (getType(message).equals("text")) {
			/**
			 * if the input is a text message, do the process here...
			 */
		}
	}

	/**
	 * get type of User input
	 * 
	 * @param message
	 * @return
	 */
	public static String getType(UserMessage message) {
		return message.getMsgType();
	}

	/**
	 * produce a text output result
	 * 
	 */
	public static void getTextResult(UserMessage message) {

	}

	/**
	 * produce a news output result
	 */
	public static void getNewsResult(UserMessage messages) {

	}
}
