package com.sgcc.parsing;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.XML;

import com.pack.file.EncodingDetect;
import com.sgcc.gconf.Format;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class GTool {

	public static void main(String[] args) {
		try {
			GTool gt = new GTool();
			Format f = new Format();
			JSONObject old = gt.split("E:\\backup\\data\\PsdCloud\\psg\\接线图1.psg");
			JSONObject young = f.getXmlJson();
			System.out.println(gt.standard(old, young));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取g文件内容
	 * 
	 * @param path 文件路径
	 * @return
	 * @throws IOException
	 */
	public JSONObject split(String path) throws IOException {
		String info = FileUtils.readFileToString(new File(path), Charset.forName(EncodingDetect.getJavaEncode(path)));
		String xmlJson = XML.toJSONObject(info).toString();
		JSONObject jo = JSONObject.fromObject(xmlJson);
		return jo.getJSONObject("G").getJSONObject("Layer");
	}

	/**
	 * 规范
	 * 
	 * @param old   原本的g文件
	 * @param young 规范样本
	 * @return
	 */
	public JSONObject standard(JSONObject old, JSONObject young) {
		JSONObject jo = new JSONObject();
		Set<?> keys = old.keySet();
		if (keys != null) {
			for (Object key : keys) {
				try {
					// 原本g文件中的json格式
					JSONArray ja_old = old.getJSONArray(key.toString());
					// 存放规范后的json格式
					JSONArray ja_young = new JSONArray();
					// 规范样板
					JSONObject template = young.getJSONObject(key.toString());
					if (template != null && !template.toString().equals("null")) {
						for (int i = 0; i < ja_old.size(); i++) {
							Set<?> arrts = template.keySet();
							if (arrts != null) {
								JSONObject result = new JSONObject();
								for (Object attr : arrts) {
									if (!attr.equals("alias") && !attr.equals("zh")) {
										String val = template.getString(attr.toString());
										JSONObject current_old = ja_old.getJSONObject(i);
										try {
											result.put(attr, current_old.get(val).toString());
										} catch (NullPointerException e) {
											result.put(attr, "");
										}
									}
								}
								ja_young.add(result);
								jo.put(template.get("alias"), ja_young);
							}
						}
					}
				} catch (JSONException e) {
					if (e.getMessage().endsWith("is not a JSONArray.")) {
						continue;
					}
				}
			}
		}
		return jo;
	}

}
