package com.cloud.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.entity.Account;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
	/**
	 * 扣减账户余额
	 */
	void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
