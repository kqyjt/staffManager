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

@Service("SalaryMgr")
@Scope("prototype")
public class SalaryMgr extends Business{

	@Override
	public RETURN execute() throws Exception {
		if("salaryRemove".equals(this.getPageUri().get("f"))){
			return salaryRemove(); //删除工资信息
		} else if("salaryUpdate".equals(this.getPageUri().get("f"))){
			return salaryUpdate(); //更新工资信息
		} else if("salaryAdd".equals(this.getPageUri().get("f"))){
			return salaryAdd(); //添加工资信息
		} else if("salarySave".equals(this.getPageUri().get("f"))){
			return salarySave(); //保存工资信息
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
		if("salaryQuery".equals(this.getPageUri().get("f"))){
			return salaryQuery(); //工资信息查询
		}
		
		return RETURN.SUCCESS;
	}

	/**
	 * 工资信息查询
	 * @return
	 */
	private RETURN salaryQuery(){
		IMap inParam = new IMap(this.getInParam());
		inParam.initPageParam(this.getPageUri());
		HashMap<String, Object> outParam = this.getOutParam();
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		String month = inParam.get("month") == null ? "" : inParam.get("month").toString();
		String staffId = inParam.get("staffId") == null ? "" : inParam.get("staffId").toString();
		String staffName = inParam.get("staffName") == null ? "" : inParam.get("staffName").toString();
		
		paramMap.put("month", month);
		paramMap.put("staffId", staffId);
		paramMap.put("staffName", staffName);
		
		List<HashMap<String,Object>> salaryList = myBatisDao.querySalaryList(paramMap);
		int totalCount = salaryList.size();
		int size = inParam.getInt("size");
		int totalPage = totalCount%size == 0 ? totalCount/size : totalCount/size+1; // 计算总页数
		
		int page = inParam.getInt("page");
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		//页面初始化时page会等于0
		if(page == 0){
			page = 1;
		}
		for(int i=size*(page-1); i<size*page&&i<totalCount; i++) {
			list.add(salaryList.get(i));
		}
		
		outParam.put("page", totalPage);
		outParam.put("total",totalCount);
		outParam.put("rows",list);
		this.setLogicView("salaryMgr");
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 删除工资
	 * @return
	 */
	private RETURN salaryRemove(){
		IMap inParam=new IMap(this.getInParam());
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", inParam.getInt("id"));
		myBatisDAO.removeSalary(paramMap);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 获取月份列表
	 * @param monthId
	 * @param monthName
	 * @return
	 */
	private List<HashMap<String,Object>> getDefaultMonthList(String monthId, String monthName){
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("monthId", monthId);
		List<HashMap<String, Object>> monthList = (List<HashMap<String, Object>>)myBatisDAO.getDefaultMonth(map);
		if(monthList != null){
			HashMap<String, Object> temp = new HashMap<String, Object>();
			temp.put("monthId", monthId);
			temp.put("monthName", monthName);
			monthList.add(0, temp);
		}
		
		return monthList;
	}
	
	/**
	 * 更新工资信息
	 * @return
	 */
	private RETURN salaryUpdate(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		HashMap<String, Object> outParam = this.getOutParam();
		
		List<HashMap<String, Object>> stateList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> stateTemp1 = new HashMap<String, Object>();
		stateTemp1.put("stateId", "00");
		stateTemp1.put("stateName", "在职");
		HashMap<String, Object> stateTemp2 = new HashMap<String, Object>();
		stateTemp2.put("stateId", "01");
		stateTemp2.put("stateName", "离职");
		stateList.add(stateTemp1);  
		stateList.add(stateTemp2);
		
		if(inParam.get("id") != null){
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", inParam.getInt("id"));
			List<HashMap<String, Object>> salaryList = myBatisDAO.querySalaryInfo(paramMap);
			
			HashMap<String, Object> temp1 = new HashMap<String, Object>();
			temp1.put("monthId", salaryList.get(0).get("month"));
			List<HashMap<String, Object>> monthNameList = myBatisDAO.queryMonthName(temp1);
			
			List<HashMap<String, Object>> monthList = this.getDefaultMonthList((String)monthNameList.get(0).get("monthId"),
					(String)monthNameList.get(0).get("monthName"));
			
			if(salaryList.size() > 0){
				outParam.put("id", salaryList.get(0).get("id"));
				outParam.put("monthList", monthList);
				outParam.put("staffId", salaryList.get(0).get("staffId"));
				outParam.put("staffName", salaryList.get(0).get("staffName"));
				
				//outParam.put("state", salaryList.get(0).get("state"));
				outParam.put("stateList", stateList);
				
				outParam.put("inputtime", salaryList.get(0).get("inputtime"));
				outParam.put("workage", salaryList.get(0).get("workage"));
				outParam.put("basicSalary", salaryList.get(0).get("basicSalary"));
				outParam.put("welfare", salaryList.get(0).get("welfare"));
				outParam.put("absencePay", salaryList.get(0).get("absencePay"));
				outParam.put("postPay", salaryList.get(0).get("postPay"));
				outParam.put("mealPay", salaryList.get(0).get("mealPay"));
				outParam.put("medicalPay", salaryList.get(0).get("medicalPay"));
				outParam.put("workagePay", salaryList.get(0).get("workagePay"));
				outParam.put("retirePay", salaryList.get(0).get("retirePay"));
				outParam.put("overtimePay", salaryList.get(0).get("overtimePay"));
				outParam.put("addSalary", salaryList.get(0).get("addSalary"));
				outParam.put("bonus", salaryList.get(0).get("bonus"));
				outParam.put("otherPay", salaryList.get(0).get("otherPay"));
				outParam.put("incomeTax", salaryList.get(0).get("incomeTax"));
				outParam.put("deductSalary", salaryList.get(0).get("deductSalary"));
				outParam.put("realitySalary", salaryList.get(0).get("realitySalary"));
				outParam.put("remark", salaryList.get(0).get("remark"));
			}
		} else {
			HashMap<String, Object> map = new HashMap<String, Object>();
			List<HashMap<String, Object>> monthList = myBatisDAO.getDefaultMonth(map);
			outParam.put("monthList", monthList);
			outParam.put("stateList", stateList);
		}
		
		this.setLogicView("salaryUpdate");
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 添加工资
	 * @return
	 */
	private RETURN salaryAdd(){
		IMap inParam = new IMap(this.getInParam());
		MyBatisDAO myBatisDao = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		
		String month = inParam.get("month") == null ? "" : inParam.get("month").toString();
		String staffId = inParam.get("staffId") == null ? "" : inParam.get("staffId").toString();
		String staffName = inParam.get("staffName") == null ? "" : inParam.get("staffName").toString();
		String state = inParam.get("state") == null ? "" : inParam.get("state").toString();
		String inputtime = inParam.get("inputtime") == null ? "" : inParam.get("inputtime").toString();
		String workage = inParam.get("workage") == null ? "" : inParam.get("workage").toString();
		String basicSalary = inParam.get("basicSalary") == null ? "" : inParam.get("basicSalary").toString();
		String welfare = inParam.get("welfare") == null ? "" : inParam.get("welfare").toString();
		String absencePay = inParam.get("absencePay") == null ? "" : inParam.get("absencePay").toString();
		String postPay = inParam.get("postPay") == null ? "" : inParam.get("postPay").toString();
		String mealPay = inParam.get("mealPay") == null ? "" : inParam.get("mealPay").toString();
		String medicalPay = inParam.get("medicalPay") == null ? "" : inParam.get("medicalPay").toString();
		String workagePay = inParam.get("workagePay") == null ? "" : inParam.get("workagePay").toString();
		String retirePay = inParam.get("retirePay") == null ? "" : inParam.get("retirePay").toString();
		String overtimePay = inParam.get("overtimePay") == null ? "" : inParam.get("overtimePay").toString();
		String addSalary = inParam.get("addSalary") == null ? "" : inParam.get("addSalary").toString();
		String bonus = inParam.get("bonus") == null ? "" : inParam.get("bonus").toString();
		String otherPay = inParam.get("otherPay") == null ? "" : inParam.get("otherPay").toString();
		String incomeTax = inParam.get("incomeTax") == null ? "" : inParam.get("incomeTax").toString();
		String deductSalary = inParam.get("deductSalary") == null ? "" : inParam.get("deductSalary").toString();
		String realitySalary = inParam.get("realitySalary") == null ? "" : inParam.get("realitySalary").toString();
		String remark = inParam.get("remark") == null ? "" : inParam.get("remark").toString();
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("month", month);
		paramMap.put("staffId", staffId);
		paramMap.put("staffName", staffName);
		paramMap.put("state", state);
		paramMap.put("inputtime", inputtime);
		paramMap.put("workage", workage);
		paramMap.put("basicSalary", basicSalary);
		paramMap.put("welfare", welfare);
		paramMap.put("absencePay", absencePay);
		paramMap.put("postPay", postPay);
		paramMap.put("mealPay", mealPay);
		paramMap.put("medicalPay", medicalPay);
		paramMap.put("workagePay", workagePay);
		paramMap.put("retirePay", retirePay);
		paramMap.put("overtimePay", overtimePay);
		paramMap.put("addSalary", addSalary);
		paramMap.put("bonus", bonus);
		paramMap.put("otherPay", otherPay);
		paramMap.put("incomeTax", incomeTax);
		paramMap.put("deductSalary", deductSalary);
		paramMap.put("realitySalary", realitySalary);
		paramMap.put("remark", remark);
		
		myBatisDao.addSalaryList(paramMap);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 保存工资信息
	 * @return
	 */
	private RETURN salarySave(){
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		IMap inParam = new IMap(this.getInParam());
		
		String month = inParam.get("month") == null ? "" : inParam.get("month").toString();
		String staffId = inParam.get("staffId") == null ? "" : inParam.get("staffId").toString();
		String staffName = inParam.get("staffName") == null ? "" : inParam.get("staffName").toString();
		String state = inParam.get("state") == null ? "" : inParam.get("state").toString();
		String inputtime = inParam.get("inputtime") == null ? "" : inParam.get("inputtime").toString();
		String workage = inParam.get("workage") == null ? "" : inParam.get("workage").toString();
		String basicSalary = inParam.get("basicSalary") == null ? "" : inParam.get("basicSalary").toString();
		String welfare = inParam.get("welfare") == null ? "" : inParam.get("welfare").toString();
		String absencePay = inParam.get("absencePay") == null ? "" : inParam.get("absencePay").toString();
		String postPay = inParam.get("postPay") == null ? "" : inParam.get("postPay").toString();
		String mealPay = inParam.get("mealPay") == null ? "" : inParam.get("mealPay").toString();
		String medicalPay = inParam.get("medicalPay") == null ? "" : inParam.get("medicalPay").toString();
		String workagePay = inParam.get("workagePay") == null ? "" : inParam.get("workagePay").toString();
		String retirePay = inParam.get("retirePay") == null ? "" : inParam.get("retirePay").toString();
		String overtimePay = inParam.get("overtimePay") == null ? "" : inParam.get("overtimePay").toString();
		String addSalary = inParam.get("addSalary") == null ? "" : inParam.get("addSalary").toString();
		String bonus = inParam.get("bonus") == null ? "" : inParam.get("bonus").toString();
		String otherPay = inParam.get("otherPay") == null ? "" : inParam.get("otherPay").toString();
		String incomeTax = inParam.get("incomeTax") == null ? "" : inParam.get("incomeTax").toString();
		String deductSalary = inParam.get("deductSalary") == null ? "" : inParam.get("deductSalary").toString();
		String realitySalary = inParam.get("realitySalary") == null ? "" : inParam.get("realitySalary").toString();
		String remark = inParam.get("remark") == null ? "" : inParam.get("remark").toString();
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("id", inParam.get("id"));
		paramMap.put("month", month);
		paramMap.put("staffId", staffId);
		paramMap.put("staffName", staffName);
		paramMap.put("state", state);
		paramMap.put("inputtime", inputtime);
		paramMap.put("workage", workage);
		paramMap.put("basicSalary", basicSalary);
		paramMap.put("welfare", welfare);
		paramMap.put("absencePay", absencePay);
		paramMap.put("postPay", postPay);
		paramMap.put("mealPay", mealPay);
		paramMap.put("medicalPay", medicalPay);
		paramMap.put("workagePay", workagePay);
		paramMap.put("retirePay", retirePay);
		paramMap.put("overtimePay", overtimePay);
		paramMap.put("addSalary", addSalary);
		paramMap.put("bonus", bonus);
		paramMap.put("otherPay", otherPay);
		paramMap.put("incomeTax", incomeTax);
		paramMap.put("deductSalary", deductSalary);
		paramMap.put("realitySalary", realitySalary);
		paramMap.put("remark", remark);
		
		myBatisDAO.updateSalary(paramMap);
		
		return RETURN.SUCCESS;
	}
	
}
