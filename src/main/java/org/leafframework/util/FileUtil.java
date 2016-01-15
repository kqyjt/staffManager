package org.leafframework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileUtil {

	public static final int BUFFER_SIZE = 16 * 1024;
	
	// 生产环境
	public static final String uploadPath = "/home/vfly/Tomcat/vfly-data/emarketing/manager/file/upload/";
	public static final String templatePath = "/home/vfly/Tomcat/vfly-data/emarketing/manager/file/template/";
	public static final String downloadPath = "/home/vfly/Tomcat/vfly-data/emarketing/manager/file/download/";
	public static final String mubanPath = "/home/vfly/Tomcat/vfly-data/emarketing/manager/file/muban/";
	
	// 测试环境
//	public static final String uploadPath = "/ngbss/webapp/vfly_test_wlyxplus_data/manager/file/upload/";
//	public static final String templatePath = "/ngbss/webapp/vfly_test_wlyxplus_data/manager/file/template/";
//	public static final String downloadPath = "/ngbss/webapp/vfly_test_wlyxplus_data/manager/file/download/";
//	public static final String mubanPath = "/ngbss/webapp/vfly_test_wlyxplus_data/manager/file/muban/";

	// 本地环境
//	public static final String uploadPath = "D:/Users/ailk/emarketingWorkspace/emarketing/src/main/webapp/WEB-INF/manager/file/upload/";
//	public static final String templatePath = "D:/Users/ailk/emarketingWorkspace/emarketing/src/main/webapp/WEB-INF/manager/file/template/";
//	public static final String downloadPath = "D:/Users/ailk/emarketingWorkspace/emarketing/src/main/webapp/WEB-INF/manager/file/download/";
//	public static final String mubanPath = "D:/Users/ailk/emarketingWorkspace/emarketing/src/main/webapp/WEB-INF/manager/file/muban/";
	
	public static void copy(File srcFile, File dstFile) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(srcFile), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dstFile), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];

			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				in.close();
			}
			if (null != out) {
				out.close();
			}
		}
	}
	public static void copyFile(String srcFile, String dstFile) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(srcFile), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dstFile), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];

			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				in.close();
			}
			if (null != out) {
				out.close();
			}
		}
	}
	static public HashMap<Integer, Object> readExcelHeader(String fileName, int sheetIdx, int rowOff) throws Exception {

		FileInputStream fis = new FileInputStream(fileName);
		try {
			// Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if (fileName.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else if (fileName.toLowerCase().endsWith("xls")) {
				workbook = new HSSFWorkbook(fis);
			} else {
				throw new Exception("invalid file name, should be xls or xlsx");
			}

			Sheet sheet = workbook.getSheetAt(sheetIdx);

			// every sheet has rows, iterate over them
			Iterator<Row> rowIterator = sheet.iterator();
			for (int i = 0; i < rowOff; i++) {
				if (rowIterator.hasNext()) {
					rowIterator.next();
				}
			}

			// 分析首行，将字段名称加载
			HashMap<Integer, Object> hmHeader = null;
			if (rowIterator.hasNext()) {
				Row HeaderRow = rowIterator.next();
				Iterator<Cell> cellIterator = HeaderRow.cellIterator();
				hmHeader = new HashMap<Integer, Object>();
				int headerIdx = 0;
				while (cellIterator.hasNext()) {

					// Get the Cell object
					Cell cell = cellIterator.next();

					// check the cell type and process accordingly
					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING :
							hmHeader.put(headerIdx, underlineToCamel(cell.getStringCellValue().trim()));
							break;
						case Cell.CELL_TYPE_NUMERIC :
							BigDecimal big=new BigDecimal(cell.getNumericCellValue());  
			                String value = big.toString().replace(',', '.');  
			                //解决1234.0  去掉后面的.0  
			                if(null!=value&&!"".equals(value.trim())){  
			                     String[] item = value.split("[.]");  
			                     if(1<item.length)
			                    	 if("0".equals(item[1])){
			                    		 value=item[0]; 
			                    	 }else if(item[1].length() > 2 ){
			                    		 value = item[0]+"."+item[1].substring(0,2);
			                    	 }else{
			                    		 value = item[0]+"."+item[1];
			                    	 }
			                          
			                     } 
			                hmHeader.put(headerIdx, value);
							break;
					}
					headerIdx = headerIdx + 1;

				} // end of cell iterator
			}
			fis.close();
			return hmHeader;
		} catch (IOException e) {
			fis.close();		
			throw e;
		}
	}
	static public List<HashMap<String, Object>> readExcelFile(String fileName, int sheetIdx, int rowOff)
			throws Exception {

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		try {

			Logger.getLogger(FileUtil.class).debug(fileName);

			// baseFileName = system.20140806103510.TMStaff.工号信息导入模板.xlsx;
			String baseFileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
			Logger.getLogger(FileUtil.class).debug(baseFileName);

			// tmp = TMStaff.工号信息导入模板.xlsx;
			String tmp = baseFileName.substring(baseFileName.indexOf(".") + 14 + 2);
			Logger.getLogger(FileUtil.class).debug(tmp);

			// templateFileName=TMStaff_template.xlsx
			String templateFileName = tmp.substring(0, tmp.indexOf(".")) + "_template"
					+ tmp.substring(tmp.lastIndexOf("."));
			Logger.getLogger(FileUtil.class).debug(templateFileName);

			//判断模板是否有改动
			// 分析模板首行，将字段名称加载
			HashMap<Integer, Object> hmHeader_template=readExcelHeader(templatePath+templateFileName,  sheetIdx,  rowOff);
			// 分析首行，将字段名称加载
			HashMap<Integer, Object> hmHeader=readExcelHeader( fileName,  sheetIdx,  rowOff);	
			
			Iterator<Entry<Integer, Object>> iter = hmHeader_template.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Integer key = (Integer) entry.getKey();				
				if(!hmHeader.get(key).equals(entry.getValue())){
					throw new Exception("上传文件同模板不一致，第"+(key+1)+"个字段被修改，请重新下载模板按照模板要求上传数据。");
				}				
			}
		
			// Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(fileName);
			boolean is2003 = false;
			
			// Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if (fileName.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(fis);
				is2003 = false;
			} else if (fileName.toLowerCase().endsWith("xls")) {
				workbook = new HSSFWorkbook(fis);
				is2003 = true;
			} else {
				throw new Exception("invalid file name, should be xls or xlsx");
			}

			int numberOfSheets = workbook.getNumberOfSheets();

			Sheet sheet = workbook.getSheetAt(sheetIdx);

			Iterator<Row> rowIterator = sheet.iterator();
			
			//跳过行首；
			int rowIdx=rowOff+1;
			for (int i = 0; i < rowIdx; i++) {
				if (rowIterator.hasNext()) {
					rowIterator.next();
				}
			}	

			while (rowIterator.hasNext()) {

				// Get the row object
				Row row = rowIterator.next();

				// Every row has columns, get the column iterator and
				// iterate over them
				Iterator<Cell> cellIterator = row.cellIterator();

				int cellIdx = 0;
				HashMap<String, Object> hmrow = new HashMap<String, Object>();
				while (cellIterator.hasNext()) {

					// Get the Cell object
					Cell cell = cellIterator.next();

					// check the cell type and process accordingly
					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING :
							hmrow.put((String) hmHeader.get(cellIdx), cell.getStringCellValue().trim());
							break;
						case Cell.CELL_TYPE_NUMERIC :
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date d = cell.getDateCellValue();
								DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								hmrow.put((String) hmHeader.get(cellIdx), formater.format(d));
							} else {
								BigDecimal big=new BigDecimal(cell.getNumericCellValue());  
				                String value = big.toString().replace(',', '.');  
				                //解决1234.0  去掉后面的.0  
				                if(null!=value&&!"".equals(value.trim())){  
				                     String[] item = value.split("[.]");  
				                     if(1<item.length)
				                    	 if("0".equals(item[1])){
				                    		 value=item[0]; 
				                    	 }else if(item[1].length() > 2 ){
				                    		 value = item[0]+"."+item[1].substring(0,2);
				                    	 }else{
				                    		 value = item[0]+"."+item[1];
				                    	 }
				                          
				                     } 
				                hmrow.put((String) hmHeader.get(cellIdx), value);
							}
							break;
					}
					cellIdx = cellIdx + 1;
				} // end of cell iterator

				resultList.add(hmrow);
			} // end of rows iterator

			fis.close();

		} catch (IOException e) {
			throw e;
		}
		return resultList;
	}
	
	/**
	 * 解析Excel文件
	 * @param fileName 源文件路径
	 * @param secondTemplate	第二模版后缀
	 * @param sheetIdx
	 * @param rowOff
	 * @return
	 * @throws Exception
	 */
	static public Map readExcelFile(String fileName, String secondTemplate, int sheetIdx, int rowOff)
			throws Exception {
		Map m = new HashMap();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		try {

			Logger.getLogger(FileUtil.class).debug(fileName);

			// baseFileName = system.20140806103510.TMStaff.工号信息导入模板.xlsx;
			String baseFileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
			Logger.getLogger(FileUtil.class).debug(baseFileName);

			// tmp = TMStaff.工号信息导入模板.xlsx;
			String tmp = baseFileName.substring(baseFileName.indexOf(".") + 14 + 2);
			Logger.getLogger(FileUtil.class).debug(tmp);

			// templateFileName=TMStaff_template.xlsx
			String templateFileName = tmp.substring(0, tmp.indexOf(".")) + "_template"
					+ tmp.substring(tmp.lastIndexOf("."));
			Logger.getLogger(FileUtil.class).debug(templateFileName);
			
			// secondTemplateFileName=TABill_Manual_template.xlsx
			String secondTemplateFileName = secondTemplate + "_template"
					+ tmp.substring(tmp.lastIndexOf("."));
			Logger.getLogger(FileUtil.class).debug(secondTemplateFileName);
			
			
			// 判断模板是否有改动
			// 分析第一种模板首行，将字段名称加载
			HashMap<Integer, Object> hmHeader_template=readExcelHeader(templatePath+templateFileName,  sheetIdx,  rowOff);
			// 分析首行，将字段名称加载
			HashMap<Integer, Object> hmHeader=readExcelHeader( fileName,  sheetIdx,  rowOff);	
			// 模版验证
			boolean templateValidate = true;
			
			// 进行第一种模版验证
			Iterator<Entry<Integer, Object>> iter = hmHeader_template.entrySet().iterator();
			while (iter.hasNext()) {
				m.put("template", "SYSTEM");
				Map.Entry entry = (Map.Entry) iter.next();
				Integer key = (Integer) entry.getKey();				
				if(!hmHeader.get(key).equals(entry.getValue())){
					templateValidate = false;
					break;
				}				
			}
			
			// 进行第二种模版验证
			if(templateValidate == false){
				m.put("template", "MANUAL");
				HashMap<Integer, Object> secondHeader_template=readExcelHeader(templatePath+secondTemplateFileName,  sheetIdx,  rowOff);
				iter = secondHeader_template.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					Integer key = (Integer) entry.getKey();				
					if(!hmHeader.get(key).equals(entry.getValue())){
						templateValidate = false;
						throw new Exception("上传文件同模板不一致，第"+(key+1)+"个字段被修改，请重新下载模板按照模板要求上传数据。");
					}				
				}
			}
			
			
			// Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(fileName);
			boolean is2003 = false;
			
			// Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if (fileName.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(fis);
				is2003 = false;
			} else if (fileName.toLowerCase().endsWith("xls")) {
				workbook = new HSSFWorkbook(fis);
				is2003 = true;
			} else {
				throw new Exception("invalid file name, should be xls or xlsx");
			}

			int numberOfSheets = workbook.getNumberOfSheets();

			Sheet sheet = workbook.getSheetAt(sheetIdx);

			Iterator<Row> rowIterator = sheet.iterator();
			
			//跳过行首；
			int rowIdx=rowOff+1;
			for (int i = 0; i < rowIdx; i++) {
				if (rowIterator.hasNext()) {
					rowIterator.next();
				}
			}	

			while (rowIterator.hasNext()) {

				// Get the row object
				Row row = rowIterator.next();

				// Every row has columns, get the column iterator and
				// iterate over them
				Iterator<Cell> cellIterator = row.cellIterator();

				int cellIdx = 0;
				HashMap<String, Object> hmrow = new HashMap<String, Object>();
				while (cellIterator.hasNext()) {

					// Get the Cell object
					Cell cell = cellIterator.next();

					// check the cell type and process accordingly
					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING :
							hmrow.put((String) hmHeader.get(cellIdx), cell.getStringCellValue().trim());
							break;
						case Cell.CELL_TYPE_NUMERIC :
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date d = cell.getDateCellValue();
								DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								hmrow.put((String) hmHeader.get(cellIdx), formater.format(d));
							} else {
								BigDecimal big=new BigDecimal(cell.getNumericCellValue());  
				                String value = big.toString().replace(',', '.');  
				                //解决1234.0  去掉后面的.0  
				                if(null!=value&&!"".equals(value.trim())){  
				                     String[] item = value.split("[.]");  
				                     if(1<item.length)
				                    	 if("0".equals(item[1])){
				                    		 value=item[0]; 
				                    	 }else if(item[1].length() > 2 ){
				                    		 // value = item[0]+"."+item[1].substring(0,2);
				                    		 value = item[0]+"."+String.format("%.2f", Double.parseDouble(item[1]));
				                    		 
				                    		/*
				                    		 double valuedb = Double.valueOf(value);
				                    		 value =String.valueOf(Math.round(valuedb*100)/100.0);
				                    		 */
				                    	 }else{
				                    		 value = item[0]+"."+item[1];
				                    	 }
				                          
				                     } 
				                hmrow.put((String) hmHeader.get(cellIdx), value);
							}
							break;
					}
					cellIdx = cellIdx + 1;
				} // end of cell iterator

				resultList.add(hmrow);
			} // end of rows iterator

			fis.close();

		} catch (IOException e) {
			throw e;
		}
		m.put("resultList", resultList);
		return m;
	}
	
	static public void writeExcelFile(List<LinkedHashMap<String, Object>> listData, String fileName) throws Exception {
		Workbook workbook = null;
		boolean is2003 = false;
		if (fileName.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
			is2003 = false;
		} else if (fileName.endsWith("xls")) {
			workbook = new HSSFWorkbook();
			is2003 = true;
		} else {
			throw new Exception("invalid file name, should be xls or xlsx");
		}
		Sheet sheet = workbook.createSheet("文件模板");

		Iterator<LinkedHashMap<String, Object>> iterator = listData.iterator();

		// 处理表格内容
		int rowIndex = 0;
		while (iterator.hasNext()) {
			HashMap<String, Object> hmRow = iterator.next();
			// 行首取KEY作为字段名,value 作为批注
			if (rowIndex == 0) {
				Row row = sheet.createRow(rowIndex++);
				int columnIndex = 0;
				for (Map.Entry<String, Object> entry : hmRow.entrySet()) {
					Cell cell = row.createCell(columnIndex);

					// 批注处理
					if (is2003) {
						HSSFComment comment = (HSSFComment) sheet.createDrawingPatriarch().createCellComment(
								new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
						comment.setString(new HSSFRichTextString((String) entry.getValue()));
						comment.setAuthor("bruce.lee");
						cell.setCellComment(comment);
					} else {
						XSSFComment comment = (XSSFComment) sheet.createDrawingPatriarch().createCellComment(
								new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
						comment.setString(new XSSFRichTextString((String) entry.getValue()));
						comment.setAuthor("bruce.lee");
						cell.setCellComment(comment);
					}
					cell.setCellValue((String) entry.getKey());
					columnIndex++;
				}
				continue;
			}

			Row row = sheet.createRow(rowIndex++);
			int columnIndex = 0;
			for (Map.Entry<String, Object> entry : hmRow.entrySet()) {
				Cell cell = row.createCell(columnIndex);
				cell.setCellValue((entry.getValue()==null || entry.getValue().equals("null") ?"":(String)entry.getValue()));
				columnIndex++;
			}
		}

		// lets write the excel data to file now
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		fos.close();
		return;
	}
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == '_') {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(Character.toLowerCase(c));
			}
		}
		return sb.toString();
	}
	
	public static void genGoodsDetailPage(String path, String content) {  
        String s = new String();  
        String s1 = new String();  
          
        try {  
            File f = new File(path);  
              
            /*if (f.exists()) {  
                System.out.println("文件存在");  
            } else {  
                System.out.println("文件不存在，正在创建...");  
                if (f.createNewFile()) {  
                    System.out.println("文件创建成功！");  
                } else {  
                    System.out.println("文件创建失败！");  
                }  
            }  */
              
            /*BufferedReader input = new BufferedReader(new FileReader(f));  
  
            while ((s = input.readLine()) != null) {  
                s1 += s + "/n";  
            }  
              
            //System.out.println("文件内容：" + s1);  
            input.close();  
            s1 += content;  */
            BufferedWriter output = new BufferedWriter(new FileWriter(f));  
           //output.write(s1);  
            output.write(content);
            output.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
	
}
