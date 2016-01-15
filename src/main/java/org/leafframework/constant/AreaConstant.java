package org.leafframework.constant;

import java.util.HashMap;

/**
 * 获取地市代码、名称
 * 
 * @author vfly_xbd
 */
public final class AreaConstant {

	public static HashMap<String, Object> getAreaNameByCode() {

		HashMap<String, Object> aremMap = new HashMap<String, Object>();

		aremMap.put("001", "淄博");
		aremMap.put("002", "滨州");
		aremMap.put("003", "临沂");
		aremMap.put("004", "日照");
		aremMap.put("005", "潍坊");
		aremMap.put("006", "东营");
		aremMap.put("007", "枣庄");
		aremMap.put("008", "济宁");
		aremMap.put("009", "菏泽");
		aremMap.put("010", "莱芜");
		aremMap.put("011", "烟台");
		aremMap.put("012", "青岛");
		aremMap.put("013", "济南");
		aremMap.put("014", "泰安");
		aremMap.put("015", "德州");
		aremMap.put("016", "聊城");
		aremMap.put("017", "威海");
		aremMap.put("000", "山东");

		return aremMap;
	}
	
	/**
	 * 根据staff的areaCode返回对应的地市,支持单个areaCode,也支持012,013多个地市
	 * @return
	 * @author zhaidw
	 */
	public static HashMap<String, Object> getAreaNameByCode(String staffAreaCode) {
		HashMap<String, Object> all=getAreaNameByCode();
		if(staffAreaCode!=null&&staffAreaCode.equals("000")){
			return all;
		}else{
			HashMap<String, Object> cityMap=new HashMap<String, Object>();
			if(all.containsKey(staffAreaCode)){
				cityMap.put(staffAreaCode, all.get(staffAreaCode));
			}
			return cityMap;
		}
	}

	public static HashMap<String, Object> getAreaCodeByName() {

		HashMap<String, Object> aremMap = new HashMap<String, Object>();

		aremMap.put("淄博", "001");
		aremMap.put("滨州", "002");
		aremMap.put("临沂", "003");
		aremMap.put("日照", "004");
		aremMap.put("潍坊", "005");
		aremMap.put("东营", "006");
		aremMap.put("枣庄", "007");
		aremMap.put("济宁", "008");
		aremMap.put("菏泽", "009");
		aremMap.put("莱芜", "010");
		aremMap.put("烟台", "011");
		aremMap.put("青岛", "012");
		aremMap.put("济南", "013");
		aremMap.put("泰安", "014");
		aremMap.put("德州", "015");
		aremMap.put("聊城", "016");
		aremMap.put("威海", "017");
		aremMap.put("山东", "000"); 
		
		aremMap.put("淄博市", "001");
		aremMap.put("滨州市", "002");
		aremMap.put("临沂市", "003");
		aremMap.put("日照市", "004");
		aremMap.put("潍坊市", "005");
		aremMap.put("东营市", "006");
		aremMap.put("枣庄市", "007");
		aremMap.put("济宁市", "008");
		aremMap.put("菏泽市", "009");
		aremMap.put("莱芜市", "010");
		aremMap.put("烟台市", "011");
		aremMap.put("青岛市", "012");
		aremMap.put("济南市", "013");
		aremMap.put("泰安市", "014");
		aremMap.put("德州市", "015");
		aremMap.put("聊城市", "016");
		aremMap.put("威海市", "017");
		aremMap.put("山东省", "000");

		return aremMap;
	}
	
	public static HashMap<String, Object> getBSSAreaCodeByAreaCode() {

		HashMap<String, Object> aremMap = new HashMap<String, Object>();

		aremMap.put("001", "150");    
		aremMap.put("002", "151");
		aremMap.put("003", "153");
		aremMap.put("004", "154");
		aremMap.put("005", "155");
		aremMap.put("006", "156");
		aremMap.put("007", "157");
		aremMap.put("008", "158");
		aremMap.put("009", "159");
		aremMap.put("010", "160");
		aremMap.put("011", "161");
		aremMap.put("012", "166");
		aremMap.put("013", "170");
		aremMap.put("014", "172");
		aremMap.put("015", "173");
		aremMap.put("016", "174");
		aremMap.put("017", "152");
		aremMap.put("000", "000"); 
		
		return aremMap;
	}

