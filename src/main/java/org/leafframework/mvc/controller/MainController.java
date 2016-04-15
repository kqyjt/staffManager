package org.leafframework.mvc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.leafframework.data.mao.RedisDao;
import org.leafframework.mvc.exception.MyException;
import org.leafframework.mvc.model.PageData;
import org.leafframework.mvc.service.IBusiness;
import org.leafframework.util.AddressUtil;
import org.leafframework.util.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Constants;

@Controller
@Scope("prototype")
public class MainController {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	ApplicationContext applicationContext;

	@Value("${leaf.app.name}")
	private String appName;

	@Value("${leaf.app.mode}")
	private String appMode;

	@Value("${leaf.app.auth}")
	private String appAuth;

	@Value("${leaf.app.exclue}")
	private String appExclue;

	@Value("${leaf.app.uauth}")
	private String appUAuth;

	@Value("${leaf.session.location}")
	private String sessionLocation;

	@Value("${leaf.session.timeout}")
	private long sessionTimeout;

	@Value("${leaf.cookie.domain}")
	private String cookieDomain;

	@Value("${leaf.cookie.path}")
	private String cookiePath;

	@Value("${leaf.cookie.age}")
	private int cookieAge;

	private HttpSession httpSession;

	private String authToken;

	private HashMap<String, Object> pageUri;

	private HashMap<String, Object> pageData;

	private HashMap<String, Object> cookies;

	private HashMap<String, Object> result = new HashMap<String, Object>();

	private IBusiness tradeObj;

	private String subsystem;

	private String module;

