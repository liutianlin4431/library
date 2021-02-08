package com.pack.console;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本地命令执行工具类
 * 
 * @author ltl
 * 
 */
public class LocalExecute {
	private static Logger log = LoggerFactory.getLogger(LocalExecute.class);
	private static LocalExecute le = null;

	/**
	 * 创建类（单例）
	 * 
	 * @return
	 */
	public static LocalExecute init() {
		if (le == null) {
			synchronized (LocalExecute.class) {
				if (le == null) {
					le = new LocalExecute();
				}
			}
		}
		return le;
	}

	private LocalExecute() {
	}

	/**
	 * 执行命令
	 * 
	 * @param cmd_array
	 * @return
	 */
	public Integer runCommand(String cmd) {
		log.debug(cmd);
		try {
			Process pos = Runtime.getRuntime().exec(cmd);
			printLog(pos.getErrorStream());
			printLog(pos.getInputStream());
			return pos.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		} finally {
		}
	}

	public void printLog(InputStream is) {
		InputStreamReader ir = null;
		LineNumberReader input = null;
		try {
			ir = new InputStreamReader(is);
			input = new LineNumberReader(ir);
			String ln = "";
			while ((ln = input.readLine()) != null) {
				log.debug(ln);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (input != null) {
					input.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (ir != null) {
					ir.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}
}
