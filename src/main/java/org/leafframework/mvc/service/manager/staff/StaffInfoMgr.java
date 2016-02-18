package org.leafframework.mvc.service.manager.staff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.mvc.model.IMap;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.RETURN;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("StaffInfoMgr")
@Scope("prototype")
public class StaffInfoMgr extends Business{

	@Override
	public RETURN execute() throws Exception {
		if("staffInfoRemove".equals(this.getPageUri().get("f"))){
			return staffInfoRemove(); //删除员工
		} else if("staffInfoEdit".equals(this.getPageUri().get("f"))){
			return staffInfoEdit(); //编辑员工信息
		} else if("staffInfoAdd".equals(this.getPageUri().get("f"))){
			return staffInfoAdd(); //添加员工
		} else if("staffInfoMgr".equals(this.getPageUri().get("f"))){
			return staffInfoMgr(); //员工基本信息管理
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
		if("staffInfoQuery".equals(this.getPageUri().get("f"))){
			return staffInfoQuery(); //员工信息查询
		}
		
		return RETURN.SUCCESS;
	}

	/**
	 * 员工信息管理
	 * @return
	 */
	private RETURN staffInfoMgr(){
		HashMap<String, Object> outParam = this.getOutParam();
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> apartmentList = myBatisDAO.queryApartmentList(paramMap);
		outParam.put("apartmentList", apartmentList);
		outParam.put("apartmentSize", apartmentList.size());
		this.setLogicView("staffInfoMgr");
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 员工信息查询
	 * @return
	 */
	private RETURN staffInfoQuery(){
		IMap inParam = new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		HashMap<String, Object> outParam = this.getOutParam();
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		String staffInfoId = inParam.get("staffInfoId") == null ? "" : inParam.get("staffInfoId").toString();
		String staffInfoName = inParam.get("staffInfoName") == null ? "" : inParam.get("staffInfoName").toString();
		String staffIdCard = inParam.get("staffIdCard") == null ? "" : inParam.get("staffIdCard").toString();
		String staffApartment = inParam.get("staffApartment") == null ? "" : inParam.get("staffApartment").toString();
		
		paramMap.put("staffInfoId", staffInfoId);
		paramMap.put("staffInfoName", staffInfoName);
		paramMap.put("staffIdCard", staffIdCard);
		paramMap.put("staffApartment", staffApartment);
		
		List<HashMap<String,Object>> staffList = myBatisDao.staffInfoQuery(paramMap);
		int totalCount = staffList.size();
		int size = inParam.getInt("size");
		int totalPage = totalCount%size == 0 ? totalCount/size : totalCount/size+1; // 计算总页数
		
		int page = inParam.getInt("page");
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		//页面初始化时page会等于0
		if(page == 0){
			page = 1;
		}
		for(int i=size*(page-1); i<size*page&&i<totalCount; i++) {
			list.add(staffList.get(i));
		}
		
		outParam.put("page", totalPage);
		outParam.put("total",totalCount);
		outParam.put("rows",list);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 删除员工
	 * @return
	 */
	private RETURN staffInfoRemove(){
		IMap inParam=new IMap(this.getInParam());
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", inParam.getInt("id"));
		myBatisDAO.removeStaffInfo(paramMap);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 编辑员工信息
	 * @return
	 */
	private RETURN staffInfoEdit(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", inParam.getInt("id"));
		paramMap.put("staffInfoId", inParam.getString("staffInfoId"));
		paramMap.put("staffInfoName", inParam.getString("staffInfoName"));
		paramMap.put("sex", inParam.getString("sex"));
		paramMap.put("nation", inParam.getString("nation"));
		paramMap.put("idcard", inParam.getString("idcard"));
		paramMap.put("birthday", inParam.getString("birthday"));
		paramMap.put("address", inParam.getString("address"));
		paramMap.put("birthplace", inParam.getString("birthplace"));
		paramMap.put("diploma", inParam.getString("diploma"));
		paramMap.put("graduation", inParam.getString("graduation"));
		paramMap.put("special", inParam.getString("special"));
		paramMap.put("marriage", inParam.getString("marriage"));
		paramMap.put("apartment", inParam.getString("apartment"));
		paramMap.put("post", inParam.getString("post"));
		paramMap.put("updatetime", inParam.getNow());
		myBatisDAO.updateStaffInfo(paramMap);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 添加员工
	 * @return
	 */
	private RETURN staffInfoAdd(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		
		String staffInfoId = inParam.get("staffInfoId") == null ? "" : inParam.get("staffInfoId").toString();
		String staffInfoName = inParam.get("staffInfoName") == null ? "" : inParam.get("staffInfoName").toString();
		String sex = inParam.get("sex") == null ? "" : inParam.get("sex").toString();
		String nation = inParam.get("nation") == null ? "" : inParam.get("nation").toString();
		String idcard = inParam.get("idcard") == null ? "" : inParam.get("idcard").toString();
		String birthday = inParam.get("birthday") == null ? "" : inParam.get("birthday").toString();
		String address = inParam.get("address") == null ? "" : inParam.get("address").toString();
		String birthplace = inParam.get("birthplace") == null ? "" : inParam.get("birthplace").toString();
		String diploma = inParam.get("diploma") == null ? "" : inParam.get("diploma").toString();
		String graduation = inParam.get("graduation") == null ? "" : inParam.get("graduation").toString();
		String special = inParam.get("special") == null ? "" : inParam.get("special").toString();
		String marriage = inParam.get("marriage") == null ? "" : inParam.get("marriage").toString();
		String apartment = inParam.get("apartment") == null ? "" : inParam.get("apartment").toString();
		String post = inParam.get("post") == null ? "" : inParam.get("post").toString();
		
		//获取员工工号
		HashMap<String, Object> temp1 = new HashMap<String,Object>();
		temp1.put("staffInfoId", staffInfoId);
		List<HashMap<String,Object>> staffInfoIdList = myBatisDao.queryStaffInfoId(temp1);
		if(staffInfoIdList.size() > 0){
			return new RETURN("10007", "该工号已存在！");
		}
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("staffInfoId", staffInfoId);
		paramMap.put("staffInfoName", staffInfoName);
		paramMap.put("sex", sex);
		paramMap.put("nation", nation);
		paramMap.put("idcard", idcard);
		paramMap.put("birthday", birthday);
		paramMap.put("address", address);
		paramMap.put("birthplace", birthplace);
		paramMap.put("diploma", diploma);
		paramMap.put("graduation", graduation);
		paramMap.put("special", special);
		paramMap.put("marriage", marriage);
		paramMap.put("apartment", apartment);
		paramMap.put("post", post);
		paramMap.put("createtime", inParam.getNow());
		
		myBatisDao.addStaffInfoList(paramMap);
		
		return RETURN.SUCCESS;
	}
	
}
