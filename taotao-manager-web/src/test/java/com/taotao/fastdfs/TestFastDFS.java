package com.taotao.fastdfs;

public class TestFastDFS {

	public void uploadFile() {
		//1.向工程中添加FastDFS的相关依赖
		//2.创建一个FastDFS的配置文件,主要配置tracker跟踪器地址
		//3.加载配置文件
		//4.创建一个trackerclient对象
		//5.使用trackerclient对象获得trackerserver对象
		//6.创建一个storageserver的引用,先给null
		//7.创建一个storageclient对象。需要两个参数trackerserver，storageserver
		//8.使用storageclient对象上传文件
	}
}
