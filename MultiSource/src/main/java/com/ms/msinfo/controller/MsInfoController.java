package com.ms.msinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.msinfo.entity.MsInfo;
import com.ms.msinfo.ser.first.MsInfoFirstSer;
import com.ms.msinfo.ser.second.MsInfoSecondSer;

@RestController
public class MsInfoController {
	@Autowired
	private MsInfoFirstSer mfs;
	@Autowired
	private MsInfoSecondSer mss;

	@GetMapping(value = "/get/data")
	public void getData() {
		MsInfo ms1 = mfs.getById(1);
		System.out.println(ms1.toString());
		MsInfo ms2 = mss.getById(1);
		System.out.println(ms2.toString());
	}
}
