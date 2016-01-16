package com.xuwuji.wechat.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xuwuji.wechat.common.model.UserSubscribeEvent;

public interface UserMapper {

	@Select("select * from user_subscribe_event where Event='subscribe'")
	public List<UserSubscribeEvent> getAllSubscribeUser();
}
