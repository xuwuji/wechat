package com.xuwuji.wechat.app.dao;

import org.apache.ibatis.session.SqlSession;
import com.xuwuji.wechat.app.model.user.event.UserSubscribeEvent;
import com.xuwuji.wechat.app.util.MyBatisUtil;

public class UserEventDao {
	/**
	 * Insert the users' subscribe event into DB
	 * 
	 * @param message
	 */
	public static void insertUserSubscribeEvents(UserSubscribeEvent event) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UserEventMapper mapper = sqlSession.getMapper(UserEventMapper.class);
			mapper.insertSubscribeEvent(event);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
}
