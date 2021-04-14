package com.pack.file.properties;

import java.io.File;
import java.util.Properties;
import java.util.regex.Matcher;

import org.springframework.core.io.ClassPathResource;

import com.pack.file.FileUtility;

public class PropertiesUtility {
	private static PropertiesUtility pu = null;

	public static PropertiesUtility init() {
		if (pu == null) {
			synchronized (PropertiesUtility.class) {
				if (pu == null) {
					pu = new PropertiesUtility();
				}
			}
		}
		return pu;
	}

	private PropertiesUtility() {
	}

	/**
	 * 解析.properties文件(根据路径与名称获取)
	 * 
	 * @param path 文件路径
	 * @param name 文件名称
	 * @return
	 */
	public Properties getPropertiesByPathAndName(String path, String name) {
		path = FileUtility.init().StandardPath(path + Matcher.quoteReplacement(File.separator) + name);
		return this.getPropertiesByPath(path);
	}

	/**
	 * 解析.properties文件
	 * 
	 * @param path
	 * @return
	 */
	public Properties getPropertiesByPath(String path) {
		try {
			// 生成properties对象
			Properties p = new Properties();
			ClassPathResource resource = new ClassPathResource(path);
			p.load(resource.getInputStream());
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析.properties文件(默认路径为conf/)
	 * 
	 * @param name
	 * @return
	 */
	public Properties getProperties(String name) {
		return this.getPropertiesByPathAndName("conf/", name);
	}

}
