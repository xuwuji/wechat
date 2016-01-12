package com.xuwuji.wechat.admin.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.wechat.admin.dao.AuthService;

/**
 * Controller for logins
 * 
 * @author wuxu
 * @time 2016年1月12日
 */
@Controller
public class LoginController {

	@RequestMapping(value = { "/login.html", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = "/login/validate", method = RequestMethod.POST)
	public ModelAndView validate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		Integer authority = AuthService.validate(username, password);
		System.out.println(authority);
		System.out.println(request.getParameter("remember"));
		ModelAndView model = new ModelAndView();
		if (authority == null) {
			model.setViewName("redirect:/login");
		} else {
			model.setViewName("redirect:/index");
		}
		return model;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
}
