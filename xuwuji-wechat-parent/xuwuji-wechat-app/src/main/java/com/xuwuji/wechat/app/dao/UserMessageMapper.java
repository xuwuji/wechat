package com.xuwuji.wechat.app.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.xuwuji.wechat.app.model.user.UserTextMessage;

public interface UserMessageMapper {
	@Insert("INSERT INTO user_text_message(id,userOpenId,content, time) VALUES(#{id}, #{FromUserName},#{Content},#{CreateTime})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insertUserTextMessage(UserTextMessage message);
}
