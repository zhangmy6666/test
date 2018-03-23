package cn.zmy.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {
	public AtomicInteger inc = new AtomicInteger(0);

	/**
	 * volatile不保证操作的原子性，i++这种操作并不是原子操作。
	 * volatile保证每次得到的数据是最新的（从内存中读取），i++; --> i=i+1; 
	 * 如果执行到i+1没有赋值给i的话，就无法保证另个线程得到的数据是最新的
	 * AtomicInteger是原子的int
	 */
	public void increase() {
		inc.getAndIncrement();
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		final VolatileTest test = new VolatileTest();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 100000; j++)
						test.increase();
				};
			}.start();
		}

		while (Thread.activeCount() > 1) // 保证前面的线程都执行完
			Thread.yield();
		System.out.println(test.inc);
		long time = System.currentTimeMillis() - start;
		System.out.println("消耗：" + (double) time / 1000 + " s");
	}

}
