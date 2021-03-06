package com.xuwuji.wechat.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.xuwuji.wechat.common.model.Product;

public interface ProductMapper {

	@Insert("INSERT INTO PRODUCT(id,title,description, category, price,count,picUrl,url,time,flag) VALUES(#{id}, #{title},#{description},#{category},#{price},#{count},#{picUrl},#{url},#{time},#{flag})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insertProduct(Product product);

	@Select("SELECT * FROM PRODUCT where flag=1")
	public List<Product> getAllProduct();

	@Select("SELECT * FROM PRODUCT where category=#{category} order by time DES")
	public List<Product> getByCategory(String category);

	@Select("SELECT * FROM PRODUCT where title like #{namePattern}")
	public List<Product> getByNamePattern(String namePattern);

	@Select("SELECT category,count(category) as count FROM PRODUCT group by category")
	public List<HashMap<String, Object>> groupByCategory();

	@Update("update PRODUCT set flag =0 WHERE id=#{prodcut_id}")
	public void delelteById(int prodcut_id);

}
