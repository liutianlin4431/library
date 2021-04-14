package com.pack.console;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
			// input信息需要在error之前执行，否input信息会阻塞-如果一步执行不存在此问题
			printLog(pos.getInputStream());
			printLog(pos.getErrorStream());
			return pos.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		} finally {
		}
	}

	/**
	 * 执行命令-超时摧毁
	 * 
	 * @param cmd
	 * @param timeout 在规定秒内结束
	 * @return
	 */
	public Integer runCommand(String cmd, Long timeout) {
		log.debug(cmd);
		try {
			// 开启异步任务
			ExecutorService executorService = Executors.newSingleThreadExecutor();
			// 开始执行cmd命令
			Process pos = Runtime.getRuntime().exec(cmd);
			// 获取返回结果
			Future<Integer> future = executorService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					try {
						// input信息需要在error之前执行，否input信息会阻塞-如果异步执行不存在此问题
						printLog(pos.getInputStream());
						printLog(pos.getErrorStream());
						return pos.waitFor();
					} catch (Exception e) {
						log.error("出错了-1", e);
						return 1;
					}
				}

			});
			try {
				TimeUnit.SECONDS.sleep(timeout);
			} catch (Exception e) {
				log.error("出错了-2", e);
			}
			// 如果限制时间结束后还未结束则手动结束
			if (!future.isDone()) {
				pos.destroyForcibly();
				future.cancel(true);
				return 1;
			}
			return future.get();
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
