package com.xuwuji.wechat.app.service.input;

import com.xuwuji.wechat.app.dao.UserMessageDao;
import com.xuwuji.wechat.app.model.UserMessage;
import com.xuwuji.wechat.app.model.user.UserTextMessage;

/**
 * Service for User Input Message
 * 
 * @author wuxu
 * @time 2016年1月12日
 */
public class UserMessageService {

	public static void insert(UserMessage message) {
		if (getType(message).equals("text")) {
			UserMessageDao.insertUserTextMessage((UserTextMessage) message);
		}
	}

	public static String getType(UserMessage message) {
		return message.getMsgType();
	}

}
