package com.cloud.ser;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class PaymentSer {
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
}
