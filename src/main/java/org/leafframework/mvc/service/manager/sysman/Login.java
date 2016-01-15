package org.leafframework.mvc.service.manager.sysman;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.orm.TMMenus;
import org.leafframework.data.dao.orm.TMModules;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.data.dao.orm.TMStaffHome;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.DateUtil;
import org.leafframework.util.RETURN;
import org.leafframework.util.RSAUtil;
import org.leafframework.util.TripleDES;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("Login")
@Scope("prototype")
public class Login extends Business {

	@Override
	public RETURN init() throws Exception {
		HashMap<String, Object> outParam = this.getOutParam();
		TMStaff staff = (TMStaff)this.getSession().get("managerAuthBean"); 
		if(null==staff)
		{
			this.setLogicView("manager"+this.getLogicView());
		}
		else
		{
			List<TMMenus> listMenus = (List<TMMenus>)this.getSession().get("TMMenusList");
			outParam.put("initMenu", listMenus.get(0).getName());
			outParam.put("initMenuIcon", listMenus.get(0).getIcon());
			this.setLogicView("managerMain");
		}
		return RETURN.SUCCESS;
	}

	@Override
	public RETURN query() throws Exception {
		if (this.getPageUri().get("f").equals("getLoginfor")) {
			return getLoginfor();
		}		
		HashMap<String, Object> outParam = this.getOutParam();

		// 动态sql查询
		TMMenus pojo = new TMMenus();
		MyBatisDAO myBatisDAO = (MyBatisDAO) this.getDaoFactory().get(
				"myBatisDAO");
		List<TMMenus> list = (List<TMMenus>) myBatisDAO.getMenus(pojo);
		
		this.setLogicView("manager"+this.getLogicView());
		outParam.put("TMMenusList", list);
		
		return RETURN.SUCCESS;
	}

	//获取登陆信息
	private RETURN getLoginfor() {
		HashMap<String, Object> outParam = this.getOutParam();
		
		TMStaff customer = (TMStaff)this.getSession().get("managerAuthBean"); 
		if(null != customer)
		{
			outParam.put("customer", customer);
		}
		else
		{
			this.setErrorDetail("unlogin");
		}
		
		return RETURN.SUCCESS;
	}

	@Override
	public RETURN execute() throws Exception {
		if (this.getPageUri().get("f").equals("login")) {
			return login();
		}
		if (this.getPageUri().get("f").equals("logout")) {
			return logout();
		}
		if (this.getPageUri().get("f").equals("changePassWord")) {
			return changePassWord();
		}
		if (this.getPageUri().get("f").equals("staffChangePsw")) {
			return staffChangePsw();
		}
		return RETURN.REQ_ACTION_ERROR;
	}
	private RETURN staffChangePsw() {
		HashMap<String, Object> outParam = this.getOutParam();
		
		TMStaff customer = (TMStaff)this.getSession().get("managerAuthBean"); 
		if(null != customer)
		{
			outParam.put("customer", customer);
			this.setLogicView("modPassword");
		}
		else
		{
			outParam.put("errorDetail", "登录超时");
			this.setLogicView("managerLoginPage");
			return RETURN.SES_TIME_OUT;
		}
		return RETURN.SUCCESS;
	}

	protected RETURN logout() {
		this.getSession().remove("TMModulesList");
		this.getSession().remove("TMMenusList");
		this.getSession().remove("managerAuthBean");
		this.setLogicView("manager"+this.getLogicView());
		return RETURN.SUCCESS;
	}
	protected RETURN login() {
		HashMap<String, Object> pageData = this.getInParam();
		this.setLogicView("manager"+this.getLogicView());
		
		//获取验证码
		String captcha = (String) pageData.get("captcha");
		if (captcha != null) {
			if (!captcha.equals(this.getSession().get("captcha"))) {
				this.setErrorDetail("验证码不正确");
				return RETURN.CHK_ERROR;
			}
		}
		else
		{
			this.setErrorDetail("验证码不正确");
			return RETURN.CHK_ERROR;
		}

		TMStaff criteria = new TMStaff();
		criteria.setStaffId((String) pageData.get("staffId"));

		TMStaffHome loginuserDao = (TMStaffHome) this.getDaoFactory().get(
				"TMStaffHome");

		List<?> staffs = loginuserDao.findByExample(criteria);
		if (staffs.size() > 0) {
			String password=pageData.get("password").toString();
			TMStaff staff = (TMStaff) staffs.get(0);
		 	//加密密码还原
			password=RSAUtil.secretToOld(password);
			if (staff.getPassword().equals(TripleDES.decrypt(password)) ) {
				MyBatisDAO myBatisDAO=(MyBatisDAO) this.getDaoFactory().get("myBatisDAO");
				//获取顶级菜单
				List<TMMenus> listMenus = (List<TMMenus>) myBatisDAO.getStaffMenus((String) pageData.get("staffId"));
				//获取首次加载的二级菜单
				int menuId = listMenus.get(0).getId();
				HashMap<String,Object> map = new HashMap<String, Object>();
				map.put("staffId", (String) pageData.get("staffId"));
				map.put("menuId", menuId);
				List<TMModules> listModules = (List<TMModules>) myBatisDAO.getModules(map);
				
				this.getSession().put("managerAuthBean", staffs.get(0));
				this.getSession().put("TMModulesList", listModules);
				this.getSession().put("TMMenusList", listMenus);
				
				this.getCookies().put("managertoken", this.getPageUri().get("authToken"));
				this.setLogicView("redirect:/manager/sysman/Login.htm");
				return RETURN.SUCCESS;
			} else {
				this.setErrorDetail("用户名或密码不正确");
				return RETURN.CUSTORPASSERROR;
			}
		}else{
			this.setErrorDetail("用户名或密码不正确");
			return RETURN.CUSTORPASSERROR;
		}
	}
	
