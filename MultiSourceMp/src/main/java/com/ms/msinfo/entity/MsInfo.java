package com.ms.msinfo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("msinfo")
public class MsInfo implements Serializable {
	private static final long serialVersionUID = -6499969965445255865L;
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String info;

	public Integer getId() {
		return id;
	}

	public String getInfo() {
		return info;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public MsInfo() {
		super();
	}

	public MsInfo(Integer id, String info) {
		super();
		this.id = id;
		this.info = info;
	}

	@Override
	public String toString() {
		return "MsInfo [id=" + id + ", info=" + info + ", getId()=" + getId() + ", getInfo()=" + getInfo()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
