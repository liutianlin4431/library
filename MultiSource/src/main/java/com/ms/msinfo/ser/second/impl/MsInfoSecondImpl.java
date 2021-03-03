package com.ms.msinfo.ser.second.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.ds.DataSource;
import com.ms.ds.DataSourceEnum;
import com.ms.msinfo.entity.MsInfo;
import com.ms.msinfo.mapper.MsInfoSecond;
import com.ms.msinfo.ser.second.MsInfoSecondSer;

@Service
@Transactional
public class MsInfoSecondImpl extends ServiceImpl<MsInfoSecond, MsInfo> implements MsInfoSecondSer {
	@Override
	@DataSource(DataSourceEnum.SECOND)
	public MsInfo getById(Serializable id) {
		return super.getById(id);
	}
}
