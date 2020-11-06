package com.shiro.user.ser.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pack.refactoring.mybatis_plus.impl.MyServiceImpl;
import com.shiro.user.entity.User;
import com.shiro.user.mapper.UserMapper;
import com.shiro.user.ser.UserSer;

@Service
@Transactional
public class UserImpl extends MyServiceImpl<UserMapper, User> implements UserSer {
}
