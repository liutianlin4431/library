package com.shiro.role.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiro.role.entity.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
