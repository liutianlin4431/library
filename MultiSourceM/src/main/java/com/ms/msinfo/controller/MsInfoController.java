package com.ms.msinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.msinfo.entity.MsInfo;
import com.ms.msinfo.ser.MsInfoSer;

@RestController
public class MsInfoController {
	@Autowired
	private MsInfoSer mis;

	@GetMapping(value = "/get/data")
	public void getData() {
		Long time = System.currentTimeMillis();
		if (time % 2 == 0 || time % 6 == 0) {
			MsInfo ms1 = mis.getById1(1);
			System.out.println(ms1.toString());
		} else {
			MsInfo ms2 = mis.getById2(1);
			System.out.println(ms2.toString());
		}

	}
}
