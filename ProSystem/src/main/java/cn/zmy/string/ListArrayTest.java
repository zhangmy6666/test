package cn.zmy.string;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListArrayTest {
	public static void main(String[] args) {
		String a = "bbc";
		//字符串的length方法，String是类，不会直接访问到属性，用get方法调用
		System.out.println(a.length());		
		
		//---------------list-----------------
		/*list与 set 不同，列表通常允许重复的元素。*/
		String b = "bbc";
		/*ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。 
		 * 对于随机访问get和set，ArrayList觉得优于LinkedList，
		 * 因为LinkedList要移动指针。 LinkedList不支持高效的随机元素访问。
		 * 对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。*/
		List<String> list = new ArrayList<String>();
		// add 允许值重复
		list.add(a);
		list.add(b);
		list.add(0,"aaa");
		// 允许空值
		list.add(null);
		System.out.println(list.toString());
		// size 列表长度
		System.out.println(list.size());
		// toArray()方法转换成数组 与Arrays.asList
		System.out.println(list.toArray()[0]);
		// set
		list.set(1, "abc");
		System.out.println(list);
		a = "ccc";
		list.add(a);
		System.out.println(list);
		// iterator迭代
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String str = it.next();
			System.out.print(str + " ");
		}
		// 区别于iterator 有add set 向前遍历
		ListIterator<String> lit = list.listIterator(list.size());
		while(lit.hasPrevious())
		{
			System.out.print(lit.previous() + " ");
		}
		System.out.println();
		System.out.println(list);
		
		
		// --------------- String[]-----------------
		/*数组对象并不是从某个类实例化来的，而是由JVM直接创建的，
		因此查看类名的时候会发现是很奇怪的类似于"[I"这样的样子，
		这个直接创建的对象的父类就是Object，
		所以可以调用Object中的所有方法，包括toString()。*/
		String[] arr = list.toArray(new String[list.size()]);
	}
	

}
