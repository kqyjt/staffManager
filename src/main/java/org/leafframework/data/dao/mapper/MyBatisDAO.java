package org.leafframework.data.dao.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.leafframework.data.dao.IDao;
import org.leafframework.data.dao.orm.TCRegisterSmscode;
import org.leafframework.data.dao.orm.TLMain;
import org.leafframework.data.dao.orm.TMArea;
import org.leafframework.data.dao.orm.TMMenus;
import org.leafframework.data.dao.orm.TMOffice;
import org.leafframework.data.dao.orm.TMRoles;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.data.dao.orm.TSTempIdCard;

public interface MyBatisDAO extends IDao{

	public int insertTlmain(TLMain tlmain);

	public List<?> getTables(String schema);

	public String getSysdate();
	
	public String getSysdateAddMinute(int minute);

	public String getSequence(String seqName);
	
	public List<?> getMenus(TMMenus menu);

	public List<?> getModules(HashMap<String,Object> map);

	public List<?> getStaffMenus(String staffId);
	
	public int insertTmmenus(TMMenus tmmenus);
	
	public List<?> getColumns(@Param("schema") String schema,
			@Param("tableName") String tableName);
	
	
	/**
	 * 一分钟内手机收到的所有验证码 
	 * @param map
	 * @return
	 * @date 2015年1月20日
	 * @author wanghao
	 */
	public List<TCRegisterSmscode> getSmsCodeWithInOneMinute(HashMap<String,Object> map);
	
	/**
	 * 获取操作员所管理的区域
	 * @param map
	 * @return
	 * @date 2015年6月27日
	 * @author zhangyy
	 *
	 */
	public List<TMArea> getAreasOfStaff(HashMap<String,Object> map);
	
	/**
	 * 获取操作员所管理的区域下的所有的员工
	 * @param map
	 * @return
	 * @date 2015年6月28日
	 * @author zhangyy
	 *
	 */
	public List<TMStaff> getViewStaffs(HashMap<String,Object> map);
	
	/**
	 * 获取操作员的角色
	 * @param map
	 * @return
	 * @date 2015年6月28日
	 * @author zhangyy
	 *
	 */
	public List<TMRoles> getStaffRoles(HashMap<String,Object> map);
	
	/**
	 * 根据号码获取号码所属号段信息
	 * @param map
	 * @return
	 * @date 2015年8月26日
	 * @author zhangyy
	 *
	 */
	public TMOffice getOfficeByNumber(HashMap<String,Object> map);	
	
	/**
	 *  查询所有 Area
	 * @param 
	 * @return
	 * @author zhanggs
	 */
	public List<TMArea> queryAreaList();
	
	/**
	 *  查询管理员地市
	 * @param 
	 * @return
	 * @author zhanggs
	 */
	public String queryManagerCity(String staffId);

	/**
	 * 查询header头节点位置
	 * @return
	 * @date 2015年9月16日
	 * @author rencong
	 */
	public HashMap<String,Object> queryHeaderLocation(HashMap<String,Object> param);
	
	/**
	 * 查询body头节点位置
	 * @return
	 * @date 2015年9月16日
	 * @author rencong
	 */
	public HashMap<String,Object> queryBodyLocation(HashMap<String,Object> param);

	/**
	 * 获取后台管理员的信息
	 * @param staffMap
	 * @return
	 * @author shihh3
	 */
	public List<TMStaff> getStaffInfor(HashMap<String, Object> staffMap);

	
	/**
	 * 获取管理员对应的地市编码bss_code
	 * @return
	 * @author xbd
	 */
	public HashMap<String,Object> getBssAreaCodebyStaffid(HashMap<String,Object> staffMap);

	/**
	 * 查询角色信息
	 * @param param
	 * @return
	 * @date 2015年10月14日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryRoleList(HashMap<String,Object> param);
	
	/**
	 * 删除角色信息
	 * @param param
	 * @return
	 * @date 2015年10月15日
	 * @author rencong
	 */
	public void removeRoleList(HashMap<String,Object> param);
	
	/**
	 * 删除角色权限关联信息
	 * @param param
	 * @return
	 * @date 2015年10月19日
	 * @author rencong
	 */
	public void deleteRoleList(HashMap<String,Object> param);
	
	/**
	 * 删除角色权限
	 * @param param
	 * @return
	 * @date 2015年10月15日
	 * @author rencong
	 */
	public void removePowerList(HashMap<String,Object> param);
	
	/**
	 * 删除角色菜单
	 * @param param
	 * @return
	 * @date 2015年11月5日
	 * @author rencong
	 */
	public void removeMenuList(HashMap<String,Object> param);
	
