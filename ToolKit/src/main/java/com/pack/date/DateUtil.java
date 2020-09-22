package com.pack.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author ltl
 *
 */
public class DateUtil {
	private static DateUtil du = null;

	public static DateUtil init() {
		if (du == null) {
			synchronized (DateUtil.class) {
				if (du == null) {
					du = new DateUtil();
				}
			}
		}
		return du;
	}

	private DateUtil() {
	}

	/**
	 * 字符串转时间
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public Date StrToDate(String date, String format) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 时间转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public String DateToStr(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
}
