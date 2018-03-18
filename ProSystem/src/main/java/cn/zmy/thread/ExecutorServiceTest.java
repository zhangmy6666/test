package cn.zmy.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {
	static CountDownLatch cdl = new CountDownLatch(10);
	
	public static void main(String[] args) {
		ExecutorService executorService = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(20));
		List<Future<String>> resultList = new ArrayList<Future<String>>();
		

		/**
		 * runnable callable
		 * 执行完任务之后是否可以获取执行结果
		 */
		/**
		 * submit execute
		 * 1、接收的参数不一样
		 * 2、submit有返回值，而execute没有
		 * 3、submit方便Exception处理
		 */
		// 创建10个任务并执行
		for (int i = 0; i < 10; i++) {
			// 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
			Future<String> future = executorService.submit(new ExecutorServiceTest().new TaskWithResult(i));
			// 将任务执行结果存储到List中
			resultList.add(future);
		}
		
		try {
			cdl.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			executorService.shutdownNow();
			e1.printStackTrace();
		}

		// 遍历任务的结果
		for (Future<String> fs : resultList) {
			try {
				System.out.println(fs.get());// 打印各个线程（任务）执行的结果
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
				//如果其中一个task失败，其它的task就不需要执行了。
				return;				
			}finally {
				executorService.shutdownNow();
			}
		}
		
		
	}
	
	class TaskWithResult implements Callable<String> {
		private int id;

		public TaskWithResult(int id) {
			this.id = id;
		}

		/**
		 * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。
		 * 
		 * @return
		 * @throws Exception
		 */
		public String call() throws Exception {
			System.out.println("call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());
			if (new Random().nextBoolean()) {
				cdl.countDown();
				// 随机数生成器序列的均匀分布的 boolean 值。
				throw new TaskException("Meet error in task." + Thread.currentThread().getName());
			}
				
			// 一个模拟耗时的操作
			for (int i = 999999999; i > 0; i--)
				;
			cdl.countDown();
			System.out.println(cdl.getCount() + "       " + Thread.currentThread().getName());
			return "call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();
		}
	}

}


class TaskException extends Exception {
	public TaskException(String message) {
		super(message);
	}
}