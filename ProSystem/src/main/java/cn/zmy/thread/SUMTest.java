package cn.zmy.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SUMTest {
	static CountDownLatch cdl = new CountDownLatch(2);
	
	public static void main(String[] args) {
		ExecutorService es = new ThreadPoolExecutor(3, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		int sumInteger = 0;
		List<Future<Integer>> resultList = new ArrayList<Future<Integer>>();

		// 访问内部类需要外部类的实例
		for (int i = 0; i < 3; i++) {
			Future<Integer> result = es.submit(new SUMTest().new Task(1 + i * 100, (i + 1) * 100));
			resultList.add(result);
		}
		
		try {
			cdl.await();
		} catch (InterruptedException e) {
			es.shutdown();
			e.printStackTrace();
		}
		es.shutdown();

		for (Future<Integer>  result: resultList) {
			try {
				sumInteger += result.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("The sum is :" + sumInteger);

		
	}

	class Task implements Callable<Integer> {
		Integer sum = 0;
		int i;
		int j;

		public Task(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public Integer call() throws Exception {
			for (int k = i; k < j; k++) {
				sum += k;
			}
			Thread.sleep(i*30);
			System.out.println("i===" + i + ", sum ====" + sum);
			cdl.countDown();
			System.out.println(cdl.getCount());
			return sum;
		}

	}

}
