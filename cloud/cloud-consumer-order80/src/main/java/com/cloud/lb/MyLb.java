package com.cloud.lb;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cloud.client.ServiceInstance;

public class MyLb implements LoadBalancer {
	// 原子整型
	private AtomicInteger atomicInteger = new AtomicInteger(0);

	/**
	 * 获取第几次请求数
	 * 
	 * @return
	 */
	public final int getAndIncrement() {
		int current;
		int next;
		do {
			current = this.atomicInteger.get();
			next = current >= Integer.MAX_VALUE ? 0 : current + 1;
		} while (!this.atomicInteger.compareAndSet(current, next));
		return next;
	}

	@Override
	public ServiceInstance instance(List<ServiceInstance> serviceInstance) {
		int index = this.getAndIncrement() % serviceInstance.size();
		return serviceInstance.get(index);
	}

}
