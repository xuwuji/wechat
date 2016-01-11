package com.xuwuji.wechat.app.service.input;

import com.xuwuji.wechat.app.model.result.ResultTextMessage;
import com.xuwuji.wechat.app.model.user.UserTextMessage;

/**
 * Process Users' Nomal Text Message
 * 
 * @author wuxu
 * @time 2016Äê1ÔÂ8ÈÕ
 */
public class TextMessageService {

	public static ResultTextMessage process(UserTextMessage usermessage) {
		String user = usermessage.getFromUserName();
		String dev = usermessage.getToUserName();
		ResultTextMessage result = new ResultTextMessage();
		// users' open id
		result.setToUserName(user);
		// dev's count
		result.setFromUserName(dev);
		result.setCreateTime(System.currentTimeMillis() / 1000L);
		result.setContent("test!!");
		return result;
	}

}
