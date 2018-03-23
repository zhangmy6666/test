package cn.zmy.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static final int NUM = 4;
	
	static Semaphore sema = new Semaphore(NUM);

	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		
		

		for (int i = 0 ; i < 10; i++) {
			
			es.submit(new SemaphoreTest().new Task());
		}
		
		es.shutdown();
		
	}

	class Task implements Runnable {		
		
		@Override
		public void run() {
			try {
				sema.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "获得信号灯，当前已有 "
					+ (NUM-sema.availablePermits()) +"并发");//获取可以提供的信号灯。
			try {
				Thread.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sema.release();
			System.out.println(Thread.currentThread().getName() + "离开，释放信号灯");

		}

	}

}