	public String getAppMode() {
		return appMode;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getSubsystem() {
		return subsystem;
	}

	public void setSubsystem(String subsystem) {
		this.subsystem = subsystem;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getAppAuth() {
		return appAuth;
	}

	public String getAppExclue() {
		return appExclue;
	}

	public String getAppName() {
		return appName;
	}

	public String getAppUAuth() {
		return appUAuth;
	}

	public HashMap<String, Object> getCookies() {
		return cookies;
	}

	public void setCookies(HashMap<String, Object> cookies) {
		this.cookies = cookies;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public String getSessionLocation() {
		return sessionLocation;
	}

	public long getSessionTimeout() {
		return sessionTimeout;
	}

	public String getCookieDomain() {
		return cookieDomain;
	}

	public String getCookiePath() {
		return cookiePath;
	}

	public int getCookieAge() {
		return cookieAge;
	}

	public String getSessionPrefix() {
		String[] appUAuthLIst = this.getAppUAuth().split(",");
		String subsystem = this.getSubsystem();
		String prefix;
		// 统一认证session前缀为统一前缀
		if (Arrays.asList(appUAuthLIst).contains(subsystem)) {
			prefix = "session_leaf";
		} else {
			prefix = "session_" + this.getSubsystem();
		}
		return prefix;
	}

	public HashMap<String, Object> getSessionContext() {
		HashMap<String, Object> sessionContext;
		if (this.getSessionLocation().equals("local")) {
			sessionContext = (HashMap<String, Object>) this.getHttpSession()
					.getAttribute("sessionContext");
		} else if (this.getSessionLocation().equals("redis")) {
			RedisDao redisDao;
			try {
				redisDao = (RedisDao) this.getApplicationContext().getBean(
						"redisDao");
				sessionContext = redisDao.read(this.getSessionPrefix(),
						this.getAuthToken());
			} catch (Exception e) {
				throw e;
			}
		} else {
			sessionContext = null;
		}
		if (sessionContext == null) {
			sessionContext = new HashMap<String, Object>();
		}
		return sessionContext;
	}

	public void setSessionContext(HashMap<String, Object> sessionContext) {

		// session 在业务层同控制层传递过程中无登录标签不进行session持久化,验证码不受登录bean限制
		if (this.getSessionLocation().equals("local")) {
			this.getHttpSession()
					.setAttribute("sessionContext", sessionContext);
		} else if (this.getSessionLocation().equals("redis")) {
			RedisDao redisDao;
			try {
				redisDao = (RedisDao) this.getApplicationContext().getBean(
						"redisDao");
				redisDao.save(this.getSessionPrefix(), this.getAuthToken(),
						sessionContext, this.getSessionTimeout());
			} catch (Exception e) {
				throw e;
			}
		}
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	public HashMap<String, Object> getPageUri() {
		return pageUri;
	}

	public void setPageUri(HashMap<String, Object> pageParams) {
		this.pageUri = pageParams;
	}

	public HashMap<String, Object> getPageData() {
		return pageData;
	}
	public void setPageData(HashMap<String, Object> pageData) {
		this.pageData = pageData;
	}

	public IBusiness getTradeObj() {
		return tradeObj;
	}
	public void setTradeObj(IBusiness tradeObj) {
		this.tradeObj = tradeObj;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}
	public void setResult(RETURN retinfo, HashMap<String, Object> dataSet) {
		if (tradeObj != null) {
			HashMap<String, Object> session = (HashMap<String, Object>) tradeObj.getContext().get("session");
			if (session != null) {
				this.getResult().put("LeafAuthBean", session.get("LeafAuthBean"));
				this.getResult().put("managerAuthBean", session.get("managerAuthBean"));
			}
		}
		this.getResult().put("dataSet", dataSet);
		this.getResult().put("appMode", this.getAppMode());
		this.getResult().put("errorCode", retinfo.getErrorCode());
		this.getResult().put("errorMsg", retinfo.getErrorMsg());
		this.getResult().put("success",
				retinfo.getErrorCode().equals("00000") ? true : false);
	}
	private String getStackMsg(Exception e) {

		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stackArray = e.getStackTrace();
		for (int i = 0; i < stackArray.length; i++) {
			StackTraceElement element = stackArray[i];
			sb.append(element.toString() + "\r\n");
		}
		return sb.toString();
	}
	public void afterTradeObjParam() {
		this.setSessionContext((HashMap<String, Object>) this.getTradeObj()
				.getContext().get("session"));
		this.setCookies((HashMap<String, Object>) this.getTradeObj()
				.getContext().get("cookies"));
	}
	public void initTradeObjParam() {

		HashMap<String, Object> hmContext = new HashMap<String, Object>();
		hmContext.put("session", this.getSessionContext());
		hmContext.put("pageUri", this.getPageUri());
		hmContext.put("cookies", this.getCookies());

		this.getTradeObj().setContext(hmContext);
		HashMap<String,Object> tmpMap = this.getPageData();
		String objTemp = "";
		for (String key : tmpMap.keySet()) {
			if (tmpMap.get(key) instanceof String && !key.equals("notify_data")) {
				objTemp = tmpMap.get(key).toString();
				objTemp = objTemp.replaceAll("=", "").replaceAll("<", "《").replaceAll(">", "》").replaceAll("&", "&amp;").replaceAll("'", "“").replaceAll("\\(", "（").replaceAll("\\)", "）").toString();
				
				if(objTemp.indexOf("u003c") != -1 || objTemp.indexOf("u003e") != -1 || objTemp.indexOf("0xc0") != -1){
					objTemp = objTemp.replaceAll("u003c","《").replaceAll("u003e","》").replaceAll("0xc0","；").toString();
				}
				
				tmpMap.put(key, objTemp);
			}
		}
		
		this.getTradeObj().setInParam(tmpMap);
	}

	public void beforeTradeDeal() throws Exception {

		if (this.getPageData() == null) {
			this.setResult(RETURN.REQ_PARAM_ERROR, null);
			return;
		}

		initTradeObjParam();
	}

	public void afterTradeDeal() throws Exception {
		afterTradeObjParam();
	}

	public void beforeTradeInit() throws Exception {
		initTradeObjParam();
	}

	public void afterTradeInit() throws Exception {
		afterTradeObjParam();
	}

	public void beforeTradeQuery() throws Exception {
		initTradeObjParam();
	}

	public void afterTradeQuery() throws Exception {
		afterTradeObjParam();
	}

	public void execute() throws Exception {
		try {

			beforeTradeDeal();

			RETURN dret = this.getTradeObj().deal();

			this.setResult(dret, this.getTradeObj().getOutParam());

			afterTradeDeal();

		} catch (MyException e) {
			this.setResult(e.getRet(), this.getTradeObj().getOutParam());
		} catch (DataAccessException e) {
			e.printStackTrace();
			this.getTradeObj().getOutParam().put("errorDetail", e.getMessage());
			this.setResult(RETURN.DB_ERROR, this.getTradeObj().getOutParam());
		} catch (Exception e) {
			e.printStackTrace();
			this.getTradeObj().getOutParam().put("errorDetail", e.getMessage());
			this.setResult(RETURN.THROW_ERROR, this.getTradeObj().getOutParam());
		}
	}

	public void init() throws Exception {
		try {

			beforeTradeInit();

			RETURN dret = this.getTradeObj().pageInit();

			this.setResult(dret, this.getTradeObj().getOutParam());

			afterTradeInit();

		} catch (MyException e) {
			this.setResult(e.getRet(), this.getTradeObj().getOutParam());
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			this.getTradeObj().getOutParam().put("errorDetail", e.getMessage());
			this.setResult(RETURN.DB_ERROR, this.getTradeObj().getOutParam());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			this.getTradeObj().getOutParam().put("errorDetail", e.getMessage());
			this.setResult(RETURN.THROW_ERROR, this.getTradeObj().getOutParam());
		}
	}

	public void query() throws Exception {
		try {

			if (this.getPageData() == null) {
				throw new MyException(RETURN.REQ_PARAM_ERROR);
			}
			beforeTradeQuery();

			RETURN dret = this.getTradeObj().queryByCond();

			this.setResult(dret, this.getTradeObj().getOutParam());

			afterTradeQuery();

		} catch (MyException e) {
			this.setResult(e.getRet(), this.getTradeObj().getOutParam());
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			this.getTradeObj().getOutParam().put("errorDetail", e.getMessage());
			this.setResult(RETURN.DB_ERROR, this.getTradeObj().getOutParam());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			this.getTradeObj().getOutParam().put("errorDetail", e.getMessage());
			this.setResult(RETURN.THROW_ERROR, this.getTradeObj().getOutParam());
		}
	}

	// URI:/子系统/模块/处理对象。htm?m=init&f=add&page=0&size=20&url=target.jsp
	@RequestMapping(value = "{subsystem}/{module}/{performer}.{suffix}")
	public ModelAndView core(
			HttpServletRequest request,
			HttpServletResponse response,
			// 请求路径解析
			@PathVariable String subsystem,
			@PathVariable String module,
			@PathVariable String performer,// 执行业务对象
			@PathVariable(value = "suffix") String suffix,// json，html，jsp，excel等

			// 请求参数解析
			@RequestParam(value = "m", required = false, defaultValue = "init") String m,// method://
			// init,query,execute
			@RequestParam(value = "f", required = false, defaultValue = "zoo") String f,// function
			@RequestParam(value = "p", required = false) String p,// parameter：产品ID，p=190031
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "size", required = false) String size,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "authToken", required = false) String authToken,

			// 文件上传参数
			@RequestParam(value = "uploadFile", required = false) MultipartFile[] uploadFile,
			// 页面form表单解析
			@ModelAttribute("pageData") PageData pageData) throws Exception {
		/* +-------------------------初始化全局变量------------------------------+ */
		ModelAndView modelAndView = new ModelAndView();
		this.setHttpSession(request.getSession());
		this.getResult().put("requestURI", request.getRequestURI());
		this.setSubsystem(subsystem);
		this.setModule(module);
		this.setAuthToken(authToken == null ? request.getSession().getId() : authToken);
		
		/* +-------------------------链接是否注入验证 -------------------------+ */
		StringBuffer sb = new StringBuffer();
		String queryStr = request.getQueryString();
		
		if(queryStr!=null){
	        for(int i=0;i<queryStr.length();i++){
	            char c = queryStr.charAt(i);
	            if(Character.isUpperCase(c)){
	                sb.append(Character.toLowerCase(c));
	            }else if(Character.isLowerCase(c)){
	                // sb.append(Character.toUpperCase(c));
	            	sb.append(c);
	            }else{
	            	sb.append(c);
	            }
	        }
		}
		
		/*
		// 如此判断虽然可以验证某些非法链接，但是不严谨，易误杀
		if(sb.indexOf("script") != -1 && (sb.indexOf("alipaysubmit") != -1 || sb.indexOf("ecpaysubmit") != -1 || sb.indexOf("yeepayform") != -1)){
		} else {
			if (sb.indexOf("script") != -1 || sb.indexOf("prompt") != -1
					|| sb.indexOf("alert") != -1
					|| sb.indexOf("onmouseover") != -1
					|| sb.indexOf("iframe") != -1 || sb.indexOf("style") != -1) {
				this.setResult(RETURN.URLERROR, null);
				modelAndView.addObject("result", this.getResult());
				modelAndView.setViewName(null);
				return modelAndView;
			}
		} */
		
		/* +------------------------- 获取cookies参数 -------------------------+ */
		HashMap<String, Object> cookies = new LinkedHashMap<String, Object>();
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				cookies.put(cookie.getName(), cookie.getValue());
				if (cookie.getName().equals("leaftoken")) {
					this.setAuthToken(cookie.getValue());
				}
			}
		}
		
		/* +-------------------------json数据POST提交，相关处理-------------------+ */
		if (request.getHeader("Content-Type") != null && request.getHeader("Content-Type").contains("application/json")) {
			String jsonData = IOUtils.toString(request.getInputStream());
			if (jsonData != null && jsonData.length() > 0) {
				JsonFactory factory = new JsonFactory();
				ObjectMapper mapper = new ObjectMapper(factory);
				TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};
				HashMap<String, Object> jsonHm = mapper.readValue(jsonData,typeRef);
				HashMap<String, Object> jsonPageData = (HashMap<String, Object>) jsonHm.get("pageData");
				pageData.setPf((HashMap<String, Object>) jsonPageData.get("pf"));
			}
		}
		
