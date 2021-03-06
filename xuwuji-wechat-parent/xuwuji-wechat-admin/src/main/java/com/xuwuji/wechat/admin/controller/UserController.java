package com.xuwuji.wechat.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.wechat.admin.service.ProductService;
import com.xuwuji.wechat.admin.service.UserService;
import com.xuwuji.wechat.common.model.UserSubscribeEvent;
import com.xuwuji.wechat.common.util.TimeUtil;

@Controller
public class UserController {

	@Autowired
	UserService service;

	@RequestMapping(value = "/user/trend", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Integer> userTrend() {
		System.out.println("here");
		ArrayList<UserSubscribeEvent> list = (ArrayList<UserSubscribeEvent>) service.getAllSubscribeUser();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (UserSubscribeEvent e : list) {
			System.out.println(e.toString());
			String time = TimeUtil.converSimpleUnix(e.getCreateTime());
			if (map.get(time) == null) {
				map.put(time, 0);
			}
			map.put(time, map.get(time) + 1);

		}
		return map;
	}

	@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> userSearchCount() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list = service.getUserTextMessageCount();
		HashMap<String, Object> result = new HashMap<String, Object>();
		for (HashMap<String, Object> map : list) {
			String name = "";
			Integer count = 0;
			for (Entry<String, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
				if (entry.getKey().equals("content")) {
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

	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public ModelAndView userIndex() {
		ModelAndView model = new ModelAndView();
		model.setViewName("user");
		return model;
	}

}
