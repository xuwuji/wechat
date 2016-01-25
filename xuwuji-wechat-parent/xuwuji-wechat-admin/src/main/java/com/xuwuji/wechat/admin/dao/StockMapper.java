package com.xuwuji.wechat.admin.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface StockMapper {

	@Insert("insert into ${table} (dot,date) values (#{dot},#{date})")
	public void addStockRecord(@Param("dot") Double dot, @Param("table") String table, @Param("date") String date);

	@Select("select dot,time from ${table}")
	public List<HashMap<String, Object>> get(@Param("table") String table);

	@Select("select dot,date from ${table} as select_table inner join (select min(time) as time from ${table} where HOUR(time) >= 14 and MINUTE(time)>=59  group by date) temp on select_table.time =temp.time")
	public List<HashMap<String, Object>> getClose(@Param("table") String table);

	@Select("select dot,date from ${table} as select_table inner join (select min(time) as time from ${table} where HOUR(time) >= 9 and MINUTE(time)>=30 group by date) temp on select_table.time =temp.time")
	public List<HashMap<String, Object>> getOpen(@Param("table") String table);

	@Select("select max(dot) as dot,date from ${table} group by date")
	public List<HashMap<String, Object>> getMax(@Param("table") String table);

	@Select("select min(dot) as dot,date from ${table} group by date")
	public List<HashMap<String, Object>> getMin(@Param("table") String table);

}
