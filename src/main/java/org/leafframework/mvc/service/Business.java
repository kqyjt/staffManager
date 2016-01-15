package org.leafframework.mvc.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.leafframework.data.dao.IDao;
import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.orm.TCCustomer;
import org.leafframework.data.dao.orm.TLMain;
import org.leafframework.data.dao.orm.TLMainHome;
import org.leafframework.data.dao.orm.TMStaff;
import org.leafframework.mvc.exception.MyException;
import org.leafframework.util.DateUtil;
import org.leafframework.util.PojoUtil;
import org.leafframework.util.RETURN;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

public abstract class Business implements IBusiness {
	protected Logger logger = Logger.getLogger(this.getClass());

	private int size = 15;
	
	private int page = 0;

	private HashMap<String, Object> context;

	private HashMap<String, Object> inParam = new HashMap<String, Object>();
	
	private String LogicView=this.getClass().getSimpleName()+"Page";
	
	private HashMap<String, Object> outParam = new HashMap<String, Object>();

	@Resource
	Map<String,IDao> daoFactory;

	private HashMap<String, Object> activitiService = new HashMap<String, Object>();
	
	@Value("${leaf.app.name}")
	private String appName;
	
	public Logger getLogger() {
		return logger;
	}

	public String getAppName() {
		return appName;
	}

	public String getLogicView() {
		return LogicView;
	}

	public void setLogicView(String logicView) {
		LogicView = logicView;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int pageSize) {
		this.size = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int pageNum) {
		this.page = pageNum;
	}

	public HashMap<String, Object> getActivitiService() {
		return activitiService;
	}

	public void setActivitiService(HashMap<String, Object> activitiService) {
		this.activitiService = activitiService;
	}

	public Map<String, IDao> getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(Map<String, IDao> daoFactory) {
		this.daoFactory = daoFactory;
	}

	public HashMap<String, Object> getOutParam() {
		return outParam;
	}

	public void setOutParam(HashMap<String, Object> outParam) {
		this.outParam = outParam;
	}

	public HashMap<String, Object> getInParam() {
		return inParam;
	}

	public void setInParam(HashMap<String, Object> inParam) {
		this.inParam = inParam;
	}

	public HashMap<String, Object> getContext() {
		return context;
	}

	public void setContext(HashMap<String, Object> context) {
		this.context = context;
	}

	public void setErrorDetail(String errorDetail) {
		this.getOutParam().put("errorDetail", errorDetail);
	}
	
	public String getSysdate() {
		MyBatisDAO myBatisDAO=(MyBatisDAO) this.getDaoFactory().get("myBatisDAO");
		return myBatisDAO.getSysdate();
	}

	public String getSequence(String sequenceName) {
		MyBatisDAO myBatisDAO=(MyBatisDAO) this.getDaoFactory().get("myBatisDAO");
		return myBatisDAO.getSequence(sequenceName);
	}

	public Object getPojoFromPage(Class<?> cls,String prefix) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException,
			ParseException {
		return PojoUtil.getPojoFromMap(cls, this.getInParam(), prefix);
	}
		
	public void initParam() throws Exception {
		HashMap<String, Object> pageUri =this.getPageUri();
		if (pageUri != null) {
			this.setPage(pageUri.get("page") == null ? this.getPage() : Integer.valueOf(pageUri.get(
					"page").toString()) - 1);// 页面显示页码开始1，后台数据处理页码开始0。
			this.setSize(pageUri.get("size") == null ? this.getSize() : Integer.valueOf(pageUri.get(
					"size").toString()));
		}
	}

	public abstract RETURN execute() throws Exception;

	public abstract RETURN init() throws Exception;

	public abstract RETURN query() throws Exception;

	public RETURN beforeExecute() throws Exception {

		initParam();
		serviceLog("begin");		
		return RETURN.SUCCESS;
	}

	@Transactional("txManagerLocal")
	public RETURN deal() throws Exception {
		RETURN ret = null;
		try {

			RETURN bret = beforeExecute();
			if (bret != RETURN.SUCCESS) {
				throw new MyException(bret);
			}

			ret = execute();

			if (ret != RETURN.SUCCESS) {
				throw new MyException(ret);
			}
			
			RETURN aret = afterExecute();
			if (aret != RETURN.SUCCESS) {
				throw new MyException(aret);
			}
			return ret;
		} catch (Exception e) {
			logger.info("LEAF-ERR=>"+e.getMessage());
			serviceLog("end");
			throw e;
		}
	}

