package com.sgcc.gconf;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;

import org.json.XML;
import org.springframework.core.io.ClassPathResource;

import net.sf.json.JSONObject;

public class Format {
	private static JSONObject xmlJson = null;
	private static String[] labelNames = null;

	public static void main(String[] args) {
		Format f = new Format();
		System.out.println(Arrays.toString(f.getLabelNames()));
	}

	/**
	 * 获取XML配置（转JSON）
	 * 
	 * @return
	 */
	public JSONObject getXmlJson() {
		if (xmlJson == null) {
			synchronized (Format.class) {
				if (xmlJson == null) {
					StringBuffer sb = new StringBuffer();
					ClassPathResource resource = null;
					InputStream fis = null;
					BufferedReader br = null;
					try {
						resource = new ClassPathResource("gconf/format.xml");
						fis = resource.getInputStream();
						br = new BufferedReader(new InputStreamReader(fis));
						while (br.ready()) {
							sb.append(br.readLine());
						}
						xmlJson = JSONObject.fromObject(XML.toJSONObject(sb.toString()).toString()).getJSONObject("G");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (br != null) {
							try {
								br.close();
							} catch (Exception e2) {
								e2.printStackTrace();
							}

						}
						if (fis != null) {
							try {
								fis.close();
							} catch (Exception e2) {
								e2.printStackTrace();
							}

						}
					}
				}
			}
		}
		return xmlJson;
	}

	/**
	 * 获取所有设备标签
	 * 
	 * @return
	 */
	public String[] getLabelNames() {
		if (xmlJson == null) {
			this.getXmlJson();
		}
		if (labelNames == null) {
			synchronized (Format.class) {
				if (labelNames == null) {
					JSONObject ja = xmlJson.getJSONObject("G");
					Set<?> keys = ja.keySet();
					labelNames = new String[keys.size()];
					keys.toArray(labelNames);
				}
			}
		}
		return labelNames;
	}

}
