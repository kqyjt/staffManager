package org.leafframework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {
	public static String jdateformat = "yyyy-MM-dd HH:mm:ss";

	protected static SimpleDateFormat sdf = new SimpleDateFormat(
			DateUtil.jdateformat);

	public static Date parse(String date) {
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date parse(String date,String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String format(Date date) {
		return sdf.format(date);
	}
	
	/**
	 * 将日期按指定格式格式化
	 * @param date
	 * @param pattern
	 * @return
	 * @date 2015年6月10日
	 * @author zhangyy
	 *
	 */
	public static String format(Date date, String pattern) {
		String dateStr;
		try {
			dateStr = new SimpleDateFormat(pattern).format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dateStr = sdf.format(date);
		}
		return dateStr;
	}
	
	/**
	 * 获取指定天数后的日期
	 * @param afterNum
	 * @return
	 * @date 2015年5月26日
	 * @author zhangyy
	 *
	 */
	public static Date getDayOfAfterNum(int afterNum){
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, afterNum);
		Date day = ca.getTime();
		return day;
	}
	
	/**
	 * 获取某个时间的指定天数后的日期
	 * @param afterNum
	 * @param dateStr
	 * @return
	 * @date 2015年6月5日
	 * @author zhangyy
	 *
	 */
	public static Date getDayOfAfterNum(int afterNum,String dateStr){
		Calendar ca = Calendar.getInstance();
		Date date = DateUtil.parse(dateStr);
		ca.setTime(date);
		ca.add(Calendar.DAY_OF_MONTH, afterNum);
		Date day = ca.getTime();
		return day;
	}
	
	//获取本月的第一天
	public static Date getFirstDayOfMonth(){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH,1); 
		Date firstDay = parse(sdf.format(ca.getTime()));
		return firstDay;
	}
	
	//获取当前年
	public static int getYear(){
		Calendar cal = Calendar.getInstance(); 
		int year = cal.get(Calendar.YEAR); 
		return year;
	}
	
	//获取当前月
	public static int getMonth(){
		Calendar cal = Calendar.getInstance(); 
		int month = cal.get(Calendar.MONTH)+1;
		return month;
	}
	
	//获取两个日期相差的月份数
	public  static int betwMonth(Date end ,Date begin){
		int result = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2  = Calendar.getInstance();
		c1.setTime(end);
		c2.setTime(begin);
		result =  (c1.get(Calendar.YEAR)-c2.get(Calendar.YEAR))*12+ c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
		return result;
	}
	
	public static Date SysExpireDate = DateUtil.parse("2099-12-31 23:59:59");
	
	//获取两个日期相差的天数
	public  static int betwDay(String end ,String begin){
		int result = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    long to;
		try {
			to = df.parse(end).getTime();
			 long from = df.parse(begin).getTime();
			 //System.out.println((to - from) / (1000 * 60 * 60 * 24));
			 result=(int) ((to - from) / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
	   
		return result;
	}
	
	/**
	 * 获取当前月最后一天
	 * @return
	 * @date 2015年11月17日
	 * @author zhangyy
	 *
	 */
	public static Date getLastDayOfMonth() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}
	
	/**
	 * 比较两个日期相差天数
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 * @date 2015年11月17日
	 * @author zhangyy
	 *
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}    
	
	public static void main(String[] args) throws ParseException {
		//System.out.println(DateUtil.betwMonth(DateUtil.parse("20151231235959", "yyyyMMddHHmmss"),DateUtil.parse("20150731180000", "yyyyMMddHHmmss")));
	   
		System.out.println(DateUtil.daysBetween(new Date(),DateUtil.getLastDayOfMonth()));
	}
	
}