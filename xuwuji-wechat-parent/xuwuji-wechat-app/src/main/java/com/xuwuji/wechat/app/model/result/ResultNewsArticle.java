package com.xuwuji.wechat.app.model.result;

/**
 * Article Model For Result Message
 * 
 * @author xuwuji
 *
 *         Jan 9, 2016
 */
public class ResultNewsArticle {

	// title for the pic
	private String title;
	// description for the pic, show when only one article in the news
	private String description;
	// the url for the pic
	private String picUrl;
	// the page to jump when click the pic
	private String url;

	public ResultNewsArticle() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ResultArticle [title=" + title + ", description=" + description + ", picUrl=" + picUrl + ", url=" + url
				+ "]";
	}

}
