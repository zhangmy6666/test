package cn.zmy.base;

public class BitTest {
	public static void main(String[] args) {
		int i = 10;
		int j = 12;
		int k = -5;
//		System.out.println(Integer.toBinaryString(i));
//		System.out.println(Integer.toBinaryString(j));
		System.out.println(Integer.toBinaryString(k));// 5 = 101 -1 100 取反 
//		System.out.println(i&j);
//		System.out.println(false & (1/0==1));//& 也可以做逻辑与功能，不短路
//		System.out.println(i|j);
		System.out.println(i^k);
//		System.out.println(~i);
//		System.out.println(Integer.toBinaryString(~i));
		/**
		 * 正数右移，高位用0补，负数右移，高位用1补，
		 * 当负数使用无符号右移时，用0进行部位(自然而然的，就由负数变成了正数了)
		 * 正数或者负数左移，低位都是用0补。(自行测试)
		 */
//		System.out.println(i << 2);
//		System.out.println(Integer.toBinaryString(i << 2));
//		System.out.println(i >> 2);
//		System.out.println(Integer.toBinaryString(i >> 2));
//		System.out.println(i >>> 2);
//		System.out.println(Integer.toBinaryString(i >>> 2));
//		System.out.println(k << 2);
//		System.out.println(Integer.toBinaryString(k << 2));
//		System.out.println(k >> 2);
//		System.out.println(Integer.toBinaryString(k >> 2));
//		System.out.println(k >>> 2);
//		System.out.println(Integer.toBinaryString(k >>> 2));
	}

}
