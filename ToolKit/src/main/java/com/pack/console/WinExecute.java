package com.pack.console;

/**
 * 远程win执行
 * 
 * @author ltl
 * 
 */
public class WinExecute {
	private static WinExecute we = null;

	public static WinExecute init() {
		if (we == null) {
			synchronized (WinExecute.class) {
				if (we == null) {
					we = new WinExecute();
				}
			}
		}
		return we;
	}

	private WinExecute() {
	}
}
