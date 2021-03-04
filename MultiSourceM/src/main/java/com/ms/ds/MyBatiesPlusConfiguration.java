package com.ms.ds;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@MapperScan("com.ms.*.mapper*")
public class MyBatiesPlusConfiguration {

	@Bean(name = "first")
	@ConfigurationProperties(prefix = "spring.datasource.first")
	public DataSource first() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "second")
	@ConfigurationProperties(prefix = "spring.datasource.second")
	public DataSource second() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * 动态数据源配置
	 * 
	 * @return
	 */
	@Bean
	@Primary
	public DataSource multipleDataSource(@Qualifier("first") DataSource first, @Qualifier("second") DataSource second) {
		MultipleDataSource multipleDataSource = new MultipleDataSource();
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DataSourceEnum.FIRST.getValue(), first);
		targetDataSources.put(DataSourceEnum.SECOND.getValue(), second);
		// 添加数据源
		multipleDataSource.setTargetDataSources(targetDataSources);
		// 设置默认数据源
		multipleDataSource.setDefaultTargetDataSource(first);
		return multipleDataSource;
	}

	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(multipleDataSource(first(), second()));
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setCacheEnabled(false);
		sqlSessionFactory
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*/*.xml"));
		sqlSessionFactory.setConfiguration(configuration);
		return sqlSessionFactory.getObject();
	}

}