package org.leafframework.mvc.service.manager.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.orm.TMRoleModule;
import org.leafframework.data.dao.orm.TMRoleModuleHome;
import org.leafframework.data.dao.orm.TMRoles;
import org.leafframework.data.dao.orm.TMRolesHome;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.data.dao.orm.TMStaffRole;
import org.leafframework.data.dao.orm.TMStaffRoleHome;
import org.leafframework.mvc.model.IMap;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.RETURN;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * 角色管理
 * @author liuxm
 *
 */
@Service("TMRolesMgr")
@Scope("prototype")
public class TMRolesMgr extends Business{
	
	@Override
	public RETURN execute() throws Exception {
		if("roleAdd".equals(this.getPageUri().get("f"))){
			return roleAdd();//新增角色
		}else if("roleSave".equals(this.getPageUri().get("f"))){
			return roleSave();//保存角色信息
		}else if("roleEdit".equals(this.getPageUri().get("f"))){
			return roleEdit();//编辑角色
		}else if("roleUpdate".equals(this.getPageUri().get("f"))){
			return roleUpdate();//更新角色
		}else if("roleRemove".equals(this.getPageUri().get("f"))){
			return roleRemove();//删除角色
		}else if("roleModuleSave".equals(this.getPageUri().get("f"))){
			return roleModuleSave();//模块角色
		}else if("staffRoleSave".equals(this.getPageUri().get("f"))){
			return staffRoleSave();//用户角色
		}
		return RETURN.REQ_ACTION_ERROR;
	}
	
	@Override
	public RETURN init() throws Exception {
		return null;
	}
	
