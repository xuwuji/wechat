package com.xuwuji.wechat.app.model;

import com.xuwuji.wechat.app.util.TimeUtil;

/**
 * The Interface for users' input message
 * 
 * @author wuxu
 * @time 2016Äê1ÔÂ8ÈÕ
 */
public interface UserMessage {
	public void display();

	public String getMsgType();

	public String getContent();

	public String toString();

	public String getFromUserName();

	public String getToUserName();
}
