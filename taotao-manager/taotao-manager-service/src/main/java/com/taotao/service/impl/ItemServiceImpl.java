package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.DataResult;
import com.taotao.common.pojo.TaoTaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

import cn.hutool.core.util.IdUtil;
/**
 * 商品管理服务
 * @author MLOONG
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
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

	/**
	 * 添加商品信息
	 */
	@Override
	public TaoTaoResult addItem(TbItem item, String desc) {
		//生成商品id(生成id的算法也仅仅只能写写测试案例，在生产中并发过大还是可能会造成id重复)
		long itemId = IDUtils.genItemId();
		//使用糊涂工具包的id生成类，详细参考https://hutool.cn/docs/#/core/工具类/唯一ID工具-IdUtil
		//String uuid = IdUtil.randomUUID();
		//补全item的属性
		item.setId(itemId);
		//商品状态:1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//向商品表插入数据
		itemMapper.insert(item);
		//创建一个商品描述表对应的pojo
		TbItemDesc itemDesc = new TbItemDesc();
		//补全pojo的属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		//向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		return TaoTaoResult.ok();
	}

}
