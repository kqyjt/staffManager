package org.leafframework.data.dao.orm;

// Generated 2015-7-8 20:06:55 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * TMStaffRole generated by hbm2java
 */
public class TMStaffRole implements java.io.Serializable {

	private Integer id;
	private String staffId;
	private Integer roleId;
	private String remark;
	private String updateStaffId;
	private Date updateTime;

	public TMStaffRole() {
	}

	public TMStaffRole(String updateStaffId, Date updateTime) {
		this.updateStaffId = updateStaffId;
		this.updateTime = updateTime;
	}

	public TMStaffRole(String staffId, Integer roleId, String remark, String updateStaffId, Date updateTime) {
		this.staffId = staffId;
		this.roleId = roleId;
		this.remark = remark;
		this.updateStaffId = updateStaffId;
		this.updateTime = updateTime;
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

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
