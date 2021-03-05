package com.cloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.entity.Storage;

@Mapper
public interface StorageMapper extends BaseMapper<Storage> {
	void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
