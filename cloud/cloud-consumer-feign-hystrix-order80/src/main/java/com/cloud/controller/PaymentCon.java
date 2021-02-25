package com.cloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ser.PaymentHystrixSer;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@DefaultProperties(defaultFallback = "ensure") // 全局兜底方法；需要兜底的方法使用@HystrixCommand注解不需要填写指定兜底方法
public class PaymentCon {
	@Resource
	private PaymentHystrixSer phs;

	@GetMapping("/consumer/payment/hystrix/ok/{id}")
	public String ok(@PathVariable("id") Long id) {
		return phs.ok(id);
	}

	@GetMapping("/consumer/payment/hystrix/timeOut/{id}")
	@HystrixCommand(fallbackMethod = "timeOut_ensure", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500") }) // 使用@EnableHystrix启动注解
	public String timeOut(@PathVariable("id") Long id) {
		return phs.timeOut(id);
	}

	/**
	 * timeOut方法的备用方法，当timeOut出现问题时使用该方法
	 * 
	 * @param id
	 * @return
	 */
	public String timeOut_ensure(Long id) {
		return Thread.currentThread().getName() + ":~~~80~~~:" + id + ":/(ㄒoㄒ)/~~\t" + System.currentTimeMillis();
	}

	public String ensure() {
		return "当前请求出现异常：" + System.currentTimeMillis();
	}
}
