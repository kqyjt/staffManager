/*
  注意：
  此部分代码有系统自动生成，请不要手工修改，如需修改请联系李文武
  数据库用户：UP4BSS 数据库表：菜单目录树[T_M_MENUS]
 */

package org.leafframework.mvc.service.portal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.mapper.MyBatisUtil;
import org.leafframework.data.dao.mapper.VerifyBatisDAO;
import org.leafframework.data.dao.orm.TCRegisterSmscode;
import org.leafframework.data.dao.orm.TCRegisterSmscodeHome;
import org.leafframework.mvc.exception.MyException;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.AESCoder;
import org.leafframework.util.DateUtil;
import org.leafframework.util.FtpUtil;
import org.leafframework.util.RETURN;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sinovatech.ntf.send.NtfplatService;

import org.apache.commons.lang.StringUtils;

@Service("VerifyMgr")
@Scope("prototype")
public class VerifyMgr extends Business {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public RETURN init() throws Exception {
		//this.getUnLogOrderList();
		return RETURN.SUCCESS;
	}
	
	@Override
	public RETURN execute() throws Exception {
		if (this.getPageUri().get("f").equals("getverifycode")) {
			return getVerifyCode();
		} else if (this.getPageUri().get("f").equals("checkverifycode")) {
			return checkVerifyCode();
		} else if (this.getPageUri().get("f").equals("checkverifycodesucess")) {
			return checkVerifyCodeSuccess();
		} else if (this.getPageUri().get("f").equals("initQita2")) {
			return initQita2();
		} else if (this.getPageUri().get("f").equals("addVerify")) {
			return addVerify();
		} else if(this.getPageUri().get("f").equals("checkVerify")){
			checkVerify();
		} else if(this.getPageUri().get("f").equals("certVerify")) {
			return certVerify();
		} else if(this.getPageUri().get("f").equals("login")){
			return login();
		} else if(this.getPageUri().get("f").equals("decryptPhone")){
			return decryptPhone();
		} else if(this.getPageUri().get("f").equals("decryptParam")){
			return decryptParam();
		}
		
		return RETURN.REQ_ACTION_ERROR;
	}
	
	private RETURN decryptParam(){
		HashMap<String, Object> inParam = this.getInParam();
		HashMap<String, Object> outParam = this.getOutParam();
		String param = inParam.get("param")==null ? "":inParam.get("param").toString();
		String tmp = AESCoder.decrypt(param);
		String str[] = tmp.split("&");
		outParam.put("phone", str[0]);
		outParam.put("certName", str[1]);
		outParam.put("certId", str[2]);
		outParam.put("addr", str[3]);
		
		return RETURN.SUCCESS;
	}
	
	private RETURN decryptPhone(){
		HashMap<String, Object> inParam = this.getInParam();
		HashMap<String, Object> outParam = this.getOutParam();
		String phoneNumber = inParam.get("phoneNumber").toString();
		String str = AESCoder.decrypt(phoneNumber);
		outParam.put("phone", str);
		
		return RETURN.SUCCESS;
	}
	
	private RETURN login(){
		//获取客户端访问
		String agent = this.getPageUri().get("agent").toString();
		if("computer".equals(agent))
		{
			this.setLogicView("verifySMBD1");
		}
		else
		{
			this.setLogicView("verifySMBD11");
		}
		return RETURN.SUCCESS;
	}
	
