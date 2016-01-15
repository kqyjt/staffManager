package org.leafframework.data.dao.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyBatisUtil {
	
	/**
	 * 将查询结果集Map 中key值 统一转化为大写  qiuyg
	 * 
	 * @param list 结果集数据
	 * @return
	 */
	public static List<Map<String, String>> convertListMapToUpper(List<Map<String, String>> list) {
		for (Map<String, String> m : list) {
			Set<String> set = new HashSet<String>();
			set.addAll(m.keySet());
			for (String key : set) {
				Object o = m.get(key);
				//避免重复数据，增加大B/S交互及内存占用，将原键值移除
				m.remove(key);
				m.put(key.toUpperCase(), String.valueOf(o));
			}
		}
		return list;
	}
	
	/**
	 * 将查询结果集Map 中key值 统一转化为大写  qiuyg
	 * 
	 * @param map 结果集数据
	 * @return
	 */
	public static Map<String, String> convertMapToUpper(Map<String, String> map) {
		Set<String> set = new HashSet<String>();
		set.addAll(map.keySet());
		for (String key : set) {
			Object o = map.get(key);
			//避免重复数据，增加大B/S交互及内存占用，将原键值移除
			map.remove(key);
			map.put(key.toUpperCase(), String.valueOf(o));
		}
		return map;
	}
	
	public static List<Map<String, String>> convertListMapToString(List<Map<String, String>> list) {
		for (Map<String, String> m : list) {
			Set<String> set = new HashSet<String>();
			set.addAll(m.keySet());
			for (String key : set) {
				Object o = m.get(key);
				//避免重复数据，增加大B/S交互及内存占用，将原键值移除
				m.remove(key);
				m.put(key, String.valueOf(o));
			}
		}
		return list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
