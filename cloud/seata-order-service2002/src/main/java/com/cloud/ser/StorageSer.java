package com.cloud.ser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.entity.Storage;

public interface StorageSer extends IService<Storage> {
	// 扣减库存
	void decrease(Long productId, Integer count);
}
