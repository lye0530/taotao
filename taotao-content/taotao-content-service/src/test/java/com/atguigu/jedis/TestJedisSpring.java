package com.atguigu.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.jedis.JedisClientPool;

public class TestJedisSpring {

	/**
	 * 	使用redis单机版
	 */
	@Test
	public void t1() {
		//初始化spring容器
		ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		//从容器中获得jedisclient对象
		JedisClientPool jClientPool = applicationContext.getBean(JedisClientPool.class);
		//使用jedisclient对象，操作redis
		jClientPool.set("jedisClient", "mytest");
		String result = jClientPool.get("jedisClient");
		System.out.println(result);
	}
}
