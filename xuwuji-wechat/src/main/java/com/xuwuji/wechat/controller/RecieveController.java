package com.xuwuji.wechat.controller;

import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuwuji.wechat.dao.UserMessageDao;
import com.xuwuji.wechat.json.TulingParser;
import com.xuwuji.wechat.model.ResultMessage;
import com.xuwuji.wechat.model.UserMessage;
import com.xuwuji.wechat.model.user.UserTextMessage;
import com.xuwuji.wechat.service.input.TextMessageService;
import com.xuwuji.wechat.service.output.NewsService;
import com.xuwuji.wechat.util.SHA1;
import com.xuwuji.wechat.xml.input.UserXMLParser;
import com.xuwuji.wechat.xml.output.OutPutXMLParser;
import com.xuwuji.wewchat.tuling.TulingGetResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RecieveController {
	private String TOKEN = "weixin";

	/**
	 * 1.wechat server send some parameters
	 * 
	 * 2.calculate these parameters with SHA1 algorithm
	 * 
	 * 3.send back the result
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void validateToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String signature = request.getParameter("signature");// ΢�ż���ǩ��
		System.out.println("signature: " + signature);
		String timestamp = request.getParameter("timestamp");// ʱ���
		System.out.println("timestamp: " + timestamp);
		String nonce = request.getParameter("nonce");// �����
		System.out.println("nonce: " + nonce);
		String echostr = request.getParameter("echostr");// ����ַ���
		System.out.println("echostr: " + echostr);
		List<String> params = new ArrayList<String>();
		params.add(TOKEN);
		params.add(timestamp);
		params.add(nonce);
		// 1. ��token��timestamp��nonce�������������ֵ�������
		Collections.sort(params, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		// 2. �����������ַ���ƴ�ӳ�һ���ַ�������sha1����
		String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
		if (temp.equals(signature)) {
			response.getWriter().write(echostr);
		}
	}

	/**
	 * This is where recieves the message from wechat and send back the result
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void recieve(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		OutputStream out = response.getOutputStream();
		StringBuffer sb = new StringBuffer();
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		String xml = sb.toString();
		// System.out.println(xml);
		UserMessage message = UserXMLParser.parse(sb.toString());
		System.out.println("test:" + message.getContent() + "!!!!!");
		UserMessageDao.insertUserTextMessage((UserTextMessage) message);
		String user = message.getFromUserName();

		if (message != null && message.getMsgType().equals("text")) {

			NewsService.getNewsResultMessage(message, "category", message.getContent(), out);

			if (!message.getContent().equals("��")) {
				message.display();
				String tulingresult = TulingGetResult.getResult(message.getContent());
				System.out.println("tuling result:" + tulingresult);
				ResultMessage rm = TextMessageService.process((UserTextMessage) message);
				tulingresult = TulingParser.getNormalText(tulingresult);
				rm.setContent(tulingresult);
				OutPutXMLParser.parse(rm, out);
			} else {
				String a = "<xml><ToUserName>" + user + "</ToUserName>"
						+ "<FromUserName>gh_156fb851cf61</FromUserName><CreateTime>1452265616</CreateTime>"
						+ "<MsgType>news</MsgType><ArticleCount>3</ArticleCount>"
						+ "<Articles><item><Title>lv</Title> <Description>pic-des-test</Description>"
						+ "<PicUrl>http://7xpxq6.com1.z0.glb.clouddn.com/lv.jpg</PicUrl>"
						+ "<Url>http://weidian.com/?userid=870151513</Url></item>"
						+ "<item><Title>Gucci</Title> <Description>pic-des-test</Description>"
						+ "<PicUrl>http://7xpxq6.com1.z0.glb.clouddn.com/gucci.jpg</PicUrl>"
						+ "<Url>http://weidian.com/?userid=870151513</Url></item>"
						+ "<item><Title>channel</Title> <Description>pic-des-test</Description>"
						+ "<PicUrl>http://7xpxq6.com1.z0.glb.clouddn.com/channel.jpeg</PicUrl>"
						+ "<Url>http://weidian.com/?userid=870151513</Url></item></Articles></xml> ";
				response.getWriter().write(a);
			}
		}

	}

}
