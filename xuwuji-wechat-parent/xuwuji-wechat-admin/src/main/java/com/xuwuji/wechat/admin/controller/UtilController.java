package com.xuwuji.wechat.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.wechat.admin.model.Stock;
import com.xuwuji.wechat.admin.service.APIService;
import com.xuwuji.wechat.common.util.TimeUtil;

/**
 * Controller for util service
 * 
 * @author wuxu
 *
 *         Jan 23, 2016
 */

@Controller
public class UtilController {

	@Autowired
	private APIService apiService;

	@RequestMapping(value = "/util/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("util");
	}

	@RequestMapping(value = "util/exchange", method = RequestMethod.GET)
	public @ResponseBody String exchange(@RequestParam("amount") String amount, @RequestParam("source") String source,
			@RequestParam("target") String target) throws IOException, ParseException {
		String result = apiService.exchange(source, target, amount);
		return result;
	}

	@RequestMapping(value = "util/stock", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Stock> stock() throws IOException, ParseException {
		ArrayList<Stock> list = apiService.getStockInfo("2016-01-25", "2016-01-25", "shanghai");
		return list;
	}
}