	private RETURN getVerifyCode() {
		HashMap<String, Object> outParam = this.getOutParam();
		
		Map<String,Object> inparam = this.getInParam();
		log.info("this.getInParam():" + inparam);
		
		String phone = (String)inparam.get("SERIAL_NUMBER");
		
		if(StringUtils.isEmpty(phone)){
			this.setErrorDetail("接收验证码手机为空");
			return RETURN.REGISTER_RECEIVE_NOPHONE;
		}
		
		VerifyBatisDAO dao = (VerifyBatisDAO) this.getDaoFactory().get("verifyBatisDAO");
		String telephone = (String)inparam.get("SERIAL_NUMBER");
		
		HashMap<String, Object> tempMap1 = new HashMap<String, Object>();
		tempMap1.put("phoneNumber", telephone);
		List<HashMap<String, Object>> stateList1 = dao.queryVerifyStateList1(tempMap1);
		
		HashMap<String, Object> tempMap2 = new HashMap<String, Object>();
		tempMap2.put("phoneNumber", telephone);
		List<HashMap<String, Object>> stateList2 = dao.queryVerifyStateList2(tempMap2);
		if(stateList1.size() != 0){
			outParam.put("answer", "您前期提交的补登记信息正在处理中，请等待处理结果。");
		}
		if(stateList2.size() != 0){
			outParam.put("answer", telephone+"用户已经补登记成功。");
		}
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("phoneNumber", telephone);
		List<HashMap<String, Object>> phoneList = dao.queryPhoneList(paramMap);
		if(phoneList.size() == 0){
			outParam.put("errorDetail", "非山东联通用户不能通过此界面办理自助补登记");
			this.setLogicView("forHomePagee");//返回至系统首页
			return RETURN.PHONE_FAILED_MSG;
		}
		
		String strTel = phone.substring(0, 3);
		//处理 联通号段:186 185 156 155 130 131 132 176
		if(("186".equals(strTel)) || ("185".equals(strTel)) || ("156".equals(strTel)) || ("155".equals(strTel)) 
				|| ("130".equals(strTel)) || ("131".equals(strTel)) || ("132".equals(strTel)) || ("176".equals(strTel))){
			
			String sRand = "";
			for (int i = 0; i < 4; i++) {
				String rand = String.valueOf(RandomUtils.nextInt(10));
				sRand += rand;
			}
			logger.info(phone+",code:"+sRand);
			
			//String content = "您身份验证的验证码为:" + sRand + ",有效期一分钟,请在页面上输入验证码，完成操作。山东联通网盟平台";
			String content = "【山东联通实名补登记通知】尊敬的" + phone + "用户，您的验证码为" + sRand + ",有效期一分钟。";
			//从短信表里获取当前手机一分钟内收到的验证码
			HashMap<String,Object> param1 = new HashMap<String,Object>();
			param1.put("phone",phone);
			
			MyBatisDAO myBatisDAO=(MyBatisDAO) this.getDaoFactory().get("myBatisDAO");
			List<TCRegisterSmscode> smsList = myBatisDAO.getSmsCodeWithInOneMinute(param1);
			if(null != smsList && smsList.size()>0) {
				  outParam.put("errorDetail", "获取验证码过于频繁，请稍候再试!");
				  this.setErrorDetail("获取验证码过于频繁，请稍候再试!");
				  return  RETURN.SENDSMS_FAILED;
			} else{
				// 调用短信接口
				try{
					// 业务类别编码
					String bizCode = "11006";
					// 业务类别名称
					String bizName = "通知短信下发";
					// 任务名称
					String taskName = "通知短信告知";

					NtfplatService.sendSingle(bizCode, bizName, taskName, content, phone);
					
					//短信发送成功落表
					TCRegisterSmscode smscode = new TCRegisterSmscode();
					smscode.setPhone(phone);
					smscode.setRegicode(sRand);
					smscode.setSendtime(DateUtil.parse(this.getSysdate()));
					TCRegisterSmscodeHome TCRegisterSmscodeDao = (TCRegisterSmscodeHome) this.getDaoFactory().get("TCRegisterSmscodeHome");
					TCRegisterSmscodeDao.persist(smscode);
				}catch(Exception e){
					throw new MyException(RETURN.SENDSMS_ENCODINGEXCEPTION);
				}
			}
		}else{
			outParam.put("errorDetail", "非山东联通用户不能通过此界面办理自助补登记");
			this.setLogicView("forHomePagee");//返回至系统首页
			return RETURN.PHONE_FAILED_MSG;
		}
		
		return RETURN.SUCCESS;
	}
	
