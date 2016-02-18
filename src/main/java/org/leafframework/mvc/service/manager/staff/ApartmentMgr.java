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

@Service("ApartmentMgr")
@Scope("prototype")
public class ApartmentMgr extends Business{

	@Override
	public RETURN execute() throws Exception {
		if("apartmentRemove".equals(this.getPageUri().get("f"))){
			return apartmentRemove(); //删除部门
		} else if("apartmentEdit".equals(this.getPageUri().get("f"))){
			return apartmentEdit(); //编辑部门信息
		} else if("apartmentAdd".equals(this.getPageUri().get("f"))){
			return apartmentAdd(); //添加部门
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
		if("apartmentQuery".equals(this.getPageUri().get("f"))){
			return apartmentQuery(); //部门信息查询
		}
		
		return RETURN.SUCCESS;
	}

	/**
	 * 部门信息查询
	 * @return
	 */
	private RETURN apartmentQuery(){
		IMap inParam = new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		HashMap<String, Object> outParam = this.getOutParam();
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		String apartId = inParam.get("apartId") == null ? "" : inParam.get("apartId").toString();
		String apartName = inParam.get("apartName") == null ? "" : inParam.get("apartName").toString();
		
		paramMap.put("apartId", apartId);
		paramMap.put("apartName", apartName);
		
		List<HashMap<String,Object>> apartmentList = myBatisDao.queryApartmentList(paramMap);
		int totalCount = apartmentList.size();
		int size = inParam.getInt("size");
		int totalPage = totalCount%size == 0 ? totalCount/size : totalCount/size+1; // 计算总页数
		
		int page = inParam.getInt("page");
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		//页面初始化时page会等于0
		if(page == 0){
			page = 1;
		}
		for(int i=size*(page-1); i<size*page&&i<totalCount; i++) {
			list.add(apartmentList.get(i));
		}
		
		outParam.put("page", totalPage);
		outParam.put("total",totalCount);
		outParam.put("rows",list);
		this.setLogicView("apartmentMgr");
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 删除部门
	 * @return
	 */
	private RETURN apartmentRemove(){
		IMap inParam=new IMap(this.getInParam());
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", inParam.getInt("id"));
		myBatisDAO.removeApartment(paramMap);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 编辑部门信息
	 * @return
	 */
	private RETURN apartmentEdit(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", inParam.getInt("id"));
		paramMap.put("apartId", inParam.getString("apartId"));
		paramMap.put("apartName", inParam.getString("apartName"));
		paramMap.put("phone", inParam.getString("phone"));
		paramMap.put("description", inParam.getString("description"));
		myBatisDAO.updateApartment(paramMap);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 添加部门
	 * @return
	 */
	private RETURN apartmentAdd(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		
		String apartId = inParam.get("apartId") == null ? "" : inParam.get("apartId").toString();
		String apartName = inParam.get("apartName") == null ? "" : inParam.get("apartName").toString();
		String phone = inParam.get("phone") == null ? "" : inParam.get("phone").toString();
		String description = inParam.get("description") == null ? "" : inParam.get("description").toString();
		
		//获取部门工号
		HashMap<String, Object> temp1 = new HashMap<String,Object>();
		temp1.put("apartId", apartId);
		List<HashMap<String,Object>> apartmentIdList = myBatisDao.queryApartmentId(temp1);
		if(apartmentIdList.size() > 0){
			return new RETURN("10007", "该部门编号已存在！");
		}
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("apartId", apartId);
		paramMap.put("apartName", apartName);
		paramMap.put("phone", phone);
		paramMap.put("description", description);
		
		myBatisDao.addApartmentList(paramMap);
		
		return RETURN.SUCCESS;
	}
	
}
