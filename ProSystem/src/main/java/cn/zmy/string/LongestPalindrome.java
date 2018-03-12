package cn.zmy.string;

public class LongestPalindrome {
	public String longestPalindrome(String s) {
		int index = 0;
        String res = "";
        while(index < s.length()) {
            char item = s.charAt(index);
            // single character is Palindrome.
            if(res.length() == 0) {
        		res = String.valueOf(item);
        	}
            int start1 = index;
            int start2 = index;
            int end1 = index + 1;
            int end2 = index + 2;
            if (end2<s.length() && s.charAt(start2)==s.charAt(end2)) {
            	while(start2>=0 && end2<s.length() && s.charAt(start2)==s.charAt(end2)){
                	if (end2+1-start2 > res.length())
                	{
                		res = s.substring(start2,end2+1);
                	}
                	
                	start2--;
                	end2++;
                }
                
            } 
            if (end1<s.length() && s.charAt(start1)==s.charAt(end1)) {                
            	while(start1>=0 && end1<s.length() && s.charAt(start1)==s.charAt(end1)){
                	if (end1+1-start1 > res.length())
                	{
                		res = s.substring(start1,end1+1);
                	}
                	
                	start1--;
                	end1++;
                }
            }
            
            index++;
        }
        return res;
        
    }
	
	public boolean isPalindrome(String s){
		String left = s;
		String right = "";
		int i = s.length();
		boolean res = false;
		while(left.length()-right.length()>1){
			left = s.substring(0,i-1);
			right = right + s.substring(i-1,i);
			i--;
		}
		if (left.equals(right) || left.startsWith(right))
		{
			res= true;
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(new LongestPalindrome().longestPalindrome("aaaa"));
		
	}
}
