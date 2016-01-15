package org.leafframework.mvc.service.manager.configuration;

import java.util.HashMap;

import org.leafframework.data.dao.mapper.MyBatisDAO;
import org.leafframework.data.dao.orm.TSTempIdCard;
import org.leafframework.data.dao.orm.TSTempIdCardHome;
import org.leafframework.mvc.model.IMap;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.FtpUtil;
import org.leafframework.util.RETURN;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 手机端身份证上传
 * @author zhanggs
 * 2015年5月25日
 */
@Service("UpLoadIDCardByPhone")
@Scope("prototype")
public class UpLoadIDCardByPhone extends Business {

	@Override
	public RETURN execute() throws Exception {
		// TODO Auto-generated method stub
		if (this.getPageUri().get("f").equals("uploadIDCardByPhone")) {
			this.setLogicView("upload_idcard_by_phone");
			return RETURN.SUCCESS;
		}
		if (this.getPageUri().get("f").equals("addIDCards")) {
			return addIDCards();
		}
		
		return RETURN.REQ_ACTION_ERROR;
	}

	@Override
	public RETURN init() throws Exception {
		// TODO Auto-generated method stub
		return RETURN.REQ_ACTION_ERROR;
	}

	@Override
	public RETURN query() throws Exception {
		// TODO Auto-generated method stub
		if (this.getPageUri().get("f").equals("queryIDCard")) {
			return queryIDCardByUUID();
		}
		
		return RETURN.REQ_ACTION_ERROR;
	}

	private RETURN addIDCards() throws Exception {
		
		IMap inParam = new IMap(this.getInParam());
		
		TSTempIdCardHome idCardDao = (TSTempIdCardHome)this.getDaoFactory().get("TSTempIdCardHome");
		TSTempIdCard idCard = new TSTempIdCard();
		
		if (inParam.containsKey("uuid")) idCard.setUuid(inParam.getString("uuid"));
		if (inParam.containsKey("zhengmianIdCard")) idCard.setPhotoUpUrl(inParam.getString("zhengmianIdCard"));
		if (inParam.containsKey("fanmianIdCard")) idCard.setPhotoDownUrl(inParam.getString("fanmianIdCard"));
		idCard.setCreateTime(inParam.getNow());
		
		idCardDao.merge(idCard);
		
		return RETURN.SUCCESS;
	}
	
	private RETURN queryIDCardByUUID() throws Exception {
		
		IMap inParam = new IMap(this.getInParam());
		HashMap<String, Object> outParam = this.getOutParam();
		HashMap<String, Object> pageUri = (HashMap<String, Object>) this.getContext().get("pageUri");
		String remoteIp = pageUri.get("requestHost").toString();
		String uuid = inParam.getString("uuid");
		String photoUpUrl = "";
		String photoDownUrl = "";
		
		MyBatisDAO myBatisDAO = (MyBatisDAO)this.getDaoFactory().get("myBatisDAO");
		TSTempIdCard idCard = myBatisDAO.queryIDCardByUUID(uuid);
		
		if(null != idCard){
			photoUpUrl = idCard.getPhotoUpUrl();
			photoDownUrl = idCard.getPhotoDownUrl();
			
			idCard.setPhotoUpUrl(new FtpUtil().genImgShowUrl(photoUpUrl,remoteIp));
			idCard.setPhotoDownUrl(new FtpUtil().genImgShowUrl(photoDownUrl,remoteIp));
		}
		
		outParam.put("idCardUrlUp", photoUpUrl);
		outParam.put("idCardUrlDown", photoDownUrl);
		outParam.put("idCard", idCard);
		
		return RETURN.SUCCESS;
	}
}
