package com.xuwuji.wechat.admin.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface StockMapper {

	@Insert("insert into ${table} (dot) values (#{dot})")
	public void addStockRecord(@Param("dot") String dot, @Param("table") String table);

	@Select("select dot,time from ${table}")
	public List<HashMap<String, Object>> get(@Param("table") String table);

}
