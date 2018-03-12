package cn.zmy.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayInit {
	
	public ArrayInit(String str) {
		System.out.println("cons " + str);
	}

	public static void main(String[] args) {
//		ArrayInit[] test = new ArrayInit[10];
//		ArrayInit[] test1 = new ArrayInit[]{new ArrayInit("hello")};
		// 没有初始化时编译器不允许指定数组的大小 要为数组分配存储空间，必须初始化；初始化必须指定大小
		// a2是数组的引用 默认给引用分配了足够的空间
		// a, a1 引用的数组已初始化
//		int[10] a0;
//		int[] a = new int[10];
//		int[] a0 = new int[]{1,2,3,5}; // 维度和具体值只能择一
//		int[] a2; // 只定义未初始化
//		// 在没有数组的时候就定义了引用，因为可以将一个数组的引用赋给a3
//		System.out.println(a.length);
//		// 特殊初始化 只能在 定义的时候
//		int[] a1 = {1,2,3,4,5};
//		int[] a11 = {2,4,6,8};
////		a3 = {2,4,6,8}; // don't work
//		a2=a1;	// a1,a2是{1,2,3,4,5}这个数组的两个别名	
//		
//		/*数组对象并不是从某个类实例化来的，而是由JVM直接创建的，
//		因此查看类名的时候会发现是很奇怪的类似于"[I"这样的样子，
//		这个直接创建的对象的父类就是Object，
//		所以可以调用Object中的所有方法，包括toString()。*/
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		String[] arr = list.toArray(new String[list.size()]);
////		String[] arr1 = (String[]) list.toArray();// Object cannot be cast to String
//		Object[] arr2 = list.toArray();
		System.out.println(list);  // 数组可以直接打印
		System.out.println(arr);
		System.out.println(Arrays.toString(arr));
//		System.out.println(arr.length);
//		System.out.println(arr2.length);

//		for (int i = 0;i<a2.length;i++) {
//			a2[i] ++;  // 数组的长度不能修改
//		}
//		for (int i = 0;i<a1.length;i++) {
//			System.out.print(a1[i] + " ");
//		}
//		a2=a11; // a2修改了引用， 此时引用{2,4,6,8}
//		System.out.println();
//		for (int i = 0;i<a1.length;i++) {			
//			System.out.print(a1[i] + " ");
//		}
//		System.out.println();
//		for (int i = 0;i<a2.length;i++) {
//			System.out.print(a2[i] + " ");
//		}
		
	}

}
