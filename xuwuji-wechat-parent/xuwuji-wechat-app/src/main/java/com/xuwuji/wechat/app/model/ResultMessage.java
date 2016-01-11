package com.xuwuji.wechat.app.model;

/**
 * Interface for Message Back to User
 * 
 * @author wuxu
 * @time 2016��1��8��
 */
public interface ResultMessage {
	String getMsgType();

	void setContent(String content);

	String getToUserName();

	String getFromUserName();

	long getCreateTime();
}
