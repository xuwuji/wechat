package com.xuwuji.wechat.service.output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.xuwuji.wechat.model.UserMessage;
import com.xuwuji.wechat.model.result.ResultNewsArticle;
import com.xuwuji.wechat.model.result.ResultNewsMessage;
import com.xuwuji.wechat.model.result.ResultTextMessage;
import com.xuwuji.wechat.xml.output.OutPutXMLParser;

/**
 * Service for Result News Message
 * 
 * @author xuwuji
 *
 *         Jan 11, 2016
 */
public class NewsService {

	/**
	 * return a news result message by category
	 * 
	 * @throws IOException
	 */
	public static void getNewsResultMessage(UserMessage user, String type, String key, OutputStream out)
			throws IOException {
		List<ResultNewsArticle> articles = NewsArticleService.getArticles(type, key);

		if (articles.size() == 0) {
			ResultTextMessage message = new ResultTextMessage();
			message.setCreateTime(System.currentTimeMillis() / 1000L);
			message.setToUserName(user.getFromUserName());
			message.setFromUserName(user.getToUserName());
			message.setMsgType("text");
			message.setContent("抱歉，我们还没有此样商品哦，请浏览其他");
			OutPutXMLParser.parse(message, out);
			return;
		}

		ResultNewsMessage message = new ResultNewsMessage();
		for (ResultNewsArticle article : articles) {
			message.addArticle(article);
		}
		message.setCreateTime(System.currentTimeMillis() / 1000L);
		message.setToUserName(user.getFromUserName());
		message.setFromUserName(user.getToUserName());
		message.setMsgType("news");
		message.setArticleCount(articles.size());
		OutPutXMLParser.parse(message, out);
	}

}
