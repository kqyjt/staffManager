package org.leafframework.data.dao.orm;

// Generated 2016-2-19 9:01:12 by Hibernate Tools 3.4.0.CR1

/**
 * Checkattend generated by hbm2java
 */
public class Checkattend implements java.io.Serializable {

	private Integer id;
	private String staffId;
	private String staffName;
	private String checkMonth;
	private String absenceCount;

	public Checkattend() {
	}

	public Checkattend(String staffId, String staffName, String checkMonth) {
		this.staffId = staffId;
		this.staffName = staffName;
		this.checkMonth = checkMonth;
	}

	public Checkattend(String staffId, String staffName, String checkMonth,
			String absenceCount) {
		this.staffId = staffId;
		this.staffName = staffName;
		this.checkMonth = checkMonth;
		this.absenceCount = absenceCount;
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

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getCheckMonth() {
		return this.checkMonth;
	}

	public void setCheckMonth(String checkMonth) {
		this.checkMonth = checkMonth;
	}

	public String getAbsenceCount() {
		return this.absenceCount;
	}

	public void setAbsenceCount(String absenceCount) {
		this.absenceCount = absenceCount;
	}

}
