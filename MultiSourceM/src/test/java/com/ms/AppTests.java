package com.ms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ms.msinfo.entity.MsInfo;
import com.ms.msinfo.ser.MsInfoSer;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AppTests {
	@Autowired
	private MsInfoSer mis;

	@Test
	public void test01() {
		MsInfo ms1 = mis.getById1(1);
		System.out.println(ms1.toString());
		MsInfo ms2 = mis.getById2(1);
		System.out.println(ms2.toString());
	}
}
