package com.pack.num;

import java.math.BigDecimal;

/**
 * 浮点数（Double）工具类
 * 
 * @author ltl
 *
 */
public class DoubleUtility {

	/**
	 * 将值转成double类型
	 * 
	 * @param object 数值
	 * @return
	 */
	public static Double parseDouble(Object object) {
		try {
			return Double.parseDouble(object.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 保留小数点位数
	 * 
	 * @param object        数值
	 * @param decimalLength 小数点长度
	 * @return
	 */
	public static Double operationDouble(Object object, int decimalLength) {
		Double d = null;
		if (!(object instanceof Double)) {
			d = parseDouble(object);
		} else {
			d = (Double) object;
		}
		if (d != null) {
			BigDecimal bigDecimal = new BigDecimal(d);
			return bigDecimal.setScale(decimalLength, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return 0.0;
	}
}