	/**
	 * 查询角色权限
	 * @param param
	 * @return
	 * @date 2015年10月15日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryPowerList(HashMap<String,Object> param);
	
	/**
	 * 查询角色菜单
	 * @param param
	 * @return
	 * @date 2015年11月4日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryMenuList(HashMap<String,Object> param);
	
	/**
	 * 查询角色权限id
	 * @param param
	 * @return
	 * @date 2015年10月15日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryPowerIdList(HashMap<String,Object> param);
	
	/**
	 * 通过用户工号查询角色id
	 * @param param
	 * @return
	 * @date 2015年10月15日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryRoleIdList(HashMap<String,Object> param);
	
	/**
	 * 添加角色权限
	 * @param param
	 * @return
	 * @date 2015年10月15日
	 * @author rencong
	 */
	public void addPowerList(HashMap<String,Object> param);
	
	/**
	 * 添加角色
	 * @param param
	 * @return
	 * @date 2015年10月16日
	 * @author rencong
	 */
	public void addRoleList(HashMap<String,Object> param);
	
	/**
	 * 通过角色名称查询角色id
	 * @param param
	 * @return
	 * @date 2015年10月16日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryRoleByName(HashMap<String,Object> param);
	
	/**
	 * 保存角色
	 * @param param
	 * @return
	 * @date 2015年10月16日
	 * @author rencong
	 */
	public void saveRoleList(HashMap<String,Object> param);
	
	/**
	 * 通过权限名称查询权限备注
	 * @param param
	 * @return
	 * @date 2015年10月19日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryRemarkByName(HashMap<String,Object> param);
	
	/**
	 * 查询角色名称
	 * @param param
	 * @return
	 * @date 2015年10月19日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryRoleName(HashMap<String,Object> param);
	
	/**
	 * 查询角色未添加权限
	 * @param param
	 * @return
	 * @date 2015年10月20日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryNoPowerList(HashMap<String,Object> param);
	
	/**
	 * 查询角色未添加菜单
	 * @param param
	 * @return
	 * @date 2015年11月4日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryNoMenuList(HashMap<String,Object> param);
	
	/**
	 * 查询员工所属角色
	 * @param param
	 * @return
	 * @date 2015年10月20日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryStaffAttList(HashMap<String,Object> param);
	
	/**
	 * 解除角色
	 * @param param
	 * @return
	 * @date 2015年10月20日
	 * @author rencong
	 */
	public void removeStaffList(HashMap<String,Object> param);
	
	/**
	 * 更换角色
	 * @param param
	 * @return
	 * @date 2015年10月20日
	 * @author rencong
	 */
	public void changeRoleList(HashMap<String,Object> param);
	
	/**
	 * 删除原员工角色关系
	 * @param param
	 * @return
	 * @date 2015年10月21日
	 * @author rencong
	 */
	public void deleteStaffList(HashMap<String,Object> param);
	
	/**
	 * 通过角色id查询角色名称
	 * @param param
	 * @return
	 * @date 2015年10月21日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryNameById(HashMap<String,Object> param);
	
	/**
	 * 查询员工编号
	 * @param param
	 * @return
	 * @date 2015年10月21日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryStaffId(HashMap<String,Object> param);
	
	/**
	 * 添加员工
	 * @param param
	 * @return
	 * @date 2015年10月22日
	 * @author rencong
	 */
	public void addStaffList(HashMap<String,Object> param);
	
	/**
	 * 通过工号查询角色id
	 * @param param
	 * @return
	 * @date 2015年11月16日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryRoleByStaff(HashMap<String, Object> param);

	/**
	 * 通过地市编码查询地市名称
	 * @param param
	 * @return
	 * @date 2015年11月17日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryHomeCity(HashMap<String, Object> param);

	/**
	 * 获取后台管理员的权限Id
	 * @param rightMap
	 * @return
	 */
	public List<Integer> getModulesId(HashMap<String, Object> rightMap);
	
	/**
	 * 查询操作员所管理的区域下的所有的员工及其地市、角色等
	 * @param param
	 * @return
	 * @date 2015年11月11日
	 * @author gelei
	 */
	public List<HashMap<String, Object>> getViewStaffsDetails(HashMap<String,Object> param);
	/**
	 * 查询操作员所管理的区域下可以分配给订单的员工数量
	 * @param param
	 * @return
	 * @date 2015年11月20日
	 * @author gelei
	 */
	public int getViewStaffsDetailsCount(HashMap<String,Object> param);
	
	/**
	 * 查询员工下待开户订单数量
	 * @param param
	 * @return
	 * @date 2015年11月16日
	 * @author gelei
	 */
	public List<HashMap<String, Object>> getWaitOpenCountNumber(HashMap<String,Object> param);
	
