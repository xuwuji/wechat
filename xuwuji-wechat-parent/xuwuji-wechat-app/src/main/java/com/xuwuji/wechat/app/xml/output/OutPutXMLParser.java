package com.xuwuji.wechat.app.xml.output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import com.xuwuji.wechat.app.model.ResultMessage;
import com.xuwuji.wechat.app.model.result.ResultNewsArticle;
import com.xuwuji.wechat.app.model.result.ResultNewsMessage;
import com.xuwuji.wechat.app.model.result.ResultTextMessage;

/**
 * Parse a result model to xml format and output it through a output stream
 * 
 * @author wuxu
 * @time 2016年1月8日
 */
public class OutPutXMLParser {
	private static SAXBuilder builder = new SAXBuilder();

	public static void parse(ResultMessage message, OutputStream out) throws IOException {
		if (check(message).equals("text")) {
			parseTextMessage((ResultTextMessage) message, out);
		} else if (check(message).equals("news")) {
			parseNewsMessage((ResultNewsMessage) message, out);
		}
	}

	private static String check(ResultMessage message) {
		return message.getMsgType();
	}

	private static Element commonRoot(ResultMessage message) {
		Element root = new Element("xml");
		// users' open id
		String user = message.getToUserName();
		Element toUser = new Element("ToUserName");
		toUser.addContent(user);
		// dev wechat count
		String dev = message.getFromUserName();
		Element fromUser = new Element("FromUserName");
		fromUser.addContent(dev);
		// create time
		long time = message.getCreateTime();
		Element ctime = new Element("CreateTime");
		ctime.addContent(String.valueOf(time));
		root.addContent(toUser);
		root.addContent(fromUser);
		root.addContent(ctime);
		return root;
	}

	private static void parseTextMessage(ResultTextMessage message, OutputStream out) throws IOException {
		Element root = commonRoot(message);
		Document doc = new Document(root);
		Element type = new Element("MsgType");
		type.addContent("text");
		root.addContent(type);
		String content = message.getContent();
		Element Content = new Element("Content");
		Content.addContent(content);
		root.addContent(Content);
		XMLOutputter serializer = new XMLOutputter();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		serializer.output(doc, out);
		// byte[] lens = baos.toByteArray();
		// String result = new String(lens);// result结果显示正常：含中文无乱码
		// String str = baos.toString();
		// return result;
	}

	/**
	 * parse a news result message
	 * 
	 * @throws IOException
	 */
	public static void parseNewsMessage(ResultNewsMessage message, OutputStream out) throws IOException {
		Element root = commonRoot(message);
		Document doc = new Document(root);
		Element type = new Element("MsgType");
		type.addContent("news");
		root.addContent(type);
		// article count
		int Acount = message.getArticleCount();
		Element count = new Element("ArticleCount");
		count.addContent(String.valueOf(Acount));
		// articles
		Element articles = new Element("Articles");
		for (int i = 0; i < Acount; i++) {
			Element e = parseNewArticle(message.getArticles().get(i));
			articles.addContent(e.detach());
		}
		root.addContent(count);
		root.addContent(articles);
		XMLOutputter serializer = new XMLOutputter();
		serializer.output(doc, out);

	}

	/**
	 * parse the Result News Article Model
	 * 
	 * <item>
	 * 
	 * <Title><![CDATA[title1]]></Title>
	 * <Description><![CDATA[description1]]></Description>
	 * <PicUrl><![CDATA[picurl]]></PicUrl> <Url><![CDATA[url]]></Url>
	 * 
	 * </item>
	 * 
	 * @param article
	 * @throws IOException
	 */
	private static Element parseNewArticle(ResultNewsArticle article) throws IOException {
		Element root = new Element("item");
		Document doc = new Document(root);
		Element title = new Element("Title");
		title.addContent(article.getTitle());
		Element des = new Element("Description");
		des.addContent(article.getDescription());
		Element picUrl = new Element("PicUrl");
		picUrl.addContent(article.getPicUrl());
		Element url = new Element("Url");
		url.addContent(article.getUrl());
		root.addContent(title);
		root.addContent(des);
		root.addContent(picUrl);
		root.addContent(url);
		return root;
	}

}
