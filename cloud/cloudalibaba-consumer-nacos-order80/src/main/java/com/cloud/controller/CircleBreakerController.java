package com.cloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.entity.CommonResult;
import com.cloud.entity.Payment;

@RestController
public class CircleBreakerController {

	public static final String SERVICE_URL = "http://nacos-payment-provider";

	@Resource
	private RestTemplate restTemplate;

	@RequestMapping("/consumer/fallback/{id}")
	// @SentinelResource(value = "fallback") //没有配置
	// @SentinelResource(value = "fallback",fallback = "handlerFallback")
	// //fallback只负责业务异常
	// @SentinelResource(value = "fallback",blockHandler = "blockHandler")
	// //blockHandler只负责sentinel控制台配置违规
	@SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler", exceptionsToIgnore = {
			IllegalArgumentException.class })
	public CommonResult fallback(@PathVariable Long id) {
		CommonResult result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

		if (id == 4) {
			throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
		} else if (result.getData() == null) {
			throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
		}

		return result;
	}

	// fallback
	public CommonResult handlerFallback(@PathVariable Long id, Throwable e) {
		Payment payment = new Payment(id, "null");
		return new CommonResult(444, "兜底异常handlerFallback,exception内容  " + e.getMessage(), payment);
	}

	// blockHandler
	public CommonResult blockHandler(@PathVariable Long id, BlockException blockException) {
		Payment payment = new Payment(id, "null");
		return new CommonResult(445, "blockHandler-sentinel限流,无此流水: blockException  " + blockException.getMessage(),
				payment);
	}

}
