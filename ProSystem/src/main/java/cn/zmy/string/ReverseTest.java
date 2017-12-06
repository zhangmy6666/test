package cn.zmy.string;

public class ReverseTest {
	public int reverse(int x) {
        String str = String.valueOf(x);
        String result = "";
        if (str.startsWith("-")){
            String newStr = str.substring(1);
            char[] arr = newStr.toCharArray();
            char temp = 0;
            for(int i = 0;i<=(arr.length-1)/2;i++) {
                temp = arr[i];
                arr[i] = arr[arr.length-i-1];
                arr[arr.length-i-1] = temp;
            }
            result="-"+String.valueOf(arr);
        } else {
//            while(str.startsWith("0")) {
//                str = str.substring(1);
//            }
            char[] arr = str.toCharArray();// String --> char[]
            char temp = 0;
            for(int i = 0;i<=(arr.length-1)/2;i++) {
                temp = arr[i];
                arr[i] = arr[arr.length-i-1];
                arr[arr.length-i-1] = temp;
            }
            result=String.valueOf(arr);// char[] --> String
        }
        int resultInt = 0;
        try {
        	resultInt = Integer.parseInt(result);
        } catch (Exception e) {
        	
        }
        
        return resultInt;   
    }
	
	public static void main(String[] args) {
		ReverseTest test = new ReverseTest();
		System.out.println(test.reverse(1534236469));
	}

}
