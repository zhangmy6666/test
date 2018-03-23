package cn.zmy.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {
	static CountDownLatch cdl = new CountDownLatch(10);
	
	public static void main(String[] args) {
		/**
		 * 线程资源必须通过线程池提供，不允许显式创建线程
		 * 减少在创建和销毁线程上所消耗的时间和资源
		 * 避免系统创建大量同类线程而导致消耗完内存或“过度切换”的问题
		 * 主张通过ThreadPoolExecutor创建线程池
		 */
		/**
		 * 通过Executors创建四类线程池
		 * Executors 是一个工具类，类似于 Collections。提供工厂方法来创建不同类型的线程池。
		 * CachedThreadPool 线程复用，但不能控制线程数量(0,max)
		 * ScheduledThreadPool ScheduledExecutorService 支持定时及周期性任务执行。(n,max)
		 * 允许创建的线程数量是Integer.MAX_VALUE
		 * FixedThreadPool可控制线程最大并发数，超出的线程会在队列中等待 (n,n)
		 * 允许请求队列的长度Integer.MAX_VALUE，可能会堆积大量请求，导致OOM
		 * SingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，(1,1)
		 * 保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行，允许请求队列的长度Integer.MAX_VALUE
		 */
		/**
		 * Executor 和 ExecutorService 这两个接口主要的区别是：
		 * ExecutorService 接口继承了 Executor 接口，是 Executor 的子接口
		 * 
		 * Executor 接口定义了 execute()方法用来接收一个Runnable接口的对象，
		 * 而 ExecutorService 接口中的 submit()方法可以接受Runnable和Callable接口的对象。
		 * 
		 * Executor 中的 execute() 方法不返回任何结果，
		 * 而 ExecutorService 中的 submit()方法可以通过一个 Future 对象返回运算结果。
		 * Future.get() 方法是一个阻塞式的方法，如果调用时任务还没有完成，会等待直到任务执行结束。
		 * Future 对象，还可以取消任务的执行。Future 提供了 cancel() 方法用来取消执行 pending 中的任务。
		 * 
		 * ExecutorService 还提供控制线程池的方法。比如：调用 shutDown() 方法终止线程池
		 */
//		ExecutorService executorServiceCache = Executors.newCachedThreadPool();
		/**
		 * 10 : corePoolSize核心线程数量，线程池刚创建时，线程数量为0，
		 * 当每次执行execute添加新的任务时会在线程池创建一个新的线程，直到线程数量达到corePoolSize为止
		 * 
		 * LinkedBlockingQueue： 阻塞队列，当线程池正在运行的线程数量已经达到corePoolSize，
		 * 那么再通过execute添加新的任务则会被加到workQueue队列中，在队列中排队等待执行，而不会立即执行。
		 * 
		 * 20：最大线程数量maximumPoolSize，当workQueue队列已满，放不下新的任务，
		 * 再通过execute添加新的任务则线程池会再创建新的线程，
		 * 线程数量大于corePoolSize但不会超过maximumPoolSize，
		 * 如果超过maximumPoolSize，那么会抛出异常，如RejectedExecutionException。
		 * 
		 * 60, TimeUnit.SECONDS：当线程池中线程数量大于workQueue，
		 * 如果一个线程的空闲时间大于keepAliveTime，则该线程会被销毁。unit则是keepAliveTime的时间单位。
		 */
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