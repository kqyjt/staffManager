package org.leafframework.data.dao.orm;

// Generated 2015-8-26 15:56:50 by Hibernate Tools 4.0.0

/**
 * TMOffice generated by hbm2java
 */
public class TMOffice implements java.io.Serializable {

	private Integer id;
	private String eparchyCode;
	private String eparchyName;
	private String serialnumberS;
	private String serialnumberE;
	private String segment;
	private String remark;
	private String areaCode;

	public TMOffice() {
	}

	public TMOffice(String eparchyCode, String eparchyName, String serialnumberS, String serialnumberE, String segment) {
		this.eparchyCode = eparchyCode;
		this.eparchyName = eparchyName;
		this.serialnumberS = serialnumberS;
		this.serialnumberE = serialnumberE;
		this.segment = segment;
	}

	public TMOffice(String eparchyCode, String eparchyName, String serialnumberS, String serialnumberE, String segment, String remark,
			String areaCode) {
		this.eparchyCode = eparchyCode;
		this.eparchyName = eparchyName;
		this.serialnumberS = serialnumberS;
		this.serialnumberE = serialnumberE;
		this.segment = segment;
		this.remark = remark;
		this.areaCode = areaCode;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEparchyCode() {
		return this.eparchyCode;
	}

	public void setEparchyCode(String eparchyCode) {
		this.eparchyCode = eparchyCode;
	}

	public String getEparchyName() {
		return this.eparchyName;
	}

	public void setEparchyName(String eparchyName) {
		this.eparchyName = eparchyName;
	}

	public String getSerialnumberS() {
		return this.serialnumberS;
	}

	public void setSerialnumberS(String serialnumberS) {
		this.serialnumberS = serialnumberS;
	}

	public String getSerialnumberE() {
		return this.serialnumberE;
	}

	public void setSerialnumberE(String serialnumberE) {
		this.serialnumberE = serialnumberE;
	}

	public String getSegment() {
		return this.segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
