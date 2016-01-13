package com.xuwuji.wechat.admin.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xuwuji.wechat.admin.util.Constants;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		/**
		 * check cookie, if the user select the remember me function, here can
		 * get cookie from the browser within 7 days
		 */

		if (cookies != null) {
			for (Cookie c : cookies) {
				System.out.println(c.getName() + ":" + c.getValue());
			}
			/**
			 * if get the cookie, then return true jump out of the
			 * Interceptor,continue the process
			 */
			for (Cookie c : cookies) {
				if (c.getName().equals(Constants.LOGIN_TOKEN)) {
					System.out.println("cookie found here in interceptor");
					return true;
				}
			}
		} else {
			// request.getContextPath() is the project name
			System.out.println("cookie not found here in interceptor");
			response.sendRedirect(request.getContextPath() + "/login");
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
