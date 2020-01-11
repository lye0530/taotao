package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.TreeNode;

public interface ItemCatService {

	/**
	 * 根据商品类目的父类id获取商品类目
	 * @param parentId 商品类目父类id
	 * @return
	 */
	public List<TreeNode> getItemCatList(long parentId);
}
