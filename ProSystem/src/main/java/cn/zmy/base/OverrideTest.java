package cn.zmy.base;

import java.io.IOException;
/**
 * 重写方法只能存在于具有继承关系中，重写方法只能重写父类非私有的方法。
 * @author zhang
 *
 */
public class OverrideTest {
	public static void main(String[] args) {
		Child child = new Child();
		child.f(1);
	}
	

}

class Father {
	/**
	 * 没有访问修饰符，默认default，同一个package的类可以访问
	 * protected 同一个package，子类可访问
	 */
    void f(int i){
    	System.out.println("I am baba");
	}
	
    /*方法名相同，参数相同，视为方法重复*/
//	public int f(int i) {
//		return i;
//	}
}

class Child extends Father{
	/**
	    一般来说，@Override写与不写没什么区别，JVM可以自识别 
	    写的情况下：即说明子类要覆盖基类的方法，基类必须存在方法 
	                        （控制类型不小于父类，返回值，参数列表类型,方法名）与子类方法完成一致的方法，否则会报错（找不到被Override的方法）。 
	    在不写@Override注解的情况下，当基类存在与子类各种条件都符合的方法是即实现覆盖； 
	    如果条件不符合时，则是当成新定义的方法使用。 
	    所以如果想覆盖基类方法时，最好还是写上@Override注解，这样有利于编译器帮助检查错误 
	 * @throws IOException 
	*/
	@Override
	/**
	 * 重写的理解：
	 * 方法名，参数，返回类型一致;子类的访问修饰权限不能小于父类
	 * 
	 * 当父类的方法被定义为final，private 该方法将不能被子类重写
	 * 重写方法一定不能抛出新的检查异常或者比被重写方法申明更加宽泛的检查型异常
	 */
	public void f(int i) /*throws Exception*/ {
		System.out.println("I am son");
//		throw new IOException();
	}
}
