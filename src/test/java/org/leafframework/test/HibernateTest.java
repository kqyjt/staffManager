package org.leafframework.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.leafframework.data.dao.orm.TMMenus;
import org.leafframework.data.dao.orm.TMMenusHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

public class HibernateTest {

	private TMMenusHome tMMenusHome;
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:org/leafframework/conf/spring-beans.xml",
						"classpath:org/leafframework/conf/spring-mybatis.xml",
						"classpath:org/leafframework/conf/spring-hibernate.xml"});
		tMMenusHome = (TMMenusHome) context.getBean("TMMenusHome");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		TMMenus detachedInstance = new TMMenus();
		detachedInstance.setId(1);
		detachedInstance.setLvlId("13");
		detachedInstance.setName("测试1");
		detachedInstance.setParentId(0);
		detachedInstance.setUpdateStaffId("1003");		
		/*logger.debug(tMMenusHome.merge(detachedInstance));*/
	}

}
