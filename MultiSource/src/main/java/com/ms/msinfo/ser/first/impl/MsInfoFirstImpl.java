package com.ms.msinfo.ser.first.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.ds.DataSource;
import com.ms.ds.DataSourceEnum;
import com.ms.msinfo.entity.MsInfo;
import com.ms.msinfo.mapper.MsInfoFirst;
import com.ms.msinfo.ser.first.MsInfoFirstSer;

@Service
@Transactional
public class MsInfoFirstImpl extends ServiceImpl<MsInfoFirst, MsInfo> implements MsInfoFirstSer {
	@Override
	@DataSource(DataSourceEnum.FIRST)
	public MsInfo getById(Serializable id) {
		return super.getById(id);
	}
}
