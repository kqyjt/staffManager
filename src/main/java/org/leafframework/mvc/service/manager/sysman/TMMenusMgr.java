/*
  注意：
  此部分代码有系统自动生成，请不要手工修改，如需修改请联系李文武
  数据库用户：UP4BSS 数据库表：菜单目录树[T_M_MENUS]
 */

package org.leafframework.mvc.service.manager.sysman;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.io.File;

import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.orm.TMAreaHome;
import org.leafframework.data.dao.orm.TMMenus;
import org.leafframework.data.dao.orm.TMMenusHome;
import org.leafframework.data.dao.orm.TMRolesHome;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.DateUtil;
import org.leafframework.util.FileUtil;
import org.leafframework.util.PojoUtil;
import org.leafframework.util.RETURN;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("TMMenusMgr")
@Scope("prototype")
public class TMMenusMgr extends Business {
	@Override
	public RETURN init() throws Exception {
		HashMap<String, Object> outParam = this.getOutParam();
		HashMap<String, Object> inParam = this.getInParam();

		TMAreaHome tMAreaDao = (TMAreaHome) this.getDaoFactory().get("TMAreaHome");
		List<?> TMAreaList = tMAreaDao.findAll();
		outParam.put("TMAreaList", TMAreaList);

		TMRolesHome tMRolesDao = (TMRolesHome) this.getDaoFactory().get("TMRolesHome");
		List<?> TMRolesList = tMRolesDao.findAll();
		outParam.put("TMRolesList", TMRolesList);

		return RETURN.SUCCESS;
	}

	@Override
	public RETURN execute() throws Exception {
		if (this.getPageUri().get("f").equals("update")) {
			return updateTMMenusMgr();
		}
		if (this.getPageUri().get("f").equals("delete")) {
			return deleteTMMenusMgr();
		}
		if (this.getPageUri().get("f").equals("create")) {
			return createTMMenusMgr();
		}
		if (this.getPageUri().get("f").equals("import")) {
			return importTMMenusMgr();
		}
		if (this.getPageUri().get("f").equals("export")) {
			return exportTMMenusMgr();
		}
		return RETURN.REQ_ACTION_ERROR;
	}

	@Override
	public RETURN query() throws Exception {
		HashMap<String, Object> outParam = this.getOutParam();
		HashMap<String, Object> inParam = this.getInParam();

		// 动态sql查询
		TMMenus pojo = (TMMenus) getPojoFromPage(TMMenus.class,null);
		if (pojo == null) {
			outParam.put("errorDetail", "入参不完整");
			return RETURN.REQ_TRADE_ERROR;
		}
		MyBatisDAO myBatisDAO=(MyBatisDAO) this.getDaoFactory().get("myBatisDAO");
		List<TMMenus> list = (List<TMMenus>) myBatisDAO.getMenus(pojo);
		outParam.put("TMMenusList", list);
		return RETURN.SUCCESS;
	}

	public RETURN updateTMMenusMgr() throws Exception {
		HashMap<String, Object> outParam = this.getOutParam();
		TMMenus pojo = (TMMenus) getPojoFromPage(TMMenus.class,null);
		if (pojo == null) {
			outParam.put("errorDetail", "入参不完整");
			return RETURN.REQ_TRADE_ERROR;
		}
		TMMenusHome TMMenusDao = (TMMenusHome) this.getDaoFactory().get(
				"TMMenusHome");
		TMMenus ret=TMMenusDao.merge(pojo);
		
		MyBatisDAO myBatisDAO=(MyBatisDAO) this.getDaoFactory().get("myBatisDAO");
		pojo.setId(ret.getId()+1);
		myBatisDAO.insertTmmenus(pojo);

		return RETURN.SUCCESS;
	}

	public RETURN deleteTMMenusMgr() throws Exception {
		HashMap<String, Object> outParam = this.getOutParam();
		HashMap<String, Object> inParam = this.getInParam();
		TMMenusHome TMMenusDao = (TMMenusHome) this.getDaoFactory().get(
				"TMMenusHome");
		List TMMenusList = (List) inParam.get("TMMenusList");
		if (TMMenusList == null) {
			outParam.put("errorDetail", "入参不完整");
			return RETURN.REQ_TRADE_ERROR;
		}
		Iterator itr = TMMenusList.iterator();
		while (itr.hasNext()) {
			HashMap<String, Object> map = (HashMap<String, Object>) itr.next();
			TMMenus pojo = (TMMenus) PojoUtil.getPojoFromMap(TMMenus.class, map,null);
			TMMenusDao.delete(pojo);
		}

		return RETURN.SUCCESS;
	}

	public RETURN createTMMenusMgr() throws Exception {
		HashMap<String, Object> outParam = this.getOutParam();
		TMMenus pojo = (TMMenus) getPojoFromPage(TMMenus.class,null);
		if (pojo == null) {
			outParam.put("errorDetail", "入参不完整");
			return RETURN.REQ_TRADE_ERROR;
		}
		TMMenusHome TMMenusDao = (TMMenusHome) this.getDaoFactory().get(
				"TMMenusHome");
		TMMenusDao.persist(pojo);

		return RETURN.SUCCESS;
	}

	public RETURN importTMMenusMgr() throws Exception {
		HashMap<String, Object> outParam = this.getOutParam();
		HashMap<String, Object> inParam = this.getInParam();

		File importFile = (File) inParam.get("importFile");
		String fileName = (String) inParam.get("fileName");

		TMStaff loginStaff = (TMStaff) this.getSession().get("loginStaff");
		String dstFileName = loginStaff.getStaffId() + "."
				+ DateUtil.format(new Date()).replaceAll("[\\s-:]", "")
				+ ".TMMenus." + fileName;

		File dstFile = new File(FileUtil.uploadPath + dstFileName);

		FileUtil.copy(importFile, dstFile);
		List<HashMap<String, Object>> resultList = FileUtil.readExcelFile(
				dstFile.getPath(), 0, 0);

		logger.debug(resultList);

		Iterator<HashMap<String, Object>> itr = resultList.iterator();
		int count = 0;
		while (itr.hasNext()) {
			HashMap<String, Object> hmTMMenus = (HashMap<String, Object>) itr
					.next();
			TMMenus pojo = (TMMenus) PojoUtil.getPojoFromMap(TMMenus.class, hmTMMenus,null);
			TMMenusHome TMMenusDao = (TMMenusHome) this.getDaoFactory().get(
					"TMMenusHome");
			TMMenusDao.persist(pojo);
			count = count + 1;
		}
		outParam.put("errorDetail", "导入总记录数:[" + count + "]");

		return RETURN.SUCCESS;
	}

	public RETURN exportTMMenusMgr() throws Exception {
		return RETURN.SUCCESS;
	}
}
