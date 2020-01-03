package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面展示controller
 * @author MLOONG
 *
 */
@Controller
public class PageController {
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String showIndex() {
		return "index";
	}
}
