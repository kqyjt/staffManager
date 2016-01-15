package org.leafframework.util;

import java.util.Map;

public interface ServerMain {

	
	/**
	 * 获取号码预占信息
	 * 
	 * @param param
	 * @return
	 */
	public Map<String, String> queryOccupyInfo(Map<String, String> param);
	
	/**
	* 网盟获取用户产品信息
	*
	* @param param
	* @return
	*/
	public Map<String, String> getUserProductValue(Map<String, String> param);
}
