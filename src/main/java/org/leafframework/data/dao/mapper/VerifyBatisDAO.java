package org.leafframework.data.dao.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.leafframework.data.dao.IDao;

public interface VerifyBatisDAO extends IDao{

	
	/**
	 * 测试
	 * @param param
	 * @return
	 * @date 2015年10月22日
	 * @author rencong
	 */
	public List<Map<String, String>> queryTestList(Map<String,String> param);

	/**
	 * 添加实名信息
	 * 
	 * @param ret
	 */
	public void addVerifyUserInfo(Map<String, String> ret);

	/**
	 * 得到手机地市信息
	 * 
	 * @param ret
	 * @return
	 */
	public List<Map<String, String>> getEparchyCodeByPhone(Map<String, String> ret);

	public List<Map<String, String>> getOrderManagerList(Map<String, Object> queryMap);

	public int getOrderManagerCount(Map<String, Object> queryMap);

	public String getLocalCityCode(Map<String, String> map);

	public void updateVerifyOrder(Map<String, Object> inParam);

	public Map<String, String> getVerifyDetail(Map<String, Object> inParam);

	public List<Map<String, String>> getVerifyAuditList(
			Map<String, Object> inParam);

	public void saveAuditInfo(Map<String, Object> inParam);

	public void updateAuditState(Map<String, Object> inParam);

	public void updateBisState(Map<String, Object> inParam);
	

	/**
	 * 手机号码查询
	 * @param param
	 * @return
	 * @date 2015年12月25日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryPhoneList(HashMap<String,Object> param);
	
	/**
	 * 补登记处理结果1查询
	 * @param param
	 * @return
	 * @date 2016年1月22日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryVerifyStateList1(HashMap<String,Object> param);
	
	/**
	 * 补登记处理结果2查询
	 * @param param
	 * @return
	 * @date 2016年1月22日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryVerifyStateList2(HashMap<String,Object> param);

}
