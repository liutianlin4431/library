package com.cloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.entity.CommonResult;
import com.cloud.entity.Order;
import com.cloud.ser.OrderSer;

@RestController
public class OrderController {
	@Resource
	private OrderSer orderService;

	@GetMapping("/order/create")
	public CommonResult create(Order order) {
		orderService.create(order);
		return new CommonResult(200, "订单创建成功");
	}
}
