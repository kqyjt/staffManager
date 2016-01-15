package org.leafframework.mvc.service.manager.roleright;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.orm.TMMenus;
import org.leafframework.data.dao.orm.TMMenusHome;
import org.leafframework.data.dao.orm.TMModules;
import org.leafframework.data.dao.orm.TMModulesHome;
import org.leafframework.data.dao.orm.TMRoleModule;
import org.leafframework.data.dao.orm.TMRoleModuleHome;
import org.leafframework.data.dao.orm.TMRoleRight;
import org.leafframework.data.dao.orm.TMRoleRightHome;
import org.leafframework.data.dao.orm.TMRoles;
import org.leafframework.data.dao.orm.TMRolesHome;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.data.dao.orm.TMStaffRight;
import org.leafframework.data.dao.orm.TMStaffRightHome;
import org.leafframework.data.dao.orm.TMStaffRole;
import org.leafframework.data.dao.orm.TMStaffRoleHome;
import org.leafframework.mvc.model.IMap;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.RETURN;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 权限管理
 * @author liuxm
 *
 */
@Service("TMRoleRightMgr")
@Scope("prototype")
public class TMRoleRightMgr extends Business{
	
	@Override
	public RETURN execute() throws Exception {
		if("roleRightAdd".equals(this.getPageUri().get("f"))){
			return roleRightAdd();//新增权限
		}else if("roleRightSave".equals(this.getPageUri().get("f"))){
			return roleRightSave();//保存权限信息
		}else if("roleRightEdit".equals(this.getPageUri().get("f"))){
			return roleRightEdit();//编辑权限
		}else if("roleRightUpdate".equals(this.getPageUri().get("f"))){
			return roleRightUpdate();//更新权限
		}else if("roleRightRemove".equals(this.getPageUri().get("f"))){
			return roleRightRemove();//删除权限
		}else if("staffRightSave".equals(this.getPageUri().get("f"))){
			return staffRightSave();//用户权限
		}else if("modulesRightSave".equals(this.getPageUri().get("f"))){
			return modulesRightSave();//模块权限
		}
		
		return RETURN.REQ_ACTION_ERROR;
	}
	
	@Override
	public RETURN init() throws Exception {
		return null;
	}
	
	@Override
	public RETURN query() throws Exception {
		if("roleRightQuery".equals(this.getPageUri().get("f"))){
			return roleRightQuery();//权限列表查询
		}else if("rightListQuery".equals(this.getPageUri().get("f"))){
			return rightListQuery();//right list查询
		}else if("qx".equals(this.getPageUri().get("f"))){
			return qx();
		}
		return RETURN.SUCCESS;
	}
	
	/**
	 * 新增权限
	 * @return
	 */
	private RETURN roleRightAdd() {
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		this.setLogicView("/manage/system/roleRightAdd");
		
		//outParam.put("","");
		return RETURN.SUCCESS;
	}
	
	/**
	 * 保存权限信息
	 * @return
	 */
	private RETURN roleRightSave() {
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		
		TMRoleRight r=new TMRoleRight();
		r.setRoleId(inParam.getInt("RoleId"));
		r.setResType(inParam.getString("ResType"));
		r.setResId(inParam.getInt("ResId"));
		r.setUpdateStaffId(inParam.getString("UpdateStaffId"));
		r.setUpdateTime(inParam.getNow());
		
		TMRoleRightHome dao=(TMRoleRightHome)this.getDaoFactory().get("TMRoleRightHome");
		dao.persist(r);
		
		//System.out.println("save role right");
		outParam.put("id", r.getId());
		return RETURN.SUCCESS;
	}
	
	/**
	 * 编辑权限
	 * @return
	 */
	private RETURN roleRightEdit() {
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		
		TMRoleRightHome dao=(TMRoleRightHome)this.getDaoFactory().get("TMRoleRightHome");
		TMRoleRight r=dao.findById(inParam.getInt("id"));
		
		outParam.put("data",r);
		//System.out.println("edit role right");
		this.setLogicView("/manage/system/roleRightEdit");
		return RETURN.SUCCESS;
	}
	
