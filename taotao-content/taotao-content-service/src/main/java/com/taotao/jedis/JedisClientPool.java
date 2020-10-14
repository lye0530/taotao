package com.taotao.jedis;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientPool implements JedisClient {
	
	@Autowired
	private JedisPool jedisPool;

	@Override
	public String set(String key, String value) {
		//从连接池中获得连接
		Jedis jedis = jedisPool.getResource();
		//使用Jedis操作数据库(方法级别使用，在方法内使用)
		jedis.auth("redis123");
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		//从连接池中获得连接
		Jedis jedis = jedisPool.getResource();
		//使用Jedis操作数据库(方法级别使用，在方法内使用)
		jedis.auth("redis123");
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public Boolean exists(String key) {
		//从连接池中获得连接
		Jedis jedis = jedisPool.getResource();
		//使用Jedis操作数据库(方法级别使用，在方法内使用)
		jedis.auth("redis123");
		Boolean result = jedis.exists(key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, int seconds) {
		//从连接池中获得连接
		Jedis jedis = jedisPool.getResource();
		//使用Jedis操作数据库(方法级别使用，在方法内使用)
		jedis.auth("redis123");
		Long result = jedis.expire(key, seconds);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		//从连接池中获得连接
		Jedis jedis = jedisPool.getResource();
		//使用Jedis操作数据库(方法级别使用，在方法内使用)
		jedis.auth("redis123");
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public Long incr(String key) {
		//从连接池中获得连接
		Jedis jedis = jedisPool.getResource();
		//使用Jedis操作数据库(方法级别使用，在方法内使用)
		jedis.auth("redis123");
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hset(String key, String field, String value) {
		//从连接池中获得连接
		Jedis jedis = jedisPool.getResource();
		//使用Jedis操作数据库(方法级别使用，在方法内使用)
		jedis.auth("redis123");
		Long result = jedis.hset(key, field, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String key, String field) {
		//从连接池中获得连接
		Jedis jedis = jedisPool.getResource();
		//使用Jedis操作数据库(方法级别使用，在方法内使用)
		jedis.auth("redis123");
		String result = jedis.hget(key, field);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String key, String... field) {
		//从连接池中获得连接
		Jedis jedis = jedisPool.getResource();
		//使用Jedis操作数据库(方法级别使用，在方法内使用)
		jedis.auth("redis123");
		Long result = jedis.hdel(key, field);
		jedis.close();
		return result;
	}

}
