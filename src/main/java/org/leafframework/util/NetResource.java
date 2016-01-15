package org.leafframework.util;

public class NetResource {
	
	private String brandCode;
	private String accessType;
	private String typeName;
    private String maxRate;
    private String ipType;
    private String exchCode;
    private String exchName;
    private String processDescr;
    private String finishDate;
    private String accessDescr;
	
	public NetResource(String brandCode, String accessType, String typeName,
			String maxRate, String ipType, String exchCode, String exchName,
			String processDescr, String finishDate, String accessDescr) {
		super();
		this.brandCode = brandCode;
		this.accessType = accessType;
		this.typeName = typeName;
		this.maxRate = maxRate;
		this.ipType = ipType;
		this.exchCode = exchCode;
		this.exchName = exchName;
		this.processDescr = processDescr;
		this.finishDate = finishDate;
		this.accessDescr = accessDescr;
	}
	
    public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getMaxRate() {
		return maxRate;
	}
	public void setMaxRate(String maxRate) {
		this.maxRate = maxRate;
	}
	public String getIpType() {
		return ipType;
	}
	public void setIpType(String ipType) {
		this.ipType = ipType;
	}
	public String getExchCode() {
		return exchCode;
	}
	public void setExchCode(String exchCode) {
		this.exchCode = exchCode;
	}
	public String getExchName() {
		return exchName;
	}
	public void setExchName(String exchName) {
		this.exchName = exchName;
	}
	public String getProcessDescr() {
		return processDescr;
	}
	public void setProcessDescr(String processDescr) {
		this.processDescr = processDescr;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getAccessDescr() {
		return accessDescr;
	}
	public void setAccessDescr(String accessDescr) {
		this.accessDescr = accessDescr;
	}
	
    
}
