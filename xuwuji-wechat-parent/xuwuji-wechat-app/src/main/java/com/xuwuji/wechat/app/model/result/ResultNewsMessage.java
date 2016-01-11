package com.xuwuji.wechat.app.model.result;

import java.util.ArrayList;
import java.util.List;

/**
 * Model For Normal TEXT&PIC Message To User
 * 
 * Extends the normal text result message, add new articles attribute
 * 
 * @author xuwuji
 *
 *         Jan 9, 2016
 */
public class ResultNewsMessage extends ResultTextMessage {

	private String MsgType = "news";
	private int articleCount;
	private List<ResultNewsArticle> articles;

	public ResultNewsMessage() {
		super();
		this.articles = new ArrayList<ResultNewsArticle>();
		this.MsgType = "news";
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public int getArticleCount() {
		return this.articles.size();
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public List<ResultNewsArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<ResultNewsArticle> articles) {
		this.articles = articles;
	}

	public void addArticle(ResultNewsArticle article) {
		this.articles.add(article);
	}

}
