package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.AdvertisementDTO;

/**
 * 未测试
 * @author MLOONG
 *
 */
@Controller
public class IndexController {

	@Value("${AD1_CATEGORY_ID}")
	private long categoryId;
	
	@Value("${AD1_WIDTH}")
	private Integer width;
	
	@Value("${AD1_WIDTH_B}")
	private Integer widthB;
	
	@Value("${AD1_HEIGHT}")
	private Integer height;
	
	@Value("${AD1_HEIGHT_B}")
	private Integer heightB;
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		//根据cid查询轮播图内容列表
		List<TbContent> cidList = contentService.getContentByCid(categoryId); 
		//把列表转换为AdvertisementDTO
		List<AdvertisementDTO> adList = new ArrayList<>();
		for (TbContent tbContent : cidList) {
			AdvertisementDTO ad = new AdvertisementDTO();
			ad.setAlt(tbContent.getTitle());
			ad.setHeight(height);
			ad.setHeightB(heightB);
			ad.setWidth(width);
			ad.setWidthB(widthB);
			ad.setSrc(tbContent.getPic());
			ad.setSrcB(tbContent.getPic2());
			ad.setHref(tbContent.getUrl());
			//添加到数据列表中
			adList.add(ad);
		}
		//把列表转换为json数据
		String ad1JsonString = JsonUtils.objectToJson(adList);
		//把json数据传到给页面
		//不加@ResponseBody注解，spring会根据返回的字符串去找对应的逻辑视图
		model.addAttribute("ad1", ad1JsonString);
		return "index";
	}
}
