package com.cloud.lb;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;

public interface LoadBalancer {
	ServiceInstance instance(List<ServiceInstance> serviceInstance);
}
