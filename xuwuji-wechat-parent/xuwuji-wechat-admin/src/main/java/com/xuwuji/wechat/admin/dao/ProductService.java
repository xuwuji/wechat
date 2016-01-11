package com.xuwuji.wechat.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xuwuji.wechat.admin.model.Product;

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

}