	/**
	 * 更新权限
	 * @return
	 */
	private RETURN roleRightUpdate() {
		IMap inParam=new IMap(this.getInParam());
		
		TMRoleRightHome dao=(TMRoleRightHome)this.getDaoFactory().get("TMRoleRightHome");
		TMRoleRight r=dao.findById(inParam.getInt("id"));
		
		if(r!=null){
			
			if(0 != inParam.getInt("RoleId")){
				r.setResId(inParam.getInt("RoleId"));
			}
			if(!StringUtils.isEmpty(inParam.getString("ResType"))){
				r.setResType(inParam.getString("ResType"));
			}
			if(0 != inParam.getInt("ResId")){
				r.setResId(inParam.getInt("ResId"));
			}
			if(!StringUtils.isEmpty(inParam.getString("UpdateStaffId"))){
				r.setUpdateStaffId(inParam.getString("UpdateStaffId"));
			}
			r.setUpdateTime(inParam.getNow());
			
			dao.merge(r);
		}
		//System.out.println("update role right");
		return RETURN.SUCCESS;
	}
	
	/**
	 * 删除权限
	 * @return
	 */
	private RETURN roleRightRemove() {
		IMap inParam=new IMap(this.getInParam());
		
		TMRoleRightHome dao=(TMRoleRightHome)this.getDaoFactory().get("TMRoleRightHome");
		TMRoleRight s=dao.findById(inParam.getInt("id"));
		if(s!=null){
			dao.delete(s);
		}
		
		//System.out.println("delete role right");
		return RETURN.SUCCESS;
	}
	
