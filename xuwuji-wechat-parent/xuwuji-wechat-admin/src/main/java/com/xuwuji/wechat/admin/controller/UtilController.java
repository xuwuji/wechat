package com.xuwuji.wechat.admin.controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.wechat.admin.service.APIService;

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
}