	public RETURN afterExecute() throws Exception {

		String logType = null;
		String optrType = null;
		int optrUserId = 0;
		
		//管理中心用户操作
		if(this.getAppName().equals("manager")){
			TMStaff loginStaff = (TMStaff) this.getSession().get("managerAuthBean");
			if(loginStaff!=null){
				logType="00";
				optrType = "00";
				optrUserId=loginStaff.getId();
			}
		}
		//普通客户用户操作
		else if(this.getAppName().equals("center")){
			TCCustomer loginCustomer = (TCCustomer) this.getSession().get("loginCustomer");
			if(loginCustomer!=null){
				logType="01";
				optrType = "00";
				optrUserId=loginCustomer.getId();
			}
		}
		
		if (logType != null) {
			String optrName = this.getOperatorStr();			
			Date optrTime = DateUtil.parse(this.getSysdate());
			String remark = "测试";
			TLMain pojo = new TLMain(logType, optrType, optrName, optrUserId,
					optrTime, remark);

			TLMainHome TLMainDao = (TLMainHome) this.getDaoFactory().get(
					"TLMainHome");
			TLMainDao.persist(pojo);
		}
		serviceLog("end");
		return RETURN.SUCCESS;
	}

	public RETURN beforeInit() throws Exception {
		initParam();
		serviceLog("begin");	

		return RETURN.SUCCESS;
	}

	@Transactional("txManagerLocal")
	public RETURN pageInit() throws Exception {
		RETURN ret = null;
		try {
			RETURN bret = beforeInit();
			if (bret != RETURN.SUCCESS) {
				throw new MyException(bret);
			}

			ret = init();
			if (ret != RETURN.SUCCESS) {
				throw new MyException(ret);
			}
			
			RETURN aret = afterInit();
			if (aret != RETURN.SUCCESS) {
				throw new MyException(aret);
			}

			return ret;
		} catch (Exception e) {
			logger.info("LEAF-ERR=>"+e.getMessage());
			serviceLog("end");
			throw e;
		}
	}

	public RETURN afterInit() throws Exception {

		serviceLog("end");
		return RETURN.SUCCESS;
	}

	public RETURN beforeQuery() throws Exception {
		initParam();
		serviceLog("begin");
		return RETURN.SUCCESS;
	}

	@Transactional("txManagerLocal")
	public RETURN queryByCond() throws Exception {
		try {
			RETURN bret = beforeQuery();
			if (bret != RETURN.SUCCESS) {
				throw new MyException(bret);
			}
			RETURN ret = query();

			if (ret != RETURN.SUCCESS) {
				throw new MyException(ret);
			}
			
			RETURN aret = afterQuery();
			if (aret != RETURN.SUCCESS) {
				throw new MyException(aret);
			}
			return ret;
		} catch (Exception e) {
			logger.info("LEAF-ERR=>"+e.getMessage());
			serviceLog("end");
			throw e;
		}
	}

	public RETURN afterQuery() throws Exception {
		serviceLog("end");
		return RETURN.SUCCESS;
	}

	public String getOperatorStr() {
		HashMap<String, Object> pageUri = (HashMap<String, Object>) this.getPageUri();
		String operatorStr = pageUri.get("performer") + "=>" + pageUri.get("m") + "=>" + pageUri.get("f");
		return operatorStr;
	}
	public HashMap<String, Object> getSession() {
		HashMap<String, Object> session = (HashMap<String, Object>) this.getContext().get("session");
		return session;
	}
	public HashMap<String, Object> getPageUri() {
		HashMap<String, Object> pageUri = (HashMap<String, Object>) this.getContext().get("pageUri");
		return pageUri;
	}
	public HashMap<String, Object> getCookies() {
		HashMap<String, Object> pageUri = (HashMap<String, Object>) this.getContext().get("cookies");
		return pageUri;
	}
	private void serviceLog(String type) {
		if(type.equals("begin")){
			logger.info("########################[BEGIN:" + this.getOperatorStr() + "]##########################");
			logger.info("PageUri:" + this.getPageUri());
			logger.debug("Cookies:" + this.getCookies());
			logger.debug("Session:" + this.getSession());
			logger.info("InParam:" + this.getInParam());
			logger.info("+--------------------------------------------------------------------------+");
		}
		else if(type.equals("end")){
			logger.info("+--------------------------------------------------------------------------+");
			logger.debug("Cookies:" + this.getCookies());
			logger.debug("Session:" + this.getSession());
			// 输出日志太多，屏蔽输出信息
			// logger.info("OutParam:" + this.getOutParam());
			logger.info("########################[END  :" + this.getOperatorStr() + "]##########################");
		}
		else{
			//to do
		}
	}
}