	public static HashMap<String, Object> getZoneCodeByAreaName() {

		HashMap<String, Object> aremMap = new HashMap<String, Object>();

		aremMap.put("淄博", "0533");    
		aremMap.put("滨州", "0543");
		aremMap.put("临沂", "0539");
		aremMap.put("日照", "0633");
		aremMap.put("潍坊", "0536");
		aremMap.put("东营", "0546");
		aremMap.put("枣庄", "0632");
		aremMap.put("济宁", "0537");
		aremMap.put("菏泽", "0530");
		aremMap.put("莱芜", "0634");
		aremMap.put("烟台", "0535");
		aremMap.put("青岛", "0532");
		aremMap.put("济南", "0531");
		aremMap.put("泰安", "0538");
		aremMap.put("德州", "0534");
		aremMap.put("聊城", "0635");
		aremMap.put("威海", "0631");
		aremMap.put("山东", "0000"); 
		
		aremMap.put("淄博市", "0533");    
		aremMap.put("滨州市", "0543");
		aremMap.put("临沂市", "0539");
		aremMap.put("日照市", "0633");
		aremMap.put("潍坊市", "0536");
		aremMap.put("东营市", "0546");
		aremMap.put("枣庄市", "0632");
		aremMap.put("济宁市", "0537");
		aremMap.put("菏泽市", "0530");
		aremMap.put("莱芜市", "0634");
		aremMap.put("烟台市", "0535");
		aremMap.put("青岛市", "0532");
		aremMap.put("济南市", "0531");
		aremMap.put("泰安市", "0538");
		aremMap.put("德州市", "0534");
		aremMap.put("聊城市", "0635");
		aremMap.put("威海市", "0631");
		aremMap.put("山东省", "0000"); 

		
		return aremMap;
	}
	
	
	
	public static HashMap<String, Object> getAreaNameBySelfCode() {

		HashMap<String, Object> aremMap = new HashMap<String, Object>();

		aremMap.put("170", "济南");
		aremMap.put("171", "青岛");
		aremMap.put("172", "淄博");
		aremMap.put("173", "枣庄");
		aremMap.put("174", "东营");
		aremMap.put("175", "烟台");
		aremMap.put("176", "潍坊");
		aremMap.put("177", "济宁");
		aremMap.put("178", "泰安");
		aremMap.put("179", "威海");
		aremMap.put("180", "日照");
		aremMap.put("181", "莱芜");
		aremMap.put("182", "临沂");
		aremMap.put("183", "德州");
		aremMap.put("184", "聊城");
		aremMap.put("185", "滨州");
		aremMap.put("186", "菏泽");
		aremMap.put("000", "山东");
		
		return aremMap;
	}
	
	public static HashMap<String, Object> getSelfCodeByAreaName() {

		HashMap<String, Object> aremMap = new HashMap<String, Object>();

		aremMap.put("济南", "170");
		aremMap.put("青岛", "171");
		aremMap.put("淄博", "172");
		aremMap.put("枣庄", "173");
		aremMap.put("东营", "174");
		aremMap.put("烟台", "175");
		aremMap.put("潍坊", "176");
		aremMap.put("济宁", "177");
		aremMap.put("泰安", "178");
		aremMap.put("威海", "179");
		aremMap.put("日照", "180");
		aremMap.put("莱芜", "181");
		aremMap.put("临沂", "182");
		aremMap.put("德州", "183");
		aremMap.put("聊城", "184");
		aremMap.put("滨州", "185");
		aremMap.put("菏泽", "186");
		aremMap.put("山东", "000");
		
		return aremMap;
	}
	
	public static HashMap<String, Object> getZoneCodeByAreaCode() {

		HashMap<String, Object> aremMap = new HashMap<String, Object>();

		aremMap.put("001", "0533");    
		aremMap.put("002", "0543");
		aremMap.put("003", "0539");
		aremMap.put("004", "0633");
		aremMap.put("005", "0536");
		aremMap.put("006", "0546");
		aremMap.put("007", "0632");
		aremMap.put("008", "0537");
		aremMap.put("009", "0530");
		aremMap.put("010", "0634");
		aremMap.put("011", "0535");
		aremMap.put("012", "0532");
		aremMap.put("013", "0531");
		aremMap.put("014", "0538");
		aremMap.put("015", "0534");
		aremMap.put("016", "0635");
		aremMap.put("017", "0631");
		aremMap.put("000", "0000");
		
		return aremMap;
	}
	
	public static HashMap<String, Object> getAreaNameByZoneCode() {

		HashMap<String, Object> aremMap = new HashMap<String, Object>();

		aremMap.put("0533", "淄博");
		aremMap.put("0543", "滨州");
		aremMap.put("0539", "临沂");
		aremMap.put("0633", "日照");
		aremMap.put("0536", "潍坊");
		aremMap.put("0546", "东营");
		aremMap.put("0632", "枣庄");
		aremMap.put("0537", "济宁");
		aremMap.put("0530", "菏泽");
		aremMap.put("0634", "莱芜");
		aremMap.put("0535", "烟台");
		aremMap.put("0532", "青岛");
		aremMap.put("0531", "济南");
		aremMap.put("0538", "泰安");
		aremMap.put("0534", "德州");
		aremMap.put("0635", "聊城");
		aremMap.put("0631", "威海");
		aremMap.put("0000", "山东");

		return aremMap;
	}
	
	/**
	 * 根据staff的zoneCode返回对应的地市
	 * @param staffZoneCode
	 * @return
	 */
	public static HashMap<String, Object> getAreaNameByZoneCode(String staffZoneCode) {
		HashMap<String, Object> all=getAreaNameByZoneCode();
		if(staffZoneCode != null && staffZoneCode.equals("0000")){
			return all;
		}else{
			HashMap<String, Object> cityMap=new HashMap<String, Object>();
			if(all.containsKey(staffZoneCode)){
				cityMap.put(staffZoneCode, all.get(staffZoneCode));
			}
			return cityMap;
		}
	}
}
