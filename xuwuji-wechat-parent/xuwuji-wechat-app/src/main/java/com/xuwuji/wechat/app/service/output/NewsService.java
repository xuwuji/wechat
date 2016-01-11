package com.xuwuji.wechat.app.service.output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.xuwuji.wechat.app.model.UserMessage;
import com.xuwuji.wechat.app.model.result.ResultNewsArticle;
import com.xuwuji.wechat.app.model.result.ResultNewsMessage;
import com.xuwuji.wechat.app.model.result.ResultTextMessage;
import com.xuwuji.wechat.app.xml.output.OutPutXMLParser;

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
			message.setContent("我们还有没有这件商品哦");
			OutPutXMLParser.parse(message, out);
			return;
		}

		ResultNewsMessage message = new ResultNewsMessage();
		for (ResultNewsArticle article : articles) {
			System.out.println(article.toString());
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
