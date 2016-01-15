package org.leafframework.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.leafframework.mvc.service.ui.mail.EmailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailTest {
	private EmailService emailService;

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
		emailService = (EmailService) context.getBean("emailService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendEmail() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", "zhangsan");
		param.put("password", "lisi");
		
		//文件正文包含图片
/*		param.put("cid:welcomePic1", "C:/Users/hh/Desktop/menu_bg.gif");
		param.put("cid:welcomePic2", "C:/Users/hh/Desktop/menu_bg.gif");*/

		emailService.setFrom("liww2@asiainfo.com");
		emailService.setTo("bruce.lee.soft@gmail.com");
		emailService.setTitle("天朝上品：注册成功提示邮件");
		emailService.addAttachment("C:/Users/hh/Desktop/test.wav");
		emailService.addAttachment("C:/Users/hh/Desktop/test.wav");
		emailService.addAttachment("C:/Users/hh/Desktop/test.wav");
		emailService.setTemplate(param,"mailTest.ftl");
		emailService.sendEmail();
	}
}
