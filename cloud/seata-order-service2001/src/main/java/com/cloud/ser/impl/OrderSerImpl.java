package com.cloud.ser.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.entity.Order;
import com.cloud.mapper.OrderMapper;
import com.cloud.ser.AccountService;
import com.cloud.ser.OrderSer;
import com.cloud.ser.StorageService;

import io.seata.spring.annotation.GlobalTransactional;

@Service
@Transactional
public class OrderSerImpl extends ServiceImpl<OrderMapper, Order> implements OrderSer {
	private static Logger log = LoggerFactory.getLogger(OrderSerImpl.class);
	@Resource
	private StorageService storageService;
	@Resource
	private AccountService accountService;

	@Override
	@GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
	public void create(Order order) {
		log.info("----->开始新建订单");
		// 新建订单
		order.setStatus(1);
		this.save(order);

		// 扣减库存
		log.info("----->订单微服务开始调用库存，做扣减Count");
		storageService.decrease(order.getProductId(), order.getCount());
		log.info("----->订单微服务开始调用库存，做扣减end");

		// 扣减账户
		log.info("----->订单微服务开始调用账户，做扣减Money");
		accountService.decrease(order.getUserId(), order.getMoney());
		log.info("----->订单微服务开始调用账户，做扣减end");

		// 修改订单状态，从零到1代表已经完成
		log.info("----->修改订单状态开始");
		this.update(new UpdateWrapper<Order>().lambda().set(Order::getStatus, 1).eq(Order::getId, order.getId())
				.eq(Order::getStatus, 0));
		log.info("----->修改订单状态结束");

		log.info("----->下订单结束了");

	}

}
