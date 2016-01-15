package org.leafframework.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.leafframework.data.mao.RedisDao;
import org.leafframework.mvc.service.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;

import redis.clients.jedis.Jedis;

public class CommonFunctions {
	public synchronized static HashMap<String, Object> getSession(
			String sessionId) {
		Jedis jedis = new Jedis("121.40.126.222", 6379);
		jedis.auth("qwer1234");
		String hm = jedis.get("session_manager");
		return null;
	}

	public synchronized static String getStaffName(String staffId) {
		return staffId + "Hello";
	}
	public synchronized static HashMap<?, ?> getSessionContext(String authToken) {
		ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
		RedisDao redisDao;
		HashMap<?, ?> sessionContext = null;
		try {
			redisDao = (RedisDao) ctx.getBean("redisDao");
			sessionContext = redisDao.read("session_manager", authToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionContext;
	}
	
	public synchronized static ArrayList<?> getMenus(String authToken) {
		HashMap<?, ?> sessionContext=(HashMap<?, ?>) getSessionContext(authToken);
		return (ArrayList<?>) (sessionContext==null?null:sessionContext.get("TMMenusList"));
	}
	
	public synchronized static ArrayList<?> getModules(String authToken) {
		HashMap<?, ?> sessionContext=(HashMap<?, ?>) getSessionContext(authToken);
		return (ArrayList<?>) (sessionContext==null?null:sessionContext.get("TMModulesList"));
	}
}
