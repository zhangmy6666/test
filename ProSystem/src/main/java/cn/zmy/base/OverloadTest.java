package cn.zmy.base;

public class OverloadTest {
	public static void main(String[] args) {
		Overload over = new Overload();
		over.f();
		over.f(0);
		over.f(1, 2);
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
	
	public int f(int i)
	{
		System.out.println("int parameter, return int");
		return i;
	}
	
	private int f(double i)
	{
		System.out.println("double parameter, return int");
		return (int) i;
	}
	
	public void f(int i, int j)
	{
		System.out.println("int parameters, return void");
	}
}
