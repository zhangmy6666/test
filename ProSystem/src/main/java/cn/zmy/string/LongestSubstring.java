package cn.zmy.string;

public class LongestSubstring {
	public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int index =0;
        int max = 0;
        while(index<s.length()){
            String sub = s.substring(start,index);
            char ele = s.charAt(index);
            if(sub.indexOf(ele)!=-1){
                start = s.lastIndexOf(ele, index-1) + 1;
            } 
            if (index -start + 1>max) {
                max = index - start + 1;
            }
            index++;
        }
        
        return max;
    }
	
	public static void main(String[] args) {
		char a = '1';
		char b = '1';
		System.out.println(a==b);
	}

}
