package com.taotao.content.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.DataResult;
import com.taotao.common.pojo.TaoTaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;

/**
 * 	内容管理服务实现类
 * @author MLOONG
 *
 */
@Service
public class ContentServiceImpl implements ContentService {
	
	private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);
	
	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public TaoTaoResult addContent(TbContent content) {
		//补全pojo属性
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入到内容表
		contentMapper.insert(content);
		return TaoTaoResult.ok();
	}

	@Override
	public DataResult<TbContent> getContentList(int page, int rows,long categoryId) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//构成查询条件---有问题代码
		//TbContentExample example = new TbContentExample();
		//Criteria criteria = example.createCriteria();
		//criteria.andCategoryIdEqualTo(categoryId);
		//List<TbContent> list = contentMapper.selectByExample(example);
		List<TbContent> list = contentMapper.selectByCategoryId(categoryId);
		//获取查询结果
		PageInfo<TbContent> info = new PageInfo<>(list);
		//构建返回对象
		DataResult<TbContent> dResult = new DataResult<>();
		dResult.setTotal(info.getTotal());
		dResult.setRows(list);
		return dResult;
	}

	/**
	 * 	仅仅限于测试,使用Criteria有问题
	 */
	@Override
	public DataResult<TbContent> getContentList(long categoryId) {
		//构成查询条件
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		//设置categoryId
		criteria.andCategoryIdEqualTo(categoryId);
		//根据内容分类id获取数据
		List<TbContent> list = contentMapper.selectByExample(example);
		//构建返回对象
		DataResult<TbContent> dResult = new DataResult<>();
		dResult.setTotal(list.size());
		dResult.setRows(list);
		return dResult;
	}
	
	@Override
	public List<TbContent> getContentByCid(long cid) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExample(example);
		return list;
	}
	
	@Override
	public TaoTaoResult updateContent(TbContent content) {
		contentMapper.updateByPrimaryKey(content);
		return TaoTaoResult.ok();
	}

	@Override
	public TaoTaoResult deleteContent(String ids) {
		//id字符串拆分成数组然后转换为集合
		//List<String> idsL = Arrays.asList(ids.split(","));
		String[] idl =ids.split(",");
		contentMapper.deleteByIds(idl);
		return TaoTaoResult.ok();
	}

}
