package cn.zmy.leetcode;

import java.util.HashSet;
import java.util.Set;

import cn.zmy.leetcode.MergeTwoLists.ListNode;

public class GetIntersectionNode {
	
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		Set<ListNode> set = new HashSet<ListNode>();
		while (headA != null && headB != null) {
			if (set.contains(headA)) {
				return headA;
			} else {
				set.add(headA);
				headA = headA.next;
			}
			
			if (set.contains(headB)) {				
				return headB;
			} else {
				set.add(headB);
				headB = headB.next;
			}		
			
		}
		
		while (headA != null) {
			if (set.contains(headA)) {
				return headA;
			}else {
				set.add(headA);
				headA = headA.next;
			}
		}
		while (headB != null) {
			if (set.contains(headB)) {
				return headB;
			}else {
				set.add(headB);
				headB = headB.next;
			}
		}
		
		return null;
        
    }

}
