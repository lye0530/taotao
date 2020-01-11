package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品类目信息管理
 * @author MLOONG
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<TreeNode> getItemCatList(long parentId) {
		//根据父节点id查询子节点列表
		TbItemCatExample example = new TbItemCatExample();
		//创建查询条件
		Criteria criteria = example.createCriteria();
		//设置parentId
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//构建返回对象
		List<TreeNode> resultList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			TreeNode node = new TreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			//判断是否是父节点，如果是则说明有子节点所以状态应为closed，如果不是则说明没有子节点状态应为open
			//此逻辑不太妥当，如果子节点下还有子节点呢？该逻辑判断感觉只适用于只有两层目录的树形结构
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加到节点列表
			resultList.add(node);
		}
		return resultList;
	}

}
