package org.leafframework.mvc.service.manager.pageloader;

import java.util.HashMap;
import java.util.List;

import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.orm.TMMenus;
import org.leafframework.data.dao.orm.TMModules;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.RETURN;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("PageLoader")
@Scope("prototype")
public class PageLoader extends Business {

	@Override
	public RETURN init() throws Exception {
		return RETURN.SUCCESS;
	}

	@Override
	public RETURN query() throws Exception {
		
		if (this.getPageUri().get("f").equals("headPage")) {
			return forHeadPage();
		}
		if (this.getPageUri().get("f").equals("welcomePage")) {
			return forWelcomePage();
		}
		if (this.getPageUri().get("f").equals("leftMenuPage")) {
			return forLeftMenuPage();
		}
		if (this.getPageUri().get("f").equals("getMenuModules")) {
			return getMenuModules();
		}
		return RETURN.SUCCESS;
	}

	@Override
	public RETURN execute() throws Exception {
		
		return RETURN.REQ_ACTION_ERROR;
	}
	
	private RETURN forHeadPage(){
		HashMap<String, Object> outParam = this.getOutParam();
		TMStaff staff = (TMStaff)this.getSession().get("managerAuthBean"); 
		if(null==staff)
		{
			this.setLogicView("manager"+this.getLogicView());
		}
		else
		{
			List<TMMenus> listMenus = (List<TMMenus>)this.getSession().get("TMMenusList");
			outParam.put("menuList", listMenus);
			this.setLogicView("headPage");
		}
		return RETURN.SUCCESS;
	}
	
	private RETURN forWelcomePage(){
		this.setLogicView("welcomePage");
		
		return RETURN.SUCCESS;
	}
	
	private RETURN forLeftMenuPage(){
		HashMap<String, Object> outParam = this.getOutParam();
		TMStaff staff = (TMStaff)this.getSession().get("managerAuthBean"); 
		if(null==staff)
		{
			this.setLogicView("manager"+this.getLogicView());
		}
		else
		{
			List<TMModules> listModules = (List<TMModules>) this.getSession().get("TMModulesList");
			outParam.put("modulesList", listModules);
			this.setLogicView("leftMenuPage");
		}
		return RETURN.SUCCESS;
	}
	
	private RETURN getMenuModules(){
		HashMap<String, Object> inParam = this.getInParam();
		HashMap<String, Object> outParam = this.getOutParam();
		TMStaff staff = (TMStaff)this.getSession().get("managerAuthBean");
		if(null==staff)
		{
			this.setLogicView("manager"+this.getLogicView());
		}
		else
		{
			MyBatisDAO myBatisDAO=(MyBatisDAO) this.getDaoFactory().get("myBatisDAO");
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("staffId", (String) staff.getStaffId());
			map.put("menuId", Integer.valueOf(inParam.get("groupid").toString()));
			List<TMModules> listModules = (List<TMModules>) myBatisDAO.getModules(map);
			outParam.put("modulesList", listModules);
			this.setLogicView("leftMenuPage");
		}
		return RETURN.SUCCESS;
	}
	
	
}
