package org.leafframework.test;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.RETURN;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StaffInfoTest {
	protected Logger logger = Logger.getLogger(this.getClass());

	private Business tradeObj;

	private String performer = "StaffInfoMgr";
	private HashMap<String, Object> pageUri = new HashMap<String, Object>();
	private HashMap<String, Object> pageData = new HashMap<String, Object>();
	private HashMap<String, Object> session = new HashMap<String, Object>();
	private String page = "1";
	private String size = "20";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {
						"classpath:org/leafframework/conf/spring-beans.xml",
						"classpath:org/leafframework/conf/spring-mybatis.xml",
						"classpath:org/leafframework/conf/spring-hibernate.xml" });

		tradeObj = (Business) context.getBean(performer);

		pageUri.put("performer", performer);
		pageUri.put("page", page);
		pageUri.put("size", size);

		HashMap<String, Object> hmContext = new HashMap<String, Object>();
		TMStaff loginBean = new TMStaff();
		loginBean.setId(9988);
		session.put("loginBean", loginBean);
		hmContext.put("pageUri", pageUri);
		hmContext.put("session", session);

		tradeObj.setContext(hmContext);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	//测试员工信息查询
	public void testStaffInfoQuery() throws JAXBException, IOException {
		try{
			pageUri.put("m", "query");
			pageUri.put("f", "staffInfoQuery");
			pageUri.put("p", "");
			
			pageData.put("staffInfoId", "1001");
			
			tradeObj.setInParam(pageData);
			assertEquals(RETURN.SUCCESS, tradeObj.query());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	//测试删除员工
	public void testStaffRemove() throws JAXBException, IOException {
		try{
			pageUri.put("m", "execute");
			pageUri.put("f", "staffRemove");
			pageUri.put("p", "");
			
			pageData.put("id", 1);
			
			tradeObj.setInParam(pageData);
			assertEquals(RETURN.SUCCESS, tradeObj.execute());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	//测试编辑员工信息
	public void testRoleInfoEdit() throws JAXBException, IOException {
		try{
			pageUri.put("m", "execute");
			pageUri.put("f", "staffInfoEdit");
			pageUri.put("p", "");
			
			pageData.put("id", 1);
			pageData.put("staffInfoName", "赵大宝");
			
			tradeObj.setInParam(pageData);
			assertEquals(RETURN.SUCCESS, tradeObj.deal());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
