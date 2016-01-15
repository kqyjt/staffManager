package org.leafframework.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.json.simple.JSONValue;

public class HttpPost {
	private static Logger logger = Logger.getLogger(HttpPost.class);

	public HashMap<String, Object> DoPost(String urlStr, Map parameters) {
		String line = "";
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator iter = parameters.entrySet().iterator(); iter
					.hasNext();) {
				Entry element = (Entry) iter.next();
				params.append(element.getKey().toString());
				params.append("=");
				params.append(URLEncoder.encode(element.getValue().toString(),
						"UTF-8"));
				params.append("&");
			}

			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(urlStr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestMethod("POST");
			// System.setProperty("sun.net.client.defaultConnectTimeout",
			// String.valueOf(HttpRequestProxy.connectTimeOut));
			// （单位：毫秒）jdk1.4换成这个,连接超时
			// System.setProperty("sun.net.client.defaultReadTimeout",
			// String.valueOf(HttpRequestProxy.readTimeOut));
			// （单位：毫秒）jdk1.4换成这个,读操作超时
			// url_con.setConnectTimeout(5000);//（单位：毫秒）jdk
			// 1.5换成这个,连接超时
			// url_con.setReadTimeout(5000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
			con.setConnectTimeout(1000 * 10);
			con.setReadTimeout(1000 * 8);
			con.setRequestProperty("Pragma", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			con.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:10.0.12) Gecko/20100101 Firefox/10.0.12");
			con.setRequestProperty("charset", "UTF-8");
			OutputStreamWriter out = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			// String
			// testme="prov_url=http%3A%2F%2F134.32.30.103%3A26441%2FXMLReceiver&xmlmsg=%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%0D%0A%3CUniBSS%3E%0D%0A%09%09%3COrigDomain%3EECIP%3C%2FOrigDomain%3E%0D%0A%09%09%3CHomeDomain%3EUCRM%3C%2FHomeDomain%3E%0D%0A%09%09%3CBIPCode%3EBIP2A001%3C%2FBIPCode%3E%0D%0A%09%09%3CBIPVer%3E0100%3C%2FBIPVer%3E%0D%0A%09%09%3CActivityCode%3ET2000101%3C%2FActivityCode%3E%0D%0A%09%09%3CActionCode%3E0%3C%2FActionCode%3E%0D%0A%09%09%3CActionRelation%3E0%3C%2FActionRelation%3E%0D%0A%09%09%3CRouting%3E%0D%0A%09%09%09%3CRouteType%3E01%3C%2FRouteType%3E%0D%0A%09%09%09%3CRouteValue%3E18654325306%3C%2FRouteValue%3E%0D%0A%09%09%3C%2FRouting%3E%0D%0A%09%09%3CProcID%3EVAS0422014030616550741997%3C%2FProcID%3E%0D%0A%09%09%3CTransIDO%3EVAS0422014030616550741997%3C%2FTransIDO%3E%0D%0A%09%09%3CTransIDH%2F%3E%0D%0A%09%09%3CProcessTime%3E20140306165507%3C%2FProcessTime%3E%0D%0A%09%09%3CSPReserve%3E%0D%0A%09%09%09%3CTransIDC%3EECIP0002VAS0422014030616550741997SEDB02%3C%2FTransIDC%3E%0D%0A%09%09%09%3CCutOffDay%3E20140306%3C%2FCutOffDay%3E%0D%0A%09%09%09%3COSNDUNS%3E0002%3C%2FOSNDUNS%3E%0D%0A%09%09%09%3CHSNDUNS%3E1700%3C%2FHSNDUNS%3E%0D%0A%09%09%09%3CConvID%3EECIPpsns0062VAS042201403061655074199720140306165509376%3C%2FConvID%3E%0D%0A%09%09%3C%2FSPReserve%3E%0D%0A%09%09%3CTestFlag%3E1%3C%2FTestFlag%3E%0D%0A%09%09%3CMsgSender%3E1700%3C%2FMsgSender%3E%0D%0A%09%09%3CMsgReceiver%3E1701%3C%2FMsgReceiver%3E%0D%0A%09%09%3CSvcContVer%3E0100%3C%2FSvcContVer%3E%0D%0A%09%09%3CSvcCont%3E%3C%21%5BCDATA%5B%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3CPackageChangeReq%3E%3CUserNumber%3E18654325306%3C%2FUserNumber%3E%3CPackageCode%3E8200000201%3C%2FPackageCode%3E%3COperCode%3E1%3C%2FOperCode%3E%3CProcTime%3E20140306165500007%3C%2FProcTime%3E%3C%2FPackageChangeReq%3E%5D%5D%3E%3C%2FSvcCont%3E%0D%0A%09%3C%2FUniBSS%3E%0D%0A";
			// out.write(testme);
			// logger.info(urlStr);
			// logger.info(URLDecoder.decode(params.toString()));
			out.write(params.toString());
			out.flush();
			out.close();
			// logger.info("Server response code:"+con.getResponseCode());
			BufferedReader br;
			if (con.getResponseCode() == 200) {
				br = new BufferedReader(new InputStreamReader(
						con.getInputStream(), "UTF-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(
						con.getErrorStream(), "UTF-8"));
			}

			for (String linetmp = br.readLine(); linetmp != null; linetmp = br
					.readLine()) {
				line = line + linetmp;
			}
			br.close();

			con.disconnect();

			logger.info(line);

		} catch (MalformedURLException e) {
			logger.info("\n提示：网络地址错误！请检查网络");
			// e.printStackTrace();
		} catch (IOException e) {
			logger.info("\n提示：网络错误！请检查网络");
			// e.printStackTrace();
		} finally {
			// /释放资源

		}

		Object allmanobj = JSONValue.parse(line);
		HashMap<String, Object> lineMap = (HashMap<String, Object>) allmanobj;
		
		/*
		HashMap<String, Object> result = (HashMap<String, Object>) lineMap.get("result");
		HashMap<String, Object> dataSet = (HashMap<String, Object>) result.get("dataSet");
		String payPageContent = dataSet.get("payPageContent").toString();
		*/

		return lineMap;
	}

}
