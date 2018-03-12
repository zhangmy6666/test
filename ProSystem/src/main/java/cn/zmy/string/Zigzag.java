package cn.zmy.string;

public class Zigzag {
	public String convert(String s, int numRows) {
        String res = "";
        if (numRows == 1) {
            res = s;
        } else{
            String[] sa = new String[numRows];
            boolean flag = true;
            int index = 0;
            for(int i = 0;i<s.length();i++){
            	if(sa[index] == null){
            		sa[index] = "";
            	}
                sa[index]+=String.valueOf(s.charAt(i));
                if(flag) {
                    index++;
                    if(index==numRows-1){
                        flag=false;
                    }
                }else{
                    index--;
                    if(index==0){
                        flag=true;
                    }
                }
            }

            for(int j= 0;j<sa.length;j++){
                if(sa[j]!=null){
                	res+=sa[j];
                }
                
            }
        }
        
        
        return res;
    }
	
	public static void main(String[] args) {
		System.out.println(new Zigzag().convert("A", 2));
	}

}
