package com.monkey.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.dao.RedisCacheDao;
import com.monkey.service.RedisCacheService;

@Service
public class RedisCacheServiceImpl implements RedisCacheService {
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
	@Autowired
	private RedisCacheDao redisCacheDao;

	@Override
	public boolean putSessionObject(String id, String sessionObject) {
		boolean flag = false;
		if (sessionObject != null && id != null && id.trim().length() > 0) {
			flag = true;
			redisCacheDao.save(id, sessionObject);
			logger.info("数据缓存标志:" + flag);
		} else {
			logger.info("参数含空值或null，缓存失败" + flag);
		}
		return flag;
	}

	@Override
	public boolean deleteSessionObject(String id) {
		boolean flag = false;
		if (id != null && id.trim().length() > 0) {
			redisCacheDao.delete(id);
			flag = true;
			logger.info("缓存清空标志:" + flag);
		} else {
			logger.info("参数含空值或null，清空缓存失败");
		}
		return flag;
	}

	@Override
	public String getSessionObject(String id) {
		String sessionObject = "";
		if (id != null && id.trim().length() > 0) {
			sessionObject = redisCacheDao.get(id);
		} else {
			logger.info("参数含空值或null，获取缓存失败");
		}

		return sessionObject;
	}

}
