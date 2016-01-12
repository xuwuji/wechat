package com.xuwuji.wechat.app.dao;

import org.apache.ibatis.annotations.Insert;

import com.xuwuji.wechat.app.model.user.event.UserSubscribeEvent;

public interface UserEventMapper {

	@Insert(value = {
			"insert into user_subscribe_event (ToUserName,FromUserName,CreateTime,Event) values(#{ToUserName},#{FromUserName},#{CreateTime},#{Event})" })
	public void insertSubscribeEvent(UserSubscribeEvent event);

}
