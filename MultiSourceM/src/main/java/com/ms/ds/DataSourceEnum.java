package com.ms.ds;

public enum DataSourceEnum {

	FIRST("first"), SECOND("second");

	private String value;

	DataSourceEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}