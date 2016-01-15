package org.leafframework.mvc.service.manager.verify;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.mapper.MyBatisUtil;
import org.leafframework.data.dao.mapper.VerifyBatisDAO;
import org.leafframework.data.dao.orm.TMArea;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.mvc.exception.MyException;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.DateUtil;
import org.leafframework.util.FtpUtil;
import org.leafframework.util.RETURN;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sinovatech.ntf.send.NtfplatService;

/**
 * 后台订单管理
 * 
 * @author zhangyy 2015年5月19日
 */
@Service("VerifyManagerMgr")
@Scope("prototype")
public class VerifyManagerMgr extends Business {
	protected Logger log = Logger.getLogger(this.getClass());
	
	@Value("${leaf.fileUpload.path}")
	private String leafFileUpLoadpath;

	@Override
	public RETURN execute() throws Exception {
		log.info("输入参数： " + this.getInParam());
		
		if (this.getPageUri().get("f").equals("updateVerifyOrder")) {
			return this.updateVerifyOrder();
		} else if (this.getPageUri().get("f").equals("saveAuditInfo")) {
			return this.saveAuditInfo();
		} else if (this.getPageUri().get("f").equals("saveBisStateInfo")) {
			return this.saveBisStateInfo();
		}
		return null;
	}
	
	@Override
	public RETURN query() throws Exception {
		// TODO Auto-generated method stub
		if (this.getPageUri().get("f").equals("getWaitVerifyOrders")) {
			return this.getWaitVerifyOrders();
		} else if (this.getPageUri().get("f").equals("getMyAuditOrders")) {
			return this.getMyAuditOrders();
		} else if (this.getPageUri().get("f").equals("verifyDetail")) {
			return this.getVerifyDetail();
		} else if (this.getPageUri().get("f").equals("getVerifyOrderList")) {
			return this.getVerifyOrderList();
		} else if (this.getPageUri().get("f").equals("exportVerifyOrderList")) {
			return this.exportVerifyOrderList();
		} 
		return null;
	}

