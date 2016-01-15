package org.leafframework.util;


public class AddressList {
	
	private String netId;
	private String cityCode;
    private String addressCode;
    private String addressName;
    private String exchCode;
    private String exchName;
    private String restypeCode;
    private String restypeName;
    private String regionPrpty;
    private String accessType;
    private String rateFlag;
	
	public AddressList(String netId, String cityCode, String addressCode,
			String addressName, String exchCode, String exchName,
			String restypeCode, String restypeName, String regionPrpty,
			String accessType, String rateFlag) {
		super();
		this.netId = netId;
		this.cityCode = cityCode;
		this.addressCode = addressCode;
		this.addressName = addressName;
		this.exchCode = exchCode;
		this.exchName = exchName;
		this.restypeCode = restypeCode;
		this.restypeName = restypeName;
		this.regionPrpty = regionPrpty;
		this.accessType = accessType;
		this.rateFlag = rateFlag;
	}
	
	public String getNetId() {
		return netId;
	}
	public void setNetId(String netId) {
		this.netId = netId;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
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
	public String getRestypeCode() {
		return restypeCode;
	}
	public void setRestypeCode(String restypeCode) {
		this.restypeCode = restypeCode;
	}
	public String getRestypeName() {
		return restypeName;
	}
	public void setRestypeName(String restypeName) {
		this.restypeName = restypeName;
	}
	public String getRegionPrpty() {
		return regionPrpty;
	}
	public void setRegionPrpty(String regionPrpty) {
		this.regionPrpty = regionPrpty;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getRateFlag() {
		return rateFlag;
	}
	public void setRateFlag(String rateFlag) {
		this.rateFlag = rateFlag;
	}

}
