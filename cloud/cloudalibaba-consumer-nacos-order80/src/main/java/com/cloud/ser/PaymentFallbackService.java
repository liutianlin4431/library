package com.cloud.ser;

import org.springframework.stereotype.Component;

import com.cloud.entity.CommonResult;
import com.cloud.entity.Payment;

@Component
public class PaymentFallbackService implements PaymentService {
	@Override
	public CommonResult paymentSQL(Long id) {
		return new CommonResult(44444, "服务降级返回,---PaymentFallbackService", new Payment(id, "errorSerial"));
	}
}
