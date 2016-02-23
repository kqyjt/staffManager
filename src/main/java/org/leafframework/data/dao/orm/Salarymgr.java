package org.leafframework.data.dao.orm;

// Generated 2016-2-23 15:13:15 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Salarymgr generated by hbm2java
 */
public class Salarymgr implements java.io.Serializable {

	private Integer id;
	private String month;
	private String staffId;
	private String staffName;
	private String state;
	private Date inputtime;
	private String workage;
	private String basicSalary;
	private String welfare;
	private String absencePay;
	private String postPay;
	private String mealPay;
	private String medicalPay;
	private String workagePay;
	private String retirePay;
	private String overtimePay;
	private String addSalary;
	private String bonus;
	private String otherPay;
	private String incomeTax;
	private String deductSalary;
	private String realitySalary;
	private String remark;

	public Salarymgr() {
	}

	public Salarymgr(String month, String staffId, String staffName,
			String basicSalary, String realitySalary) {
		this.month = month;
		this.staffId = staffId;
		this.staffName = staffName;
		this.basicSalary = basicSalary;
		this.realitySalary = realitySalary;
	}

	public Salarymgr(String month, String staffId, String staffName,
			String state, Date inputtime, String workage, String basicSalary,
			String welfare, String absencePay, String postPay, String mealPay,
			String medicalPay, String workagePay, String retirePay,
			String overtimePay, String addSalary, String bonus,
			String otherPay, String incomeTax, String deductSalary,
			String realitySalary, String remark) {
		this.month = month;
		this.staffId = staffId;
		this.staffName = staffName;
		this.state = state;
		this.inputtime = inputtime;
		this.workage = workage;
		this.basicSalary = basicSalary;
		this.welfare = welfare;
		this.absencePay = absencePay;
		this.postPay = postPay;
		this.mealPay = mealPay;
		this.medicalPay = medicalPay;
		this.workagePay = workagePay;
		this.retirePay = retirePay;
		this.overtimePay = overtimePay;
		this.addSalary = addSalary;
		this.bonus = bonus;
		this.otherPay = otherPay;
		this.incomeTax = incomeTax;
		this.deductSalary = deductSalary;
		this.realitySalary = realitySalary;
		this.remark = remark;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getInputtime() {
		return this.inputtime;
	}

	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}

	public String getWorkage() {
		return this.workage;
	}

	public void setWorkage(String workage) {
		this.workage = workage;
	}

	public String getBasicSalary() {
		return this.basicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}

	public String getWelfare() {
		return this.welfare;
	}

	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}

	public String getAbsencePay() {
		return this.absencePay;
	}

	public void setAbsencePay(String absencePay) {
		this.absencePay = absencePay;
	}

	public String getPostPay() {
		return this.postPay;
	}

	public void setPostPay(String postPay) {
		this.postPay = postPay;
	}

	public String getMealPay() {
		return this.mealPay;
	}

	public void setMealPay(String mealPay) {
		this.mealPay = mealPay;
	}

	public String getMedicalPay() {
		return this.medicalPay;
	}

	public void setMedicalPay(String medicalPay) {
		this.medicalPay = medicalPay;
	}

	public String getWorkagePay() {
		return this.workagePay;
	}

	public void setWorkagePay(String workagePay) {
		this.workagePay = workagePay;
	}

	public String getRetirePay() {
		return this.retirePay;
	}

	public void setRetirePay(String retirePay) {
		this.retirePay = retirePay;
	}

	public String getOvertimePay() {
		return this.overtimePay;
	}

	public void setOvertimePay(String overtimePay) {
		this.overtimePay = overtimePay;
	}

	public String getAddSalary() {
		return this.addSalary;
	}

	public void setAddSalary(String addSalary) {
		this.addSalary = addSalary;
	}

	public String getBonus() {
		return this.bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getOtherPay() {
		return this.otherPay;
	}

	public void setOtherPay(String otherPay) {
		this.otherPay = otherPay;
	}

	public String getIncomeTax() {
		return this.incomeTax;
	}

	public void setIncomeTax(String incomeTax) {
		this.incomeTax = incomeTax;
	}

	public String getDeductSalary() {
		return this.deductSalary;
	}

	public void setDeductSalary(String deductSalary) {
		this.deductSalary = deductSalary;
	}

	public String getRealitySalary() {
		return this.realitySalary;
	}

	public void setRealitySalary(String realitySalary) {
		this.realitySalary = realitySalary;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
