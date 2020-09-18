package com.pack.encryption;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;

import com.pack.str.StrUtility;

/**
 * BASE64工具类
 * 
 * @author ltl
 *
 */
public class Base64Utility {
	private static Base64Utility bu = null;

	public static Base64Utility init() {
		if (bu == null) {
			synchronized (Base64Utility.class) {
				if (bu == null) {
					bu = new Base64Utility();
				}
			}
		}
		return bu;
	}

	private Base64Utility() {

	}

	/**
	 * BASE64加密字符串(UTF-8)
	 * 
	 * @param str 字符串
	 * @return
	 */
	public String encode(String str) {
		return encode(str, CharEncoding.UTF_8);
	}

	/**
	 * BASE64加密字符串
	 * 
	 * @param str    字符串
	 * @param coding 编码(When the encoding value is Null, the default is UTF-8)
	 * @return
	 */
	public String encode(String str, String coding) {
		try {
			if (StrUtility.init().StrIsNull(str)) {
				return null;
			}
			if (StrUtility.init().StrIsNull(coding)) {
				return encode(str);
			}
			return new String(Base64.encodeBase64(str.getBytes(coding)), coding);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * BASE64解密字符串(UTF-8)
	 * 
	 * @param str 字符串
	 * @return
	 */
	public String decode(String str) {
		return decode(str, CharEncoding.UTF_8);
	}

	/**
	 * BASE64解密字符串
	 * 
	 * @param str    字符串
	 * @param coding 编码(When the encoding value is Null, the default is UTF-8)
	 * @return
	 */
	public String decode(String str, String coding) {
		try {
			if (StrUtility.init().StrIsNull(str)) {
				return null;
			}
			if (StrUtility.init().StrIsNull(coding)) {
				return decode(str);
			}
			return new String(Base64.decodeBase64(str.getBytes(coding)), coding);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

}
