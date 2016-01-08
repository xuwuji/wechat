package com.xuwuji.wechat.controller;

import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuwuji.wechat.json.TulingParser;
import com.xuwuji.wechat.model.ResultMessage;
import com.xuwuji.wechat.model.UserMessage;
import com.xuwuji.wechat.model.user.UserTextMessage;
import com.xuwuji.wechat.service.TextMessageService;
import com.xuwuji.wechat.util.SHA1;
import com.xuwuji.wechat.xml.OutPutXMLParser;
import com.xuwuji.wechat.xml.UserXMLParser;
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
		String signature = request.getParameter("signature");// 微信加密签名
		System.out.println("signature: " + signature);
		String timestamp = request.getParameter("timestamp");// 时间戳
		System.out.println("timestamp: " + timestamp);
		String nonce = request.getParameter("nonce");// 随机数
		System.out.println("nonce: " + nonce);
		String echostr = request.getParameter("echostr");// 随机字符串
		System.out.println("echostr: " + echostr);
		List<String> params = new ArrayList<String>();
		params.add(TOKEN);
		params.add(timestamp);
		params.add(nonce);
		// 1. 将token、timestamp、nonce三个参数进行字典序排序
		Collections.sort(params, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		// 2. 将三个参数字符串拼接成一个字符串进行sha1加密
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
		//OutputStream out = response.getOutputStream();
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
		if (message != null && message.getMsgType().equals("text")) {
			message.display();
			String tulingresult = TulingGetResult.getResult(message.getContent());
			System.out.println(tulingresult);
			ResultMessage rm = TextMessageService.process((UserTextMessage) message);
			tulingresult = TulingParser.getNormalText(tulingresult);
			rm.setContent(tulingresult);
			// OutPutXMLParser.parse(rm, out);
		}

		String a = "<xml><ToUserName>oLUO5jm7NHn7cMTU6HYZXI52Eavw</ToUserName>"
				+ "<FromUserName>gh_156fb851cf61</FromUserName><CreateTime>1452265616</CreateTime>"
				+ "<MsgType>news</MsgType><ArticleCount>1</ArticleCount>"
				+ "<Articles><item><Title>test</Title> <Description>pic-des-test</Description>"
				+ "<PicUrl>http://img5.imgtn.bdimg.com/it/u=3596538801,2340004782&fm=15&gp=0.jpg</PicUrl>"
				+ "<Url>www.baidu.com</Url></item></Articles></xml> ";
		response.getWriter().write(a);
	}

}
