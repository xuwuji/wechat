package com.xuwuji.wechat.app.service.input;

import com.xuwuji.wechat.app.dao.UserEventDao;
import com.xuwuji.wechat.app.model.UserEvent;
import com.xuwuji.wechat.app.model.user.event.UserSubscribeEvent;

/**
 * Service for User Input Event
 * 
 * @author wuxu
 * @time 2016年1月12日
 */
public class UserEventService {

	public static void insert(UserEvent event) {
		if (getEventType(event).equals("subscribe") || getEventType(event).equals("unsubscribe")) {
			insertSubscribeEvent((UserSubscribeEvent) event);
		}
	}

	public static String getEventType(UserEvent event) {
		return event.getEvent();
	}

	/**
	 * handle the subscribe event
	 */
	public static void insertSubscribeEvent(UserSubscribeEvent event) {
		UserEventDao.insertUserSubscribeEvents(event);
	}

}
