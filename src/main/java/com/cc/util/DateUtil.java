package com.cc.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 * @author 程胜儒
 *
 */
public class DateUtil {

	private static final Log log = LogFactory.getLog(DateUtil.class);
	/**
	 *
	 * @param date:日期
	 * @param formatString:格式
	 * @return
	 */
	public static String convertDateToString(Date date,String formatString){
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		return format.format(date);
	}
	/**
	 *
	 * @param dateString
	 * @param formatString
	 * @return
	 */
	public static Date convertStringToDate(String dateString,String formatString){
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		Date date=null;
		if(dateString!=null)
		{
			try {
				date = format.parse(dateString);
			} catch (ParseException e) {
				log.error("--------时间格式转换错误-----"+dateString);
			}
		}
		else {
			return null;
		}

		return date;
	}
	/**
	 * yyyyMMddHHmm
	 * @param date
	 * @return
	 */
	public static String convertDateToCommonString(Date date){
		return  convertDateToString(date,"yyyyMMddHHmm");
	}
	/**
	 * yyyyMMdd
	 * @param date
	 * @return
	 */
	public static String convertDateToShortString(Date date){
		return  convertDateToString(date,"yyyyMMdd");
	}

	/**
	 * yyyyMMddHHmmssSSS
	 * @param date
	 * @return
	 */
	public static String convertDateToLongString(Date date){
		return  convertDateToString(date,"yyyyMMddHHmmss");
	}

	/**
	 * yyyyMMdd
	 * @param dateString
	 * @return
	 */
	public static Date convertShortStringToDate(String dateString){
		return convertStringToDate(dateString,"yyyyMMdd");
	}
	/**
	 * yyyyMMddHHmm
	 * @param dateString
	 * @return
	 */
	public static Date convertCommonStringToDate(String dateString){
		return convertStringToDate(dateString,"yyyyMMddHHmm");
	}
	/**
	 * yyyyMMddHHmmss
	 * @param dateString
	 * @return
	 */
	public static Date convertLongStringToDate(String dateString){
		return convertStringToDate(dateString,"yyyyMMddHHmmss");
	}

	/**
	 * xietieyun
	 * 将当前日期转换成mysql对应日期格式
	 * @param date
	 * @return
	 */
	public static String convertDateToMysqlString(Date date) {
		return convertDateToString(date , "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * xietieyun
	 * 将mysql中日期字符串转化成对应的日期
	 * @param dateString
	 * @return
	 */
	public static Date convertMysqlDateStringToDate(String dateString){
		return convertStringToDate(dateString,"yyyy-MM-dd HH:mm:ss");
	}

	private static String getDateStr(long millis) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(millis));
	}

	/**
	 * 将字符串日期转换成另一种字符串格式
	 * @param dateStr
	 * @param formatStr1
	 * @param formatStr2
	 * @return
	 */
	public static String formatDateString(String dateStr, String formatStr1, String formatStr2) {
		SimpleDateFormat format1 = new SimpleDateFormat(formatStr1);
		try {
			Date date = format1.parse(dateStr);
			SimpleDateFormat format2 = new SimpleDateFormat(formatStr2);
			return format2.format(date);
		} catch (ParseException e) {
			log.error("日期字符串格式化异常，dateStr=" + dateStr);
		}
		return null;
	}

	public static void main(String[] args) throws Exception{
//        String datestr = getDateStr(System.currentTimeMillis());
		System.out.println(convertDateToString(new Date(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss"));


	}


}
