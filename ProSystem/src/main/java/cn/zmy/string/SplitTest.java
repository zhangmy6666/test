package cn.zmy.string;

public class SplitTest {
	public static void main(String[] args) {
		String a  = "1|2|{\"topic\":\"3|4\"}";
		String[] arr = a.split("\\|");// |, ., &, *, + 是转义字符, 要加\\
		for (int i = 0; i < arr.length; i++)
		{
			System.out.println(arr[i]);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < arr.length; i++)
		{
			sb.append(arr[i]);
		}
		System.out.println(sb.toString());
	}

}
