package org.leafframework.test;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.orm.TMMenus;
import org.leafframework.data.dao.orm.TMModules;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBatisTest {
	protected Logger logger = Logger.getLogger(this.getClass());
	private MyBatisDAO mybatisDAO;
	
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
		mybatisDAO = (MyBatisDAO) context.getBean("myBatisDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void insertTmmenus() {
		TMMenus menus=new TMMenus();
		menus.setLvlId("00");
		menus.setName("测试");
		menus.setParentId(99);
		menus.setIcon("test");
		menus.setUpdateStaffId("9870");
		logger.debug(mybatisDAO.insertTmmenus(menus));
	}
	
	@Test
	public void testGetMenus() {
		TMMenus menu = new TMMenus();
		logger.debug(mybatisDAO.getMenus(menu));
	}

	@Test
	public void testGetSysdate() {
		logger.debug(mybatisDAO.getSysdate());
	}

	@Test
	public void testGetSequence() {
		logger.debug(mybatisDAO.getSequence("depart_code"));
	}

	@Test
	public void testGetModules() {
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("staffId","system");
		map.put("menuId",1);
		List<TMModules> modules=(List<TMModules>) mybatisDAO.getModules(map);
		logger.debug(modules);
	}
	
	@Test
	public void testGetTables() {
		List<HashMap<String, Object>> tables=(List<HashMap<String, Object>>) mybatisDAO.getTables("leafdb");
		logger.debug(tables);
	}
	
//	@Test
//	public void testGetColumns() {
//		List<HashMap<String, Object>> columns=(List<HashMap<String, Object>>) mybatisDAO.getColumns("leafdb","t_l_main");
//		logger.debug(columns);
//	}
}
