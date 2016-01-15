package org.leafframework.mvc.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 将HashMap对象转化为Imap对象，以方便map取值，同时可设置默认值
 * 
 * @author zhaidw
 */
public class IMap extends HashMap {

	private HashMap inmap;
	
	/**
	 * 请使用一个HashMap作为构造函数入参
	 * 
	 * @param inmap
	 */
	public IMap(HashMap inmap) {
		this.inmap = inmap;
	}

	public Object get(Object key) {
		return inmap.get(key);
	}
	
	public String getString(Object key) {
		Object o = get(key);
		return o == null ? null : o.toString();
	}

	public String getString(String name, String defaultValue) {
		String value = getString(name);
		return value == null ? defaultValue : value;
	}
	
	/**
	 * 得到现在的时间
	 * @return
	 */
	public Date getNow(){
		if(!inmap.containsKey("now")){
			Date now=Calendar.getInstance().getTime();
			inmap.put("now", now);
			return now;
		}else{
			return (Date) inmap.get("now");
		}
	}
	
	/**
	 * get names
	 * 
	 * @return String[]
	 */
	public String[] getNames() {
		String[] names = (String[]) keySet().toArray(new String[0]);
		Arrays.sort(names);
		return names;
	}

	/**
	 * get int
	 * 
	 * @param name
	 * @return int
	 */
	public int getInt(String name) {
		return getInt(name, 0);
	}

	/**
	 * get int
	 * 
	 * @param name
	 * @param defaultValue
	 * @return int
	 */
	public int getInt(String name, int defaultValue) {
		String value = getString(name, "");
		return "".equals(value) ? defaultValue : Integer.parseInt(value);
	}

	/**
	 * get BigDecimal
	 * 
	 * @param name
	 * @return BigDecimal
	 */
	public BigDecimal getDecimal(String name) {
		return getDecimal(name, 0);
	}

	/**
	 * get BigDecimal
	 * 
	 * @param name
	 * @param defaultValue
	 * @return BigDecimal
	 */
	public BigDecimal getDecimal(String name, int defaultValue) {
		String value = getString(name, "");
		return "".equals(value) ? new BigDecimal(0) : new BigDecimal(value);
	}

	/**
	 * get long
	 * 
	 * @param name
	 * @return long
	 */
	public long getLong(String name) {
		return getLong(name, 0l);
	}

	/**
	 * get long
	 * 
	 * @param name
	 * @param defaultValue
	 * @return long
	 */
	public long getLong(String name, long defaultValue) {
		String value = getString(name, "");
		return "".equals(value) ? defaultValue : Long.parseLong(value);
	}

	/**
	 * get double
	 * 
	 * @param name
	 * @return double
	 */
	public double getDouble(String name) {
		return getDouble(name, 0);
	}

	/**
	 * get double
	 * 
	 * @param name
	 * @param defaultValue
	 * @return double
	 */
	public double getDouble(String name, double defaultValue) {
		String value = getString(name, "");
		return "".equals(value) ? defaultValue : Double.parseDouble(value);
	}

	/**
	 * get boolean
	 * 
	 * @param name
	 * @return boolean
	 */
	public boolean getBoolean(String name) {
		return getBoolean(name, false);
	}

	/**
	 * get boolean
	 * 
	 * @param name
	 * @param defaultValue
	 * @return boolean
	 */
	public boolean getBoolean(String name, boolean defaultValue) {
		String value = getString(name, "");
		return "".equals(value) ? defaultValue : Boolean.valueOf(value)
				.booleanValue();
	}
	
	/**
	 * get getDate0000
	 * 将一个yyyy-MM-dd格式的日期,转化为yyyy-MM-dd HH:mm:ss格式的日期(当天开始)
	 * @param name
	 * @return java.util.Date
	 */
	public Date getDate000000(String value){
//		String value = getString(name, "").trim();
		if(value.length()!=10){
			return null;
		}else{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(value+" 00:00:00");
			} catch (ParseException e) {
				return null;
			}
		}
	}
	
	/**
	 * get getDate235959
	 * 将一个yyyy-MM-dd格式的日期,转化为yyyy-MM-dd 23:59:59格式的日期(当天最后)
	 * @param name
	 * @return java.util.Date
	 */
	public Date getDate235959(String value){
//		String value = getString(name, "").trim();
		if(value.length()!=10){
			return null;
		}else{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(value+" 23:59:59");
			} catch (ParseException e) {
				return null;
			}
		}
	}
	
	/**
	 * get getDate now
	 * 将一个yyyy-MM-dd格式的日期,转化为yyyy-MM-dd + 当前时间 格式的日期
	 * @param name
	 * @return java.util.Date
	 */
	public Date getDateNow(String value){
		if(value.length()!=10){
			return null;
		}else{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date now = getNow();
				String[] nowTime = (now+"").split(" ");
				return sdf.parse(value+" "+nowTime[3]);
			} catch (ParseException e) {
				return null;
			}
		}
	}

	public Object put(Object key, Object value) {
		return inmap.put(key, value);
	}

	public Object remove(Object key) {
		return inmap.remove(key);
	}

	public void clear() {
		inmap.clear();
	}

	public boolean containsKey(Object key) {
		return inmap.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return inmap.containsValue(value);
	}

	public Set entrySet() {
		return inmap.entrySet();
	}

	public boolean isEmpty() {
		return inmap.isEmpty();
	}

	public Set keySet() {
		return inmap.keySet();
	}

	public void putAll(Map t) {
		inmap.putAll(t);
	}

	public int size() {
		return inmap.size();
	}

	public Collection values() {
		return inmap.values();
	}

	public void initPageParam(HashMap<String, Object> pageUri) {
		int page;
		if (pageUri.get("page") == null || pageUri.get("page") == "") {
			page = 1;
		} else {
			page = Integer.parseInt((String) pageUri.get("page"));
		}
		int size;
		if (pageUri.get("size") == null || pageUri.get("size") == "") {
			size = 10;
		} else {
			size = Integer.parseInt((String) pageUri.get("size"));// 每页显示的数量
		}
		if(inmap.containsKey("rows")){
			size=getInt("rows");
		}
		inmap.put("page", page);
		inmap.put("begin", size * (page - 1));
		inmap.put("size", size);
	}
	
	
}
