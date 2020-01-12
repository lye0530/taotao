package com.taotao.pagehelper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
//import com.taotao.pojo.TbItemExample.Criteria;

/**
 * 测试PageHelper
 * @author MLOONG
 *
 */
public class TestPageHelper {
	
	@Test
	public void testPageHelper() throws Exception{
		//初始化spring容器
		ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbItemMapper itemMapper = appContext.getBean(TbItemMapper.class);
		//******1.先在mybatis的全局配置文件中配置分页插件******
		//******2.在执行查询之前配置分页条件，使用pagehelper的静态方法******
		PageHelper.startPage(1, 10);
		//******3.执行查询，pagehelper会根据分页信息取数据库查询数据******
		//example只是一个分装了查询条件的对象
		TbItemExample example = new TbItemExample();
		//创建条件对象
//		Criteria criteria=example.createCriteria();
//		criteria.andTitleEqualTo("");
		List<TbItem> list =itemMapper.selectByExample(example);
		//******4.取分页信息，使用pageinfo对象获取信息******
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println("总记录数："+pageInfo.getTotal());
		System.out.println("总页数："+pageInfo.getPages());
		System.err.println("返回的记录数："+list.size());
	}
}