		/* +-------------------------json数据POST提交，相关处理-------------------+ */
		if (request.getHeader("Content-Type") != null && request.getHeader("Content-Type").contains("text/xml")) {
			InputStream inputStream = request.getInputStream();
			// 读取输入流
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();

			// 遍历所有子节点
			// 将解析结果存储在HashMap中
			HashMap<String, Object> xmlHm = new HashMap<String, Object>();
			for (Element e : elementList){
				xmlHm.put(e.getName(), e.getText());
			}
			pageData.setPf(xmlHm);
			// 释放资源
			inputStream.close();
			inputStream = null;
		}

		/* +-------------------------判断当前请求是否为当前应用的功能------------------+ */
		String[] appModLIst = this.getAppName().split(",");
		if (!Arrays.asList(appModLIst).contains(subsystem)) {
			this.setResult(RETURN.SUCCESS, null);
			modelAndView.addObject("result", this.getResult());
			modelAndView.setViewName(null);
			return modelAndView;
		}
		/* +-------------------------session相关处理--------------------------+ */
		// 如果以产生验证码，则将验证码放入业务上下文sessionContext
		String[] appAuthList = this.getAppAuth().split(",");
		String[] appExclueList = this.getAppExclue().split(",");
		String[] appUAuthLIst = this.getAppUAuth().split(",");

