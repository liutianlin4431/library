package com.shiro.role.ser.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pack.refactoring.mybatis_plus.impl.MyServiceImpl;
import com.shiro.role.entity.Role;
import com.shiro.role.mapper.RoleMapper;
import com.shiro.role.ser.RoleSer;

@Service
@Transactional
public class RoleImpl extends MyServiceImpl<RoleMapper, Role> implements RoleSer {
}
