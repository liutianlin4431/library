package com.cloud.controller;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.entity.CommonResult;
import com.cloud.ser.AccountSer;

@RestController
public class AccountController {

	@Resource
	AccountSer accountService;

	/**
	 * 扣减账户余额
	 */
	@RequestMapping("/account/decrease")
	public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
		accountService.decrease(userId, money);
		return new CommonResult(200, "扣减账户余额成功！");
	}
}
