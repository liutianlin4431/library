package com.rule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class SelfRule {
	/**
	 * 随机；主启动类需要配置@RibbonClient注解
	 * 
	 * @return
	 */
	@Bean
	public IRule myRule() {
		return new RandomRule();
	}
}
