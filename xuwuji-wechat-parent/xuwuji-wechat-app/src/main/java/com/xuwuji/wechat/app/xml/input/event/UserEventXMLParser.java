package com.xuwuji.wechat.app.xml.input.event;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.xuwuji.wechat.app.model.UserEvent;
import com.xuwuji.wechat.app.model.user.event.UserSubscribeEvent;

/**
 * XML Parser for User Event Input
 * 
 * @author wuxu
 * @time 2016年1月12日
 */
public class UserEventXMLParser {
	public static SAXBuilder builder = new SAXBuilder();

	public static UserEvent parse(String input) throws JDOMException, IOException {
		if (getEventType(input).equals("subscribe") || getEventType(input).equals("unsubscribe")) {
			return parseSubsribeEvent(input);
		} else {
			return null;
		}
	}

	public static String getEventType(String input) throws JDOMException, IOException {
		InputStream stream = new ByteArrayInputStream(input.getBytes("UTF-8"));
		Document document = (Document) builder.build(stream);
		Element rootNode = document.getRootElement();
		return rootNode.getChildText("Event");
	}

	public static UserSubscribeEvent parseSubsribeEvent(String input) throws JDOMException, IOException {
		UserSubscribeEvent event = new UserSubscribeEvent();
		InputStream stream = new ByteArrayInputStream(input.getBytes("UTF-8"));
		Document document = (Document) builder.build(stream);
		Element rootNode = document.getRootElement();
		event.setToUserName(rootNode.getChildText("ToUserName"));
		event.setFromUserName(rootNode.getChildText("FromUserName"));
		event.setCreateTime(Long.valueOf(rootNode.getChildText("CreateTime")));
		event.setMsgType(rootNode.getChildText("MsgType"));
		event.setEvent(rootNode.getChildText("Event"));
		System.out.println(event.toString());
		return event;
	}

}
