package com.taotao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.utils.FastDFSClient;

/**
 * 图片上传控制器
 * @author MLOONG
 *
 */
@RestController
public class PictureController {
	
	@Value("${IMAGE_SERVER_HOST}")
	private String HOST;
	
	@RequestMapping(value = "/pic/upload",method = RequestMethod.POST)
	public Map<String, Object> picUpLoad(@RequestPart("uploadFile") MultipartFile uploadFile) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//1.接收上传的文件
			//文件上传时的名字
			String sname = uploadFile.getOriginalFilename();
			String extName = sname.substring(sname.lastIndexOf(".")+1);
			//2.上传到图片服务器fastdfs
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:rs/client.conf");
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			url = HOST+url;
			//3.响应上传图片的url
			result.put("error", 0);
			result.put("url", url);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", 1);
			result.put("url", "图片上传失败");
			return result;
		}
		
	}

}
