package com.cloud.ser;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cloud.entity.CommonResult;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {
	@GetMapping(value = "/paymentSQL/{id}")
	public CommonResult paymentSQL(@PathVariable("id") Long id);
}
