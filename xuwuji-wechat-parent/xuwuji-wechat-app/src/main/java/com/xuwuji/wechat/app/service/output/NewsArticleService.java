package com.xuwuji.wechat.app.service.output;

import java.util.ArrayList;
import java.util.List;

import com.xuwuji.wechat.app.dao.Product;
import com.xuwuji.wechat.app.dao.ProductService;
import com.xuwuji.wechat.app.model.result.ResultNewsArticle;

/**
 * Service for Articles of A News Result Message
 * 
 * Get the data from db and output to the parser to wrap it
 * 
 * @author xuwuji
 *
 *         Jan 11, 2016
 */
public class NewsArticleService {

	/**
	 * return articles in a result news message by type
	 * 
	 * 1.product name pattern
	 * 
	 * 2.product category
	 * 
	 * @param category
	 * @return
	 */
	public static List<ResultNewsArticle> getArticles(String type, String key) {
		List<Product> list = new ArrayList<Product>();
		System.out.println(type);
		if (type.equals("name")) {
			list = ProductService.getByNamePattern(key);
			System.out.println("here:" + list.size());

		} else if (type.equals("category")) {
			list = ProductService.getByCategory(key);
		}
		List<ResultNewsArticle> result = new ArrayList<ResultNewsArticle>();
		int length = list.size();
		if (length > 4) {
			length = 4;
		}
		for (int i = 0; i < length; i++) {
			ResultNewsArticle article = new ResultNewsArticle();
			article.setTitle(list.get(i).getTitle());
			article.setDescription(list.get(i).getDescription());
			article.setPicUrl(list.get(i).getPicUrl());
			article.setUrl(list.get(i).getUrl());
			result.add(article);
		}
		return result;
	}

}
