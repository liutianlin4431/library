package com.cloud.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

@RestController
public class FlowLimitController {
	@GetMapping("/testA")
	public String testA() {
		return "------testA";
	}

	@GetMapping("/testB")
	public String testB() {

		return "------testB";
	}

	/**
	 * RT降级测试
	 * 
	 * @return
	 */
	@GetMapping("/testD")
	public String testD() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "------testD";
	}

	/**
	 * 热点规则
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	@GetMapping("/hotkey")
	@SentinelResource(value = "hotkey", blockHandler = "deal_hotkey")
	public String hotkey(@RequestParam(value = "p1", required = false) String p1,
			@RequestParam(value = "p2", required = false) String p2) {
		System.out.println("hotkey:" + p1);
		System.out.println("hotkey:" + p2);
		return "热点	o(*￣▽￣*)ブ";
	}

	public String deal_hotkey(String p1, String p2, BlockException exception) {
		System.out.println("deal_hotkey:" + p1);
		System.out.println("deal_hotkey:" + p2);
		return "热点	/(ㄒoㄒ)/~~";
	}
}
