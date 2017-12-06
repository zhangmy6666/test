package cn.zmy.string;

/**
 * 如果一个字符串可以由某个长度为k的字符串重复多次得到，我们说该串以k为周期。
 * 例如，abcabcabcabc以3为周期（注意，它也可以6和12为周期,结果取最小周期3）。
 * @author zhang
 *
 */
public class GetMinPeriodTest {
	int getMinPeriod(String str) {
		char[] arr = str.toCharArray();		
		for (int i = 1; i < arr.length; i++) {
			boolean flag = true;
			if (arr[i] == arr[0]) {
				for (int j = 2;j <= arr.length/i; j++) {
					if (!str.substring(0, i).equals(str.substring((j-1)*i, j*i))) {
						flag = false;
						break;
					}					
				}
				if (flag) {
					return i;
				}
				
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		GetMinPeriodTest test = new GetMinPeriodTest();
		String a = "abcabcbcabcabcbc";
		System.out.println(test.getMinPeriod(a));
	}

}
