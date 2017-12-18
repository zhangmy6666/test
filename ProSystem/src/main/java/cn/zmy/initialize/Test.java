package cn.zmy.initialize;

import java.util.Random;

public class Test {
	// 初始化代码 在构造器调用前执行
	// 直接默认值和初始化块合并属于初始化代码，而构造器不属于初始化代码
	// 多个初始化代码，按顺序执行
	{ a = 6; }  // 初始化块
	private int a = 9;  
	private int b = 10;  //直接默认值
	{ a = 7; b = 11; }
	public String str = "abc"; 
	
	Test() {
	}
	
	Test(String s) {
		// 覆盖初始化代码
		this.str = s;
	}
	// 成员变量可以不用初始化，保证会有初始值。
	int i;
	
	void print() {
		System.out.println(i);
	}
	
	final class Empty{
		
	}
	
	class Te{
		Empty e ; //可以组合 不可以被继承
	}
	
	public static void main(String[] args) {
		// 方法内的局部变量必须初始化；
//		int i;
//		System.out.println(i);
//		Test tt = new Test();
//		tt.print();
//		
//		System.out.println(new Random(47).nextInt(20));
//		
//		Test test = new Test();
//		System.out.println(test.str);
//		Test test2 = new Test("xyz");
//		System.out.println(test2.str);
//		System.out.println("b===" + test.b);
//		System.out.println("a===" + test.a);
		
		// 一个对象初始化时一定要先保证其父类部分初始化再初始化自己的，组合不是如此
		// 加载类也是先加载父类
//		new C();
		// 不会加载子类
//		new B();
		new D();
	}

}

class B extends A {  
    static { System.out.println("static B"); }  
    { System.out.println("B"); }  
    public B() { System.out.println("cons B"); }  
}

class A {  
	// 静态初始化代码会在类第一次加载时执行，会在所有对象创建之前执行，并且只执行一次；
    static { System.out.println("static A"); }  
    { System.out.println("A"); }  
    public A() { System.out.println("cons A"); }  
}

class D {
	A a = new A(); // 组合 非继承
	static { System.out.println("static D"); }  
	{ System.out.println("D"); }
	public D() {System.out.println("cons D");}
	A a1 = new A(); // 变量的初始化 即使写在构造器后面，也是在任何方法调用之前初始化
}
  
class C extends B {  
    static { System.out.println("static C"); }  
    { System.out.println("C"); }  
    public C() { System.out.println("cons C"); }  
}  
