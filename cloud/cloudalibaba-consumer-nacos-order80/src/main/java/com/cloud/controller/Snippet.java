package com.cloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.entity.CommonResult;
import com.cloud.ser.PaymentService;

@RestController
public class Snippet {
	@Resource
	private PaymentService paymentService;

	@GetMapping(value = "/consumer/paymentSQL/{id}")
	public CommonResult paymentSQL(@PathVariable("id") Long id) {
		return paymentService.paymentSQL(id);
	}

}
