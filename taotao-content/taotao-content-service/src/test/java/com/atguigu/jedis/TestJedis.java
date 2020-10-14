package com.atguigu.jedis;

import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * 测试Jedis
 * @author MLOONG
 *
 */
public class TestJedis {

	@Test
	public void testJedis() {
		//创建一个Jedis对象，需要指定服务的ip和端口号
		Jedis jedis = new Jedis("192.168.131.136", 6379);
		jedis.auth("redis123");
		//直接操作
		jedis.set("jedis-key", "1234");
		String result = jedis.get("jedis-key");
		System.out.println(result);
		//关闭
		jedis.close();
	}
	
	@Test
	public void testJedisPool() {
		//创建数据库连接池对象(单例),需要指定两个参数服务的ip和端口号
		JedisPool jedisPool = new JedisPool("192.168.131.136", 6379);
		//从连接池中获得连接
		Jedis jedis = jedisPool.getResource();
		//使用Jedis操作数据库(方法级别使用，在方法内使用)
		jedis.auth("redis123");
		String result = jedis.get("jedis-key");
		System.out.println(result);
		//一定要关闭Jedis连接
		jedis.close();
		//系统结束时关闭连接池
		jedisPool.close();
	}
	
	@Test
	public void testJedisCluster() throws IOException {
		//创建一个JedisCluster对象，构造参数-->set类型，集合中每个元素是HostAndPort类型
		HashSet<HostAndPort> nodes = new HashSet<>();
		//向集合中添加redis节点
		nodes.add(new HostAndPort("192.168.131.136", 7001));
		nodes.add(new HostAndPort("192.168.131.136", 7002));
		nodes.add(new HostAndPort("192.168.131.136", 7003));
		nodes.add(new HostAndPort("192.168.131.136", 7004));
		nodes.add(new HostAndPort("192.168.131.136", 7005));
		nodes.add(new HostAndPort("192.168.131.136", 7006));
		
		JedisCluster jCluster = new JedisCluster(nodes);
		//直接使用JedisCluster操作redis，自带连接池。JedisCluster对象可以是单例的
		jCluster.set("cluster-test", "hello1Jedis2cluster");
		String reslut = jCluster.get("cluster-test");
		System.out.println("使用redis集群存取数据："+reslut);
		//系统关闭前关闭JedisCluster
		jCluster.close();
	}
}
