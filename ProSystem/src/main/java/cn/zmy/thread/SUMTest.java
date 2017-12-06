package cn.zmy.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SUMTest {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		int start=1;
		int sumInteger = 0;
		Integer[] sum = new Integer[3];
		
		for (int i = 0; i < 3; i++) {  
			sum[i] = new Integer(0);  
        }
		for (int i = 0 ;i<3;i++)
		{
			es.submit(new Task(sum[i],1+i*100,(i+1)*100));
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //等待加和程序执行结束  
		  
	    for (int m = 0; m < 3; m++) {  
	        sumInteger += sum[m];  
	    }  
	    System.out.println("The sum is :" + sumInteger);
	    
	    new Thread().start();
	}
	 
}

class Task implements Runnable
{
	Integer sum;
	int i;
	int j;

	public Task(Integer sum, int i, int j) {
		this.i = i;
		this.j=j;
		this.sum=sum;
	}

	@Override
	public void run() {
		for (int k=i;k<j;k++)
		{
			sum +=k;
		}
		
	}
	
}

