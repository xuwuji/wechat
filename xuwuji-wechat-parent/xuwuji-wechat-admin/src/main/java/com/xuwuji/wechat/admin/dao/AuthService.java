package com.xuwuji.wechat.admin.dao;

import org.apache.ibatis.session.SqlSession;

import com.xuwuji.wechat.admin.util.MyBatisUtil;

public class AuthService {

	public static Integer validate(String username, String password) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AuthMapper mapper = sqlSession.getMapper(AuthMapper.class);
			Integer result = mapper.validate(username, password);
			sqlSession.commit();
			return result;
		} finally {
			sqlSession.close();
		}
	}

}
