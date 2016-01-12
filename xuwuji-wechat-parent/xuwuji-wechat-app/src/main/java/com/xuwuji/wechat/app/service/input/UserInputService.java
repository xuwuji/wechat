package com.xuwuji.wechat.app.service.input;

import java.io.IOException;

import org.jdom2.JDOMException;

import com.xuwuji.wechat.app.model.user.event.UserSubscribeEvent;
import com.xuwuji.wechat.app.xml.input.event.UserEventXMLParser;
import com.xuwuji.wechat.app.xml.input.message.UserMessageXMLParser;

/**
 * Service for User Input, handle two types
 * 
 * 1.user message
 * 
 * 2.user event
 * 
 * @author wuxu
 * @time 2016年1月12日
 */
public class UserInputService {

	public static boolean isEvent(String data) throws JDOMException, IOException {
		if (UserMessageXMLParser.getType(data).equals("event")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * log the user input into db
	 * 
	 * 1.check the type
	 * 
	 * 2.call the corresponding xml parser
	 * 
	 * 3.call the corresponding service to log the formatted data
	 * 
	 * @param data
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static void log(String data) throws JDOMException, IOException {
		if (isEvent(data)) {
			UserEventService.insert(UserEventXMLParser.parse(data));
		} else {
			UserMessageService.insert(UserMessageXMLParser.parse(data));
		}
	}

}
