package com.cloud.ser;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import cn.hutool.core.util.IdUtil;

@Service
public class PaymentSer {
	// --------------------------------------------------服务降级
	/**
	 * 正常访问接口
	 * 
	 * @param id
	 * @return
	 */
	public String ok(Long id) {
		return Thread.currentThread().getName() + ":" + id;
	}

	/**
	 * 模拟时间较长的逻辑方法
	 * 
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "timeOut_ensure", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") }) // 超过3秒中执行备用方法;启动类需要使用@EnableCircuitBreaker
	public String timeOut(Long id) {
		Integer timeNum = 5;
		try {
			TimeUnit.SECONDS.sleep(timeNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Thread.currentThread().getName() + ":" + id + ":等待" + timeNum + "秒钟";
	}

	/**
	 * timeOut方法的备用方法，当timeOut出现问题时使用该方法
	 * 
	 * @param id
	 * @return
	 */
	public String timeOut_ensure(Long id) {
		return Thread.currentThread().getName() + ":" + id + ":/(ㄒoㄒ)/~~\t" + System.currentTimeMillis();
	}

	// --------------------------------------------------服务熔断
	@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间范围
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 失败率达到多少后跳闸
	})
	// 开启断路器，10秒钟之内10中之内百分之60错误则断开
	// 定制兜底方法时方法入参必须保持一致
	public String paymentCircuitBreaker(@PathVariable("id") Long id) {
		if (id < 0) {
			throw new RuntimeException("*****id 不能负数");
		}
		String serialNumber = IdUtil.simpleUUID();

		return Thread.currentThread().getName() + "\t" + "调用成功,流水号：" + serialNumber;
	}

	public String paymentCircuitBreaker_fallback(@PathVariable("id") Long id) {
		return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " + id;
	}

}