	private RETURN exportVerifyOrderList() {
		InputStream inputStream = VerifyManagerMgr.class.getResourceAsStream("/org/leafframework/mvc/service/manager/verify/verifyorders.xls");
		POIFSFileSystem fs = null;
		try {
			fs = new POIFSFileSystem(inputStream);
			//读取excel模板  
	        HSSFWorkbook wb = new HSSFWorkbook(fs);  
	        //读取了模板内所有sheet内容  
	        HSSFSheet sheet = wb.getSheet("Sheet1");
	        //获取数据
			RETURN ret = this.getOrdersNoPage();
			if (ret != null && !ret.getErrorCode().equals(RETURN.SUCCESS.getErrorCode())) {
				return new RETURN("10007", "订单导出时获取数据失败");
			}
			
			List<Map<String,String>> rows = (List<Map<String,String>>) this.getOutParam().get("rows");
			if(rows != null){
				for(int i=0;i<rows.size();i++){
					Map<String,String> orderMap = (Map<String, String>) rows.get(i);
					
					HSSFRow row = sheet.createRow(i+1);
					row.createCell(0).setCellValue(orderMap.get("USER_ID"));
					row.createCell(1).setCellValue(orderMap.get("PHONE_NUM") == null ? "" : orderMap.get("PHONE_NUM"));
					row.createCell(2).setCellValue(orderMap.get("CITY_NAME") == null ? "" : orderMap.get("CITY_NAME"));
					row.createCell(3).setCellValue(orderMap.get("CARD_NUM") == null ? "" : orderMap.get("CARD_NUM"));
					row.createCell(4).setCellValue(orderMap.get("USER_NAME") == null ? "" : orderMap.get("USER_NAME"));
					row.createCell(5).setCellValue(orderMap.get("CARD_ADDRESS") == null ? "" : orderMap.get("CARD_ADDRESS"));
					row.createCell(6).setCellValue(orderMap.get("CREATE_TIME") == null ? "" : orderMap.get("CREATE_TIME"));
					row.createCell(7).setCellValue(orderMap.get("AUDIT_STATE") == null ? "" : convertOrderState(orderMap.get("AUDIT_STATE")));
					row.createCell(8).setCellValue(orderMap.get("BIS_STATE") == null ? "" : convertOrderState(orderMap.get("BIS_STATE")));
					row.createCell(9).setCellValue(orderMap.get("UPDATE_TIME") == null ? "" : orderMap.get("UPDATE_TIME"));
				}
			}
	           
	        //修改模板内容导出新模板  
			String fileName = "订单" + DateUtil.format(new Date(), "yyyyMMddHHmmssSS") + ".xls";
			String filePath = leafFileUpLoadpath +fileName;
			File file = new File(leafFileUpLoadpath);
			if(!file.exists()){
				file.mkdirs();
			}
	        FileOutputStream out = new FileOutputStream(filePath);  
	        wb.write(out);  
	        out.close(); 
	        
	        this.getOutParam().put("downLoadPath", filePath);
	        this.getOutParam().put("fileName", fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new RETURN("10007","订单导出发生异常，请联系管理员");
		}  
		
		return RETURN.SUCCESS;
	}

	private RETURN getVerifyOrderList() throws Exception {
		//this.getInParam().put("IS_WAIT", "1");
		getOrderList();
		
		this.setLogicView("manager/getVerifyOrderList");

		return RETURN.SUCCESS;
	}

	private RETURN saveAuditInfo() {
		Map<String, Object> inParam = this.getInParam();
		
		VerifyBatisDAO dao = (VerifyBatisDAO) this.getDaoFactory().get("verifyBatisDAO");
		
		//保存审核信息
		inParam.put("STATE_TYPE", "0");
		inParam.put("STATE", inParam.get("SEL_AUDIT_STATE"));
		
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		inParam.put("UPDATE_STAFF_ID", tmStaff.getStaffId());
		
		if(StringUtils.isNotBlank((String)inParam.get("AUDIT_ERROR_OTHER_REASON"))){
			inParam.put("SUGGEST", inParam.get("AUDIT_ERROR_OTHER_REASON"));
		} else {
			inParam.put("SUGGEST", inParam.get("AUDIT_FAIL_REASON"));
		}
		
		dao.saveAuditInfo(inParam);
		
		dao.updateAuditState(inParam);
		
		//发送短信通知
		//查找号码
		Map<String,String> userInfo = MyBatisUtil.convertMapToUpper(dao.getVerifyDetail(inParam));
		String auditInfo = "";
		if("A1".equals(inParam.get("STATE"))){
			auditInfo = "审核成功";
		} else {
			auditInfo = "审核不通过，不通过原因： " + inParam.get("SUGGEST");
			String content = "";
			if(inParam.get("SUGGEST").equals("B1") || inParam.get("SUGGEST").equals("B2") || inParam.get("SUGGEST").equals("B3")){
				content = "【山东联通实名补登记通知】尊敬的" + userInfo.get("PHONE_NUM") + "用户，您提交的身份信息不全，没有通过审核，请您重新上传或到号码归属地营业厅查询办理，感谢您的支持和配合。";
			} else {
				content = "【山东联通实名补登记通知】尊敬的" + userInfo.get("PHONE_NUM") + "用户，您提交的身份信息不合规或持证件与证件照片不符，没有通过审核，请持有效证件到归属地营业厅查询办理，感谢您的支持和配合。";
			}
			//String content = "您在山东联通网盟平台上传的实名认证信息，订单号: " + inParam.get("userId") + "，审核结果: " + auditInfo + "。山东联通网盟平台";
			// 业务类别编码
			String bizCode = "11006";
			// 业务类别名称
			String bizName = "通知短信下发";
			// 任务名称
			String taskName = "通知短信告知";

			try {
				NtfplatService.sendSingle(bizCode, bizName, taskName, content, userInfo.get("PHONE_NUM"));
			} catch (Exception e) {
				e.printStackTrace();
				throw new MyException(RETURN.SENDSMS_ENCODINGEXCEPTION);
			}
		}
		
		
		return RETURN.SUCCESS;
	}
	
	private RETURN saveBisStateInfo() {
		Map<String, Object> inParam = this.getInParam();
		
		VerifyBatisDAO dao = (VerifyBatisDAO) this.getDaoFactory().get("verifyBatisDAO");
		
		//保存审核信息
		inParam.put("STATE_TYPE", "1");
		inParam.put("STATE", inParam.get("SEL_BIS_STATE"));
		
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		inParam.put("UPDATE_STAFF_ID", tmStaff.getStaffId());
		
		if(StringUtils.isNotBlank((String)inParam.get("BIS_ERROR_OTHER_REASON"))){
			inParam.put("SUGGEST", inParam.get("BIS_ERROR_OTHER_REASON"));
		} else {
			inParam.put("SUGGEST", inParam.get("BIS_FAIL_REASON"));
		}
		
		dao.saveAuditInfo(inParam);
		
		dao.updateBisState(inParam);
		
		//发送短信通知
		//查找号码
		Map<String,String> userInfo = MyBatisUtil.convertMapToUpper(dao.getVerifyDetail(inParam));
		String auditInfo = "";
		String content = "";
		if("B1".equals(inParam.get("STATE"))){
			auditInfo = "办理成功";
			content = "【山东联通实名补登记通知】尊敬的" + userInfo.get("PHONE_NUM") + "用户，您的自助补登记已经完成，感谢您的支持和配合。";
		} else {
			auditInfo = "办理失败，失败原因： " + inParam.get("SUGGEST");
			content = "【山东联通实名补登记通知】尊敬的" + userInfo.get("PHONE_NUM") + "用户，您无法通过自助方式办理实名补登记，可能的原因：当前登记机主信息已实名或为集团客户、靓号、特殊套餐等限制、其他原因。请您携带有效证件及手机卡到号码归属地营业厅查询办理，感谢您的支持和配合。";
		}
		//String content = "您在山东联通网盟平台实名登记业务订单号: " + inParam.get("userId") + "，办理结果: " + auditInfo + "。山东联通网盟平台";
		// 业务类别编码
		String bizCode = "11006";
		// 业务类别名称
		String bizName = "通知短信下发";
		// 任务名称
		String taskName = "通知短信告知";

		try {
			NtfplatService.sendSingle(bizCode, bizName, taskName, content, userInfo.get("PHONE_NUM"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(RETURN.SENDSMS_ENCODINGEXCEPTION);
		}
		
		return RETURN.SUCCESS;
	}

	private RETURN updateVerifyOrder() throws Exception {
		Map<String, Object> inParam = this.getInParam();
		VerifyBatisDAO dao = (VerifyBatisDAO) this.getDaoFactory().get("verifyBatisDAO");
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		
		inParam.put("AUDIT_STAFF", tmStaff.getStaffId());
		String userIds = (String)inParam.get("USER_IDS");
		
		String[] array = userIds.split(",");
		
		inParam.put("USER_IDS", array);
		
		dao.updateVerifyOrder(inParam);
		
		return RETURN.SUCCESS;
	}

	@Override
	public RETURN init() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private RETURN getVerifyDetail() {
		Map<String, Object> inParam = this.getInParam();
		Map<String, Object> outParam = this.getOutParam();
		log.info("inParam: " + inParam);
		
		VerifyBatisDAO dao = (VerifyBatisDAO) this.getDaoFactory().get("verifyBatisDAO");
		
		//订单详情
		Map<String,String> userInfo = MyBatisUtil.convertMapToUpper(dao.getVerifyDetail(inParam));
		
		HashMap<String, Object> pageUri = (HashMap<String, Object>) this.getContext().get("pageUri");
		String remoteIp = pageUri.get("requestHost").toString();
		
		userInfo.put("CARD_PIC_A2", new FtpUtil().genImgShowUrl(userInfo.get("CARD_PIC_A"),remoteIp));
		userInfo.put("CARD_PIC_B2", new FtpUtil().genImgShowUrl(userInfo.get("CARD_PIC_B"),remoteIp));
		userInfo.put("CARD_PIC_C2", new FtpUtil().genImgShowUrl(userInfo.get("CARD_PIC_C"),remoteIp));
		
		userInfo.put("IS_AUDIT", (String)inParam.get("isaudit"));
		userInfo.put("IS_CHANGE_BIS", (String)inParam.get("ischangebis"));
		
		//国政通查询身份信息
		String imgFilePath = "/resources/guozht/" + (String)inParam.get("apptx") + ".jpg";
		userInfo.put("CARD_PIC_A_GZT", imgFilePath);
		userInfo.put("certName", (String)inParam.get("certName"));
		userInfo.put("certId", (String)inParam.get("certId"));
		if(inParam.get("sex")!=null && inParam.get("sex")!=""){
			if(inParam.get("sex").toString().equals("F")){
				userInfo.put("sex", "女");
			} else {
				userInfo.put("sex", "男");
			}
		} else {
			userInfo.put("sex", (String)inParam.get("sex"));
		}
		
		userInfo.put("nation", (String)inParam.get("nation"));
		userInfo.put("exp", (String)inParam.get("exp"));
		userInfo.put("birthday", (String)inParam.get("birthday"));
		userInfo.put("issue", (String)inParam.get("issue"));
		userInfo.put("addr", (String)inParam.get("addr"));
		outParam.put("userInfo", userInfo);
		
		//审核信息
		List<Map<String,String>> auditList = MyBatisUtil.convertListMapToUpper(dao.getVerifyAuditList(inParam));
		outParam.put("auditList", auditList);
		
		//国顺通返回证件信息
		
		
		this.setLogicView("manager/verifyDetail");
		
		return RETURN.SUCCESS;
	}

	private RETURN getMyAuditOrders() throws Exception {
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		this.getInParam().put("AUDIT_STAFF", tmStaff.getStaffId());
		this.getInParam().put("IS_AUDIT", "1");
		this.getInParam().put("isDefault", "1");
		getOrderList();
		
		this.setLogicView("manager/getMyAuditOrders");

		return RETURN.SUCCESS;
	}

	/**
	 * 查询待分配订单
	 * @return
	 * @throws Exception
	 * @date 2015年6月26日
	 * @author zhangyy
	 *
	 */
	private RETURN getWaitVerifyOrders() throws Exception {
		this.getInParam().put("IS_WAIT", "1");
		getOrderList();
		
		this.setLogicView("manager/getWaitVerifyOrders");

		return RETURN.SUCCESS;
	}
	
	private RETURN getOrderList() throws Exception{
		HashMap<String, Object> outParam = this.getOutParam();
		HashMap<String, Object> inParam = this.getInParam();
		VerifyBatisDAO myBatisDAO = (VerifyBatisDAO) this.getDaoFactory().get("verifyBatisDAO");
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息

		// 查询条件处理
		log.info("输入参数： " + inParam);
		
		
		// 查询操作员所属地市，没有区域操作权限时返回空结果集
		List<TMArea> areas = this.getAreasOfStaff();
		
		// 是否省份管理员
		boolean isProviceManager = false; 
		if (tmStaff.getAreaCode().equals("000")) {
			isProviceManager = true;
		} 
		
		if(!isProviceManager){
			inParam.put("CITY_ID", tmStaff.getAreaCode());
		}
		
		if(StringUtils.isNotBlank((String)inParam.get("BEGIN_TIME"))){
			inParam.put("BEGIN_TIME", inParam.get("BEGIN_TIME") + " 00:00:00");
		}
		if(StringUtils.isNotBlank((String)inParam.get("END_TIME"))){
			inParam.put("END_TIME", inParam.get("END_TIME") + " 23:59:59");
		}

		// 分页查询
		int page;
		if (this.getPageUri().get("page") == null || this.getPageUri().get("page") == "") {
			page = 1;
		} else {
			page = Integer.parseInt((String) this.getPageUri().get("page"));
		}
		int size;
		if (this.getInParam().get("rows") == null || this.getInParam().get("rows") == "") {
			size = 10;
		} else {
			size = Integer.parseInt((String) this.getInParam().get("rows"));// 每页显示的数量
		}
		inParam.put("begin", size * (page - 1));
		inParam.put("size", size);

		// 获取订单列表
		List<Map<String, String>> rows = MyBatisUtil.convertListMapToUpper(myBatisDAO.getOrderManagerList(inParam));

		// 查询总订单数
		int totalRecord = myBatisDAO.getOrderManagerCount(inParam);
		
		int totalPage = totalRecord%size == 0 ? totalRecord/size : totalRecord/size+1;

		outParam.put("total", totalRecord);
		outParam.put("rows", rows);
		outParam.put("areas", areas);
		outParam.put("tmStaff", tmStaff);
		outParam.put("page", totalPage);
		
		return RETURN.SUCCESS;
	}
	
	private RETURN getOrdersNoPage() {
		HashMap<String, Object> outParam = this.getOutParam();
		HashMap<String, Object> inParam = this.getInParam();
		VerifyBatisDAO myBatisDAO = (VerifyBatisDAO) this.getDaoFactory().get("verifyBatisDAO");
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息

		// 查询条件处理
		log.info("输入参数： " + inParam);
		
		// 是否省份管理员
		boolean isProviceManager = false; 
		if (tmStaff.getAreaCode().equals("000")) {
			isProviceManager = true;
		} 
		
		if(!isProviceManager){
			inParam.put("CITY_ID", tmStaff.getAreaCode());
		}
		
		if(StringUtils.isNotBlank((String)inParam.get("BEGIN_TIME"))){
			inParam.put("BEGIN_TIME", inParam.get("BEGIN_TIME") + " 00:00:00");
		}
		if(StringUtils.isNotBlank((String)inParam.get("END_TIME"))){
			inParam.put("END_TIME", inParam.get("END_TIME") + " 23:59:59");
		}

		// 获取订单列表
		List<Map<String, String>> rows = MyBatisUtil.convertListMapToUpper(myBatisDAO.getOrderManagerList(inParam));

		// 查询总订单数
		outParam.put("rows", rows);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 获取操作员管理的区域
	 * @return
	 * @date 2015年6月26日
	 * @author zhangyy
	 *
	 */
	private List<TMArea> getAreasOfStaff(){
		MyBatisDAO myBatisDAO = (MyBatisDAO) this.getDaoFactory().get("myBatisDAO");
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 管理员信息
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("staffCode", tmStaff.getAreaCode());
		List<TMArea> areas = (List<TMArea>) myBatisDAO.getAreasOfStaff(map);
		
		if (tmStaff.getAreaCode().equals("000") && areas != null) {
			TMArea a = new TMArea();
			a.setCode("");
			a.setName("--所有--");
			areas.add(0, a);
		}
		
		return areas;
	}
	
	private String convertOrderState(String value){
		if("A0".equals(value)){
			return "未审核";
		}else if("A1".equals(value)){
			return "已审核";
		}else if("A2".equals(value)){
			return "不通过";
		} else if("B0".equals(value)){
			return "未办理";
		}else if("B1".equals(value)){
			return "已办理";
		}else if("B2".equals(value)){
			return "办理失败";
		}else {
			return "异常";
		}
	}

}
