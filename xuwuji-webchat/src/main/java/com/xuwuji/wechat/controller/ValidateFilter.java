package com.xuwuji.wechat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateFilter implements Filter {
	// ���Token�Ǹ�΢�ſ����߽���ʱ���
	// ����������Ӣ����ĸ�����֣�����Ϊ3-32�ַ�
	private static String Token = "3820182";

	public void init(FilterConfig config) throws ServletException {
		System.out.println("WeixinUrlFilter�����ɹ�!");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// ΢�ŷ�����������GET������д��URL��,������Ҫ�ж��Ƿ�ΪGET����
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		System.out.println("���΢������:" + request.getMethod() + " ��ʽ");
		if (isGet) {
			// ��֤URL��ʵ��
			String signature = request.getParameter("signature");// ΢�ż���ǩ��
			String timestamp = request.getParameter("timestamp");// ʱ���
			String nonce = request.getParameter("nonce");// �����
			String echostr = request.getParameter("echostr");// ����ַ���
			List<String> params = new ArrayList<String>();
			params.add(Token);
			params.add(timestamp);
			params.add(nonce);
			// 1. ��token��timestamp��nonce�������������ֵ�������
			Collections.sort(params, new Comparator<String>() {
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			// 2. �����������ַ���ƴ�ӳ�һ���ַ�������sha1����
			String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
			if (temp.equals(signature)) {
				response.getWriter().write(echostr);
			}
		} else {
			// ���������Ϣ
		}
	}

	public void destroy() {

	}

}
