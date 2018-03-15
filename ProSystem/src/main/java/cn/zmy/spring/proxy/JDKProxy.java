package cn.zmy.spring.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy; 

/**
 * 通过实现 InvocationHandler 接口创建自己的调用处理器；
 * @author zhang
 *
 */
public class JDKProxy implements InvocationHandler {

	private Object targetObject;// 需要代理的目标对象

	public Object newProxy(Object targetObject) {// 将目标对象传入进行代理
		this.targetObject = targetObject;
		/**
		 * 通过为 Proxy 类指定 ClassLoader 对象和一组 interface 来创建动态代理类；
		 */
		ClassLoader p1 = targetObject.getClass().getClassLoader();
		Class<?>[] p2 = targetObject.getClass().getInterfaces();
		return Proxy.newProxyInstance(p1, p2, this);// 返回代理对象
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		checkPopedom();    //一般我们进行逻辑处理的函数比如这个地方是模拟检查权限    
        Object ret = null;      // 设置方法的返回值    
        ret  = method.invoke(targetObject, args);       //调用invoke方法，ret存储该方法的返回值    
        return ret;
	}

	private void checkPopedom() {//模拟检查权限的例子    
        System.out.println(".:检查权限  checkPopedom()!---jdk");    
    }
}
