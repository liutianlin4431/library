package com.cloud.ser;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
//fallback 为当前openfeign设置降级方法，创建当前接口实现类，在继承方法中编写降级方法
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentHystrixSerEnsure.class) // Eureka中服务的名称
public interface PaymentHystrixSer {
	@GetMapping("/payment/hystrix/ok/{id}")
	public String ok(@PathVariable("id") Long id);

	@GetMapping("/payment/hystrix/timeOut/{id}")
	public String timeOut(@PathVariable("id") Long id);
}
