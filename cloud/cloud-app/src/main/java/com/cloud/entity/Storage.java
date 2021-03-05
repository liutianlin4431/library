package com.cloud.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_storage")
public class Storage implements Serializable {
	private static final long serialVersionUID = 6701245195433574897L;
	@TableId(type = IdType.AUTO)
	private Long id;

	// 产品id
	@TableField("product_id")
	private Long productId;

	// 总库存
	private Integer total;

	// 已用库存
	private Integer used;

	// 剩余库存
	private Integer residue;

	public Storage() {
		super();
	}

	public Storage(Long id, Long productId, Integer total, Integer used, Integer residue) {
		super();
		this.id = id;
		this.productId = productId;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Integer getResidue() {
		return residue;
	}

	public void setResidue(Integer residue) {
		this.residue = residue;
	}
}
