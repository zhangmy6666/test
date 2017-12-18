package cn.zmy.leetcode;

import java.util.Calendar;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 * 
 * Note: Given n will be a positive integer.
 * 
 * @author zhang
 *
 */
public class ClimbStairs {
	public int climbStairs(int n) {
		int result = 0;
		if (n == 1) {
			result = 1;
		} 
		if (n == 2) {
			result = 2;
		}
		if (n > 2) {
			result = climbStairs(n-1) + climbStairs(n-2);
		}
		
		return result;
	}
	
	public int climbStairsN(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }
    public int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }
    
    public int climbS(int n) {
    	if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
	
	public static void main(String[] args) {
		long start = Calendar.getInstance().getTimeInMillis();
		
		System.out.println(new ClimbStairs().climbStairsN(47));
		
		long end = Calendar.getInstance().getTimeInMillis();
		System.out.println(end - start);
		
//		System.out.println(new ClimbStairs().climbStairs(44));
		
		long end2 = Calendar.getInstance().getTimeInMillis();
		System.out.println(end2 - end);
		
		System.out.println(new ClimbStairs().climbS(47));
		
		long end3 = Calendar.getInstance().getTimeInMillis();
		System.out.println(end3 - end2);
	}

}
