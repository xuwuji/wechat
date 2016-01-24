package com.xuwuji.wechat.admin.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import com.xuwuji.wechat.admin.dao.StockMapper;
import com.xuwuji.wechat.admin.util.Constants;
import com.xuwuji.wechat.admin.util.MyBatisUtil;

@Service
public class StockService {

	public static void addRecord(String dot, String type) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		String table = Constants.stock_table.get(type);
		StockMapper mapper = session.getMapper(StockMapper.class);
		mapper.addStockRecord(dot, table);
		session.commit();
		session.close();
	}

	public static List<HashMap<String, Object>> get(String market) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		StockMapper mapper = session.getMapper(StockMapper.class);
		String table = Constants.stock_table.get(market);
		List<HashMap<String, Object>> list = mapper.get(table);
		session.commit();
		session.close();
		return list;
	}

}
