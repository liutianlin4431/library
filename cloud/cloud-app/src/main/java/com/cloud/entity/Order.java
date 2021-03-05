package com.cloud.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_order")
public class Order implements Serializable {
	private static final long serialVersionUID = -118166124622346366L;
	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField("user_id")
	private Long userId;
	@TableField("product_id")
	private Long productId;
	private Integer count;
	private BigDecimal money;
	private Integer status; // 订单状态：0：创建中；1：已完结

	public Order() {
		super();
	}

	public Order(Long id, Long userId, Long productId, Integer count, BigDecimal money, Integer status) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.count = count;
		this.money = money;
		this.status = status;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