	private RETURN checkVerifyCode() {
		Map<String, Object> outParam = this.getOutParam();
		
		Map<String,Object> inparam = this.getInParam();
		log.info("this.getInParam():" + inparam);
		
		String telephone = (String)inparam.get("SERIAL_NUMBER");
		String smscode = (String)inparam.get("CHECK_CODE");
		
		MyBatisDAO myBatisDAO1=(MyBatisDAO) this.getDaoFactory().get("myBatisDAO");
		
		if(telephone!=null) {
			
			VerifyBatisDAO dao = (VerifyBatisDAO) this.getDaoFactory().get("verifyBatisDAO");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("phoneNumber", telephone);
			List<HashMap<String, Object>> phoneList = dao.queryPhoneList(paramMap);
			if(phoneList.size() == 0){
				outParam.put("errorDetail", "非山东联通用户不能通过此界面办理自助补登记");
				return RETURN.PHONE_FAILED_MSG;
			}
			
			if(null == smscode) {
				outParam.put("errorDetail", "请输入手机收到的验证码");
				return RETURN.REGISTER_NONESMSCODE;
			}else{
				//从短信表里获取当前手机一分钟内收到的验证码
				
				HashMap<String,Object> param1 = new HashMap<String,Object>();
				param1.put("phone",telephone);
				
				List<TCRegisterSmscode> smsList = myBatisDAO1.getSmsCodeWithInOneMinute(param1);
				if(null != smsList && smsList.size()>0) {
					String regiCode = smsList.get(0).getRegicode();
					
					if(!smscode.equals(regiCode)) {
						outParam.put("errorDetail", "手机验证码不匹配");
						return RETURN.REGISTER_NOMATCHSMSCODE;
						
					}else{
						//记录当前验证手机号
						this.getSession().put("SERIAL_NUMBER_VERIFIED", telephone);
						outParam.put("errorDetail", "Success");
						String str = AESCoder.encrypt(telephone);
						outParam.put("phoneNumber", str);
						return RETURN.SUCCESS;
					}
				}else{
					outParam.put("errorDetail", "无效手机验证码，请重新获取！");
					return RETURN.SENDSMS_FAILED_MSG;
				}
				
			}
		}
		
		return RETURN.SUCCESS;
	}
	
	private RETURN checkVerifyCodeSuccess() {
		this.setLogicView("checkVerifyCodeSuccess");
		Map<String, Object> outParam = this.getOutParam();
		outParam.put("SERIAL_NUMBER", this.getPageUri().get("serial_number"));
		return RETURN.SUCCESS;
	}
	
	private RETURN initQita2(){
		Map<String,Object> inparam = this.getInParam();
		log.info("this.getInParam():" + inparam);
		
		Map<String, Object> outParam = this.getOutParam();
		outParam.put("SERIAL_NUMBER", this.getSession().get("SERIAL_NUMBER_VERIFIED"));
		
		return RETURN.SUCCESS;
	}

