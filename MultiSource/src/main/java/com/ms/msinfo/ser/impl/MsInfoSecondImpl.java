package com.ms.msinfo.ser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.ds.DataSource;
import com.ms.ds.DataSourceEnum;
import com.ms.msinfo.entity.MsInfo;
import com.ms.msinfo.mapper.MsInfoMapper;
import com.ms.msinfo.ser.MsInfoSer;

@Service
@Transactional
public class MsInfoSecondImpl implements MsInfoSer {
	@Autowired
	private MsInfoMapper msInfoMapper;

	@Override
	@DataSource(DataSourceEnum.FIRST)
	public MsInfo getById1(Integer id) {
		return msInfoMapper.getById(id);
	}

	@Override
	@DataSource(DataSourceEnum.SECOND)
	public MsInfo getById2(Integer id) {
		return msInfoMapper.getById(id);
	}
}
