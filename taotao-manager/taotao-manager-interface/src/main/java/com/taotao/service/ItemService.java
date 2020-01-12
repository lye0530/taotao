package com.taotao.service;

import com.taotao.common.pojo.DataResult;
import com.taotao.common.pojo.TaoTaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	/**
	 * 根据商品id获取商品信息
	 * @param itemId 商品id
	 * @return
	 */
	public TbItem getTbItemById(long itemId);
	
	/**
	 * 获取所有商品信息 
	 * @param page 页码
	 * @param rows 每页显示数量
	 * @return
	 */
	public DataResult<TbItem> getAllItem(int page,int rows);
	
	/**
	 * 添加商品信息
	 * @param item 商品信息实体对象
	 * @param desc 描述
	 * @return
	 */
	public TaoTaoResult addItem(TbItem item,String desc);
}
