package com.java.cas;

/**
 * 模拟CAS算法
 * 
 * @author ltl
 *
 */
public class CasSimulation {

	public static void main(String[] args) {
		final CompareAndSwap cas = new CompareAndSwap();

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int expectedValue = cas.get();
					boolean b = cas.compareAndSet(expectedValue, (int) (Math.random() * 101));
					System.out.println(b);
				}
			}).start();
		}

	}

}

class CompareAndSwap {
	private int value;

	/**
	 * 获取原始值
	 * 
	 * @return
	 */
	public synchronized int get() {
		return value;
	}

	/**
	 * 比较【预估值与原始值相等时，原始值才可以被替换成功新值】
	 * 
	 * @param expectedValue
	 * @param newValue
	 * @return
	 */
	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		int oldValue = value;

		if (oldValue == expectedValue) {
			this.value = newValue;
		}

		return oldValue;
	}

	/**
	 * 修改值
	 *
	 * @param expectedValue
	 * @param newValue
	 * @return 修改是否成功1
	 */
	public synchronized boolean compareAndSet(int expectedValue, int newValue) {
		return expectedValue == compareAndSwap(expectedValue, newValue);
	}
}
