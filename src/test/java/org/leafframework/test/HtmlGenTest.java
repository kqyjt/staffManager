package org.leafframework.test;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.leafframework.mvc.service.ui.mail.EmailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HtmlGenTest {
	
	private FreeMarkerConfigurer freeMarkerConfigurer;

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
						"classpath:org/leafframework/conf/spring-hibernate.xml",
						"classpath:org/leafframework/conf/spring-mail.xml"});
		freeMarkerConfigurer = (FreeMarkerConfigurer) context.getBean("freeMarker");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void HtmlGenTest() throws IOException, TemplateException {
		Template template = freeMarkerConfigurer.getConfiguration()
				.getTemplate("pageTest.ftl");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", "zhangsan");
		param.put("password", "lisi");
		String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(
				template, param);
		System.out.println(htmlText);
	}
}
