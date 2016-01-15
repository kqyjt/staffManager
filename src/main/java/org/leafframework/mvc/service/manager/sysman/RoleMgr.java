package org.leafframework.mvc.service.manager.sysman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.orm.TMRoles;
import org.leafframework.data.dao.orm.TMRolesHome;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.mvc.model.IMap;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.RETURN;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("RoleMgr")
@Scope("prototype")
public class RoleMgr extends Business{

	@Override
	public RETURN execute() throws Exception {
		if("roleRemove".equals(this.getPageUri().get("f"))){
			return roleRemove(); //删除角色
		} else if("roleUpdate".equals(this.getPageUri().get("f"))){
			return roleUpdate(); //更新角色
		} else if("roleEdit".equals(this.getPageUri().get("f"))){
			return roleEdit(); //编辑角色
		} else if("powerRemove".equals(this.getPageUri().get("f"))){
			return powerRemove(); //删除角色权限
		} else if("menuRemove".equals(this.getPageUri().get("f"))){
			return menuRemove(); //删除角色菜单
		} else if("powerAdd".equals(this.getPageUri().get("f"))){
			return powerAdd(); //添加角色权限
		} else if("roleAdd".equals(this.getPageUri().get("f"))){
			return roleAdd(); //添加角色
		} else if("roleSave".equals(this.getPageUri().get("f"))){
			return roleSave(); //保存角色权限
		} else if("menuSave".equals(this.getPageUri().get("f"))){
			return menuSave(); //保存角色菜单
		} else if("staffRemove".equals(this.getPageUri().get("f"))){
			return staffRemove(); //解除角色
		} else if("roleChange".equals(this.getPageUri().get("f"))){
			return roleChange(); //更换角色
		}
		
		return RETURN.SUCCESS;
	}

	@Override
	public RETURN init() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RETURN query() throws Exception {
		if("roleQuery".equals(this.getPageUri().get("f"))){
			return roleQuery(); //角色查询
		} else if("powerQuery".equals(this.getPageUri().get("f"))){
			return powerQuery(); //角色已有权限查询
		} else if("noPowerQuery".equals(this.getPageUri().get("f"))){
			return noPowerQuery(); //角色未添加权限查询
		} else if("staffAttQuery".equals(this.getPageUri().get("f"))){
			return staffAttQuery(); //员工所属角色查询
		} else if("menuQuery".equals(this.getPageUri().get("f"))){
			return menuQuery(); //角色已有菜单查询
		} else if("noMenuQuery".equals(this.getPageUri().get("f"))){
			return noMenuQuery(); //角色未添加菜单查询
		}
		
		return RETURN.SUCCESS;
	}

