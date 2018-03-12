package cn.zmy.string;

public class SplitTest {
	public static void main(String[] args) {
		String a  = "1||2|{\"topic\":\"3 5|4\"}| |";
		String[] arr = a.split("\\|");// |, ., &, *, + 是转义字符, 要加\\
		for (int i = 0; i < arr.length; i++)
		{
			System.out.println(arr[i]);
		}
		String[] arr1 = a.split("\\|", 3);// 有limit限制
		for (int i = 0; i < arr1.length; i++)
		{
			System.out.println(arr1[i]);
		}
		
		String token = "e9ecb9c5533640d69bde-675a649cce1f";
    	System.out.println(token.split("_")[0]);
	}

}
