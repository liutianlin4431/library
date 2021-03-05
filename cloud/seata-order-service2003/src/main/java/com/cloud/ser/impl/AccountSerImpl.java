package com.cloud.ser.impl;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.entity.Account;
import com.cloud.mapper.AccountMapper;
import com.cloud.ser.AccountSer;

@Service
@Transactional
public class AccountSerImpl extends ServiceImpl<AccountMapper, Account> implements AccountSer {
	private static Logger log = LoggerFactory.getLogger(AccountSerImpl.class);

	/**
	 * 扣减账户余额
	 */
	@Override
	public void decrease(Long userId, BigDecimal money) {

		log.info("------->account-service中扣减账户余额开始");
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.getBaseMapper().decrease(userId, money);
		log.info("------->account-service中扣减账户余额结束");
	}
}
