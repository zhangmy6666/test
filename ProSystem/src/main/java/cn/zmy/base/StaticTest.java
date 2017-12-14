package cn.zmy.base;

/**
 * 静态方法不能直接访问非静态成员，类直接访问
 * final 不可变/不被继承,与static配合使用 常量
 *
 */
public class StaticTest {
	class Innerclass {
		// 非静态内部类不能有静态变量，可以有常量
		// TODO 内部类，加载顺序
		static final int j = 0;
		void funInner(){
			System.out.println("haha");
		}
	}

	int i = 0;
	// 常量，值只能够分配一次，不能更改；可以同static一起使用，避免对类的每个实例维护一个拷贝
	final int finalInt = 11;
	// 可以被类的所有实例共享。StaticTest.name即可访问 不需要实例化.值可以改变
	static String name = "lily";

	/**
	 * 匿名内部类 一般情况下是不可以用static修饰类的。如果一定要用static修饰类的话，通常static修饰的是匿名内部类。
	 */
	static class TestStatic {
		int innerI = 0;
		static int m = 0;

		// 不能访问非静态的外围类对象。 这是由Java语法中"静态方法不能直接访问非静态成员"所限定
		public int display() {
			m = 0;
			// System.out.println(i);
			System.out.println(name);
			System.out.println("innerI ==" + innerI);
			m++;
			name = name + ",hello";
			return m;
		}
	}

	public static void main(String[] args) {
		// Cannot instantiate the type StaticTest.TestAb.
		// 如果要使用abstract类，之前必须首先建一个继承abstract类的新类。
		// TestAb testAb = new TestAb();
		TestAbInst testAbInst = new TestAbInst();
		testAbInst.fun();
		Test test = new Test();
		System.out.println("final test ==" + test.fun1());

		//静态内部类
		TestStatic ts = new TestStatic();
		ts.display();
		System.out.println("m ===" + ts.display());
		System.out.println("name == " + name);

		// 非静态内部类
		new StaticTest().new Innerclass().funInner();
		
	}
}

/**
 * 类只要有一个abstract方法，类就必须定义为abstract，但abstract类不一定非要包含abstract方法不可
 * 
 * TODO 抽象类和接口对比 
 *
 */
abstract class TestAb {
	void fun() {
		System.out.println("Abstract class can have non-abstract method.");
	}

	abstract int funAb();//可有可无
	
	public final void funF() {
		System.out.println("final method cannot be override.");
	}
}

class TestAbInst extends TestAb {

	// 抽象父类的抽象方法需要实现 
	@Override
	int funAb() {
		return 0;
	}
	
	void fun() {
		System.out.println("I want to print my info.");
	}
	
//	void funF() {
//		
//	}

}

/**
 * final的类不能被继承 The type Test1 cannot subclass the final class StaticTest.Test
 */
final class Test {
	int k = 0;

	int fun1() {
		return ++k;
	}

}
