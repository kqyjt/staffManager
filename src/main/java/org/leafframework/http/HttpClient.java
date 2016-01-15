package org.leafframework.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.web.client.RestTemplate;

public class HttpClient {
	private RestTemplate rest;

	public HttpClient() {
		this.rest = new RestTemplate();
	}

	public HashMap<String, Object> getForObject(String uri) {
		HashMap<String, Object> result = rest.getForObject(uri, HashMap.class);
		return result;
	}		
	public HashMap<String, Object> postForObject(String uri,Map<String, Object> formData) {
		HashMap<String, Object> pageParam=new HashMap<String, Object>();
		HashMap<String, Object> pageData=new HashMap<String, Object>();
		pageData.put("pf", formData);
		pageParam.put("pageData", pageData);
		HashMap<String, Object> result = rest.postForObject(uri,pageParam,HashMap.class);
		return result;
	}	
	
	public String sendRequest(String url){
		GetMethod getMethod = new GetMethod(url);
		try {
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			httpClient.executeMethod(getMethod);
			return getMethod.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();;
		}finally{
			getMethod.releaseConnection();
		}
		return null;
	}
}
