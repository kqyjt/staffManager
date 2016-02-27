package org.leafframework.data.dao.orm;

// Generated 2016-2-27 15:22:44 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Agreement generated by hbm2java
 */
public class Agreement implements java.io.Serializable {

	private Integer id;
	private String agreeId;
	private String name;
	private String staffId;
	private String staffName;
	private Date starttime;
	private Date endtime;
	private String content;

	public Agreement() {
	}

	public Agreement(String agreeId, String name, String staffId,
			String staffName) {
		this.agreeId = agreeId;
		this.name = name;
		this.staffId = staffId;
		this.staffName = staffName;
	}

	public Agreement(String agreeId, String name, String staffId,
			String staffName, Date starttime, Date endtime, String content) {
		this.agreeId = agreeId;
		this.name = name;
		this.staffId = staffId;
		this.staffName = staffName;
		this.starttime = starttime;
		this.endtime = endtime;
		this.content = content;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgreeId() {
		return this.agreeId;
	}

	public void setAgreeId(String agreeId) {
		this.agreeId = agreeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
