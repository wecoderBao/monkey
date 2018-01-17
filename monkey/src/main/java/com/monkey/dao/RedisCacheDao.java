package com.monkey.dao;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisCacheDao {

	private static final Logger logger = LoggerFactory.getLogger(RedisCacheDao.class);
	
	@Resource
	protected RedisTemplate<Serializable, Serializable> redisTemplate;
	/**
	 * redis中元素的保存时间
	 */
	private static int expireTime = 3600 * 10;
	
	/**
	 * 从redis中删除元素
	 * @param key
	 */
	public void delete(String key) {
		logger.info("redis删除key: "+key);
		redisTemplate.delete(key);
	}
	/**
	 * 保存元素
	 * @param key
	 * @param value
	 */
	public void save(String key, String value) {
		logger.info("redis save:"+key+"="+value);
		redisTemplate.opsForValue().set(key, value,expireTime, TimeUnit.SECONDS);
	}
	
	/**
	 * 读取元素
	 * @param key
	 * @return
	 */
	public String get(String key) {
		logger.info("redis get:"+key);
		return (String)redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 更新元素，key不存在则保存
	 * @param key
	 * @param value
	 */
	public void update(String key, String value) {
		logger.info("redis update:"+key+"="+value);
		if(!redisTemplate.hasKey(key)) {
			redisTemplate.opsForValue().set(key, value);
		}
		redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	}
	
}
