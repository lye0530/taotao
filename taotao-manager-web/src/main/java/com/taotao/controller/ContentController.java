package com.taotao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.common.pojo.DataResult;
import com.taotao.common.pojo.TaoTaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;

/**
 * 	内容管理controller
 * @author MLOONG
 *
 */
@RestController
public class ContentController {

	private static final Logger logger = LoggerFactory.getLogger(ContentController.class);
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 	新增内容
	 * @param content 内容对象数据
	 * @return
	 */
	@RequestMapping(value = "/content/save",method = RequestMethod.POST)
	public TaoTaoResult addContent(TbContent content) {
		TaoTaoResult result = contentService.addContent(content);
		return result;
	}
	
	/**
	 * 	获取内容管理列表
	 * @param page 页码
	 * @param rows	每页显示数量
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/content/query/list",method = RequestMethod.GET)
	public DataResult<TbContent> getContentList(@RequestParam(value="page")Integer page,Integer rows,Long categoryId){
		logger.info("入参："+page+"\t\t"+rows+"\t\t"+categoryId);
		return contentService.getContentList(page, rows, categoryId);
	}
	
	/**
	 * 	仅限于测试
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/test/content/query/list",method = RequestMethod.GET)
	public DataResult<TbContent> getContentList(Long categoryId){
		logger.info("入参："+categoryId);
		return contentService.getContentList(categoryId);
	}
	
	/**
	 * 	修改内容信息
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/rest/content/edit",method = RequestMethod.POST)
	public TaoTaoResult updateContent(TbContent content) {
		return contentService.updateContent(content);
	}
	
	/**
	 * 根据id删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/content/delete",method = RequestMethod.POST)
	public TaoTaoResult deleteContent(String ids) {
		return contentService.deleteContent(ids);
	}
}
