package cn.zmy.string;

public class Atoi {
	public int myAtoi(String str) {
        String res = "";
        int start = -1;
        int end = -1;
        boolean flag = true;
        String sign = "";//初始默认为正数
        for(int i = 0; i<str.length();i++) {
        	if(str.charAt(i) == ' ' && flag){
        		continue;
        	} else {
        		flag = false;
        		// 符号位可选
                if((str.charAt(i)=='+' || str.charAt(i)=='-') 
                		&& sign == ""){            	
                	sign = String.valueOf(str.charAt(i));
                	continue;
                }
                if(str.charAt(i) >= (int)'0' 
                		&& str.charAt(i) <= (int)'9'){
                	// 去除非有效0
                	if (str.charAt(i) == '0' && start == -1) {
                        continue;
                    }
                    if(start == -1) {
                        start = i;
                    }
                    end = i;
                    if(end - start > 9){
                        break;
                    }
                } else {
                	break;
                }
        	}
        }
        if (start != -1) {
        	res = sign + str.substring(start,end+1);
        }
        if(res == "") return 0;
        if(Long.parseLong(res) > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if(Long.parseLong(res) < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return Integer.parseInt(res);
    }
	
	public static void main(String[] args) {
//		System.out.println(new Atoi().myAtoi("    +-2"));
		String str = "    +-2     ";
		str = str.trim();
		System.out.println(str);
	}
}
