package cn.zmy.recursive;

public class Fibonacci {
//	递归算法解决问题的特点：   
//    1）递归就是方法里调用自身。   
//    2）在使用递增归策略时，必须有一个明确的递归结束条件，称为递归出口。    
//    3）递归算法解题通常显得很简洁，但递归算法解题的运行效率较低。所以一般不提倡用递归算法设计程序。
//    4）在递归调用的过程当中系统为每一层的返回点、局部量等开辟了栈来存储。
//	递归次数过多容易造成栈溢出等，所以一般不提倡用递归算法设计程序。
	
//	我们通常都是从上而下的思维问题， 而递归趋势从下往上的进行思维。
	
	// 求解Fibonacci数列的第n个位置的值
	public int f(int n) {
		int result = 0;
		if (n == 1 || n == 2) {
			result = 1;
		} // 出口
		if (n > 2) {
			result = f(n-1) + f(n-2);
		}
		return result;
	}
	
	// n的阶乘
	public long factorial(int n) {
		long result = 0;
		if (n == 1) {
			result = 1;
		}
		if (n > 1) {
			result = n * factorial(n-1);
		}
		return result;
	}
	
	public static void main(String[] args) {
		Fibonacci fib =  new Fibonacci();
		System.out.println(fib.f(6));
		System.out.println(fib.factorial(4));
	}

}