		if (Arrays.asList(appAuthList).contains(subsystem)) {

			// 判断是否是同一认证，如果统一认证，多系统共享sessionbean
			String beanName;
			String LoginView;
			if (Arrays.asList(appUAuthLIst).contains(subsystem)) {
				beanName = "LeafAuthBean";
				LoginView = "LeafLoginPage";
			} else {
				beanName = subsystem + "AuthBean";
				LoginView = subsystem + "LoginPage";
			}
			if (this.getSessionContext().get(beanName) == null) {
				if (!Arrays.asList(appExclueList).contains(performer)) {
					this.setResult(RETURN.SES_TIME_OUT, null);
					
					/*// 记录登陆之前的请求页面信息
					String hostUrl = request.getHeader("Host");
					String applyUri = request.getRequestURI();
					String queryStr =  request.getQueryString();
					
					String frontPageUrl = "http://" + hostUrl + applyUri;
					if(null != queryStr){
						frontPageUrl += "?" + queryStr;
					}

					HashMap<String, Object> fp = this.getSessionContext();
					fp.put("frontPageUrl", frontPageUrl);
					this.setSessionContext(fp);*/
					
					modelAndView.addObject("result", this.getResult());
					modelAndView.setViewName(LoginView);
					return modelAndView;
				}
			} else {
				if (beanName.indexOf("LeafAuthBean") >= 0) {
					//TCCustomer customer = (TCCustomer) this.getSessionContext().get(beanName);
					if (subsystem.equals("center")) {
						this.setResult(RETURN.SES_TIME_OUT, null);
						modelAndView.addObject("result", this.getResult());
						modelAndView.setViewName(LoginView);
						return modelAndView;
					}
				}
			}
		}
		
