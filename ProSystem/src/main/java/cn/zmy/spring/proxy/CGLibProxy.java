package cn.zmy.spring.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * java动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
 * 而cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
 * 
 * 1、如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP 
 * 2、如果目标对象实现了接口，可以强制使用CGLIB实现AOP 
 * 3、如果目标对象没有实现了接口，必须采用CGLIB库，spring会自动在JDK动态代理和CGLIB之间转换
 * @author zhang
 *
 */
public class CGLibProxy implements MethodInterceptor {

	private Object targetObject;// CGLib需要代理的目标对象

	public Object createProxyObject(Object obj) {
		this.targetObject = obj;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(obj.getClass());
		enhancer.setCallback(this);
		/**
		 * cglib封装了asm，可以在运行期动态生成新的class。
		 */
		Object proxyObj = enhancer.create();
		return proxyObj;// 返回代理对象
	}

	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		Object obj = null;
		checkPopedom();// 检查权限
		obj = method.invoke(targetObject, args);
		return obj;
	}

	private void checkPopedom() {//模拟检查权限的例子    
        System.out.println(".:检查权限  checkPopedom()!---cglib");    
    }
}
