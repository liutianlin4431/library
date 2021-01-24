package com.cloud.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.entity.Payment;

@Mapper
public interface PaymentDao extends BaseMapper<Payment> {

}
