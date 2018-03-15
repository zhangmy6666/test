package cn.zmy.spring.proxy;

import java.lang.reflect.Method;
import java.util.Date;

public class TimeHandler implements InvocationHandler {
	
	private Object target;

	public TimeHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public void invoke(Object o, Method m) {
		System.out.println("开始时间:" + new Date());
		try {
			m.invoke(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("结束时间:" + new Date());
	}

}
