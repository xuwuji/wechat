package com.xuwuji.wechat.xml;

import java.io.IOException;
import java.io.OutputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import com.xuwuji.wechat.model.ResultMessage;
import com.xuwuji.wechat.model.result.ResultTextMessage;

/**
 * Parse a result model to xml
 * 
 * @author wuxu
 * @time 2016Äê1ÔÂ8ÈÕ
 */
public class OutPutXMLParser {
	private static SAXBuilder builder = new SAXBuilder();

	private static String check(ResultMessage message) {
		return message.getMsgType();
	}

	private static void parseTextMessage(ResultTextMessage message, OutputStream out) throws IOException {
		// users' open id
		String user = message.getToUserName();
		// dev wechat count
		String dev = message.getFromUserName();
		long time = message.getCreateTime();
		String type = message.getMsgType();
		String content = message.getContent();
		Element root = new Element("xml");
		Document doc = new Document(root);
		Element toUser = new Element("ToUserName");
		toUser.addContent(user);
		Element fromUser = new Element("FromUserName");
		fromUser.addContent(dev);
		Element ctime = new Element("CreateTime");
		ctime.addContent(String.valueOf(time));
		Element MsgType = new Element("MsgType");
		MsgType.addContent(type);
		Element Content = new Element("Content");
		Content.addContent(content);
		root.addContent(toUser);
		root.addContent(fromUser);
		root.addContent(ctime);
		root.addContent(MsgType);
		root.addContent(Content);
		XMLOutputter serializer = new XMLOutputter();
		serializer.output(doc, out);
	}

	public static void parse(ResultMessage message, OutputStream out) throws IOException {
		if (check(message).equals("text")) {
			parseTextMessage((ResultTextMessage) message, out);
		}
	}

}
