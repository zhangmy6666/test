package cn.zmy.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.zmy.util.StringUtil;

public class Apples {
	// 把m个相同苹果放在n个不同盘子里 不允许空
	//	5个苹果 4个盘子 输出：
	//	[2,1,1,1]
	//	[1,2,1,1]
	//	[1,1,2,1]
	//	[1,1,2,1]
	public List<Integer[]> disNoEmpty(int m, int n) {		
		List<Integer[]> result = new ArrayList<Integer[]>();
		if (m < n) {
			return null;
		} else if (m == n) {
			Integer[] arr = new Integer[n];
			for (int i = 0; i < n; i ++) {
				arr[i] = 1;
			}
			result.add(arr);
		} else {
			List<Integer[]> lastResult = disNoEmpty(m-1, n);
			for (int j = 0; j < lastResult.size(); j++) {
				Integer[] lastEle = lastResult.get(j);
				for (int k = 0; k < lastEle.length; k ++) {
					Integer[] ele = Arrays.copyOf(lastEle,lastEle.length);
					ele[k] = lastEle[k] + 1;
					result.add(ele);
					
				}
			}
		}
		return result;
	}
	
//	把m个同样的苹果放在n个同样的盘子里，允许有的盘子空着不放，
//	问有多少种不同的分法？(注：5,1,1和1,1,5是同一种分法)
	public List<Integer[]> disEmpty(int m, int n) {
		List<Integer[]> result = new ArrayList<Integer[]>();
		
		// 必须有空
		if (m < 0 || n < 0) {
			return null;
		} else if (m == 1) {
			for (int i = 0; i < n; i ++) {
				Integer[] arr = new Integer[n];
				for (int j = 0; j < n; j ++) {
					arr[j] = 0;
				}
				arr[i] = 1;
				result.add(arr);
			}			
		} 
		else {			
			List<Integer[]> lastResult = disEmpty(m-1,n);
			Iterator<Integer[]> iter = lastResult.iterator();
			while (iter.hasNext()) {
				Integer[] lastEle = iter.next();
				for (int k = 0; k < lastEle.length; k ++) {
					Integer[] ele = Arrays.copyOf(lastEle,lastEle.length);
					ele[k] = lastEle[k] + 1;
					int product = 1;
					for (int j = 0; j < ele.length; j ++) {
						product = product * ele[j];
					}
					if (product == 0) {
						result.add(ele);
					}							
				}
			}
			
		}
		return result;
	}
	
	public static void main(String[] args) {
		Apples app = new Apples();
		List<Integer[]> resultNotEmpty = app.disNoEmpty(4, 2);
		for (int j = 0; j < resultNotEmpty.size(); j++) {
			Integer[] e = resultNotEmpty.get(j);
			System.out.println(StringUtil.join(e, ","));
		}
		System.out.println();
//		List<Integer[]> emptyResult = app.disEmpty(3, 3);
//		List<Integer[]> result = new ArrayList<Integer[]>();
//		result.addAll(emptyResult);
//		result.addAll(resultNotEmpty);
//		Iterator<Integer[]> iter = result.iterator();
//		while (iter.hasNext()) {
//			Integer[] e = iter.next();
//			System.out.println(StringUtil.join(e, ","));
//		}
	}
}
