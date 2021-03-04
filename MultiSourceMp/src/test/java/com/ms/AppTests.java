package com.ms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ms.msinfo.entity.MsInfo;
import com.ms.msinfo.ser.first.MsInfoFirstSer;
import com.ms.msinfo.ser.second.MsInfoSecondSer;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AppTests {
	@Autowired
	private MsInfoFirstSer mfs;
	@Autowired
	private MsInfoSecondSer mss;

	@Test
	public void test01() {
		MsInfo ms1 = mfs.getById(1);
		System.out.println(ms1.toString());
		MsInfo ms2 = mss.getById(1);
		System.out.println(ms2.toString());
	}
}
