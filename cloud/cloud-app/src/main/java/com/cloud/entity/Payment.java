package com.cloud.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("payment")
public class Payment implements Serializable {
	private static final long serialVersionUID = 8586847305936289302L;
	@TableId(type = IdType.AUTO)
	private Long id;
	private String serial;

	public Payment() {
		super();
	}

	public Payment(Long id, String serial) {
		super();
		this.id = id;
		this.serial = serial;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
}
