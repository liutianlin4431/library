package com.cloud.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.entity.CommonResult;
import com.cloud.entity.Payment;
import com.cloud.ser.PaymentSer;

@RestController
public class PaymentCon {
	@Resource
	private PaymentSer paymentSer;
	@Value("${server.port}")
	private Integer serPort;

	@PostMapping("/payment/create")
	public CommonResult create(Payment payment) {
		if (paymentSer.save(payment)) {
			return new CommonResult(200, "成功");
		}
		return new CommonResult(444, "失败");
	}

	@GetMapping("/payment/get/{id}")
	public CommonResult getPaymentById(@PathVariable("id") Long id) {
		return new CommonResult(200, "成功-" + serPort, paymentSer.getById(id));
	}

	@GetMapping("/payment/lb")
	public String getLb() {
		return serPort.toString();
	}
}
