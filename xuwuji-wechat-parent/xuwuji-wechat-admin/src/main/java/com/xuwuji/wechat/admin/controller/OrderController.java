package com.xuwuji.wechat.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.wechat.admin.model.weidian.Order;
import com.xuwuji.wechat.admin.service.WeiDianOrderService;

@Controller
public class OrderController {
	@Autowired
	WeiDianOrderService service;

	@RequestMapping(value = "/order/get", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Order> getOrder(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws Exception {
		String result = service.getOrderResponse(startDate, endDate);
		List<Order> list = service.parseOrderList(result);
		return (ArrayList<Order>) list;
	}

	@RequestMapping(value = "/order/province", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Integer> groupByProvince(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws Exception {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String input = service.getOrderResponse(startDate, endDate);
		List<Order> list = service.parseOrderList(input);
		for (Order o : list) {
			if (map.get(o.getProvince()) == null) {
				map.put(o.getProvince(), 0);
			}
			map.put(o.getProvince(), map.get(o.getProvince()) + 1);
		}
		return map;
	}

	@RequestMapping(value = "/order/status", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Integer> groupByStatus(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws Exception {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String input = service.getOrderResponse(startDate, endDate);
		List<Order> list = service.parseOrderList(input);
		for (Order o : list) {
			if (o.getStatus() == null) {
				if (map.get("") == null) {
					map.put("", 0);
				}
				map.put("", map.get("") + 1);
			} else {
				if (map.get(o.getStatus()) == null) {
					map.put(o.getStatus(), 0);
				}
				map.put(o.getStatus(), map.get(o.getStatus()) + 1);
			}

		}
		return map;
	}

	@RequestMapping(value = "/order/detail/{order_id}", method = RequestMethod.GET)
	public @ResponseBody Order getOrderDetail(@PathVariable("order_id") String order_id,
			@RequestParam("endDate") String endDate) throws Exception {
		String result = service.getOrderDetail(order_id);
		Order order = service.parseOrderDetail(result);
		return order;
	}

	@RequestMapping(value = "/order/index", method = RequestMethod.GET)
	public ModelAndView orderIndex() {
		ModelAndView model = new ModelAndView();
		model.setViewName("order");
		return model;
	}
}
