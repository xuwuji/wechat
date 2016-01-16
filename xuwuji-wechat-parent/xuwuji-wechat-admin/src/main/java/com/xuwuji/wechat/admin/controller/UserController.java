package com.xuwuji.wechat.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.wechat.admin.dao.UserService;
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

	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public ModelAndView userIndex() {
		ModelAndView model = new ModelAndView();
		model.setViewName("user");
		return model;
	}

}
