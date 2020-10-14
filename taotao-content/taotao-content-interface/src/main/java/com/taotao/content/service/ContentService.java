package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.DataResult;
import com.taotao.common.pojo.TaoTaoResult;
import com.taotao.pojo.TbContent;

/**
 * 	内容管理服务
 * @author MLOONG
 *
 */
public interface ContentService {
	
	/**
	 *	 获取内容管理列表---用于管理系统使用
	 * @param page 页码
	 * @param rows 每页显示数量
	 * @param categoryId 内容分类的id
	 * @return
	 */
	public DataResult<TbContent> getContentList(int page, int rows,long categoryId);
	
	/**
	 * 	仅仅限于测试
	 * @param categoryId
	 * @return
	 */
	public DataResult<TbContent> getContentList(long categoryId);
	
	/**
	 *  获取内容管理列表---用于门户首页使用
	 * @param cid 内容分类的id
	 * @return 
	 */
	public List<TbContent> getContentByCid(long cid);
	/**
	 * 	新增内容信息
	 * @param content
	 * @return
	 */
	public TaoTaoResult addContent(TbContent content);
	
	/**
	 * 	修改内容信息
	 * @param content
	 * @return
	 */
	public TaoTaoResult updateContent(TbContent content);
	
	/**
	 * 删除内容---未测试
	 * @param ids
	 * @return
	 */
	public TaoTaoResult deleteContent(String ids) ;
}
