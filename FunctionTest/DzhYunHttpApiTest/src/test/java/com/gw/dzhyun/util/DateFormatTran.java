package com.gw.dzhyun.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/*
 * 一、java中Date类中的getTime()是获取时间戳的，java中生成的时间戳精确到毫秒级别，而unix中精确到秒级别，所以通过java生成的时间戳需要除以1000。
 */
public class DateFormatTran {
	/*
	 * 时间戳转化为时间格式yyyy-MM-dd的Sting ，并返回该字符串
	 */
	public static String liunxDateTranDateStr(long lxtime)
	{
		String datefor = "";
		//SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");  
	    Long time=Long.valueOf(lxtime*1000);
	    datefor = format.format(time);  
	    //Date date=format.parse(d);  
	    System.out.println("Format To String(Date):"+datefor);  
	    //System.out.println("Format To Date:"+date);  
		return datefor;
	}
	/*
	 * 将Date（yyyy-MM-dd）格式的String转化为时间戳
	 */
	public static long DateTranLiunxTime(String lxtime) throws ParseException
	{
		 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");  
		 String time="1970-01-06";  
		 Date date = format.parse(lxtime);
		 System.out.println("Format To times:"+date.getTime());
		 return date.getTime();
	}
	
	/*
	 * 将Date（yyyy-MM-dd）格式的String转化为时间类型
	 */
	public static Date StrTranDate(String lxtime) throws ParseException
	{
		 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");  
		 Date date = format.parse(lxtime);
		 System.out.println("Format To times:"+date.getTime());
		 return date;
	}
	
    /*
     *    将Date（yyyy-MM-dd）格式的String增加指定时间，再将增加的时间已String返回
     *    dateUnit取值：
     *       Calendar.DAY_OF_MONTH  单位天
     *       Calendar.MONTH         单位月
     *       Calendar.YEAR          单位年
     *    addNum取值：
     *       整数，如果addNum=3，dateUnit=Calendar.MONTH，那即为3个月
     */
	public static String strDateAddOutStr(String lxtime,int dateUnit,int addNum) throws ParseException
	{
		 Calendar c= Calendar.getInstance();
		 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		 Date date = format.parse(lxtime);
		 c.setTime(date);
		 c.add(dateUnit, addNum);
		 date = c.getTime();
		 return format.format(date);
	}
	
	
	/*
     *    将Date（yyyy-MM-dd）格式的String增加指定时间，再将增加的时间以时间戳返回
     *    dateUnit取值：
     *       Calendar.DAY_OF_MONTH  单位天
     *       Calendar.MONTH         单位月
     *       Calendar.YEAR          单位年
     *    addNum取值：
     *       整数，如果addNum=3，dateUnit=Calendar.MONTH，那即为3个月
     */
	public static long strDateAddOutDateLong(String lxtime,int dateUnit,int addNum) throws ParseException
	{
		 Calendar c= Calendar.getInstance();
		 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		 Date date = format.parse(lxtime);
		 c.setTime(date);
		 c.add(dateUnit, addNum);
		 date = c.getTime();
		 return date.getTime();
	}
	
	
	 /*
     *    将时间戳增加指定时间，再将增加的时间已String返回
     *    dateUnit取值：
     *       Calendar.DAY_OF_MONTH  单位天
     *       Calendar.MONTH         单位月
     *       Calendar.YEAR          单位年
     *    addNum取值：
     *       整数，如果addNum=3，dateUnit=Calendar.MONTH，那即为3个月
     */
//	public static String strDateAddOutStr(long lxtime,int dateUnit,int addNum) throws ParseException
//	{
//		 Calendar c= Calendar.getInstance();
//		 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
//		 Date date = format.format(Long.valueOf(lxtime));
//		 c.setTime(date);
//		 c.add(dateUnit, addNum);
//		 date = c.getTime();
//		 return format.format(date);
//	}
	public static void main(String[] args) throws ParseException 
	{
		// TODO Auto-generated method stub
		System.out.println(DateFormatTran.liunxDateTranDateStr(1442851200));
		System.out.println(DateFormatTran.DateTranLiunxTime("2015-09-22"));
		System.out.println(DateFormatTran.strDateAddOutStr("2015-09-22", Calendar.DAY_OF_MONTH, 8));
		System.out.println(DateFormatTran.strDateAddOutStr("2015-09-22", Calendar.MONTH, 8));
		

	}

}
