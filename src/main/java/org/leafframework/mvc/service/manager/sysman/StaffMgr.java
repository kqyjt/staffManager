package org.leafframework.mvc.service.manager.sysman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.leafframework.constant.AreaConstant;
import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.data.dao.orm.TMStaffHome;
import org.leafframework.mvc.model.IMap;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.RETURN;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("StaffMgr")
@Scope("prototype")
public class StaffMgr extends Business{

	@Override
	public RETURN execute() throws Exception {
		if("staffRemove".equals(this.getPageUri().get("f"))){
			return staffRemove(); //删除员工
		} else if("staffEdit".equals(this.getPageUri().get("f"))){
			return staffEdit(); //编辑员工信息
		} else if("staffAdd".equals(this.getPageUri().get("f"))){
			return staffAdd(); //添加员工
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
		if("staffQuery".equals(this.getPageUri().get("f"))){
			return staffQuery(); //员工信息查询
		}
		
		return RETURN.SUCCESS;
	}

	/**
	 * 员工信息查询
	 * @return
	 */
	private RETURN staffQuery(){
		IMap inParam = new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		HashMap<String, Object> outParam = this.getOutParam();
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		String staffId = inParam.get("staffId") == null ? "" : inParam.get("staffId").toString();
		String name = inParam.get("name") == null ? "" : inParam.get("name").toString();
		
		// 是否省份管理员
		boolean isProvinceManager = false; 
		if (tmStaff.getAreaCode().equals("000")) {
			isProvinceManager = true;
		}
		if(!isProvinceManager){
			// 地市管理员
			paramMap.put("areaCode", tmStaff.getAreaCode());
		} else {
			// 省份管理员
			String areaCode = inParam.get("areaCode") == null ? "" : inParam.get("areaCode").toString();
			paramMap.put("areaCode", areaCode);
		}
		
		paramMap.put("staffId", staffId);
		paramMap.put("name", name);
		
		List<HashMap<String,Object>> roleList = myBatisDao.staffQuery(paramMap);
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
		
		if(!isProvinceManager){
			//获取城市名
			HashMap<String, Object> temp = new HashMap<String,Object>();
			temp.put("areaCode", tmStaff.getAreaCode().toString());
			List<HashMap<String,Object>> homeCityList = myBatisDao.queryHomeCity(temp);
			outParam.put("areaCode", tmStaff.getAreaCode().toString());
			outParam.put("homeCity", homeCityList.get(0).get("homeCity")+"市");
		}
		outParam.put("page", totalPage);
		outParam.put("total",totalCount);
		outParam.put("rows",list);
		
		outParam.put("area", AreaConstant.getAreaNameByCode(tmStaff.getAreaCode()));
		String staffCityName = AreaConstant.getAreaNameByCode().get(tmStaff.getAreaCode()).toString();
		if(tmStaff.getAreaCode().equals("000")){
			outParam.put("isProManager", "yes");
		} else {
			outParam.put("isProManager", "no");
			outParam.put("code", tmStaff.getAreaCode());
			outParam.put("name", staffCityName);
		}
		this.setLogicView("staffMgr");
		return RETURN.SUCCESS;
	}
	
	/**
	 * 删除员工
	 * @return
	 */
	private RETURN staffRemove(){
		IMap inParam=new IMap(this.getInParam());
		
		TMStaffHome dao=(TMStaffHome)this.getDaoFactory().get("TMStaffHome");
		TMStaff s=dao.findById(inParam.getInt("id"));
		if(s!=null){
			dao.delete(s);
		}
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 编辑员工信息
	 * @return
	 */
	private RETURN staffEdit(){
		IMap inParam = new IMap(this.getInParam());
		
		TMStaffHome dao = (TMStaffHome)this.getDaoFactory().get("TMStaffHome");
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		TMStaff d = dao.findById(inParam.getInt("id"));
		if(d!=null){
			if(inParam.containsKey("staffId"))d.setStaffId(inParam.getString("staffId"));
			if(inParam.containsKey("name"))d.setName(inParam.getString("name"));
			if(inParam.containsKey("areaCode"))d.setAreaCode(inParam.getString("areaCode"));
			if(inParam.containsKey("phoneNumber"))d.setPhoneNumber(inParam.getString("phoneNumber"));
			if(inParam.containsKey("email"))d.setEmail(inParam.getString("email"));
			if(inParam.containsKey("updateStaff"))d.setUpdateStaffId(tmStaff.getId().toString());
			if(inParam.containsKey("updateTime"))d.setUpdateTime(inParam.getNow());
			d.setUpdateStaffId(tmStaff.getId().toString());
			dao.merge(d);
		}
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 添加员工
	 * @return
	 */
	private RETURN staffAdd(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		TMStaff tmStaff = (TMStaff) this.getSession().get("managerAuthBean"); // 登陆用户信息
		
		String staffId = inParam.get("staffId") == null ? "" : inParam.get("staffId").toString();
		String name = inParam.get("name") == null ? "" : inParam.get("name").toString();
		String phoneNumber = inParam.get("phoneNumber") == null ? "" : inParam.get("phoneNumber").toString();
		String email = inParam.get("email") == null ? "" : inParam.get("email").toString();
		
		//获取员工工号
		HashMap<String, Object> temp1 = new HashMap<String,Object>();
		temp1.put("staffId", staffId);
		List<HashMap<String,Object>> staffIdList = myBatisDao.queryStaffId(temp1);
		if(staffIdList.size() > 0){
			return new RETURN("10007", "该员工已存在！");
		}
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		// 是否省份管理员
		boolean isProvinceManager = false; 
		if (tmStaff.getAreaCode().equals("000")) {
			isProvinceManager = true;
		}
		if(!isProvinceManager){
			// 地市管理员
			paramMap.put("areaCode", tmStaff.getAreaCode());
		} else {
			// 省份管理员
			String areaCode = inParam.get("areaCode") == null ? "" : inParam.get("areaCode").toString();
			paramMap.put("areaCode", areaCode);
		}
		paramMap.put("staffId", staffId);
		paramMap.put("name", name);
		paramMap.put("isChangePass", "00");
		paramMap.put("password", "3357a9aaebfb2ebab73eed56281dd09c");
		paramMap.put("oldPass", "3357a9aaebfb2ebab73eed56281dd09c");
		paramMap.put("gender", "");
		paramMap.put("phoneNumber", phoneNumber);
		paramMap.put("email", email);
		paramMap.put("updateStaffId", tmStaff.getId());
		paramMap.put("updateTime", inParam.getNow());
		
		myBatisDao.addStaffList(paramMap);
		
		return RETURN.SUCCESS;
	}
	
}