	/**
	 * 权限列表查询
	 * @return
	 */
	private RETURN roleRightQuery() {
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		
		MyBatisDAO mybatisDao=(MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		List<HashMap<String, Object>> list=mybatisDao.roleRightQuery(inParam);
		
		int totalCount = mybatisDao.roleRightCountQuery(inParam);
		int size=inParam.getInt("size");
		int page=inParam.getInt("page");
		int totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1; // 计算总页数
		int index = (page - 1) * size; // 开始记录数

		outParam.put("records", totalCount);
		outParam.put("total", totalPage);
		outParam.put("page", page);
		outParam.put("index", index);
		outParam.put("list", list);
		
		//System.out.println("query role right");
		
		this.setLogicView("/manage/system/roleRightQuery");
		return RETURN.SUCCESS;
	}
	
	private RETURN qx(){
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		
		TMRoleModuleHome dao=(TMRoleModuleHome)this.getDaoFactory().get("TMRoleModuleHome");
		TMRoleModule instance = new TMRoleModule();
		instance.setRoleId(Integer.parseInt(inParam.get("roleId").toString()));
		List list = dao.findByExample(instance);
		if(list.size()> 0){
			String s = "";
			for(int i = 0; i<list.size(); i++){
				instance = (TMRoleModule) list.get(i);
				
				 if (s != "") s += ',';
				 s += instance.getModuleId();
			}
			outParam.put("moduleId", s);
		}
		this.setLogicView("/manage/system/rightListQuery");
		outParam.put("roleId", inParam.get("roleId"));
		return RETURN.SUCCESS;
	}
	
	/**
	 * 获取权限list
	 */
	private RETURN rightListQuery(){
		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		
		TMMenusHome tmMenusHome = (TMMenusHome) this.getDaoFactory().get("TMMenusHome");
		List<TMMenus> listMenus=tmMenusHome.findAll();
		List<HashMap<String, Object>> treeMenus = new ArrayList<HashMap<String,Object>>();
		for(int i = 0; i < listMenus.size(); i++){
			HashMap<String, Object> treeM = new HashMap<String, Object>();
			TMMenus tt = listMenus.get(i);
			treeM.put("id", tt.getId());
			treeM.put("text", tt.getName());
			
			TMModulesHome tmModulesHome = (TMModulesHome) this.getDaoFactory().get("TMModulesHome");
			MyBatisDAO mybatisDao=(MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
			
			List<TMModules> list=tmModulesHome.findAll();
			List<HashMap<String, Object>> treeJson = new ArrayList<HashMap<String,Object>>();
			for (int j = 0; j < list.size(); j++) {
				TMModules tm = list.get(j);
				if(tt.getId().equals(tm.getMenuId())){
					HashMap<String, Object> treeMod = new HashMap<String, Object>();
					
					treeMod.put("id", tm.getId());
					treeMod.put("text", tm.getName());
					treeJson.add(treeMod);
				}
				
			}
			
			treeM.put("children", treeJson);
			treeMenus.add(treeM);
		}
		
		outParam.put("list", treeMenus);

		return RETURN.SUCCESS;
	}
	
	/**
	 * 模块权限
	 * @return
	 */
	private RETURN modulesRightSave() {
		//HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		
		//后台登陆用户
		TMRoleModuleHome dao=(TMRoleModuleHome)this.getDaoFactory().get("TMRoleModuleHome");
		
		TMStaff staff = (TMStaff)this.getSession().get("managerAuthBean");
		
		TMRoleModule instance = new TMRoleModule();
		instance.setRoleId(Integer.parseInt(inParam.getString("roleId")));
		
		String[] str1 = inParam.getString("moduleId").split(",");
		List list = dao.findByExample(instance);
		if(list.size()>0){
			//在原来已有权限的基础上新增权限
			String s="";
			//更新原来选中的权限
			for(int i = 0; i< list.size();i++){
				for(int j = 0; j< str1.length; j++){
					instance = (TMRoleModule) list.get(i);
					if(instance.getModuleId().equals(Integer.parseInt(str1[j]))){
						instance.setRoleId(Integer.parseInt(inParam.getString("roleId")));
						instance.setModuleId(Integer.parseInt(str1[j]));
						//instance.setRemark("");
						instance.setUpdateStaffId(staff.getStaffId());
						instance.setUpdateTime(inParam.getNow());
						dao.merge(instance);
						if (s != "") s += ',';
						 s += str1[j];
					}

				}
				
			}
			//添加原来没有的权限
			if(s.length()>0){
				for(int j = 0; j< str1.length; j++){
					int result=s.indexOf(str1[j]);
					if(result < 0){
						TMRoleModule roleModule1 = new TMRoleModule();
						roleModule1.setRoleId(Integer.parseInt(inParam.getString("roleId")));
						roleModule1.setModuleId(Integer.parseInt(str1[j]));
						roleModule1.setRemark("");
						roleModule1.setUpdateStaffId(staff.getStaffId());
						roleModule1.setUpdateTime(inParam.getNow());
						dao.persist(roleModule1);
					}
				}
			}
		}else{
			//第一次为角色分配权限
			for(int k = 0; k< str1.length; k++){
				TMRoleModule roleModule = new TMRoleModule();
				roleModule.setRoleId(Integer.parseInt(inParam.getString("roleId")));
				roleModule.setModuleId(Integer.parseInt(str1[k]));
				roleModule.setRemark("");
				roleModule.setUpdateStaffId(staff.getStaffId());
				roleModule.setUpdateTime(inParam.getNow());
				dao.persist(roleModule);
			}
		}
				
		return RETURN.SUCCESS;

	}
	
	/**
	 * 用户权限
	 * @return
	 */
	private RETURN staffRightSave() {
		//HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam=new IMap(this.getInParam());
		
		//后台登陆用户
		TMStaffRightHome dao=(TMStaffRightHome)this.getDaoFactory().get("TMStaffRightHome");
		
		TMStaff staff = (TMStaff)this.getSession().get("managerAuthBean");
		String[] str1 = inParam.getString("resTyperesID").split(",");
		
		TMStaffRight instance = new TMStaffRight();
		instance.setStaffId(inParam.getString("staffId"));
		List list = dao.findByExample(instance);
		if(list.size()>0){
			instance = (TMStaffRight) list.get(0);
			instance.setStaffId(inParam.getString("staffId"));
			instance.setResType(str1[0]);
			instance.setResId(Integer.parseInt(str1[1]));
			//instance.setResType(inParam.getString("resType"));
			//instance.setResId(Integer.parseInt(inParam.getString("resId")));
			instance.setUpdateStaffId(staff.getStaffId());
			instance.setUpdateTime(inParam.getNow());
			dao.merge(instance);
		}else{
			instance.setStaffId(inParam.getString("staffId"));
			instance.setResType(str1[0]);
			instance.setResId(Integer.parseInt(str1[1]));
			//instance.setResType(inParam.getString("resType"));
			//instance.setResId(Integer.parseInt(inParam.getString("resId")));
			instance.setUpdateStaffId(staff.getStaffId());
			instance.setUpdateTime(inParam.getNow());
			dao.persist(instance);
		}
		
		return RETURN.SUCCESS;

	}
	
	

}
