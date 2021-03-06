package org.leafframework.data.dao.orm;

// Generated 2015-7-21 14:43:12 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * TMStaff generated by hbm2java
 */
public class TMStaff implements java.io.Serializable {

	private Integer id;
	private String staffId;
	private String name;
	private String password;
	private String isChangePass;
	private String oldPass;
	private String gender;
	private String type;
	private String areaCode;
	private String address;
	private String zipCode;
	private String phoneNumber;
	private String email;
	private String updateStaffId;
	private Date updateTime;

	public TMStaff() {
	}

	public TMStaff(String gender, String areaCode, String updateStaffId,
			Date updateTime) {
		this.gender = gender;
		this.areaCode = areaCode;
		this.updateStaffId = updateStaffId;
		this.updateTime = updateTime;
	}

	public TMStaff(String staffId, String name, String password, String gender,
			String type, String areaCode, String address, String zipCode,String isChangePass,
			String phoneNumber, String email, String updateStaffId,String oldPass,
			Date updateTime) {
		this.staffId = staffId;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.type = type;
		this.areaCode = areaCode;
		this.address = address;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.updateStaffId = updateStaffId;
		this.updateTime = updateTime;
		this.isChangePass = isChangePass;
		this.oldPass = oldPass;
	}

	

	public String getIsChangePass() {
		return isChangePass;
	}

	public void setIsChangePass(String isChangePass) {
		this.isChangePass = isChangePass;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUpdateStaffId() {
		return this.updateStaffId;
	}

	public void setUpdateStaffId(String updateStaffId) {
		this.updateStaffId = updateStaffId;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
