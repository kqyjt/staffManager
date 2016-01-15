package org.leafframework.test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.util.FileUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class FileUtilTest {

	private MyBatisDAO mybatisDAO;

	protected Logger logger = Logger.getLogger(this.getClass());
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
	public final void testReadExcelFile() throws IOException {
		try {
			List<HashMap<String, Object>> ret= FileUtil.readExcelFile(FileUtil.downloadPath + "/TMStaff_template.xlsx",0,0);
			logger.debug(ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public final void testWriteExcelFile() throws IOException {
		try {
			List<HashMap<String, Object>> hmTableRresult=(List<HashMap<String, Object>>) mybatisDAO.getTables("leafdb");
			Iterator<HashMap<String, Object>> itr = hmTableRresult.iterator();
			while (itr.hasNext()) {
				HashMap<String, Object> hmRow = itr.next();
				String tableName=(String) hmRow.get("table_name");
				String initTableName=(String) hmRow.get("initcap_table_name");
				
				List<HashMap<String, Object>> hmColumnRresult=(List<HashMap<String, Object>>) mybatisDAO.getColumns("leafdb","t_l_main");
				Iterator<HashMap<String, Object>>  itrColumn = hmColumnRresult.iterator();
				LinkedHashMap<String, Object> hmHeader=new LinkedHashMap<String, Object>();
				while (itrColumn.hasNext()) {
					HashMap<String, Object> hmColumn = itrColumn.next();
					String columnName=(String) hmColumn.get("column_name");
					String columnComent=(String) hmColumn.get("column_comment");
					hmHeader.put(columnName, columnComent);
				}
				List<LinkedHashMap<String, Object>> listData= new ArrayList<LinkedHashMap<String, Object>>();
				listData.add(hmHeader);
				FileUtil.writeExcelFile(listData,FileUtil.templatePath + initTableName+"_template.xlsx");
				logger.debug("生成完成模板本件："+initTableName+"_template.xlsx");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public final void testUnderlineToCamel() throws IOException {
		try {
			String result= FileUtil.underlineToCamel("update_staff_id");
			Logger logger = Logger.getLogger(this.getClass());
			logger.debug(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
