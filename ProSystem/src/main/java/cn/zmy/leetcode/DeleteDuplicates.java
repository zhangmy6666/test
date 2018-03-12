package cn.zmy.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 * 
 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return 1->2->3.
 * 
 * @author zhang
 *
 */
public class DeleteDuplicates {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode deleteDuplicates(ListNode head) {
		if (head != null) {
			ListNode pre = head;
			ListNode res = head.next;
			while (res != null) {
				if (res.val == pre.val) {
					res = res.next;
					pre.next = res;
				} else {
					pre = pre.next;
					res = res.next;
				}
			}
		}

		return head;
	}

	public boolean hasCycle(ListNode head) {		
		if (head != null) {
			ListNode res = head.next;
			while (res != null) {
				if (res.val == head.val) {
					ListNode pre = head;
					ListNode node = res;
					while (node != null && node.val == pre.val) {
						node = node.next;
						pre = pre.next;						
					}
					if (node == null) {
						return true;
					}
					
				}
				res = res.next;
			}
			
		}
		return false;

	}
	
	public boolean hasCycle1(ListNode head) {
	    Set<ListNode> nodesSeen = new HashSet<>();
	    while (head != null) {
	        if (nodesSeen.contains(head)) {
	            return true;
	        } else {
	            nodesSeen.add(head);
	        }
	        head = head.next;
	    }
	    return false;
	}
	
	public boolean hasCycle2(ListNode head) {
	    if (head == null || head.next == null) {
	        return false;
	    }
	    ListNode slow = head;
	    ListNode fast = head.next;
	    while (slow != fast) {
	        if (fast == null || fast.next == null) {
	            return false;
	        }
	        slow = slow.next;
	        fast = fast.next.next;
	    }
	    return true;
	}

	public static void main(String[] args) {
		DeleteDuplicates test = new DeleteDuplicates();
		// ListNode l1 = null;
		ListNode l1 = test.new ListNode(3);
		l1.next = test.new ListNode(2);
		l1.next.next = test.new ListNode(3);
		l1.next.next.next = test.new ListNode(2);
//		l1.next.next.next.next = test.new ListNode(5);
//		ListNode obj = test.deleteDuplicates(l1);
//		while (obj != null) {
//			System.out.print(obj.val + " ");
//			obj = obj.next;
//		}
		System.out.println(test.hasCycle(l1));
		System.out.println(test.hasCycle1(l1));
		System.out.println(test.hasCycle2(l1));
	}

}
