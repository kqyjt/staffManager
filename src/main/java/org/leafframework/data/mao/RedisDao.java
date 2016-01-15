package org.leafframework.data.mao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.leafframework.data.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao implements IDao{

	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	public void save(String prefix,final String key, HashMap<String, Object> param,long timeout) {
		HashOperations<Serializable, String, HashMap<String, Object>> opsForHash = redisTemplate
				.opsForHash();
		opsForHash.put(prefix, key, param);
		redisTemplate.expire(prefix,timeout,TimeUnit.SECONDS);
	}

	public HashMap<String, Object> read(String prefix,final String key) {
		return (HashMap<String, Object>) redisTemplate.opsForHash().get(
				prefix, key);
	}

	public void delete(String prefix,final String key) {
		HashOperations<Serializable, String, HashMap<String, Object>> opsForHash = redisTemplate
				.opsForHash();
		opsForHash.delete(prefix, key);
	}
}
