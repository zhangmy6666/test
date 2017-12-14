package cn.zmy.base;

public class OverloadTest {
	public static void main(String[] args) {
//		Overload over = new Overload();
//		over.f();
//		// 如果没有f(int),会去找f(float),在没有，回去找f(double)
//		over.f(0);
//		over.f(1, 2);
//		
//		// 基本类型 byte<short<int<long<float<double char<int
//		int i = 1;
//		long l = 0L;
//		// float从左到右，第一位是符号位,2-9位共8位表示整数位
//		// 2的8-1次方等于128,后面23位是表示小数的，所以最大值是2^128-1;
//		float j = i;
//		float j1 = l;
//		// double从左到右，第一位是符号位，2-12是共11位表示整数位
//		// 2的11-1次方等于1024。剩余20位表示小数,所以最大值是2^1024-1.
//		double k = j;
//		System.out.println(j);
//		System.out.println(j1);
//		System.out.println(k);
//		
//		System.out.println(String.valueOf((char) l));
//		
		char a = '1';
		char b = 12; //b='12'会报错
		int j = a;
		System.out.println(a);
		System.out.println(j);
		System.out.println(b);
//		for(int i = 0; i<Character.MAX_VALUE; i ++) {
//			System.out.print((char) i);
//			System.out.print(" ");
//		}
//		System.out.print(" --end");
	}

}

class Overload {
	/**
	 * 同一类里
	 * 方法名相同，参数不同视为重载
	 * 方法名相同，参数相同视为重复
	 * 重载与返回类型，访问修饰符都无关
	 */
	public void f()
	{
		System.out.println("no parameter, return void");
	}
	
//	public int f(int i)
//	{
//		System.out.println("int parameter, return int");
//		return i;
//	}
	
    public int f(float i)
	{
		System.out.println("float parameter, return int");
		return (int) i;
	}
	
    public int f(double i)
	{
		System.out.println("double parameter, return int");
		return (int) i;
	}
	
//	public void f(int i, int j)
//	{
//		System.out.println("int parameters, return void");
//	}
	
	public void f(float i, double j)
	{
		System.out.println("float, double parameters, return void");
	}
}
