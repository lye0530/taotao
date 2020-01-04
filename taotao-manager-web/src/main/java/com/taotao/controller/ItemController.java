package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.common.pojo.DataResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 商品管理控制器
 * @author MLOONG
 *
 */
@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 根据商品id获取商品信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/item/{itemId}",method=RequestMethod.GET)
	public TbItem geItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getTbItemById(itemId);
		return tbItem;
	}
	
	/**
	 * 获取所有商品信息 
	 * @param page 页码
	 * @param rows 每页显示数量
	 * @return
	 */
	@RequestMapping(value = "/item/list",method = RequestMethod.GET)
	public DataResult<TbItem> getAllItem(Integer page,Integer rows) {
		return itemService.getAllItem(page, rows);
	}
}
