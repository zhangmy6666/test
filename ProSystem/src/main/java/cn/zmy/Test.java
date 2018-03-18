package cn.zmy;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class Test {
	class Person {
		String name;

		public Person(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}

	public static void main(String[] args) {
		Integer i =10;
		String s = "ssss";
		Person p = new Test().new Person("haha");
		add(i);
		change(p);
		change(s);
		// 因为Integer是不可变对象,不能直接修改Integer的值,
		// 只能生成一个新的对象然后指向它.这样函数里面的临时变量和传入的变量值(引用)已经不一样了
//		System.out.println(i);
//		System.out.println(p.getName());
//		System.out.println(s);
		
		System.out.println(new Test().getClassPath());
	}
	
	private String getClassPath() {
		
		return this.getClass().getResource("").getPath();
	}

	private static void change(String s) {
		s = s + "aaaa";
		
	}

	private static void change(Person p) {
		p.setName("hehe");
	}

	private static void add(Integer i) {
		i ++;		
	}

	public static JSONObject parseObject(String json)
	{
		// 参数为空的时候 默认检查成功
		if (StringUtils.isNotBlank(json))
		{
			try
			{
				return JSON.parseObject(json);
			}
			catch (Exception e)
			{
			}
		}
		
		return new JSONObject();
	}
}
