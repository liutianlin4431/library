package com.cloud.ser;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.entity.Account;

public interface AccountSer extends IService<Account> {
	/**
	 * 扣减账户余额
	 */
	void decrease(Long userId, BigDecimal money);
}
