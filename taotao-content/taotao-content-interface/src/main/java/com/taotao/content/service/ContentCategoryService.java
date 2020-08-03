package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.TaoTaoResult;
import com.taotao.common.pojo.TreeNode;

/**
 * 	内容分类管理服务
 * @author MLOONG
 *
 */
public interface ContentCategoryService {

	/**
	 * 	获取内容管理分类节点列表
	 * @param parentId 父类目id
	 * @return
	 */
	public List<TreeNode> getContentCategoryList(long parentId);
	
	/**
	 * 	新增内容分类  未测试
	 * @param parentId	父类目id
	 * @param name	内容分类名
	 * @return
	 */
	public TaoTaoResult addContentCategory(Long parentId,String name);
	
	/**
	 * 	节点重命名  未测试
	 * @param id
	 * @param rename
	 * @return
	 */
	public TaoTaoResult updateContentCategory(long id,String rename);
	
	/**
	 * 	节点删除  未测试
	 * @param id
	 * @return
	 */
	public TaoTaoResult deleteContentCategory(long id);
}
