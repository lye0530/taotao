package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.common.pojo.DataResult;
import com.taotao.common.pojo.TaoTaoResult;
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
	public DataResult<TbItem> getAllItem(@RequestParam("page")Integer page,@RequestParam("rows")Integer rows) {
		return itemService.getAllItem(page, rows);
	}
	
	/**
	 * 注意：如果请求数据放到请求的body里面，入参必要使用@RequestBody，使用表单或问号传参的方式则可以不加，只要请求参数名和入参名一致即可(单个参数也是如此)，
	 * 不过建议还是给方法入参加上对应的注解，按照规范：对象类型：@RequestBody，此时数据就要加在请求的body里面，单个参数@PathVariable或@RequestParam
	 * 
	 * 添加商品信息
	 * @param item
	 * @param desc
	 * @return
	 */
	@RequestMapping(value = "/item/save",method = RequestMethod.POST)
	public TaoTaoResult addItem(TbItem item,String desc) {
		TaoTaoResult result = itemService.addItem(item, desc);
		return result;
	}
}
