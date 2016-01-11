package com.xuwuji.wechat.model;

import com.xuwuji.wechat.util.TimeUtil;

/**
 * The Interface for users' input message
 * 
 * @author wuxu
 * @time 2016��1��8��
 */
public interface UserMessage {
	public void display();

	public String getMsgType();

	public String getContent();

	public String toString();

	public String getFromUserName();

	public String getToUserName();
}
