package com.monkey.controller.app.util;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.monkey.common.constant.UserServiceConstant;
import com.monkey.common.util.CookieUtil;

/**
 * 获取用户id工具类
 * @author sunbao
 *
 */
@Component
public class UserUtil {
	
	private static RedisTemplate<String, Long> redisTemplateStatic;

    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    /**
     * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Serclet的init()方法。
     * 被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
     */
    @PostConstruct
    public void init() {
        redisTemplateStatic = redisTemplate;
    }


    /**
     * 获取当前登录用户的用户id，没有登录返回null
     *
     * @param request request对象
     * @return userId
     */
    public static Long getLoginUserId(HttpServletRequest request) {
        //获取cookie中存储的sessionid
        String sessionId = CookieUtil.getCookie(request, UserServiceConstant.COOKIE_KEY);
        if (sessionId == null) {
            return null;
        }
        //sessionId为空未登录
        return redisTemplateStatic.opsForValue().get(sessionId);
    }

}
