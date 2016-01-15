package org.leafframework.test;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.leafframework.data.dao.orm.TMMenus;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.PojoUtil;
import org.leafframework.util.RETURN;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

public class BusinessTest {

	private Business tradeObj;
	protected Logger logger = Logger.getLogger(this.getClass());
	private String performer = "TMMenusMgr";
	private HashMap<String, Object> pageUri = new HashMap<String, Object>();
	private HashMap<String, Object> pageData = new HashMap<String, Object>();
	private HashMap<String, Object> session = new HashMap<String, Object>();
	private String page = "1";
	private String size = "20";

	private String jdateformat = "yyyy-MM-dd HH:mm:ss";
	private SimpleDateFormat sdf = new SimpleDateFormat(jdateformat);

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
						"classpath:org/leafframework/conf/spring-mybatis.xml",
						"classpath:org/leafframework/conf/spring-hibernate.xml",
						"classpath:org/leafframework/conf/spring-beans.xml"});

		tradeObj = (Business) context.getBean(performer);

		pageUri.put("performer", performer);
		pageUri.put("page", page);
		pageUri.put("size", size);

		HashMap<String, Object> hmContext = new HashMap<String, Object>();
		TMStaff loginStaff =new TMStaff();
		loginStaff.setId(9988);
		session.put("loginStaff", loginStaff);
		hmContext.put("pageUri", pageUri);
		hmContext.put("session", session);

		tradeObj.setContext(hmContext);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInit() {
		try {
			pageUri.put("m", "init");
			pageUri.put("f", "");
			pageUri.put("p", "");

			pageData.put("stuff_id", "system");
			pageData.put("password", "qwer1234");
			String temp = "menu_menu_id";

			temp = temp.replaceFirst("menu", "");

			assertEquals(RETURN.SUCCESS, tradeObj.pageInit());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		try {
			pageUri.put("m", "query");
			pageUri.put("f", "");
			pageUri.put("p", "");

			pageData.put("id", 111);
			pageData.put("name", "qwer1234");
			
			tradeObj.setInParam(pageData);
			
			assertEquals(RETURN.SUCCESS, tradeObj.queryByCond());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeal() {
		try {
			pageUri.put("m", "execute");
			pageUri.put("f", "update");
			pageUri.put("p", "");

			TMMenus menus = new TMMenus();
			menus.setLvlId("00");
			menus.setName("测试");
			menus.setParentId(99);
			menus.setUpdateStaffId("9870");
			menus.setIcon("/menux/menu.icon");
			menus.setUpdateTime(sdf.parse(tradeObj.getSysdate()));

			tradeObj.setInParam((HashMap<String, Object>) PojoUtil
					.getMapFromPojo(menus));

			assertEquals(RETURN.SUCCESS, tradeObj.deal());
			
		} catch (DataAccessException e) {
			logger.debug(e.getMessage());
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}
}
