package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面展示控制器
 * @author MLOONG
 *
 */
@Controller
public class PageController {
	
	/**
	 * 展示首页
	 * @return
	 */
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String showIndex() {
		return "index";
	}
	
	/**
	 * 根据请求的路径变量跳转到对应的视图页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}
