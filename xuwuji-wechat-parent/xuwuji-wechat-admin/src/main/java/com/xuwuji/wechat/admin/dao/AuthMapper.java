package com.xuwuji.wechat.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Auth Mapper
 * 
 * @author xuwuji
 *
 *         Jan 12, 2016
 */
public interface AuthMapper {

	@Select("select authority from auth where username=#{username} and password=#{password}")
	public Integer validate(@Param("username") String username, @Param("password") String password);
}
