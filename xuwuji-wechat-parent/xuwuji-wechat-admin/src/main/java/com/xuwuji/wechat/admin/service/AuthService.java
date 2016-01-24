package com.xuwuji.wechat.admin.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.xuwuji.wechat.admin.dao.AuthMapper;
import com.xuwuji.wechat.admin.util.MyBatisUtil;

@Service
public class AuthService {

	public Integer validate(String username, String password) {
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
