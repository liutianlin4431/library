package com.java.production_and_consumption;

/*
 * 生产者和消费者案例
 */
public class ProductorAndConsumer {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();

		Productor pro = new Productor(clerk);
		Consumer cus = new Consumer(clerk);

		new Thread(pro, "生产者 A").start();
		new Thread(cus, "消费者 B").start();

		new Thread(pro, "生产者 C").start();
		new Thread(cus, "消费者 D").start();
	}

}

//店员
class Clerk {
	private int product = 0;

	// 进货
	public synchronized void get() {
		// 虚假唤醒-----为了避免虚假唤醒问题，应该总是使用在循环中
		// 多个线程访问时，全部处于等待时，被其他地方唤醒继续执行时product会被多次操作
		// 防止虚假唤醒使用while，当被唤醒时再次判断是否可以执行操作
		while (product >= 1) {
			System.out.println("产品已满！");

			try {
				this.wait();
			} catch (InterruptedException e) {
			}

		}

		System.out.println(Thread.currentThread().getName() + " : " + ++product);
		this.notifyAll();
	}

	// 卖货
	public synchronized void sale() {
		while (product <= 0) {
			System.out.println("缺货！");

			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}

		System.out.println(Thread.currentThread().getName() + " : " + --product);
		this.notifyAll();
	}
}

//生产者
class Productor implements Runnable {
	private Clerk clerk;

	public Productor(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}

			clerk.get();
		}
	}
}

//消费者
class Consumer implements Runnable {
	private Clerk clerk;

	public Consumer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			clerk.sale();
		}
	}
}