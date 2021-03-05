package com.cloud.ser.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.entity.Storage;
import com.cloud.mapper.StorageMapper;
import com.cloud.ser.StorageSer;

@Service
@Transactional
public class StorageSerImpl extends ServiceImpl<StorageMapper, Storage> implements StorageSer {
	private static Logger log = LoggerFactory.getLogger(StorageSerImpl.class);

	@Override
	public void decrease(Long productId, Integer count) {
		log.info("------->storage-service中扣减库存开始");
		this.getBaseMapper().decrease(productId, count);
		log.info("------->storage-service中扣减库存结束");

	}

}
