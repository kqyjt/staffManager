package org.leafframework.data.dao.orm;
// Generated 2014-11-8 21:08:30 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * TSOaction generated by hbm2java
 */
public class TSOaction implements java.io.Serializable {

	private Integer id;
	private String name;
	private String orderType;
	private String oldState;
	private String newState;
	private String remark;
	private int updateStaffId;
	private Date updateTime;

	public TSOaction() {
	}

	public TSOaction(String name, String orderType, String oldState,
			String newState, String remark, int updateStaffId, Date updateTime) {
		this.name = name;
		this.orderType = orderType;
		this.oldState = oldState;
		this.newState = newState;
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
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOldState() {
		return this.oldState;
	}

	public void setOldState(String oldState) {
		this.oldState = oldState;
	}
	public String getNewState() {
		return this.newState;
	}

	public void setNewState(String newState) {
		this.newState = newState;
	}
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getUpdateStaffId() {
		return this.updateStaffId;
	}

	public void setUpdateStaffId(int updateStaffId) {
		this.updateStaffId = updateStaffId;
	}
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