	/**
	 * 查询地市区号
	 * @param param
	 * @return
	 * @date 2015年11月27日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryCityId(HashMap<String,Object> param);
	
	/**
	 * 获取管理员地市
	 * @param map
	 * @return
	 * @date 2015年11月27日
	 * @author rc
	 *
	 */
	public List<TMArea> getCitysOfStaff(HashMap<String,Object> map);
	
	/**
	 * 查询地市code
	 * @param param
	 * @return
	 * @date 2015年11月27日
	 * @author rencong
	 */
	public List<HashMap<String, Object>> queryCitysList(HashMap<String,Object> param);
	
	/**
	 * 获取默认地市
	 * @param map
	 * @return
	 * @date 2015年11月27日
	 * @author rc
	 */
	public List<TMArea> getDefaultCity(HashMap<String,Object> map);

	/**
	 * 查询公告信息
	 * @param param
	 * @return
	 * @date 2015年11月30日
	 * @author gelei
	 */
	public List<HashMap<String, Object>> getAnnouncementInfo(HashMap<String,Object> param);

	/**
	 * 获取管理员信息
	 * @param param
	 * @return
	 */
	public List<HashMap<String, Object>> getStaffByStaffRegName(Map<String, Object> param);
	
	/**
	 * 查询用户
	 * @param inParam
	 * @return
	 * @author liuxm
	 */
	public List<HashMap<String, Object>> staffQuery(HashMap<String, Object> inParam);
	public int staffCountQuery(HashMap<String, Object> inParam);
	
	/**
	 *  查询身份证临时表
	 * @param 
	 * @return
	 * @author zhanggs
	 */
	public TSTempIdCard queryIDCardByUUID(String uuid);
	
	/**
	 * 查询角色
	 * @param inParam
	 * @return
	 * @author liuxm
	 */
	public List<HashMap<String, Object>> roleQuery(HashMap<String, Object> inParam);
	public int roleCountQuery(HashMap<String, Object> inParam);
	
	/**
	 * 查询roleTree
	 * @param inParam
	 * @return
	 * @author liuxm
	 */
	public List<HashMap<String, Object>> roleTreeQuery(HashMap<String, Object> inParam);
	
	/**
	 * 查询权限
	 * @param inParam
	 * @return
	 * @author liuxm
	 */
	public List<HashMap<String, Object>> roleRightQuery(HashMap<String, Object> inParam);
	public int roleRightCountQuery(HashMap<String, Object> inParam);
	
	/**
	 * 查询员工基本信息
	 * @param inParam
	 * @return
	 */
	public List<HashMap<String, Object>> staffInfoQuery(HashMap<String, Object> inParam);
	
	/**
	 * 查询部门信息
	 * @param inParam
	 * @return
	 */
	public List<HashMap<String, Object>> queryApartmentList(HashMap<String, Object> inParam);
	
	/**
	 * 查询员工工号
	 * @param param
	 * @return
	 */
	public List<HashMap<String, Object>> queryStaffInfoId(HashMap<String,Object> param);
	
	/**
	 * 添加员工
	 * @param param
	 * @return
	 */
	public void addStaffInfoList(HashMap<String,Object> param);
	
	/**
	 * 删除员工信息
	 * @param param
	 * @return
	 */
	public void removeStaffInfo(HashMap<String,Object> param);
	
	/**
	 * 修改员工信息
	 * @param inParam
	 * @return
	 */
	public void updateStaffInfo(HashMap<String, Object> inParam);
	
	/**
	 * 查询部门编号
	 * @param param
	 * @return
	 */
	public List<HashMap<String, Object>> queryApartmentId(HashMap<String,Object> param);
	
	/**
	 * 添加部门
	 * @param param
	 * @return
	 */
	public void addApartmentList(HashMap<String,Object> param);
	
	/**
	 * 删除部门信息
	 * @param param
	 * @return
	 */
	public void removeApartment(HashMap<String,Object> param);
	
	/**
	 * 修改部门信息
	 * @param inParam
	 * @return
	 */
	public void updateApartment(HashMap<String, Object> inParam);
	
	/**
	 * 查询考勤信息
	 * @param inParam
	 * @return
	 */
	public List<HashMap<String, Object>> checkAttendQuery(HashMap<String, Object> inParam);
	
	/**
	 * 删除考勤信息
	 * @param param
	 * @return
	 */
	public void removeCheckAttend(HashMap<String,Object> param);
	
	/**
	 * 修改考勤信息
	 * @param inParam
	 * @return
	 */
	public void updateCheckAttend(HashMap<String, Object> inParam);
	
	/**
	 * 添加考勤信息
	 * @param param
	 * @return
	 */
	public void addCheckAttendList(HashMap<String,Object> param);
	
}
