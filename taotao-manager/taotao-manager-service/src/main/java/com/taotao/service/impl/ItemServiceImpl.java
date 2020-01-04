package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.DataResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
/**
 * 商品管理服务
 * @author MLOONG
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	/**
	 * 根据商品id获取商品信息
	 */
	@Override
	public TbItem getTbItemById(long itemId) {
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		return tbItem;
	}

	/**
	 * 获取所有商品信息 
	 */
	@Override
	public DataResult<TbItem> getAllItem(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//获取查询结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//构建返回对象
		DataResult<TbItem> dResult = new DataResult<>();
		dResult.setTotal(pageInfo.getTotal());
		dResult.setRows(list);
		return dResult;
	}

}
