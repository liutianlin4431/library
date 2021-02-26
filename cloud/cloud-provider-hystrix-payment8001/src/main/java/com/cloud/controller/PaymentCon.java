package com.cloud.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ser.PaymentSer;

@RestController
public class PaymentCon {
	@Resource
	private PaymentSer paymentSer;
	@Value("${server.port}")
	private Integer serPort;

	@GetMapping("/payment/hystrix/ok/{id}")
	public String ok(@PathVariable("id") Long id) {
		return paymentSer.ok(id);
	}

	@GetMapping("/payment/hystrix/timeOut/{id}")
	public String timeOut(@PathVariable("id") Long id) {
		return paymentSer.timeOut(id);
	}

	/**
	 * 熔断测试接口
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/payment/circuit/{id}")
	public String paymentCircuitBreaker(@PathVariable("id") Long id) {
		return paymentSer.paymentCircuitBreaker(id);
	}
}
