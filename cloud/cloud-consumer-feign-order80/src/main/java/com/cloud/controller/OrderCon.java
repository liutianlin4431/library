package com.cloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.entity.CommonResult;
import com.cloud.ser.PaymentFeignSer;

@RestController
public class OrderCon {
	@Resource
	private PaymentFeignSer pfs;

	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult get(@PathVariable("id") Long id) {
		return pfs.getPaymentById(id);
	}

	/**
	 * 验证openfeign接口超时方法
	 * 
	 * @return
	 */
	@GetMapping("/consumer/payment/timeOut")
	public String getTimeOut() {
		return pfs.getTimeOut();
	}
}
