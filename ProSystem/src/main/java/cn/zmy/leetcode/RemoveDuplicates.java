package cn.zmy.leetcode;

/**
 * Given a sorted array, remove the duplicates in-place such that each element appear only once 
 * and return the new length. Do not allocate extra space for another array,
 * you must do this by modifying the input array in-place with O(1) extra memory.
 * 
 * Given nums = [1,1,2],
 * Your function should return length = 2, 
 * with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 * @author zhang
 *
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
    	int i =1;
    	int j =i;
    	while (j < nums.length) {
    		if (nums[j] != nums[i-1]) {
    			nums[i] = nums[j];
    			i ++;
    		} 
    		
    		j ++;
    	}    	
    	
    	return i;
        
    }

	public static void main(String[] args) {
		int[] nums = {1,2,2,2,4,5,6,7,7,8};
		RemoveDuplicates test = new RemoveDuplicates();
		System.out.println(test.removeDuplicates(nums));
		
		
	}

}
