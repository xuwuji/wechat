package com.xuwuji.wechat.xml.input;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.xuwuji.wechat.model.UserMessage;
import com.xuwuji.wechat.model.user.UserTextMessage;

public class UserXMLParser {

	public static SAXBuilder builder = new SAXBuilder();

	public static UserMessage parse(String input) throws JDOMException, IOException {
		if (checkType(input).equals("text")) {
			return ParseTextMessage(input);
		} else {
			return null;
		}
	}

	public static String checkType(String input) throws JDOMException, IOException {
		// SAXBuilder builder = new SAXBuilder();
		InputStream stream = new ByteArrayInputStream(input.getBytes("UTF-8"));
		Document document = (Document) builder.build(stream);
		Element rootNode = document.getRootElement();
		System.out.println("The type of input message : " + rootNode.getChildText("MsgType"));
		return rootNode.getChildText("MsgType");
	}

	public static UserTextMessage ParseTextMessage(String input) throws JDOMException, IOException {
		UserTextMessage message = new UserTextMessage();
		InputStream stream = new ByteArrayInputStream(input.getBytes("UTF-8"));
		Document document = (Document) builder.build(stream);
		Element rootNode = document.getRootElement();
		message.setToUserName(rootNode.getChildText("ToUserName"));
		message.setFromUserName(rootNode.getChildText("FromUserName"));
		message.setCreateTime(Long.valueOf(rootNode.getChildText("CreateTime")));
		message.setMsgType(rootNode.getChildText("MsgType"));
		message.setContent(rootNode.getChildText("Content"));
		message.setMsgId(Long.valueOf(rootNode.getChildText("MsgId")));
		System.out.println(message.toString());
		return message;
	}

}