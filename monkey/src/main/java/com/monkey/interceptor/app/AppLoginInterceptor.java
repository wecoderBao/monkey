package com.monkey.interceptor.app;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.monkey.common.annotation.Authorization;
import com.monkey.common.constant.UserServiceConstant;
import com.monkey.common.enums.ResultEnum;
import com.monkey.common.util.CookieUtil;
import com.monkey.common.util.ResultUtil;

/**
 * 登录拦截器对于需要登录的操作验证用户是否登录
 * @author sunbao
 *
 */
public class AppLoginInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private RedisTemplate<String, Long> redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		Authorization annotation = method.getAnnotation(Authorization.class);
		// 没有登录认证注解的不需要登录
		if (annotation == null) {
			return true;
		}
		// 获取cookie中存储的sessionid
		String sessionId = CookieUtil.getCookie(request, "cookieKey");
		// sessionId为空未登录
		if (StringUtils.isEmpty(sessionId)) {
			byte[] bytes = JSON.toJSONString(ResultUtil.error(ResultEnum.UNLOGIN)).getBytes();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json;charset=UTF-8");
			response.setContentLength(bytes.length);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(bytes);
			outputStream.flush();
			return false;
		}
		Long userId = redisTemplate.opsForValue().get(sessionId);
		// 缓存失效未登录
		if (userId == null) {
			byte[] bytes = JSON.toJSONString(ResultUtil.error(ResultEnum.UNLOGIN)).getBytes();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json;charset=UTF-8");
			response.setContentLength(bytes.length);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(bytes);
			outputStream.flush();
			return false;
		}
		// 认证后刷新缓存
		redisTemplate.expire(sessionId, UserServiceConstant.EXPIRE_TIME, TimeUnit.SECONDS);
		request.setAttribute("userId", userId);
		return true;
	}
}
