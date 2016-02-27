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

@Service("AgreementMgr")
@Scope("prototype")
public class AgreementMgr extends Business{

	@Override
	public RETURN execute() throws Exception {
		if("agreementRemove".equals(this.getPageUri().get("f"))){
			return agreementRemove(); //删除合同
		} else if("agreementUpdate".equals(this.getPageUri().get("f"))){
			return agreementUpdate(); //更新合同
		} else if("agreementAdd".equals(this.getPageUri().get("f"))){
			return agreementAdd(); //添加合同
		} else if("agreementSave".equals(this.getPageUri().get("f"))){
			return agreementSave(); //保存合同
		} else if("agreementMgr".equals(this.getPageUri().get("f"))){
			return agreementMgr(); //合同管理
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
		if("agreementQuery".equals(this.getPageUri().get("f"))){
			return agreementQuery(); //合同查询
		}
		
		return RETURN.SUCCESS;
	}

	/**
	 * 合同管理
	 * @return
	 */
	private RETURN agreementMgr(){
		HashMap<String, Object> outParam = this.getOutParam();
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> apartmentList = myBatisDAO.queryApartmentList(paramMap);
		outParam.put("apartmentList", apartmentList);
		outParam.put("apartmentSize", apartmentList.size());
		this.setLogicView("agreementMgr");
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 合同查询
	 * @return
	 */
	private RETURN agreementQuery(){
		IMap inParam = new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		HashMap<String, Object> outParam = this.getOutParam();
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		String staffId = inParam.get("staffId") == null ? "" : inParam.get("staffId").toString();
		String staffName = inParam.get("staffName") == null ? "" : inParam.get("staffName").toString();
		String apartment = inParam.get("apartment") == null ? "" : inParam.get("apartment").toString();
				
		paramMap.put("staffId", staffId);
		paramMap.put("staffName", staffName);
		paramMap.put("apartment", apartment);
		
		List<HashMap<String,Object>> agreementList = myBatisDao.queryAgreementList(paramMap);
		int totalCount = agreementList.size();
		int size = inParam.getInt("size");
		int totalPage = totalCount%size == 0 ? totalCount/size : totalCount/size+1; // 计算总页数
		
		int page = inParam.getInt("page");
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		//页面初始化时page会等于0
		if(page == 0){
			page = 1;
		}
		for(int i=size*(page-1); i<size*page&&i<totalCount; i++) {
			list.add(agreementList.get(i));
		}
		
		outParam.put("page", totalPage);
		outParam.put("total",totalCount);
		outParam.put("rows",list);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 删除合同
	 * @return
	 */
	private RETURN agreementRemove(){
		IMap inParam=new IMap(this.getInParam());
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", inParam.getInt("id"));
		myBatisDAO.removeAgreement(paramMap);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 解码js编码的内容
	 * @param src
	 * @return
	 */
	public String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	/**
	 * 更新合同
	 * @return
	 */
	private RETURN agreementUpdate(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> outParam = this.getOutParam();
		
		if(inParam.get("id") != null){
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", inParam.getInt("id"));
			List<HashMap<String, Object>> agreementList = myBatisDAO.queryAgreementInfo(paramMap);
			
			String str = unescape((String)agreementList.get(0).get("content"));
			if(agreementList.size() > 0){
				outParam.put("id", agreementList.get(0).get("id"));
				outParam.put("agreeId", agreementList.get(0).get("agreeId"));
				outParam.put("agreeName", agreementList.get(0).get("agreeName"));
				outParam.put("staffId", agreementList.get(0).get("staffId"));
				outParam.put("staffName", agreementList.get(0).get("staffName"));
				outParam.put("starttime", agreementList.get(0).get("starttime"));
				outParam.put("endtime", agreementList.get(0).get("endtime"));
				outParam.put("content", str);
			}
		}
		
		this.setLogicView("agreementUpdate");
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 添加合同
	 * @return
	 */
	private RETURN agreementAdd(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		
		String agreeId = inParam.get("agreeId") == null ? "" : inParam.get("agreeId").toString();
		String agreeName = inParam.get("agreeName") == null ? "" : inParam.get("agreeName").toString();
		String staffId = inParam.get("staffId") == null ? "" : inParam.get("staffId").toString();
		String staffName = inParam.get("staffName") == null ? "" : inParam.get("staffName").toString();
		String starttime = inParam.get("starttime") == null ? "" : inParam.get("starttime").toString();
		String endtime = inParam.get("endtime") == null ? "" : inParam.get("endtime").toString();
		String content = inParam.get("content") == null ? "" : inParam.get("content").toString();
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("agreeId", agreeId);
		paramMap.put("agreeName", agreeName);
		paramMap.put("staffId", staffId);
		paramMap.put("staffName", staffName);
		paramMap.put("starttime", starttime);
		paramMap.put("endtime", endtime);
		paramMap.put("content", content);
		
		myBatisDao.addAgreementList(paramMap);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 保存合同
	 * @return
	 */
	private RETURN agreementSave(){
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		IMap inParam = new IMap(this.getInParam());
		
		String agreeId = inParam.get("agreeId") == null ? "" : inParam.get("agreeId").toString();
		String agreeName = inParam.get("agreeName") == null ? "" : inParam.get("agreeName").toString();
		String staffId = inParam.get("staffId") == null ? "" : inParam.get("staffId").toString();
		String staffName = inParam.get("staffName") == null ? "" : inParam.get("staffName").toString();
		String starttime = inParam.get("starttime") == null ? "" : inParam.get("starttime").toString();
		String endtime = inParam.get("endtime") == null ? "" : inParam.get("endtime").toString();
		String content = inParam.get("content") == null ? "" : inParam.get("content").toString();
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("id", inParam.get("id"));
		paramMap.put("agreeId", agreeId);
		paramMap.put("agreeName", agreeName);
		paramMap.put("staffId", staffId);
		paramMap.put("staffName", staffName);
		paramMap.put("starttime", starttime);
		paramMap.put("endtime", endtime);
		paramMap.put("content", content);
		
		myBatisDAO.updateAgreement(paramMap);
		
		return RETURN.SUCCESS;
	}
	
}
