package org.leafframework.mvc.service;

import java.util.HashMap;

import org.leafframework.util.RETURN;

public interface IBusiness {

	public void setInParam(HashMap<String, Object> inParam);

	public void setContext(HashMap<String, Object> context);
	
	public HashMap<String, Object> getContext();

	public HashMap<String, Object> getOutParam();
	
	public String getLogicView();

	public RETURN deal() throws Exception;

	public RETURN pageInit() throws Exception;

	public RETURN queryByCond() throws Exception;
}
