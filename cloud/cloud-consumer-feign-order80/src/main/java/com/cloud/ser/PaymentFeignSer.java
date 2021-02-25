package com.cloud.ser;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cloud.entity.CommonResult;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE") // Eureka中服务的名称
public interface PaymentFeignSer {
	@GetMapping("/payment/get/{id}")
	public CommonResult getPaymentById(@PathVariable("id") Long id);

	@GetMapping("/payment/timeOut")
	public String getTimeOut();
}
