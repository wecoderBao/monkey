package com.monkey.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monkey.service.RedisCacheService;

@Controller
public class ViewController {
	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

	@Autowired
	private RedisCacheService redisCacheService;

	@RequestMapping("/view")
	public String view(Model model) {
		logger.info("你已通过springMVC进入controller方法");
		logger.info("-------存储数据---------");
		redisCacheService.putSessionObject("123", "wwwwwww");
		logger.info("-------读取数据---------");
		redisCacheService.getSessionObject("123");
		logger.info("-------删除数据---------");
		redisCacheService.deleteSessionObject("123");
		logger.info("-------删除后再次读取数据---------");
		redisCacheService.getSessionObject("123");
		model.addAttribute("redis","redis");
		return "index";
	}
}
