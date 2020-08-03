package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.common.pojo.TaoTaoResult;
import com.taotao.common.pojo.TreeNode;
import com.taotao.content.service.ContentCategoryService;

/**
 * 	内容分类管理controller,下面除展示列表数据之外的所有请求都未测试
 * @author MLOONG
 *
 */
@RestController
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping(value = "/content/category/list",method = RequestMethod.GET)
	public List<TreeNode> getContentCategoryList(@RequestParam(value = "id",defaultValue = "0")Long parentId){
		List<TreeNode> resultList = contentCategoryService.getContentCategoryList(parentId);
		return resultList;
	}
	
	@RequestMapping(value = "/content/category/create",method = RequestMethod.POST)
	public TaoTaoResult addContentCategory(Long parentId,String name) {
		TaoTaoResult result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}
	
	@RequestMapping(value = "/content/category/update",method = RequestMethod.POST)
	public TaoTaoResult updateContentCategory(long id, String name) {
		TaoTaoResult result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}
	
	@RequestMapping(value = "/content/category/delete",method = RequestMethod.POST)
	public TaoTaoResult deleteContentCategory(long id) {
		TaoTaoResult result = contentCategoryService.deleteContentCategory(id);
		return result;
	}
}
