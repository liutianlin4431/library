package com.cloud.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_account")
public class Account implements Serializable {

	private static final long serialVersionUID = 861741075220631486L;

	private Long id;

	/**
	 * 用户id
	 */
	@TableField("user_id")
	private Long userId;

	/**
	 * 总额度
	 */
	private BigDecimal total;

	/**
	 * 已用额度
	 */
	private BigDecimal used;

	/**
	 * 剩余额度
	 */
	private BigDecimal residue;

	public Account() {
		super();
	}

	public Account(Long id, Long userId, BigDecimal total, BigDecimal used, BigDecimal residue) {
		super();
		this.id = id;
		this.userId = userId;
		this.total = total;
		this.used = used;
		this.residue = residue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getUsed() {
		return used;
	}

	public void setUsed(BigDecimal used) {
		this.used = used;
	}

	public BigDecimal getResidue() {
		return residue;
	}

	public void setResidue(BigDecimal residue) {
		this.residue = residue;
	}
}
