package com.taotao.fastdfs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.Test;

import com.taotao.utils.FastDFSClient;

/**
 * 测试fastdfs
 * @author MLOONG
 *
 */
public class TestFastDFS {

	@Test
	public void uploadFile() {
		try {
			//1.向工程中添加FastDFS的相关依赖
			//2.创建一个FastDFS的配置文件,主要配置tracker跟踪器地址
			//3.加载配置文件
			ClientGlobal.init("D:/Eclipse/workspace2/taotao-manager-web/src/main/resources/rs/client.conf");
			//4.创建一个trackerclient对象
			TrackerClient tracker = new TrackerClient();
			//5.使用trackerclient对象获得trackerserver对象
			TrackerServer trackerServer = tracker.getConnection();
			//6.创建一个storageserver的引用,先给null
			//StorageServer storageServer = null;
			//7.创建一个storageclient对象。需要两个参数trackerserver，storageserver
			//StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			StorageClient storageClient = new StorageClient(trackerServer, null);
			//8.使用storageclient对象上传文件
			String[] strings = storageClient.upload_file("C:/Users/MLOONG/Pictures/Saved Pictures/zcbg.jpg", "jpg", null);
			for (String string : strings) {
				System.out.println(string);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFastDFSClient() {
		try {
			FastDFSClient fastDFSClient = new FastDFSClient("D:/Eclipse/workspace2/taotao-manager-web/src/main/resources/rs/client.conf");
			String path = fastDFSClient.uploadFile("C:/Users/MLOONG/Pictures/Saved Pictures/lyfm.jpg");
			System.out.println(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
