package com.pack.file.properties;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class PropertiesUtility {

	/**
	 * 获取properties文件内容
	 * 
	 * @param fileName 文件名
	 * @return
	 */
	public static Properties getProperties(String fileName) {
		try {
			// 生成properties对象
			Properties p = new Properties();
			ClassPathResource resource = new ClassPathResource("conf/" + fileName);
			InputStream fis = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = "";
			while ((line = br.readLine()) != null) {
				String array[] = line.split("=");
				p.setProperty(array[0], array[1]);
			}
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