		String captcha = (String) this.getHttpSession().getAttribute(
				Constants.KAPTCHA_SESSION_KEY);
		if (captcha != null) {
			HashMap<String, Object> hm = this.getSessionContext();
			hm.put("captcha", captcha);
			this.setSessionContext(hm);
		}
		
		/* +------------------------- 获取请求参数 ----------------------------+ */
		HashMap<String, Object> queryString = new LinkedHashMap<String, Object>();
		queryString.put("performer", performer);
		queryString.put("suffix", suffix);
		queryString.put("m", m);
		queryString.put("f", f);
		queryString.put("p", p);
		queryString.put("page", page);
		queryString.put("size", size);
		queryString.put("url", url);
		queryString.put("authToken", this.getAuthToken());
		queryString.put("remoteIp", request.getRemoteAddr());
		queryString.put("requestURI", request.getRequestURI());
		queryString.put("requestHost", request.getHeader("Host"));
		
		/* +------------------------- 获取请求终端(电脑/手机)----------------------------+ */
		String device="computer";
		String agentHeader = "";
		
		if(null != request.getHeader("user-agent") && !"".equals(request.getHeader("user-agent"))){
			agentHeader = request.getHeader("user-agent").toLowerCase();
			String[] deviceArray = new String[]{"android","iphone","windows phone","blackberry"};
			for(int i=0;i<deviceArray.length;i++){
	            if(agentHeader.indexOf(deviceArray[i])>0){
	            	device = "phone";
	            	break;
	            }
	        }
		}
		queryString.put("agent", device);
		
		queryString.put("refer", request.getHeader("referer"));
		queryString.put("contextpath", request.getContextPath());
		queryString.put("cityName", this.getIpAddr(request));
		
		String hostUrl = request.getHeader("Host");
		queryString.put("hostUrl", hostUrl);

