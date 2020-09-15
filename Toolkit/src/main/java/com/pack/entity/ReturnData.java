package com.pack.entity;

public class ReturnData {
	private Object code;
	private Object msg;
	private Boolean success;
	private Object data;

	/**
	 * 
	 * @param code    错误编号
	 * @param msg     消息
	 * @param success 是否成功
	 * @param data    返回值
	 */
	public ReturnData(Object code, Object msg, Boolean success, Object data) {
		super();
		this.code = code;
		this.msg = msg;

		this.success = success;
		this.data = data;
	}

	/**
	 * 
	 * @param code    错误编号
	 * @param msg     消息
	 * @param success 是否成功
	 * @param data    返回值
	 */
	public static ReturnData create(Object code, Object msg, Boolean success, Object data) {
		return new ReturnData(code, msg, success, data);
	}

	public Object getCode() {
		return code;
	}

	public void setCode(Object code) {
		this.code = code;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
