package org.leafframework.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.mao.RedisDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisDaoTest {
	protected Logger logger = Logger.getLogger(this.getClass());
	private RedisDao redisDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{
						"classpath:org/leafframework/conf/spring-beans.xml",
						"classpath:org/leafframework/conf/spring-mybatis.xml",
						"classpath:org/leafframework/conf/spring-hibernate.xml"});
		redisDao = (RedisDao) context.getBean("redisDao");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("name", "liww");
		hm.put("age", 28);
		hm.put("sex", "男");
		String prefix = "leaf_test";
		redisDao.save(prefix, "230125198003153531", hm, 300);
		redisDao.save(prefix, "230125198003153532", hm, 300);
	}

	@Test
	public void testRead() {
		String prefix = "leaf_test";
		HashMap<String, Object> hm = redisDao
				.read(prefix, "230125198003153531");
		logger.debug(hm);
		hm = redisDao.read(prefix, "230125198003153532");
		logger.debug(hm);
	}

	@Test
	public void testDelete() {
		String prefix = "leaf_test";
		redisDao.delete(prefix, "230125198003153531");
		redisDao.delete(prefix, "230125198003153532");
	}

}
