package com.xuwuji.wechat.admin.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import com.xuwuji.wechat.admin.dao.StockMapper;
import com.xuwuji.wechat.admin.util.Constants;
import com.xuwuji.wechat.admin.util.MyBatisUtil;
import com.xuwuji.wechat.common.util.TimeUtil;

@Service
public class StockService {

	public List<HashMap<String, Object>> get(String market) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		StockMapper mapper = session.getMapper(StockMapper.class);
		String table = Constants.stock_table.get(market);
		List<HashMap<String, Object>> list = mapper.get(table);
		session.commit();
		session.close();
		return list;
	}

	public List<HashMap<String, Object>> getClose(String market) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		StockMapper mapper = session.getMapper(StockMapper.class);
		String table = Constants.stock_table.get(market);
		List<HashMap<String, Object>> list = mapper.getClose(table);
		session.commit();
		session.close();
		return list;
	}

	public List<HashMap<String, Object>> getMax(String market) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		StockMapper mapper = session.getMapper(StockMapper.class);
		String table = Constants.stock_table.get(market);
		List<HashMap<String, Object>> list = mapper.getMax(table);
		session.commit();
		session.close();
		return list;
	}

	public List<HashMap<String, Object>> getMin(String market) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		StockMapper mapper = session.getMapper(StockMapper.class);
		String table = Constants.stock_table.get(market);
		List<HashMap<String, Object>> list = mapper.getMin(table);
		session.commit();
		session.close();
		return list;
	}

	public static List<HashMap<String, Object>> getOpen(String market) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		StockMapper mapper = session.getMapper(StockMapper.class);
		String table = Constants.stock_table.get(market);
		List<HashMap<String, Object>> list = mapper.getOpen(table);
		session.commit();
		session.close();
		return list;
	}

}
