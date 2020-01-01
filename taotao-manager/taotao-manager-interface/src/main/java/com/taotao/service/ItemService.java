package com.taotao.service;

import com.taotao.pojo.TbItem;

public interface ItemService {
	/**
	 * 根据商品id获取商品信息
	 * @param itemId
	 * @return
	 */
	public TbItem getTbItemById(long itemId);
}
