package org.leafframework.data.dao.orm;

// Generated 2015-7-13 14:44:52 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * TCCustomer generated by hbm2java
 */
public class TCCustomer implements java.io.Serializable {

	private Integer id;
	private String regName;
	private String custName;
	private String password;
	private String isChangePass;
	private String mobilePhone;
	private String email;
	private String telephone;
	private String qqNo;
	private String state;
	private String custType;
	private String custLvl;
	private String sex;
	private String idCard;
	private String homeprovinceid;
	private String homeCityid;
	private String homeAreaid;
	private String receiveAddr;
	private String provinceid;
	private String cityid;
	private String userSource;
	private String staffId;
	private String managerId;
	private String managerName;
	private Integer devCustId;
	private String expressFlag;
	private String faceUrl;
	private String teamName;
	private String remark;
	private Date createTime;
	private Date updateTime;
	private String updateOperator;

	public TCCustomer() {
	}

	public TCCustomer(String regName, String password, String isChangePass, String state, String custType, String custLvl, String userSource,
			String expressFlag, Date createTime) {
		this.regName = regName;
		this.password = password;
		this.isChangePass = isChangePass;
		this.state = state;
		this.custType = custType;
		this.custLvl = custLvl;
		this.userSource = userSource;
		this.expressFlag = expressFlag;
		this.createTime = createTime;
	}

	public TCCustomer(String regName, String custName, String password, String isChangePass, String mobilePhone, String email, String telephone, String qqNo,
			String state, String custType, String custLvl, String sex, String idCard, String homeprovinceid, String homeCityid,
			String homeAreaid, String receiveAddr, String provinceid, String cityid, String userSource, String staffId, String managerId,
			String managerName, Integer devCustId, String expressFlag, String faceUrl, String teamName, String remark, Date createTime,
			Date updateTime, String updateOperator) {
		this.regName = regName;
		this.custName = custName;
		this.isChangePass = isChangePass;
		this.password = password;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.telephone = telephone;
		this.qqNo = qqNo;
		this.state = state;
		this.custType = custType;
		this.custLvl = custLvl;
		this.sex = sex;
		this.idCard = idCard;
		this.homeprovinceid = homeprovinceid;
		this.homeCityid = homeCityid;
		this.homeAreaid = homeAreaid;
		this.receiveAddr = receiveAddr;
		this.provinceid = provinceid;
		this.cityid = cityid;
		this.userSource = userSource;
		this.staffId = staffId;
		this.managerId = managerId;
		this.managerName = managerName;
		this.devCustId = devCustId;
		this.expressFlag = expressFlag;
		this.faceUrl = faceUrl;
		this.teamName = teamName;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.updateOperator = updateOperator;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegName() {
		return this.regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getQqNo() {
		return this.qqNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCustType() {
		return this.custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustLvl() {
		return this.custLvl;
	}

	public void setCustLvl(String custLvl) {
		this.custLvl = custLvl;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getHomeprovinceid() {
		return this.homeprovinceid;
	}

	public void setHomeprovinceid(String homeprovinceid) {
		this.homeprovinceid = homeprovinceid;
	}

	public String getHomeCityid() {
		return this.homeCityid;
	}

	public void setHomeCityid(String homeCityid) {
		this.homeCityid = homeCityid;
	}

	public String getHomeAreaid() {
		return this.homeAreaid;
	}

	public void setHomeAreaid(String homeAreaid) {
		this.homeAreaid = homeAreaid;
	}

	public String getReceiveAddr() {
		return this.receiveAddr;
	}

	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}

	public String getProvinceid() {
		return this.provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getCityid() {
		return this.cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getUserSource() {
		return this.userSource;
	}

	public void setUserSource(String userSource) {
		this.userSource = userSource;
	}

	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return this.managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Integer getDevCustId() {
		return this.devCustId;
	}

	public void setDevCustId(Integer devCustId) {
		this.devCustId = devCustId;
	}

	public String getExpressFlag() {
		return this.expressFlag;
	}

	public void setExpressFlag(String expressFlag) {
		this.expressFlag = expressFlag;
	}

	public String getFaceUrl() {
		return this.faceUrl;
	}

	public void setFaceUrl(String faceUrl) {
		this.faceUrl = faceUrl;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateOperator() {
		return this.updateOperator;
	}

	public void setUpdateOperator(String updateOperator) {
		this.updateOperator = updateOperator;
	}

	public String getIsChangePass() {
		return isChangePass;
	}

	public void setIsChangePass(String isChangePass) {
		this.isChangePass = isChangePass;
	}

}
