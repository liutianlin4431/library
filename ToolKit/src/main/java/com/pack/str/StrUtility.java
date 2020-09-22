package com.pack.str;

import java.util.Random;

/**
 * 字符串工具类
 * 
 * @author ltl
 *
 */
public class StrUtility {
	private static StrUtility su = null;

	public static StrUtility init() {
		if (su == null) {
			synchronized (StrUtility.class) {
				if (su == null) {
					su = new StrUtility();
				}
			}
		}
		return su;
	}

	private StrUtility() {
	}

	/**
	 * 生成数字与字母随机组合字符串
	 * 
	 * @param length 长度
	 * @return
	 */
	public String randomCombinations(int length) {
		String val = "";
		Random random = new Random();
		// length为几位密码
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 判断str字符串中是否包含contains(忽略大小写)
	 * 
	 * @param str      字符串
	 * @param contains 包含字符串
	 * @return 包含返回true
	 */
	public Boolean Contains(String str, String contains) {
		// 将参数转换为小写
		str = str.toLowerCase();
		contains = contains.toLowerCase();
		return str.contains(contains);
	}

	/**
	 * 判断str字符串“开头”是否包含contains(忽略大小写)
	 * 
	 * @param str      字符串
	 * @param contains 开头字符串
	 * @return
	 */
	public Boolean startsWith(String str, String contains) {
		str = str.toLowerCase();
		contains = contains.toLowerCase();
		return str.startsWith(contains);
	}

	/**
	 * 判断字符串是否为空，"null" NULL ""皆为空
	 * 
	 * @param str 字符串
	 * @return 空返回“true”；非空返回“false”；
	 */
	public Boolean StrIsNull(String str) {
		if (str == null || str.equals("") || str.equals("null")) {
			return true;
		}
		return false;
	}

}
