package org.leafframework.data.dao.orm;

// Generated 2015-7-8 20:06:55 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * TLStaffRight generated by hbm2java
 */
public class TLStaffRight implements java.io.Serializable {

	private Integer id;
	private int logId;
	private int relId;
	private String staffId;
	private String resType;
	private int resId;
	private String modifyTag;
	private String updateStaffId;
	private Date updateTime;

	public TLStaffRight() {
	}

	public TLStaffRight(int logId, int relId, String staffId, String resType, int resId, String modifyTag, String updateStaffId,
			Date updateTime) {
		this.logId = logId;
		this.relId = relId;
		this.staffId = staffId;
		this.resType = resType;
		this.resId = resId;
		this.modifyTag = modifyTag;
		this.updateStaffId = updateStaffId;
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getLogId() {
		return this.logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getRelId() {
		return this.relId;
	}

	public void setRelId(int relId) {
		this.relId = relId;
	}

	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getResType() {
		return this.resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public int getResId() {
		return this.resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public String getModifyTag() {
		return this.modifyTag;
	}

	public void setModifyTag(String modifyTag) {
		this.modifyTag = modifyTag;
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