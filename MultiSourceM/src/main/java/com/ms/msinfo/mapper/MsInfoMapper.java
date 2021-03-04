package com.ms.msinfo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ms.msinfo.entity.MsInfo;

@Mapper
public interface MsInfoMapper {
	public MsInfo getById(@Param("id") Integer id);
}
