package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String showIndex() {
		//不加@ResponseBody注解，spring会根据返回的字符串去找对应的逻辑视图
		return "index";
	}
}
