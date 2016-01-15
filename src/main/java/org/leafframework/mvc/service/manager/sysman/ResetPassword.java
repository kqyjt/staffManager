package org.leafframework.mvc.service.manager.sysman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.leafframework.constant.Constants;
import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.orm.TCCustomer;
import org.leafframework.data.dao.orm.TCCustomerHome;
import org.leafframework.data.dao.orm.TMArea;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.data.dao.orm.TMStaffHome;
import org.leafframework.mvc.model.IMap;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.DateUtil;
import org.leafframework.util.RETURN;
import org.leafframework.util.TripleDES;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("ResetPassword")
@Scope("prototype")
public class ResetPassword extends Business {
	
	@Override
	public RETURN execute() throws Exception {
		if (this.getPageUri().get("f").equals("resetStaffPassword")) {
			return resetStaffPassword();
		}
		return RETURN.REQ_ACTION_ERROR;
	}
	
	@Override
	public RETURN init() throws Exception {
		this.setLogicView("resetPasswordPage");
		return RETURN.SUCCESS;
	}
	
	@Override
	public RETURN query() throws Exception {

		if (this.getPageUri().get("f").equals("getStaffInfo")) {
			return this.getStaffInfo();
		}
		return RETURN.SUCCESS;
	}


	/**
	 * 获取管理员信息
	 * @return
	 * @author shh
	 */
	private RETURN getStaffInfo() {
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		MyBatisDAO myBatisDAO=(MyBatisDAO) this.getDaoFactory().get("myBatisDAO");
		// 登录用户名
	 	String regName = (String) inParam.get("regName");
		Map<String,Object> param = new HashMap<String,Object>();
		
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		if (null == tmStaff) {
			outParam.put("errorDetail", "登录超时");
			this.setLogicView("managerLoginPage");
			return RETURN.SES_TIME_OUT;
		}
		// 是否省份管理员
		boolean isProvinceManager = false; 
		if (tmStaff.getAreaCode().equals("000")) {
			isProvinceManager = true;
		}
		if(!isProvinceManager){
			//获取城市名
			param.put("areaCode", tmStaff.getAreaCode());
		}
		param.put("regName", regName);
		List<HashMap<String, Object>> rows = new ArrayList<HashMap<String,Object>>();
		int total = 0;
		if(regName != null && !regName.equals("")){
			rows = myBatisDAO.getStaffByStaffRegName(param);
			total = rows.size();
		}
		outParam.put("rows", rows);
		outParam.put("total", total);
		outParam.put("staffName", tmStaff.getStaffId());
		return RETURN.SUCCESS;
	}
	
	/**
	 * 重置管理员密码
	 * @return
	 * @author shh
	 */
	private RETURN resetStaffPassword() {
		HashMap<String, Object> outParam = this.getOutParam();
		HashMap<String, Object> inParam= this.getInParam();
		
		MyBatisDAO myBatisDAO=(MyBatisDAO) this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> param = new HashMap<String,Object>();
		
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		if (null == tmStaff) {
			outParam.put("errorDetail", "登录超时");
			this.setLogicView("managerLoginPage");
			return RETURN.SES_TIME_OUT;
		}
		param.put("staffId", (String) inParam.get("regName"));
		List<HashMap<String, Object>> staffList = myBatisDAO.staffQuery(param);
		TMStaffHome tMStaffHome = (TMStaffHome) this.getDaoFactory().get("TMStaffHome");
		
		String resetPass = "qw12!@";
		String hidPass = TripleDES.encrypt(resetPass);
		if(staffList.size() != 0){
			int staffId = Integer.parseInt(staffList.get(0).get("id").toString());
			TMStaff staff = tMStaffHome.findById(staffId);
			staff.setPassword(hidPass);
			staff.setUpdateStaffId(tmStaff.getId().toString());
			staff.setUpdateTime(DateUtil.parse(this.getSysdate()));
			tMStaffHome.merge(staff);
		}
		return RETURN.SUCCESS;
	}
}
