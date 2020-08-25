package com.pack.entity;

public class ReturnData {
	private Object code;
	private Object msg;
	private Object success;
	private Object data;

	/**
	 * 
	 * @param code    错误编号
	 * @param msg     消息
	 * @param success 0：失败；1：成功；(旧请求存在【0：成功；1：失败；】的情况)
	 * @param data    返回值
	 */
	public ReturnData(Object code, Object msg, Object success, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.success = success;
		this.data = data;
	}

	/**
	 * 
	 * @param code
	 * @param msg
	 * @param success 0：失败；1：成功；(旧请求存在【0：成功；1：失败；】的情况)
	 * @param data
	 */
	public static ReturnData create(Object code, Object msg, Object success, Object data) {
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

	public Object getSuccess() {
		return success;
	}

	public void setSuccess(Object success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