	@Override
	public RETURN query() throws Exception {
		if("roleQuery".equals(this.getPageUri().get("f"))){
			return roleQuery();//角色列表查询
		}else if("roleTreeQuery".equals(this.getPageUri().get("f"))){
			return roleTreeQuery();//roleTree查询
		}else if("tz".equals(this.getPageUri().get("f"))){
			return tz();//..
		}
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 新增角色
	 * @return
	 */
	private RETURN roleAdd() {
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		this.setLogicView("/manage/system/roleAdd");
		
		//outParam.put("","");
		return RETURN.SUCCESS;
	}
	
	/**
	 * 保存角色信息
	 * @return
	 */
	private RETURN roleSave() {
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		
		TMRoles r=new TMRoles();
		r.setName(inParam.getString("Name"));
		r.setLvlId(inParam.getString("LvlId"));
		r.setParentId(inParam.getInt("ParentId"));
		r.setRemark(inParam.getString("Remark"));
		r.setUpdateStaffId(inParam.getString("UpdateStaffId"));
		r.setUpdateTime(inParam.getNow());
		
		TMRolesHome dao=(TMRolesHome)this.getDaoFactory().get("TMRolesHome");
		dao.persist(r);
		
		//System.out.println("save role");
		outParam.put("id", r.getId());
		return RETURN.SUCCESS;
	}
	
	/**
	 * 编辑角色
	 * @return
	 */
	private RETURN roleEdit() {
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		
		TMRolesHome dao=(TMRolesHome)this.getDaoFactory().get("TMRolesHome");
		TMRoles r=dao.findById(inParam.getInt("id"));
		
		outParam.put("data",r);
		//System.out.println("edit role");
		this.setLogicView("/manage/system/roleEdit");
		return RETURN.SUCCESS;
	}
	
	/**
	 * 更新角色
	 * @return
	 */
	private RETURN roleUpdate() {
		IMap inParam=new IMap(this.getInParam());
		
		TMRolesHome dao=(TMRolesHome)this.getDaoFactory().get("TMRolesHome");
		TMRoles r=dao.findById(inParam.getInt("id"));
		
		if(r!=null){
			
			if(!StringUtils.isEmpty(inParam.getString("Name"))){
				r.setName(inParam.getString("Name"));
			}
			if(!StringUtils.isEmpty(inParam.getString("LvlId"))){
				r.setLvlId(inParam.getString("LvlId"));
			}
			if(0 != inParam.getInt("ParentId")){
				r.setParentId(inParam.getInt("ParentId"));
			}
			if(!StringUtils.isEmpty(inParam.getString("Remark"))){
				r.setRemark(inParam.getString("Remark"));
			}
			if(!StringUtils.isEmpty(inParam.getString("UpdateStaffId"))){
				r.setUpdateStaffId(inParam.getString("UpdateStaffId"));
			}
			r.setUpdateTime(inParam.getNow());
			
			dao.merge(r);
		}
		//System.out.println("update role");
		return RETURN.SUCCESS;
	}
	
	/**
	 * 删除角色
	 * @return
	 */
	private RETURN roleRemove() {
		IMap inParam=new IMap(this.getInParam());
		
		TMRolesHome dao=(TMRolesHome)this.getDaoFactory().get("TMRolesHome");
		TMRoles s=dao.findById(inParam.getInt("id"));
		if(s!=null){
			dao.delete(s);
		}
		
		//System.out.println("delete role");
		return RETURN.SUCCESS;
	}
	
	/**
	 * 角色列表查询
	 * @return
	 */
	private RETURN roleQuery() {
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		
		MyBatisDAO mybatisDao=(MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		List<HashMap<String, Object>> list=mybatisDao.roleQuery(inParam);
		
		int totalCount = mybatisDao.roleCountQuery(inParam);
		int size=inParam.getInt("size");
		int page=inParam.getInt("page");
		int totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1; // 计算总页数
		int index = (page - 1) * size; // 开始记录数

		outParam.put("records", totalCount);
		outParam.put("total", totalPage);
		outParam.put("page", page);
		outParam.put("index", index);
		outParam.put("list", list);
		
		//System.out.println("query role");
		
		this.setLogicView("/manage/system/roleQuery");
		return RETURN.SUCCESS;
	}
	
	/**
	 * 模块角色
	 * @return
	 */
	private RETURN roleModuleSave() {
		//todo
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		
		String checkModule = inParam.getString("moduleIdArray");
		String[] ss = checkModule.split(",");
		for (int i = 0; i < ss.length; i++) {
			TMRoleModule r=new TMRoleModule();
			r.setRoleId(inParam.getInt("RoleId"));
			r.setModuleId(Integer.parseInt(ss[i]));
			r.setRemark(inParam.getString("Remark"));
			r.setUpdateStaffId(inParam.getString("UpdateStaffId"));
			r.setUpdateTime(inParam.getNow());
			
			TMRoleModuleHome dao=(TMRoleModuleHome)this.getDaoFactory().get("TMRoleModuleHome");
			dao.persist(r);
			outParam.put("id", r.getId());
			
			//System.out.println("module role");
			
		}
		return RETURN.SUCCESS;
	}
	
	private RETURN tz(){
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		
		TMStaffRoleHome dao=(TMStaffRoleHome)this.getDaoFactory().get("TMStaffRoleHome");
		TMStaffRole instance = new TMStaffRole();
		instance.setStaffId(inParam.get("staffId").toString());
		List list = dao.findByExample(instance);
		if(list.size()==1){
			instance = (TMStaffRole) list.get(0);
			outParam.put("staffRoleId", instance.getId());
			outParam.put("roleId", instance.getRoleId());
		}
		this.setLogicView("/manage/system/roleTreeQuery");
		outParam.put("staffId", inParam.get("staffId"));
		return RETURN.SUCCESS;
	}
	
	/**
	 * 获取角色TREE JSON
	 * @return
	 */
	private RETURN roleTreeQuery() {
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		TMRolesHome tmRolesHome = (TMRolesHome) this.getDaoFactory().get("TMRolesHome");
		MyBatisDAO mybatisDao=(MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		List<TMRoles> list=tmRolesHome.findAll();
		List<HashMap<String, Object>> treeJson = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> treeM = new HashMap<String, Object>();
			TMRoles tt = list.get(i);
			//System.out.println(tt.getParentId());
			if(tt.getParentId()==0){
				treeM.put("id", tt.getId());
				treeM.put("text", tt.getName());
				HashMap<String, Object> queryMap = new HashMap<String, Object>();
				queryMap.put("parentId", tt.getId());
				List<HashMap<String, Object>> childList=mybatisDao.roleTreeQuery(queryMap);
				List<HashMap<String, Object>> treeJson1 = new ArrayList<HashMap<String,Object>>();
				for (int j = 0; j < childList.size(); j++) {
					HashMap<String, Object> m1 = childList.get(j);
					m1.put("text", m1.get("name"));
					treeJson1.add(m1);
				}
				treeM.put("children", treeJson1);
				treeJson.add(treeM);
			}else{
//				treeM.put("id", tt.getId());
//				treeM.put("text", tt.getName());
//				treeM.put("children", null);
			}
		}

		outParam.put("list", treeJson);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 用户角色
	 * @return
	 */
	private RETURN staffRoleSave() {
		IMap inParam=new IMap(this.getInParam());
		
		//后台登陆用户
		TMStaffRoleHome dao=(TMStaffRoleHome)this.getDaoFactory().get("TMStaffRoleHome");
		
		TMStaff staff = (TMStaff)this.getSession().get("managerAuthBean"); 
		TMStaffRole instance = new TMStaffRole();
		instance.setStaffId(inParam.getString("staffId"));
		List list = dao.findByExample(instance);
		if(list.size()>0){
			instance = (TMStaffRole) list.get(0);
			instance.setStaffId(inParam.getString("staffId"));
			instance.setRoleId(Integer.parseInt(inParam.getString("roleId")));
			//instance.setRemark("");
			instance.setUpdateStaffId(staff.getStaffId());
			instance.setUpdateTime(inParam.getNow());
			dao.merge(instance);
		}else{
			instance.setStaffId(inParam.getString("staffId"));
			instance.setRoleId(Integer.parseInt(inParam.getString("roleId")));
			instance.setRemark("");
			instance.setUpdateStaffId(staff.getStaffId());
			instance.setUpdateTime(inParam.getNow());
			dao.persist(instance);
		}
		
		return RETURN.SUCCESS;
	}
	
}
