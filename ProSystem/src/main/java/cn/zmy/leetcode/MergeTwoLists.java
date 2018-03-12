package cn.zmy.leetcode;

/**
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * 
 * @author zhang
 *
 *Input: 1->2->4, 1->3->4   Output: 1->1->2->3->4->4
 *
 *
 */
public class MergeTwoLists {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	/**
	 * 难点在于我不知道如何返回第一个元素
	 * 
	 * 
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {		
//		ListNode result = new ListNode(0);
//		
//		while (l1 != null && l2 != null) {
//			if (l1.val <= l2.val)
//			{
//				result.val = l1.val;
//				l1 = l1.next;
//			} else {
//				result.val = l2.val;
//				l2 = l2.next;
//			}
//			if (l1 != null || l2 != null) {
//				result.next = result;
//			} else {
//				result.next = null;
//			}
//		}
//		
//		while (l1 != null) {
//			result.val = l1.val;
//			l1 = l1.next;
//			if (l1 != null) {
//				result.next = result.next.next;
//			}else {
//				result.next = null;
//			}
//		}
//		
//		while (l2 != null) {
//			result.val = l2.val;			
//			l2 = l2.next;
//			if (l1 != null) {
//				result.next = result.next.next;
//			}else {
//				result.next = null;
//			}
//		}
//		return result;
		
//		if (l1 == null) {
//            return l2;
//        }
//        else if (l2 == null) {
//            return l1;
//        }
//        else if (l1.val < l2.val) {
//            l1.next = mergeTwoLists(l1.next, l2);
//            return l1;
//        }
//        else {
//            l2.next = mergeTwoLists(l1, l2.next);
//            return l2;
//        }
		
		ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        
        // exactly one of l1 and l2 can be non-null at this point, so connect
        // the non-null list to the end of the merged list.
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
	}

	public static void main(String[] args) {
		MergeTwoLists test = new MergeTwoLists();
		ListNode l1 = test.new ListNode(1);
		l1.next = test.new ListNode(3);
		l1.next.next = test.new ListNode(6);
		ListNode l2 = test.new ListNode(2);
		l2.next = test.new ListNode(4);
		l2.next.next= test.new ListNode(5);
		l2.next.next.next= test.new ListNode(7);
		
		ListNode obj = test.mergeTwoLists(l1, l2);
		while (obj != null) {
			System.out.println(obj.val);
			obj = obj.next;
		}

	}

}
