package cn.zmy.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 * 
 * @author zhang
 *
 */
public class MajorityElement {

	public int majorityElement(int[] nums) {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>(Math.max((int)(nums.length/0.75f), 17));
		for (int i :nums) {
			if (map.get(i) == null) map.put(i, 0);
			int no = map.get(i);
			map.put(i,  ++ no);
			if (no > nums.length /2) return i;
		}
		
		return 0;
	}
	
	public int majorityElement1(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
	
	public static void main(String[] args) {
		int[] nums1 = {1,1,2,2,2};
		System.out.println(new MajorityElement().majorityElement(nums1));
//		System.out.println(new MajorityElement().majorityElement(new int[]{1,1,2,2,2,1}));
		System.out.println(new MajorityElement().majorityElement(new int[]{1,1,2,2,2,1,1}));
//		System.out.println(new MajorityElement().majorityElement(new int[]{}));
		
		System.out.println(new MajorityElement().majorityElement1(nums1));
//		System.out.println(new MajorityElement().majorityElement1(new int[]{1,1,2,2,2,1}));
		System.out.println(new MajorityElement().majorityElement1(new int[]{1,1,2,2,1,1,2}));
//		System.out.println(new MajorityElement().majorityElement1(new int[]{}));
	}
}
