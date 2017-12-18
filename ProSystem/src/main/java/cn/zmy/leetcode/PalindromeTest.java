package cn.zmy.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Determine whether an integer is a palindrome. Do this without extra space
 * 0 is a palindrome
 * @author zhang
 *
 */
public class PalindromeTest {
	public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int x = Integer.parseInt(line);
            
            boolean ret = new Solution().isPalindrome(x);
            
            System.out.print(booleanToString(ret));
        }
	}
}

class Solution {
	public boolean isPalindrome(int x) {
		int y = 0;
		if (x!= 0 && x%10 == 0) {
            return false;
        }
		while (x > 0 && x > y) {
			y = y * 10 + x % 10;
			x = x / 10;
		}
		if (y == x || x == y / 10) {
			return true;
		}

		return false;
	}
}

class PrefixSolution {
    public String longestCommonPrefix(String[] strs) {
    	String result = "";
        if(strs.length > 0 ) {
        	for(int i = 0;i<strs[0].length();i++)
        	{
        		boolean flag = true;
        		char prefix = strs[0].charAt(i);
        		for(String str:strs) {           		
            		if (!str.startsWith(result + String.valueOf(prefix))){
            			flag = false;
            		}
            	}
        		if (flag) {
        			result += String.valueOf(prefix);
        		} else {
        			break;
        		}
        	}
        }
        return result;
    }
}
