package com.ms.ds;

public class DataSourceContextHolder {
	private static final ThreadLocal<String> contextHolder = new InheritableThreadLocal<>();

	/**
	 * ��������Դ
	 * 
	 * @param db
	 */
	public static void setDataSource(String db) {
		contextHolder.set(db);
	}

	/**
	 * ȡ�õ�ǰ����Դ
	 * 
	 * @return
	 */
	public static String getDataSource() {
		return contextHolder.get();
	}

	/**
	 * �������������
	 */
	public static void clear() {
		contextHolder.remove();
	}
}
