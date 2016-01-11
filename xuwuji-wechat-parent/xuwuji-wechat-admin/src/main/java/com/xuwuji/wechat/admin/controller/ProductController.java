package com.xuwuji.wechat.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.xuwuji.wechat.admin.model.Product;
import com.xuwuji.wechat.admin.service.QiNiuService;
import com.xuwuji.wechat.admin.util.TimeUtil;

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

	/**
	 * upload a new product
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/product/add", method = RequestMethod.POST)

	private void add(@RequestParam("product_name") String product_name,
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
		InputStream fileStream = file.getInputStream();
		// check if the file name has already been in the space
		if (QiNiuService.contains(ImageName)) {
			ImageName = ImageName + "-1";
		}

		QiNiuService.uploadImage(fileStream, ImageName);
		product.setPicUrl(SpaceUrl + ImageName);
		System.out.println(product.toString());
	}

}