	private RETURN getTestList(Map<String,Object> inparam){
		VerifyBatisDAO dao = (VerifyBatisDAO) this.getDaoFactory().get("verifyBatisDAO");
		List<Map<String,String>> rows = dao.queryTestList(new HashMap<String,String>());
		log.info("VerifyBatisDAO.getTestList():" + rows);
		
		MyBatisUtil.convertListMapToUpper(rows);
		log.info("MyBatisUtil.convertListMapToUpper(rows):" + rows);
		
		this.getOutParam().put("data",rows);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 国顺通验证姓名、身份证是否一致
	 * @return
	 */
	private String checkVerify(){
		String str = "success";
		HashMap<String, Object> outParam = this.getOutParam();
		
		if(!checkGST()){
			outParam.put("errorDetail", "您提交的身份证信息不合规，请重新输入。");
			this.setLogicView("verifySMBD2");
		}
				
		return str;
	}
	
	/**
	 * 国政通接口验证
	 * @return
	 */
	private RETURN certVerify(){
		HashMap<String, Object> inParam = this.getInParam();
		HashMap<String, Object> outParam = this.getOutParam();
		outParam.put("USER_NAME", inParam.get("USER_NAME").toString());
		outParam.put("PSPT_ID", inParam.get("PSPT_ID").toString());
		
		if("1".equals(inParam.get("IS_WAP"))){
			this.setLogicView("redirect:/portal/verify/wap_qita_tow_2.htm?serialnumber=" + (String)inParam.get("SERIAL_NUMBER_HIDDEN"));//跳转成功界面
		} else {
			this.setLogicView("redirect:/portal/verify/qita_tow_2.htm?serialnumber=" + (String)inParam.get("SERIAL_NUMBER_HIDDEN"));//跳转成功界面
		}
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 实名补登
	 * @return
	 * @author shh
	 */
	private RETURN addVerify() {
		HashMap<String, Object> outParam = this.getOutParam();
		HashMap<String, Object> inParam = this.getInParam();
		String isWap = (String) inParam.get("isCheck");
				
		// 上传身份证照片
		Map<String,String> ret = registerUploadFile();
		
		if (!StringUtils.isEmpty(ret.get("ERROR"))) {
			outParam.put("errorDetail", "身份证上传失败,请重新上传");
			
			if(isWap.equals("false")){
				this.setLogicView("verifySMBD2");
			} else {
				this.setLogicView("verifySMBD22");
			}
			return RETURN.PIC_FILE_UPLOAD_FAIL;
		}else{
			//保存实名信息
			try {
				ret.put("USER_NAME", inParam.get("USER_NAME").toString());
				ret.put("CARD_NUM", new String(inParam.get("PSPT_ID").toString().getBytes("ISO_8859-1"), "UTF-8"));
				if(inParam.get("addr")!=null && inParam.get("addr")!=""){
					ret.put("CARD_ADDRESS", inParam.get("addr").toString());
				}
				ret.put("PHONE_NUM", (String)inParam.get("SERIAL_NUMBER_HIDDEN"));
				ret.put("AUDIT_STATE", "A0");
				ret.put("BIS_STATE", "B0");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			VerifyBatisDAO dao = (VerifyBatisDAO) this.getDaoFactory().get("verifyBatisDAO");
			//地市信息
			List<Map<String,String>> list = MyBatisUtil.convertListMapToUpper(dao.getEparchyCodeByPhone(ret));
			if(list.size() > 0){
				ret.put("CITY_ID", dao.getLocalCityCode(list.get(0)));
			} else {
				outParam.put("errorDetail", "根据号码获取地市信息失败");
				if(isWap.equals("false")){
					this.setLogicView("verifySMBD2");
				} else {
					this.setLogicView("verifySMBD22");
				}
				return RETURN.PIC_FILE_UPLOAD_FAIL;
			}
			
//ret.put("CITY_ID", "013");
			
			dao.addVerifyUserInfo(ret);
			
			if("1".equals(inParam.get("IS_WAP"))){
				this.setLogicView("redirect:/portal/verify/wap_qita_toz.htm?serialnumber=" + (String)inParam.get("SERIAL_NUMBER_HIDDEN"));//跳转成功界面
			} else {
				this.setLogicView("redirect:/portal/verify/qita_toz.htm?serialnumber=" + (String)inParam.get("SERIAL_NUMBER_HIDDEN"));//跳转成功界面
			}
			
		}
		
		return RETURN.SUCCESS;
	}
	
	private boolean checkGST() {
		HashMap<String, Object> inParam = this.getInParam();
		String isCheck = inParam.get("isCheck")==null ? "":inParam.get("isCheck").toString();
		if(isCheck.equals("true")){
			return true;
		} else {
			return false;
		}
	}

	private Map<String,String> registerUploadFile(){
		Map<String,String> result = new HashMap<String,String>();
		HashMap<String, Object> inParam = this.getInParam();
		MultipartFile[] uploadFiles = (MultipartFile[]) inParam.get("uploadFile");
		if (uploadFiles != null) {
			for (int k = 0; k < uploadFiles.length; k++) {
				MultipartFile idcardFile = uploadFiles[k];
				if(idcardFile.getSize() == 0){
					continue;
				}
				// 调用图片上传模块
				// 调用图片上传模块
				FtpUtil myftpnew=new FtpUtil();
				String fileresult=null;
				try {
					fileresult = myftpnew.uploadsteamfile(idcardFile.getInputStream(), idcardFile.getOriginalFilename());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (StringUtils.isEmpty(fileresult)) {
					this.setErrorDetail(idcardFile.getName() + "文件上传失败！");
					result.put("ERROR", "图片上传失败");
					return result;
				}
				if (k == 0) {
					result.put("CARD_PIC_A", fileresult);
				} else if (k == 1) {
					result.put("CARD_PIC_B", fileresult);
				} else if (k == 2) {
					result.put("CARD_PIC_C", fileresult);
				}
			}
		} 
		return result;
	}
	
	

	@Override
	public RETURN query() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		Object o = null;
		String s = (String)o;
		System.out.println(s);		
	}
}
