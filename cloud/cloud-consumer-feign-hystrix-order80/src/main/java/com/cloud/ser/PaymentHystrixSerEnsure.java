package com.cloud.ser;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixSerEnsure implements PaymentHystrixSer {

	@Override
	public String ok(Long id) {
		return "OK方法运行异常了：" + System.currentTimeMillis();
	}

	@Override
	public String timeOut(Long id) {
		return "TimeOut方法运行异常了：" + System.currentTimeMillis();
	}

}
