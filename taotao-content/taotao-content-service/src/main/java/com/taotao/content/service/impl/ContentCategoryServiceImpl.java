package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaoTaoResult;
import com.taotao.common.pojo.TreeNode;
import com.taotao.content.service.ContentCategoryService;
//import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

import cn.hutool.core.collection.CollUtil;

/**
 * 	内容分类管理service
 * @author MLOONG
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<TreeNode> getContentCategoryList(long parentId) {
		//根据parentId查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		//创建查询条件
		Criteria criteria =example.createCriteria();
		//设置parentId
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		//构建返回对象
		List<TreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			TreeNode node = new TreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			//判断是否是父节点，如果是则说明有子节点所以状态应为closed，如果不是则说明没有子节点状态应为open
			//此逻辑不太妥当，如果子节点下还有子节点呢？该逻辑判断感觉只适用于只有两层目录的树形结构
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			//添加到节点列表
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaoTaoResult addContentCategory(Long parentId, String name) {
		//创建一个pojo对象
		TbContentCategory contentCategory = new TbContentCategory();
		//补全对象的属性
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		//状态。可选值：1(正常)，2(删除)
		contentCategory.setStatus(1);
		//排序默认为1
		contentCategory.setSortOrder(1);
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//插入到数据库
		contentCategoryMapper.insert(contentCategory);
		//判断父节点状态
		TbContentCategory parenTbContentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		//如果父节点为false取反
		if (!parenTbContentCategory.getIsParent()) {
			//如果父节点为叶子节点，应该改为父节点,，因为我在parentId这个节点下，新增了子节点，如果parentId这个节点本身是父节点，不用任何改动，否则就要改为父节点
			parenTbContentCategory.setIsParent(true);
			//更新父节点信息
			contentCategoryMapper.updateByPrimaryKey(parenTbContentCategory);
		}
		//返回结果
		return TaoTaoResult.ok(contentCategory);
	}

	@Override
	public TaoTaoResult updateContentCategory(long id, String rename) {
		//先查询
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		//设置新名称
		contentCategory.setName(rename);
		//然后修改
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		return TaoTaoResult.ok();
	}

	@Override
	public TaoTaoResult deleteContentCategory(long id) {
		//先查询,下面逻辑是否可以优化!!!!!!
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		//判断要删除的是否是父节点
		if(contentCategory.getIsParent()) {
			//如果要删除的是父节点，则删除旗下关联子节点
			//先构建查询
			TbContentCategoryExample tExample = new TbContentCategoryExample();
			Criteria criteria = tExample.createCriteria();
			//设置parentId
			criteria.andParentIdEqualTo(id);
			//下面逻辑应该优化成一个sql去执行，不过后台管理系统而且还是练手的示例就没有去优化了，后续有时间再优化
			//删除旗下子节点
			contentCategoryMapper.deleteByExample(tExample);
			//删除父节点本身
			contentCategoryMapper.deleteByPrimaryKey(id);
			
		}else {
			//如果要删除的不是父节点直接删除
			contentCategoryMapper.deleteByPrimaryKey(id);
			//删除后判断父节点下是否还有其他子节点(如果有，不变isparent，如果没有就要把isparent变为false)
			//先构建查询
			TbContentCategoryExample tExample = new TbContentCategoryExample();
			Criteria criteria = tExample.createCriteria();
			//设置parentId
			criteria.andParentIdEqualTo(contentCategory.getParentId());
			//根据parentId查询是否还有子节点列表
			List<TbContentCategory> list = contentCategoryMapper.selectByExample(tExample);
			boolean flag = CollUtil.isEmpty(list);
			//如果为空，则说明父节点下没有其他子节点了，要改变isparent属性
			if (flag) {
				TbContentCategory parentContentCategory = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
				parentContentCategory.setIsParent(false);
				contentCategoryMapper.updateByPrimaryKey(parentContentCategory);
			}
		}
		return TaoTaoResult.ok();
	}

}