	/**
	 * 角色查询
	 * @return
	 */
	private RETURN roleQuery(){
		IMap inParam = new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		HashMap<String, Object> outParam = this.getOutParam();
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		TMStaff tmStaff = (TMStaff)this.getSession().get("managerAuthBean");
		//判断是否是地市管理员
		boolean isProvinceManager = false;
		if(tmStaff.getAreaCode().equals("000")){
			isProvinceManager = true;
		}
		if(!isProvinceManager){
			// 地市管理员
			HashMap<String,Object> temp = new HashMap<String,Object>();
			temp.put("StaffId", tmStaff.getStaffId());
			List<HashMap<String,Object>> roleIdList = myBatisDao.queryRoleIdList(temp);
			HashMap<String,Object> temp1 = new HashMap<String,Object>();
			temp1.put("roleId", roleIdList.get(0).get("roleId"));
			List<HashMap<String,Object>> roleNameList = myBatisDao.queryNameById(temp1);
			
			paramMap.put("roleName", roleNameList.get(0).get("roleName"));
		} else {
			// 省级管理员
			String roleName = inParam.get("roleName") == null ? "" : inParam.get("roleName").toString();
			paramMap.put("roleName", roleName);
		}
		
		List<HashMap<String,Object>> roleList = myBatisDao.queryRoleList(paramMap);
		int totalCount = roleList.size();
		int size = inParam.getInt("size");
		int totalPage = totalCount%size == 0 ? totalCount/size : totalCount/size+1; // 计算总页数
		
		int page = inParam.getInt("page");
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		//页面初始化时page会等于0
		if(page == 0){
			page = 1;
		}
		for(int i=size*(page-1); i<size*page&&i<totalCount; i++) {
			list.add(roleList.get(i));
		}
		
		outParam.put("page", totalPage);
		outParam.put("total",totalCount);
		outParam.put("rows",list);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 删除角色
	 * @return
	 */
	private RETURN roleRemove(){
		IMap inParam = new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		
		String id = inParam.getString("id") == null ? "" : inParam.getString("id").toString();
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		if(id != null && id.length() > 0){
			paramMap.put("ID", id);
			MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
			myBatisDao.removeRoleList(paramMap);
			myBatisDao.deleteRoleList(paramMap);
		}
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 更新角色
	 * @return
	 */
	private RETURN roleUpdate(){
		HashMap<String, Object> outParam = this.getOutParam();
		HashMap<String, Object> inParam = this.getInParam();
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		String temp = inParam.get("ID") == null ? "" : inParam.get("ID").toString();
		int roleId = Integer.parseInt(temp);
		//获取角色名称
		HashMap<String, Object> temp1 = new HashMap<String,Object>();
		temp1.put("roleId", roleId);
		List<HashMap<String,Object>> roleNameList = myBatisDao.queryNameById(temp1);

		String roleName = roleNameList.get(0).get("roleName") == null ? "" : roleNameList.get(0).get("roleName").toString();
		outParam.put("roleName", roleName);
		this.setLogicView("roleUpdate");
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 编辑角色
	 * @return
	 */
	private RETURN roleEdit(){
		IMap inParam = new IMap(this.getInParam());
		
		TMRolesHome dao = (TMRolesHome)this.getDaoFactory().get("TMRolesHome");
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		TMRoles d = dao.findById(inParam.getInt("id"));
		if (d != null) {
			if (inParam.containsKey("roleName"))
				d.setName(inParam.getString("roleName"));
			if (inParam.containsKey("remark"))
				d.setRemark(inParam.getString("remark"));
			if (inParam.containsKey("updateStaff"))
				d.setUpdateStaffId(tmStaff.getId().toString());
			if (inParam.containsKey("updateTime"))
				d.setUpdateTime(inParam.getNow());
			d.setUpdateStaffId(tmStaff.getId().toString());
			dao.merge(d);
		}
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 删除角色权限
	 * @return
	 */
	private RETURN powerRemove(){
		IMap inParam = new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		
		String roleName = inParam.get("roleName") == null ? "" : inParam.get("roleName").toString();		
		String moduleIds_str = inParam.getString("moduleIds");
		String[] moduleIds = moduleIds_str.split(",");
		for(int i=0; i<moduleIds.length; i++){
			String moduleId = moduleIds[i];
			//获取角色id
			HashMap<String, Object> temp1 = new HashMap<String,Object>();
			temp1.put("roleName", roleName);
			List<HashMap<String,Object>> roleIdList = myBatisDao.queryRoleByName(temp1);
			
			String roleId = roleIdList.get(0).get("roleId") == null ? "" : roleIdList.get(0).get("roleId").toString();
			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			
			if(moduleId != null && moduleId.length() > 0){
				paramMap.put("moduleId", moduleId);
				paramMap.put("roleId", roleId);
				
				myBatisDao.removePowerList(paramMap);
			}
		}
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 删除角色菜单
	 * @return
	 */
	private RETURN menuRemove(){
		IMap inParam = new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		
		String roleName = inParam.get("roleName") == null ? "" : inParam.get("roleName").toString();		
		String menuId = inParam.getString("menuId");
		//获取角色id
		HashMap<String, Object> temp1 = new HashMap<String,Object>();
		temp1.put("roleName", roleName);
		List<HashMap<String,Object>> roleIdList = myBatisDao.queryRoleByName(temp1);
		
		String roleId = roleIdList.get(0).get("roleId") == null ? "" : roleIdList.get(0).get("roleId").toString();
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		if(menuId != null && menuId.length() > 0){
			paramMap.put("menuId", menuId);
			paramMap.put("roleId", roleId);
			
			myBatisDao.removeMenuList(paramMap);
		}
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 角色权限查询
	 * @return
	 */
	private RETURN powerQuery(){
		HashMap<String, Object> inParam = this.getInParam();
		HashMap<String, Object> outParam = this.getOutParam();
		
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		String roleName = inParam.get("roleName") == null ? "" : inParam.get("roleName").toString();
		String menuId = inParam.get("menuId") == null ? "" : inParam.get("menuId").toString();
		//获取角色id
		HashMap<String, Object> temp1 = new HashMap<String,Object>();
		temp1.put("roleName", roleName);
		List<HashMap<String,Object>> roleIdList = myBatisDao.queryRoleByName(temp1);
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("role_id", roleIdList.get(0).get("roleId"));
		paramMap.put("menuId", menuId);
		
		List<HashMap<String,Object>> powerList = myBatisDao.queryPowerList(paramMap);
		int count = powerList.size();
		
		outParam.put("total",count);
		outParam.put("rows",powerList);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 角色菜单查询
	 * @return
	 */
	private RETURN menuQuery(){
		HashMap<String, Object> inParam = this.getInParam();
		HashMap<String, Object> outParam = this.getOutParam();
		
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		String roleName = inParam.get("roleName") == null ? "" : inParam.get("roleName").toString();
		//获取角色id
		HashMap<String, Object> temp1 = new HashMap<String,Object>();
		temp1.put("roleName", roleName);
		List<HashMap<String,Object>> roleIdList = myBatisDao.queryRoleByName(temp1);
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("role_id", roleIdList.get(0).get("roleId"));
		
		List<HashMap<String,Object>> powerList = myBatisDao.queryMenuList(paramMap);
		int count = powerList.size();
		
		outParam.put("total",count);
		outParam.put("rows",powerList);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 添加角色权限
	 * @return
	 */
	private RETURN powerAdd(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		String power = inParam.get("power") == null ? "" : inParam.get("power").toString();
		
		//获取权限id
		HashMap<String, Object> temp1 = new HashMap<String,Object>();
		temp1.put("powerName", power);
		List<HashMap<String,Object>> powerIdList = myBatisDao.queryPowerIdList(temp1);
		
		//获取角色id
		HashMap<String, Object> temp2 = new HashMap<String,Object>();
		temp2.put("staffId", tmStaff.getStaffId());
		List<HashMap<String,Object>> roleIdList = myBatisDao.queryRoleIdList(temp2);
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleIdList.get(0).get("roleId"));
		paramMap.put("moduleId", powerIdList.get(0).get("moduleId"));
		paramMap.put("remark", power);
		paramMap.put("updateStaffId", tmStaff.getUpdateStaffId());
		paramMap.put("updateTime", inParam.getNow());
		
		myBatisDao.addPowerList(paramMap);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 添加角色
	 * @return
	 */
	private RETURN roleAdd(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		String roleName = inParam.get("roleName") == null ? "" : inParam.get("roleName").toString();
		String remark = inParam.get("remark") == null ? "" : inParam.get("remark").toString();
		//获取备注
		HashMap<String, Object> temp1 = new HashMap<String,Object>();
		temp1.put("roleName", roleName);
		List<HashMap<String,Object>> roleNameList = myBatisDao.queryRoleName(temp1);
		if(roleNameList.size() > 0){
			return new RETURN("10007", "该角色已存在！");
		}
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleName", roleName);
		paramMap.put("lvlId", "1");
		paramMap.put("parentId", 1);
		paramMap.put("remark", remark);
		paramMap.put("updateStaffId", tmStaff.getId());
		paramMap.put("updateTime", inParam.getNow());
		
		myBatisDao.addRoleList(paramMap);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 保存角色权限
	 * @return
	 */
	private RETURN roleSave(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		inParam.initPageParam(this.getPageUri());
		
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		String roleName = inParam.get("roleName") == null ? "" : inParam.get("roleName").toString();
		String moduleNames_str = inParam.getString("moduleNames");
		String[] moduleNames = moduleNames_str.split(",");
		for(int i=0; i<moduleNames.length; i++){
			String moduleName = moduleNames[i];
			
			//获取权限id
			HashMap<String, Object> temp1 = new HashMap<String,Object>();
			temp1.put("moduleName", moduleName);
			List<HashMap<String,Object>> powerIdList = myBatisDao.queryPowerIdList(temp1);
			String moduleId = powerIdList.get(0).get("moduleId") == null ? "" : powerIdList.get(0).get("moduleId").toString();
			
			//获取角色id
			HashMap<String, Object> temp2 = new HashMap<String,Object>();
			temp2.put("roleName", roleName);
			List<HashMap<String,Object>> roleIdList = myBatisDao.queryRoleByName(temp2);
			String roleId = roleIdList.get(0).get("roleId") == null ? "" : roleIdList.get(0).get("roleId").toString();
			
			//获取备注
			HashMap<String, Object> temp3 = new HashMap<String,Object>();
			temp3.put("moduleName", moduleName);
			temp3.put("roleId", roleId);
			List<HashMap<String,Object>> remarkList = myBatisDao.queryRemarkByName(temp3);
			if(remarkList == null || remarkList.size() == 0){
				return new RETURN("10007", "该权限已存在！");
			}
			String remark = remarkList.get(0).get("remark") == null ? "" : remarkList.get(0).get("remark").toString();
			
			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("roleId", roleId);
			paramMap.put("moduleId", moduleId);
			paramMap.put("remark", remark);
			paramMap.put("updateStaffId", tmStaff.getId());
			paramMap.put("updateTime", inParam.getNow());
			
			myBatisDao.saveRoleList(paramMap);
		}
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 保存角色菜单
	 * @return
	 */
	private RETURN menuSave(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		inParam.initPageParam(this.getPageUri());
		
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		String roleName = inParam.get("roleName") == null ? "" : inParam.get("roleName").toString();
		String menuId = inParam.getString("menuId");
		//获取角色id
		HashMap<String, Object> temp = new HashMap<String,Object>();
		temp.put("roleName", roleName);
		List<HashMap<String,Object>> roleIdList = myBatisDao.queryRoleByName(temp);
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("role_id", roleIdList.get(0).get("roleId"));
		paramMap.put("menuId", menuId);
		String roleId = roleIdList.get(0).get("roleId") == null ? "" : roleIdList.get(0).get("roleId").toString();
		
		List<HashMap<String,Object>> noPowerList = myBatisDao.queryNoPowerList(paramMap);
		
		for(int i=0; i<noPowerList.size(); i++){
			String moduleName = (String)noPowerList.get(i).get("powerName");
			
			//获取权限id
			HashMap<String, Object> temp1 = new HashMap<String,Object>();
			temp1.put("moduleName", moduleName);
			List<HashMap<String,Object>> powerIdList = myBatisDao.queryPowerIdList(temp1);
			String moduleId = powerIdList.get(0).get("moduleId") == null ? "" : powerIdList.get(0).get("moduleId").toString();
			
			//获取备注
			HashMap<String, Object> temp3 = new HashMap<String,Object>();
			temp3.put("moduleName", moduleName);
			temp3.put("roleId", roleId);
			List<HashMap<String,Object>> remarkList = myBatisDao.queryRemarkByName(temp3);
			if(remarkList == null || remarkList.size() == 0){
				return new RETURN("10007", "该权限已存在！");
			}
			String remark = remarkList.get(0).get("remark") == null ? "" : remarkList.get(0).get("remark").toString();
			
			HashMap<String,Object> paramMap1 = new HashMap<String, Object>();
			paramMap1.put("roleId", roleId);
			paramMap1.put("moduleId", moduleId);
			paramMap1.put("remark", remark);
			paramMap1.put("updateStaffId", tmStaff.getId());
			paramMap1.put("updateTime", inParam.getNow());
			
			myBatisDao.saveRoleList(paramMap1);
		}
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 角色未添加权限查询
	 * @return
	 */
	private RETURN noPowerQuery(){
		HashMap<String, Object> inParam = this.getInParam();
		HashMap<String, Object> outParam = this.getOutParam();
		
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		String roleName = inParam.get("roleName") == null ? "" : inParam.get("roleName").toString();
		String menuId = inParam.get("menuId") == null ? "" : inParam.get("menuId").toString();
		//获取角色id
		HashMap<String, Object> temp1 = new HashMap<String,Object>();
		temp1.put("roleName", roleName);
		List<HashMap<String,Object>> roleIdList = myBatisDao.queryRoleByName(temp1);
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("role_id", roleIdList.get(0).get("roleId"));
		paramMap.put("menuId", menuId);
		
		List<HashMap<String,Object>> noPowerList = myBatisDao.queryNoPowerList(paramMap);
		int count = noPowerList.size();
		outParam.put("total",count);
		outParam.put("rows",noPowerList);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 角色未添加菜单查询
	 * @return
	 */
	private RETURN noMenuQuery(){
		HashMap<String, Object> inParam = this.getInParam();
		HashMap<String, Object> outParam = this.getOutParam();
		
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		String roleName = inParam.get("roleName") == null ? "" : inParam.get("roleName").toString();
		//获取角色id
		HashMap<String, Object> temp1 = new HashMap<String,Object>();
		temp1.put("roleName", roleName);
		List<HashMap<String,Object>> roleIdList = myBatisDao.queryRoleByName(temp1);
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("role_id", roleIdList.get(0).get("roleId"));
		
		List<HashMap<String,Object>> noPowerList = myBatisDao.queryNoMenuList(paramMap);
		int count = noPowerList.size();
		outParam.put("total",count);
		outParam.put("rows",noPowerList);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 员工所属角色查询
	 * @return
	 */
	private RETURN staffAttQuery(){
		HashMap<String, Object> inParam = this.getInParam();
		HashMap<String, Object> outParam = this.getOutParam();
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		String staffId = inParam.get("staffId") == null ? "" : inParam.get("staffId").toString();
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		TMStaff tmStaff = (TMStaff)this.getSession().get("managerAuthBean");
		// 判断是否是省级管理员
		boolean isProvinceManager = false;
		if(tmStaff.getAreaCode().equals("000")){
			isProvinceManager = true;
		}
		
		paramMap.put("staffId", staffId);
		
		if(!isProvinceManager){
			// 地市管理员
			paramMap.put("areaCode", tmStaff.getAreaCode());
		}
		List<HashMap<String,Object>> staffAttList = myBatisDao.queryStaffAttList(paramMap);
		int count;
		if(staffAttList.size()>0){
			count = 1;
		} else {
			count = 0;
		}
		
		outParam.put("total",count);
		outParam.put("rows",staffAttList);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 解除角色
	 * @return
	 */
	private RETURN staffRemove(){
		IMap inParam = new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		
		String staffId = inParam.getString("staffId") == null ? "" : inParam.getString("staffId").toString();
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		
		// 判断是否是省级管理员
		boolean isProvinceManager = false;
		if(tmStaff.getAreaCode().equals("000")){
			isProvinceManager = true;
		}
		if(!isProvinceManager){
			// 地市管理员
			paramMap.put("areaCode", tmStaff.getAreaCode());
		}
		
		if(staffId != null && staffId.length() > 0){
			paramMap.put("staffId", staffId);
			MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
			myBatisDao.removeStaffList(paramMap);
		}
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 更换角色
	 * @return
	 */
	private RETURN roleChange(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String,Object> paramTemp = new HashMap<String,Object>();
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		
		// 判断是否是省级管理员
		boolean isProvinceManager = false;
		if(tmStaff.getAreaCode().equals("000")){
			isProvinceManager = true;
		}
		if(!isProvinceManager){
			// 地市管理员
			paramTemp.put("areaCode", tmStaff.getAreaCode());
		}
		
		String staffId = inParam.getString("staffId") == null ? "" : inParam.getString("staffId").toString();
		String temp = inParam.getString("roleId") == null ? "" : inParam.getString("roleId").toString();
		int roleId = Integer.parseInt(temp);
		
		//查询员工编号
		paramTemp.put("staffId", staffId);
		List<HashMap<String,Object>> ids = myBatisDao.queryStaffId(paramTemp);
		if(ids == null || ids.size() == 0){
			return new RETURN("10007", "该员工不存在！");
		}
		String temp2 = ids.get(0).get("id").toString();
		int id = Integer.parseInt(temp2);
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		if(staffId != null && staffId.length() > 0){
			paramMap.put("id", id);
			paramMap.put("staffId", staffId);
			paramMap.put("roleId", roleId);
			paramMap.put("remark", "");
			paramMap.put("updateStaffId", tmStaff.getId());
			paramMap.put("updateTime", inParam.getNow());
			myBatisDao.deleteStaffList(paramMap);
			myBatisDao.changeRoleList(paramMap);
		}
		
		return RETURN.SUCCESS;
	}
}
