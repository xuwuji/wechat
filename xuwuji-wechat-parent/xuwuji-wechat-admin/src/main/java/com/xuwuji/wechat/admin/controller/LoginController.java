package com.xuwuji.wechat.admin.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.wechat.admin.dao.AuthService;

/**
 * Controller for login
 * 
 * 1.redirect to the login page if (1)not login (2)logout
 * 
 * 2.save username and password in session for 30 minutes in order to
 * automatically login next time
 * 
 * 3.remember me function will generate a key (7 days) stored in the browser
 * 
 * @author wuxu
 * @time 2016年1月12日
 */
@Controller
public class LoginController {

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("there1");
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/login");
		return model;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("there");
		ModelAndView model = new ModelAndView();
		Cookie[] cookies = request.getCookies();
		/**
		 * check cookie, if the user select the remember me function, here can
		 * get cookie from the browser within 7 days
		 */
		if (cookies != null) {
			for (Cookie c : cookies) {
				System.out.println(c.getName() + ":" + c.getValue());
			}
			for (Cookie c : cookies) {
				if (c.getName().equals("login_key")) {
					System.out.println("cookie found here");
					model.setViewName("redirect:/index");
					return model;
				}
			}
		}

		String username = (String) request.getSession().getAttribute("username");
		String password = (String) request.getSession().getAttribute("password");

		/**
		 * If the user does not select the remember me function, check the
		 * session,default 30 minutes
		 */
		if (validate(username, password) != null) {
			System.out.println("session found here");
			model.setViewName("redirect:/index");
		} else {
			System.out.println("session not found here");
			model.setViewName("login");
		}
		return model;
	}

	@RequestMapping(value = "/login/validate", method = RequestMethod.POST)
	public ModelAndView validate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember_me");
		String value = this.encode(username, password);

		System.out.println(username);
		System.out.println(password);
		Integer authority = AuthService.validate(username, password);
		System.out.println(authority);
		ModelAndView model = new ModelAndView();
		if (authority == null) {
			model.setViewName("redirect:/login");
		} else {
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("password", password);
			/**
			 * if user select the remember me function, add the cookie into the
			 * browser
			 */
			if (remember != null) {
				System.out.println("here");
				Cookie cookie = new Cookie("login_key", value);
				// 7 days expired
				cookie.setMaxAge(60 * 60 * 24 * 7);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			model.setViewName("redirect:/index");
		}
		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		/**
		 * delete cookie
		 */
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			System.out.println("logout");
			for (Cookie c : cookies) {
				System.out.println(c.getName() + ":" + c.getValue());
				if (c.getName().equals("login_key")) {
					c.setMaxAge(0);
					c.setPath("/");
					response.addCookie(c);
				}
			}
		}

		/**
		 * delete the session
		 */
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("password");
		model.setViewName("redirect:/login");
		return model;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}

	private String encode(String username, String password) {
		if (username == null || password == null) {

		}
		byte[] temp = null;
		String value = username + password;
		temp = value.getBytes();
		value = Base64.encodeBase64String(temp);
		return value;
	}

	private Integer validate(String username, String password) {
		if (username == null || password == null || username.equals("") || password.equals("")) {
			return null;
		}
		Integer authority = AuthService.validate(username, password);
		return authority;
	}

}
