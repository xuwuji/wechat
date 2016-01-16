package com.xuwuji.wechat.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.xuwuji.wechat.admin.util.MyBatisUtil;
import com.xuwuji.wechat.common.model.Product;
@Service
public class ProductService {
	public static void insertProduct(Product product) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
			mapper.insertProduct(product);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public static List<Product> getAllProduct() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
			List<Product> list = mapper.getAllProduct();
			sqlSession.commit();
			return list;
		} finally {
			sqlSession.close();
		}
	}

	public static List<Product> getByCategory(String category) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
			List<Product> list = mapper.getByCategory(category);
			sqlSession.commit();
			return list;
		} finally {
			sqlSession.close();
		}
	}

	public static List<Product> getByNamePattern(String namePattern) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
			List<Product> list = mapper.getByNamePattern(namePattern);
			sqlSession.commit();
			return list;
		} finally {
			sqlSession.close();
		}
	}

	public static List<HashMap<String, Object>> groupByCategory() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
			List<HashMap<String, Object>> list = mapper.groupByCategory();
			sqlSession.commit();
			return list;
		} finally {
			sqlSession.close();
		}
	}

	public void deleteById(int product_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
			mapper.delelteById(product_id);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

}
