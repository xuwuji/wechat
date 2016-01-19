package com.xuwuji.wechat.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xuwuji.wechat.admin.dao.ProductService;
import com.xuwuji.wechat.admin.service.QiNiuService;
import com.xuwuji.wechat.common.model.Price;
import com.xuwuji.wechat.common.model.Product;
import com.xuwuji.wechat.common.util.TimeUtil;

/**
 * Controller for Product operation
 * 
 * @author xuwuji
 *
 *         Jan 9, 2016
 */

@MultipartConfig
@Controller
public class ProductController {
	private static final String SpaceUrl = "http://7xpxq6.com1.z0.glb.clouddn.com/";
	@Autowired
	private ProductService service;

	/**
	 * upload a new product
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	private String add(@RequestParam("product_name") String product_name,
			@RequestParam("product_price") String product_price, @RequestParam("product_count") String product_count,
			@RequestParam("product_category") String product_category,
			@RequestParam("product_description") String product_description,
			@RequestParam("product_url") String product_url, @RequestParam("file") MultipartFile file)
					throws IOException, ServletException {
		String ImageName = file.getOriginalFilename();
		Product product = new Product();
		product.setTitle(product_name);
		product.setPrice(Double.valueOf(product_price));
		product.setDescription(product_description);
		product.setCount(Integer.valueOf(product_count));
		product.setCategory(product_category);
		product.setUrl(product_url);
		product.setTime(TimeUtil.currentTime());
		product.setFlag(1);
		InputStream fileStream = file.getInputStream();
		// check if the file name has already been in the space
		if (QiNiuService.contains(ImageName)) {
			ImageName = ImageName + "-" + TimeUtil.recurrentTime();
		}

		QiNiuService.uploadImage(fileStream, ImageName);
		product.setPicUrl(SpaceUrl + ImageName);
		System.out.println(product.toString());
		ProductService.insertProduct(product);
		return "redirect:/index";
	}

	/**
	 * Get all active products
	 * 
	 * @return
	 */
	@RequestMapping(value = "/product/get", method = RequestMethod.GET)
	public @ResponseBody List<Product> getAllProducts() {
		List<Product> list = ProductService.getAllProduct();
		for (Product p : list) {
			System.out.println(p.toString());
		}
		return list;
	}

	/**
	 * return data group by category
	 */
	@RequestMapping(value = "/product/category", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Integer> groupByCategory() {
		List<HashMap<String, Object>> categories = new ArrayList<HashMap<String, Object>>();
		categories = ProductService.groupByCategory();
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		for (HashMap<String, Object> map : categories) {
			String name = "";
			Integer count = 0;
			for (Entry<String, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
				if (entry.getKey().equals("category")) {
					name = (String) entry.getValue();
				}
				if (entry.getKey().equals("count")) {
					count = Integer.valueOf(String.valueOf(entry.getValue()));
				}
			}
			result.put(name, count);
		}
		return result;
	}

	/**
	 * return data group by price
	 */
	@RequestMapping(value = "/product/price", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Integer> groupByPrice() {

		HashMap<String, Integer> result = new HashMap<String, Integer>();
		result.put(Price.CHEAP.getMessage(), 0);
		result.put(Price.MEDIUM.getMessage(), 0);
		result.put(Price.DEAR.getMessage(), 0);

		List<Product> list = ProductService.getAllProduct();
		for (Product p : list) {
			if (p.getPrice() <= 500) {
				result.put(Price.CHEAP.getMessage(), result.get(Price.CHEAP.getMessage()) + 1);
			} else if (p.getPrice() > 500 && p.getPrice() <= 100) {
				result.put(Price.MEDIUM.getMessage(), result.get(Price.MEDIUM.getMessage()) + 1);
			} else {
				result.put(Price.MEDIUM.getMessage(), result.get(Price.DEAR.getMessage()) + 1);
			}
		}
		return result;
	}

	@RequestMapping(value = "/product/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Integer product_id) {
		service.deleteById(product_id);
		return "redirect:/index";
	}

}
