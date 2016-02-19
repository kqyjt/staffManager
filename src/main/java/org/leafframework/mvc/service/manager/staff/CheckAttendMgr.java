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

@Service("CheckAttendMgr")
@Scope("prototype")
public class CheckAttendMgr extends Business{

	@Override
	public RETURN execute() throws Exception {
		if("checkAttendRemove".equals(this.getPageUri().get("f"))){
			return checkAttendRemove(); //删除考勤信息
		} else if("checkAttendEdit".equals(this.getPageUri().get("f"))){
			return checkAttendEdit(); //编辑考勤信息
		} else if("checkAttendAdd".equals(this.getPageUri().get("f"))){
			return checkAttendAdd(); //添加考勤信息
		} else if("checkAttendMgr".equals(this.getPageUri().get("f"))){
			return checkAttendMgr(); //考勤信息管理
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
		if("checkAttendQuery".equals(this.getPageUri().get("f"))){
			return checkAttendQuery(); //考勤信息查询
		}
		
		return RETURN.SUCCESS;
	}

	/**
	 * 考勤信息管理
	 * @return
	 */
	private RETURN checkAttendMgr(){
		HashMap<String, Object> outParam = this.getOutParam();
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> apartmentList = myBatisDAO.queryApartmentList(paramMap);
		outParam.put("apartmentList", apartmentList);
		outParam.put("apartmentSize", apartmentList.size());
		this.setLogicView("checkAttendMgr");
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 考勤信息查询
	 * @return
	 */
	private RETURN checkAttendQuery(){
		IMap inParam = new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		HashMap<String, Object> outParam = this.getOutParam();
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		String checkAttendId = inParam.get("checkAttendId") == null ? "" : inParam.get("checkAttendId").toString();
		String checkAttendName = inParam.get("checkAttendName") == null ? "" : inParam.get("checkAttendName").toString();
		String checkAttendApartment = inParam.get("checkAttendApartment") == null ? "" : inParam.get("checkAttendApartment").toString();
		String checkAttendMonth = inParam.get("checkAttendMonth") == null ? "" : inParam.get("checkAttendMonth").toString();
		
		paramMap.put("checkAttendId", checkAttendId);
		paramMap.put("checkAttendName", checkAttendName);
		paramMap.put("checkAttendApartment", checkAttendApartment);
		paramMap.put("checkAttendMonth", checkAttendMonth);
		
		List<HashMap<String,Object>> checkAttendList = myBatisDao.checkAttendQuery(paramMap);
		int totalCount = checkAttendList.size();
		int size = inParam.getInt("size");
		int totalPage = totalCount%size == 0 ? totalCount/size : totalCount/size+1; // 计算总页数
		
		int page = inParam.getInt("page");
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		//页面初始化时page会等于0
		if(page == 0){
			page = 1;
		}
		for(int i=size*(page-1); i<size*page&&i<totalCount; i++) {
			list.add(checkAttendList.get(i));
		}
		
		outParam.put("page", totalPage);
		outParam.put("total",totalCount);
		outParam.put("rows",list);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 删除考勤记录
	 * @return
	 */
	private RETURN checkAttendRemove(){
		IMap inParam=new IMap(this.getInParam());
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", inParam.getInt("id"));
		myBatisDAO.removeCheckAttend(paramMap);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 编辑考勤信息
	 * @return
	 */
	private RETURN checkAttendEdit(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", inParam.getInt("id"));
		paramMap.put("staffId", inParam.getString("staffId"));
		paramMap.put("staffName", inParam.getString("staffName"));
		paramMap.put("checkMonth", inParam.getString("checkMonth"));
		paramMap.put("absenceCount", inParam.getString("absenceCount"));
		myBatisDAO.updateCheckAttend(paramMap);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 添加考勤
	 * @return
	 */
	private RETURN checkAttendAdd(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		
		String staffId = inParam.get("staffId") == null ? "" : inParam.get("staffId").toString();
		String staffName = inParam.get("staffName") == null ? "" : inParam.get("staffName").toString();
		String checkMonth = inParam.get("checkMonth") == null ? "" : inParam.get("checkMonth").toString();
		String absenceCount = inParam.get("absenceCount") == null ? "" : inParam.get("absenceCount").toString();
		
		//判断员工是否存在
		HashMap<String, Object> temp1 = new HashMap<String,Object>();
		temp1.put("staffInfoId", staffId);
		temp1.put("staffInfoName", staffName);
		List<HashMap<String,Object>> checkStaffList = myBatisDao.staffInfoQuery(temp1);
		if(checkStaffList.size() == 0){
			return new RETURN("10007", "该员工不存在！");
		}
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("staffId", staffId);
		paramMap.put("staffName", staffName);
		paramMap.put("checkMonth", checkMonth);
		paramMap.put("absenceCount", absenceCount);
		
		myBatisDao.addCheckAttendList(paramMap);
		
		return RETURN.SUCCESS;
	}
	
}
