package com.cloud.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.entity.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
