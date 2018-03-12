package cn.zmy.leetcode;

import cn.zmy.leetcode.MergeTwoLists.ListNode;

/**
 * Given a list, rotate the list to the right by k places, where k is
 * non-negative. Given 1->2->3->4->5->NULL and k = 2, return
 * 4->5->1->2->3->NULL.
 * 
 * @author zhang
 *
 */
public class RotateList {
	public ListNode rotateRight(ListNode head, int k) {

		ListNode move = head;
		if (move == null) {
			return null;
		}
		if (move.next == null) {
			return move;
		}
		int i = 1;
		while (move.next != null) {
			move = move.next;
			i++;
		}
		if (i > 1) {
			move.next = head;
			for (int j = 0; j < (i - k % i); j++) {
				move = move.next;
			}
			head = move.next;
			move.next = null;
		}

		return head;
	}

}
