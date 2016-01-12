package com.xuwuji.wechat.admin.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for logins
 * 
 * @author wuxu
 * @time 2016年1月12日
 */
@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = "/login/validate", method = RequestMethod.POST)
	public void validate(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		System.out.println(request.getParameter("remember"));
	}
}
