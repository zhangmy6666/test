package cn.zmy.exception;

import java.util.Random;

public class Loop {
	void func() throws Exception {
		Random random = new Random();
		while (true) {
			int i = random.nextInt(10);
			System.out.println("--------" + i);
			if(i%2 == 0) {
				throw new Exception("I don't like even");
			}
//			Thread.sleep(200);
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Loop().func();
	}

}
