package com.xuwuji.wechat.admin.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.xuwuji.wechat.admin.dao.UserMapper;
import com.xuwuji.wechat.admin.util.MyBatisUtil;
import com.xuwuji.wechat.common.model.UserSubscribeEvent;

@Service
public class UserService {

	public List<UserSubscribeEvent> getAllSubscribeUser() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<UserSubscribeEvent> list = mapper.getAllSubscribeUser();
		session.commit();
		session.close();
		return list;
	}

	public List<HashMap<String, Object>> getUserTextMessageCount() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<HashMap<String, Object>> list = mapper.getUserTextMessageCount();
		session.commit();
		session.close();
		return list;
	}

}
