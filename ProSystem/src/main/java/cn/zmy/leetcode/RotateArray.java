package cn.zmy.leetcode;

import java.util.Arrays;

/**
 * Rotate an array of n elements to the right by k steps. For example, with n =
 * 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * 
 * @author zhang
 *
 */
public class RotateArray {
	public void rotate(int[] nums, int k) {
		int n = nums.length;
		k = k%n;
		int[] newNums = nums.clone();		
		for (int i=0;i <n;i++) {
			nums[i]=newNums[(n-k+i)%n];
		}
		
	}
	
	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5,6,7};
		new RotateArray().rotate(nums, 8);
		System.out.println(Arrays.toString(nums));
	}
}
