package org.leafframework.mvc.service.manager.verify;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.leafframework.mvc.model.IMap;
import org.leafframework.mvc.service.Business;
import org.leafframework.util.AESCoder;
import org.leafframework.util.RETURN;
import org.n3r.ecaop.client.EcAopClient;
import org.n3r.ecaop.client.EcAopMethod;
import org.n3r.ecaop.client.EcAopResult;
import org.n3r.ecaop.client.SignAlgorithm;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;

import com.alibaba.fastjson.JSONObject;

@Service("CertVerify")
@Scope("prototype")
public class CertVerify extends Business{

	@Override
	public RETURN execute() throws Exception {
		if("certVerify".equals(this.getPageUri().get("f"))){
			return certVerify(); 
		}
		
		return RETURN.SUCCESS;
	}

	@Override
	public RETURN init() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RETURN query() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private RETURN certVerify(){
		String url = "http://132.35.88.104:80/aop/aopservlet";
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String todaystr = dateFormat.format(now);
		
		//生成流水号
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
		String todaystr2 = dateFormat2.format(now);
		Random r = new Random();
		String apptx = todaystr2 + r.nextInt(100);
		
		// 准备业务参数
		EcAopClient  ecAopClient = new EcAopClient(url, "tjbc.sub", "MD5");
		// 指定约定的签名方式, 如MD5
		ecAopClient.setSignAlgorithm(SignAlgorithm.MD5);

		HashMap<String, Object> outParam = this.getOutParam();
		IMap inParam = new IMap(this.getInParam());
		String phoneNumber = inParam.getString("phoneNumber")==null ? "":inParam.getString("phoneNumber").trim();
		String certId = inParam.getString("certId")==null ? "":inParam.getString("certId").trim();
		String certName = inParam.getString("certName")==null ? "":inParam.getString("certName").trim();
		
		HashMap<String, Object> inMap = new HashMap<String, Object>();
		// 准备业务参数
		inMap.put("province", "13");
		inMap.put("city", "130");
		inMap.put("certId", certId);
		inMap.put("certName", certName);
		inMap.put("certType", "");

		// 准备平台参数参数
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("msg", JSONObject.toJSON(inMap));
		inputMap.put("apptx", apptx);
		inputMap.put("timestamp", todaystr);
		inputMap.put("bizkey", "CS-007");
		inputMap.put("method", "ecaop.trades.query.comm.cert.check");
		inputMap.put("appkey", "tjbc.sub");
		// 设置超时时间
		ecAopClient.setTimeoutMillis(60000);

		// 执行调用
		EcAopMethod ecAopMethod = ecAopClient.createEcAopMethod("ecaop.trades.query.comm.cert.check", Map.class);
		EcAopResult result = ecAopMethod.exec(inputMap);
		String response = analysisString(result.getResponse());
		if(result.getOut() == null && !certId.equals("") && !certName.equals("") && certId.length()>14){
			outParam.put("response",response);
		}
		if(result.getOut() != null){
			decodeBase64ToImage(((Map) result.getOut()).get("photo").toString(), apptx);
			Map map = (Map) result.getOut();
			outParam.put("apptx", apptx);
			outParam.put("certName", map.get("certName").toString());
			outParam.put("certId", map.get("certId").toString());
			outParam.put("sex", map.get("sex").toString());
			outParam.put("nation", map.get("nation").toString());
			if(map.get("exp")!=null && map.get("exp")!=""){
				outParam.put("exp", map.get("exp").toString());
			} else {
				outParam.put("exp", "");
			}
			if(map.get("birthday")!=null && map.get("birthday")!=""){
				outParam.put("birthday", map.get("birthday").toString());
			} else {
				outParam.put("birthday", "");
			}
			if(map.get("issue")!=null && map.get("issue")!=""){
				outParam.put("issue", map.get("issue").toString());
			} else {
				outParam.put("issue", "");
			}
			if(map.get("addr")!=null && map.get("addr")!=""){
				outParam.put("addr", map.get("addr").toString());
			} else {
				outParam.put("addr", "");
			}
			
			String str = AESCoder.encrypt(phoneNumber+"&"+certName+"&"+certId+"&"+map.get("addr").toString());
			outParam.put("param", str);
		}
		
		outParam.put("result", (Map) result.getOut());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add((Map) result.getOut());
		outParam.put("rows", list);
		
		return RETURN.SUCCESS;
	}
	
	/**
	 * 解析返回的字符串
	 * @param line
	 * @return
	 */
	private String analysisString(String line){
		String outRes = "";
		line.trim();
		String outline = line.substring(1, line.length()-1);
		String[] str = outline.split("\",\"");
		for(int i=0; i<str.length; i++){
			String[] temp = str[i].split(":");
			
			if(temp[0].equals("detail\"")){
				if(temp.length > 2){
					outRes = temp[1];
					for(int j=2; j<temp.length; j++){
						outRes += ":"+temp[j];
					}
				} else {
					outRes = temp[1];
				}
				
				break;
			}
		}
		return outRes;
	}
	
	public static void decodeBase64ToImage(String base64, String apptx) {
		BASE64Decoder decoder = new BASE64Decoder();
		
	    try {
			byte[] b = decoder.decodeBuffer(base64);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			
			String str1="";
			try {
				str1 = ""+Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
				System.out.println(str1);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			String str2 = str1.substring(0,str1.length()-16)+"/resources/guozht/";
			String imgFilePath = str2 + apptx + ".jpg";
			FileOutputStream out = new FileOutputStream(new File(imgFilePath));
			out.write(b);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
