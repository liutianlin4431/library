package com.cloud.ser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.entity.Order;

public interface OrderSer extends IService<Order> {
	public void create(Order order);
}
