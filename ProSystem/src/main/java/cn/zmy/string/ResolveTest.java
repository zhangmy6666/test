package cn.zmy.string;

import cn.zmy.util.StringUtil;

/**
 * 按要求分解字符串，N代表输出的每串字符串的位数，不够补0。
 * 例如：输入8， “abc” ，“123456789”，则输出为“abc00000”,“12345678“,”90000000”
 * @author zhang
 *
 */
public class ResolveTest {
	public String[] getResolveString(String str, int n) {
		int length = str.length();
		int arrNum = 0;
		if (length % n == 0) {
			arrNum = length/n;
		} else {
			arrNum = length/n + 1;
		}
		String[] arr = new String[arrNum];
		for (int i = 0;i<arr.length;i++) {
			if ((i+1)*n>length){
				arr[i] = str.substring(i*n);
				if(arr[i].length() < n) {
					StringBuilder sb = new StringBuilder();
					sb.append(arr[i]);
					for(int k = 0;k<n-arr[i].length();k++) {
						sb.append("0");
					}
					arr[i] = sb.toString();
				}
			} else {
				arr[i] = str.substring(i*n,(i+1)*n);
			}
			
		}
		return arr;
	}
	
	public static void main(String[] args) {
		ResolveTest test = new ResolveTest();
		System.out.println(StringUtil.join(test.getResolveString("abc", 8), ","));
		System.out.println(StringUtil.join(test.getResolveString("123456789", 8), ","));
		String[] arr = {"0","1","2"};
		System.out.println(StringUtil.join(arr));
	}

}
