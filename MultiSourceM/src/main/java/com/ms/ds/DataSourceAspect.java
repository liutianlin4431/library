package com.ms.ds;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(-1)
public class DataSourceAspect {

	@Pointcut("@within(com.ms.ds.DataSource) || @annotation(com.ms.ds.DataSource)")
	public void pointCut() {

	}

	@Before("pointCut() && @annotation(dataSource)")
	public void doBefore(DataSource dataSource) {
		DataSourceContextHolder.setDataSource(dataSource.value().getValue());
	}

	@After("pointCut()")
	public void doAfter() {
		DataSourceContextHolder.clear();
	}
}