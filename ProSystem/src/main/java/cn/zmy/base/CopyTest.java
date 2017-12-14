package cn.zmy.base;

import cn.zmy.util.StringUtil;

public class CopyTest {
	public static void main(String[] args) {
		int i = 1;
		int j = i;
		i = 2;
		System.out.println("j = " + j);
		System.out.println(i == j);
		
		String a = "123";
		String b = a;
		a = "234"; // a指向一个新的对象
		System.out.println("b = " + b);
		System.out.println(a == b);
		
		String[] arr = new String[]{"1","2","3"};
		String[] brr = arr;// 指向内存堆的同一个对象
		arr[1] = "4";
		System.out.println(StringUtil.join(brr, ","));
		System.out.println(arr == brr);
		
		Car bmw = new Car(1, "benz");
		Car benz = bmw;
		Car audi = new Car(); // 开辟一个新的内存空间
		audi.setId(bmw.getId());
		audi.setName(bmw.getName());
		bmw.setName("bmw");
		System.out.println(benz.toString());
		System.out.println(audi.toString());
		
		Employee employee = new Employee();
		employee.age = 100;
		employee.changeEmployee(employee);
		System.out.println(employee.age);
		employee.change(employee);
		System.out.println(employee.age);
	}

}

class Car{
	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + "]";
	}
	public Car(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Car() {
		// TODO Auto-generated constructor stub
	}
	private int id;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}

class Employee {
	public int age;
	
	public void changeEmployee(Employee employee)
	{
		employee = new Employee();
		employee.age = 1000;
	}
	
	public void change(Employee employee)
	{
//		employee = new Employee();
		employee.age = 99;
	}
}