	//修改密码
	private RETURN changePassWord() {
		HashMap<String, Object> inparam = this.getInParam();
		HashMap<String, Object> outParam = this.getOutParam();
		MyBatisDAO mybatisDao = (MyBatisDAO) this.getDaoFactory().get("myBatisDAO");
		TMStaffHome tMStaffHome = (TMStaffHome) this.getDaoFactory().get("TMStaffHome");
		TMStaff customer = (TMStaff)this.getSession().get("managerAuthBean"); 
		if(null == customer)
		{
			outParam.put("errorDetail", "登录超时");
			this.setLogicView("managerLoginPage");
			return RETURN.SES_TIME_OUT;
		}
		//获取用户的id 旧密码  新密码 确认密码
		String staffId = customer.getStaffId();
		String oldPassWord = inparam.get("oldPassword") == null ? "":inparam.get("oldPassword").toString();
		String newPassWord1 = inparam.get("newPassword1") ==null ? "":inparam.get("newPassword1").toString();
		String newPassword2 = inparam.get("newPassword2") == null ? "":inparam.get("newPassword2").toString();
		
		
		oldPassWord=RSAUtil.secretToOld(oldPassWord);
		newPassWord1=RSAUtil.secretToOld(newPassWord1);
		newPassword2=RSAUtil.secretToOld(newPassword2);
		if (StringUtils.isEmpty(staffId)) {
			outParam.put("errorDetail", "用户名不能为空");
			return RETURN.CHK_CUSTOMER_NULLLOGNAME;
		}
		if (StringUtils.isEmpty(oldPassWord)) {
			outParam.put("errorDetail", "旧密码不能为空");
			return RETURN.CHK_CUSTOMER_NULLPWD;
		}
		if (StringUtils.isEmpty(newPassWord1)) {
			outParam.put("errorDetail", "新密码不能为空");
			return RETURN.CHK_CUSTOMER_NULLPWD;
		}
		if (StringUtils.isEmpty(newPassword2)) {
			outParam.put("errorDetail", "确认密码不能为空");
			return RETURN.CHK_CUSTOMER_NULLPWD;
		}
		if (!newPassword2.equals(newPassWord1)) {
			outParam.put("errorDetail", "新密码和确认密码不一致");
			return RETURN.NEW_PWD_ERROR;
		}
		
		TMStaff staff = null;
		HashMap<String, Object> staffIsModPswMap = new HashMap<String, Object>();
		staffIsModPswMap.put("staffId", staffId);
		List<TMStaff> staffIsModPswList  = mybatisDao.getStaffInfor(staffIsModPswMap);
		if(staffIsModPswList.size()>0){//用户存在
			if (oldPassWord.equals(newPassWord1)) {
				outParam.put("errorDetail", "新旧密码不能相同");
				return RETURN.NEWANDOLDPSW;
			}
			staff =  staffIsModPswList.get(0);
			HashMap<String, Object> staffMap = new HashMap<String, Object>();
			staffMap.put("staffId", staffId);
			staffMap.put("passWord", TripleDES.encrypt(oldPassWord));
			
			List<TMStaff> staffList  = mybatisDao.getStaffInfor(staffMap);
			if(staffList.size() > 0){
				//保存 新的密码
				staff = tMStaffHome.findById(staff.getId());
				staff.setPassword(TripleDES.encrypt(newPassWord1));
				staff.setUpdateTime(DateUtil.parse(getSysdate()));
				tMStaffHome.merge(staff);
			}else{
				//用户名或密码不正确
				outParam.put("errorDetail", "用户名或密码不正确");
				return RETURN.CUSTORPASSERROR;
			}
		}else{//用户不存在
			outParam.put("errorDetail", "用户名或密码不正确");
			return RETURN.CUSTORPASSERROR;
		}
		outParam.put("regName", staffId);
		return RETURN.SUCCESS;
	}
}
