package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.common.pojo.TreeNode;
import com.taotao.service.ItemCatService;

/**
 * 商品类目管理器
 * @author MLOONG
 *
 */
@RestController
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;

	/**
	 * 根据商品类目的父类id获取商品类目
	 * @param parentId 商品类目父类id
	 * @return
	 */
	@RequestMapping(value = "/item/cat/list",method = RequestMethod.POST)
	public List<TreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId){
		List<TreeNode> resultList = itemCatService.getItemCatList(parentId);
		return resultList;
	}
}
