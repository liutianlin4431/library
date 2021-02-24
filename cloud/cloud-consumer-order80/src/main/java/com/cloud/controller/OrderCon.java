package com.cloud.controller;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cloud.entity.CommonResult;
import com.cloud.entity.Payment;
import com.cloud.lb.LoadBalancer;

@RestController
public class OrderCon {
	public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
	@Resource
	private RestTemplate restTemplate;

	@GetMapping("/consumer/payment/create")
	public CommonResult create(@RequestBody Payment payment) {
		return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
	}

	/**
	 * RestTemplate使用getForObject调用接口；基本可以理解为JSON
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult get(@PathVariable("id") Long id) {
		return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
	}

	/**
	 * RestTemplate使用
	 * getForEntity调用接口；返回的是ResponseEntity对象，包含了相应信息（如：响应头、响应状态码、响应体等）
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/consumer/payment/getEntity/{id}")
	public CommonResult getEntity(@PathVariable("id") Long id) {
		ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id,
				CommonResult.class);
		if (entity.getStatusCode().is2xxSuccessful()) {
			return entity.getBody();
		}
		return new CommonResult(400, "查询异常");
	}

	@Resource
	private LoadBalancer loadBalancer;
	@Resource
	private DiscoveryClient discoveryClient;

	/**
	 * 使用自己写的负载均衡算法 
	 * ？？是否需要注释配置类的@LoadBalanced注解
	 * @return
	 */
	@GetMapping("/consumer/payment/lb")
	public String getLb() {
		// 获取erueka注册中心中所有CLOUD-PAYMENT-SERVICE服务
		List<ServiceInstance> siL = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
		if (siL == null || siL.size() <= 0) {
			return null;
		}
		ServiceInstance si = loadBalancer.instance(siL);
		URI uri = si.getUri();
		return restTemplate.getForObject(uri + "/payment/lb", String.class);
	}
}
