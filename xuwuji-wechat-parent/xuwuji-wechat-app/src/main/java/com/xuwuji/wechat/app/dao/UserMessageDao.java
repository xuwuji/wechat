package com.xuwuji.wechat.app.dao;

import org.apache.ibatis.session.SqlSession;

import com.xuwuji.wechat.app.model.user.UserTextMessage;
import com.xuwuji.wechat.app.util.MyBatisUtil;

public class UserMessageDao {

	/**
	 * Insert the users' text message into DB
	 * 
	 * @param message
	 */
	public static void insertUserTextMessage(UserTextMessage message) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UserMessageMapper mapper = sqlSession.getMapper(UserMessageMapper.class);
			mapper.insertUserTextMessage(message);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
}