		/*---------------将非spring 标签form 参数加入到pageData-----------------+*/
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			if (!queryString.containsKey(entry.getKey())) {
				/* pf['']包含的变量为spring 标签 form变量<form:input/> */
				if ((!entry.getKey().startsWith("pf['"))
						&& (!entry.getKey().endsWith("']"))) {
					String[] values = entry.getValue();
					if (values.length <= 1) {
						pageData.getPf().put(entry.getKey(), values[0]);
					} else {
						pageData.getPf().put(entry.getKey(), values);
					}
				}
			}
		}
		
		/*-------------------------上传文件参数传递给业务层------------------------+*/
		if(uploadFile!=null){
			pageData.getPf().put("uploadFile", uploadFile);
		}

		/*-------------------------执行者bean参数准备--------------------------+*/
		this.setPageUri(queryString);
		this.setCookies(cookies);
		this.setPageData(pageData.getPf());
		/*-------------------------获取执行者bean ---------------------------+*/
		IBusiness tradeObj;
		try {
			tradeObj = (IBusiness) this.getApplicationContext().getBean(
					performer);
		} catch (Exception e) {
			/* 找不到具体执行的java bean 将按照请求页面逻辑来处理，即按照成功返回 */
			logger.debug("-------------[" + performer
					+ "] be as a view----------------");
			this.setResult(RETURN.SUCCESS, null);
			modelAndView.addObject("result", this.getResult());
			modelAndView.setViewName("/" + subsystem + "/" + module + "/"
					+ performer);
			return modelAndView;
		}
		this.setTradeObj(tradeObj);

		/* +----------------------- 核心调度逻辑-------------------------------+ */
		if (m.equals("init")) {
			this.init();
		} else if (m.equals("execute")) {
			this.execute();
		} else if (m.equals("query")) {
			this.query();
		} else {
			// todo
		}
		/* +------------------------- 处理cookies参数 -------------------------+ */
		for (Entry<String, Object> entry : this.getCookies().entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			Cookie cookie = new Cookie(key, value);
			cookie.setDomain(this.getCookieDomain());
			cookie.setPath(this.getCookiePath());

			// JSESSIONID 不做 有效期控制
			if (!key.equals("JSESSIONID")) {
				cookie.setMaxAge(this.getCookieAge());
			}
			response.addCookie(cookie);
		}
		/* +----------------------- 返回视图处理--------------------------------+ */
		// 文件下载：怕暖业务逻辑层是否存在downLoadPath，fileName变量
		String downLoadPath=(String) this.getTradeObj().getOutParam().get("downLoadPath");
		String fileName=(String) this.getTradeObj().getOutParam().get("fileName");
		if(!StringUtils.isEmpty(downLoadPath)&&!StringUtils.isEmpty(downLoadPath)){
			return download(response, downLoadPath, fileName);
		}
		//excel文件导出 added by zhaidw
		if(!StringUtils.isEmpty(this.getTradeObj().getOutParam().get("exportExcelFile"))){
			return exportExcel(request,response, this.getTradeObj().getOutParam().get("exportExcelFile"), fileName);
		}
		
		// 常规视图处理
		modelAndView.addObject("result", this.getResult());
		if (StringUtils.isEmpty(url)) {
			modelAndView.setViewName(this.getTradeObj().getLogicView());
		} else {
			modelAndView.setViewName("redirect:" + url);
		}
		pageData.getPf().remove("uploadFile");
		return modelAndView;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param exportObject HSSFWorkbook对象
	 * @param fileName 导出excel文件时,要导出的文件名
	 * @author zhaidw
	 */
	public ModelAndView exportExcel(HttpServletRequest request,HttpServletResponse response,
			Object exportObject, String fileName) throws IOException {
		try {
			String filename = encodeFilename(fileName+".xls", request);
	        response.setContentType("application/vnd.ms-excel");     
	        response.setHeader("Content-disposition", "attachment;filename=" + filename);     
	        OutputStream ouputStream = response.getOutputStream();     
	        ((HSSFWorkbook)exportObject).write(ouputStream);     
	        ouputStream.flush();  
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	
	/**
	 * 对下载文件名编码
	 * @param filename
	 * @param request
	 * @return
	 * @author zhaidw
	 */
	public String encodeFilename(String filename, HttpServletRequest request){
		String agent = request.getHeader("USER-AGENT");    
		try {    
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {    
				String newFileName = URLEncoder.encode(filename, "UTF-8");    
				newFileName = StringUtils.replace(newFileName, "+", "%20");    
				if (newFileName.length() > 150) {    
					newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");    
					newFileName = StringUtils.replace(newFileName, " ", "%20");    
				}    
				return newFileName;    
			}    
			if ((agent != null) && (-1 != agent.indexOf("Mozilla"))){
				return MimeUtility.encodeText(filename, "UTF-8", "B");    
			}
			return filename;    
        } catch (Exception ex) {    
          return filename;    
        }    
    }

	public ModelAndView download(HttpServletResponse response,
			String downLoadPath, String fileName) throws IOException {
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-Length", String.valueOf(fileLength));
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("utf-8"), "utf-8"));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}
	
	// 获取用户地址信息
	private String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;

		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
			ipAddress = request.getHeader("HTTP_CLIENT_IP");  
		}  
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
			ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");  
		} 
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Real-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					// e.printStackTrace();
					logger.error("根据网卡取本机配置的IP未正确获取到IP，将使用默认地址！");
				}
				ipAddress = inet.getHostAddress();
			}
		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		 
		// 测试使用IP
		// ipAddress = "60.215.240.145";
		
		String cityName = "济南";
		try {
			cityName = AddressUtil.getAddresses("ip=" + ipAddress, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			logger.error("UnsupportedEncodingException -- 未根据IP正确获取到地市信息，将使用默认地址！");
		} catch (IOException ioe){
			logger.error("IOException -- 未根据IP正确获取到地市信息，将使用默认地址！");
		}
		if(null == cityName || "".equals(cityName) || "0".equals(cityName)){
			cityName = "济南";
		}
		return cityName;
	}
}
