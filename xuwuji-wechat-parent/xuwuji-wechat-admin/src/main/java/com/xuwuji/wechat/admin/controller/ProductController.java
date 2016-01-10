package com.xuwuji.wechat.admin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for Product operation
 * 
 * @author xuwuji
 *
 *         Jan 9, 2016
 */
@Controller
public class ProductController {

	/**
	 * upload a new product
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "product/add", method = RequestMethod.POST)
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// request.setCharacterEncoding("gb2312");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "gb2312"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb.toString());
	}
}
