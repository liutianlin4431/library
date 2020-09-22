package com.pack.refactoring.mybatis_plus.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pack.refactoring.mybatis_plus.ser.MyIService;

/**
 * 重构mybatsi plus ServiceImpl
 * 
 * @author ltl
 *
 * @param <M>
 * @param <T>
 */
public class MyServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements MyIService<T> {

	@Override
	public QueryWrapper<T> toQueryWrapper(T entity) {
		QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
		// 获取实体类字段
		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field field : fields) {
			// 获取字段名称
			String fieldName = field.getName();
			if (!fieldName.equals("serialVersionUID")) {
				// 设置访问
				field.setAccessible(true);
				// 获取TableField注解
				TableField ft = field.getAnnotation(TableField.class);
				boolean exist = true;
				if (ft != null) {
					// 获取注释方法
					Method[] methods = ft.annotationType().getDeclaredMethods();
					for (Method method : methods) {
						if (!method.isAccessible()) {
							method.setAccessible(true);
						}
						try {
							if (method.getName().equals("value")) {
								fieldName = method.invoke(ft).toString();
							} else if (method.getName().equals("exist")) {
								exist = (boolean) method.invoke(ft);
							}
						} catch (Exception e) {
							// TODO 不做任何处理
						}
					}
					if (exist) {
						try {
							String value = field.get(entity).toString();
							if (value != null && !value.toLowerCase().equals("null") && value.length() > 0) {
								queryWrapper.eq(fieldName, value);
							}
						} catch (Exception e) {
							// TODO 不做任何处理
						}
					}
				} else {
					try {
						String value = field.get(entity).toString();
						if (value != null && !value.toLowerCase().equals("null") && value.length() > 0) {
							queryWrapper.eq(fieldName, value);
						}
					} catch (Exception e) {
						// TODO 不做任何处理
					}
				}
			}
		}
		return queryWrapper;
	}
}
