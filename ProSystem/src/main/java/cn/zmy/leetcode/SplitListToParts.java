package cn.zmy.leetcode;

import cn.zmy.leetcode.MergeTwoLists.ListNode;

public class SplitListToParts {
	public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] result = new ListNode[k];
        if(root != null) {
            ListNode move = root;
            int i=0;//原链表的长度
            while(move != null) {
                move = move.next;
                i ++;
            }
            int num = 0;//最少链表元素
            int j = i-num*k;//前j个 链表元素+1
            if (i>=k) {
                num = i/k;
                j = i-num*k;
            } else{
                num = 1;
                j = k-i;
            }
            for(int m=0;m<j;m++){
                result[m]= move;
                for(int n=0;n<num+1;n++){
                    move = move.next;
                }
            }
            for(int m=j;m<k;m++){
                result[m]= move;
                if (move != null) {
                    for(int n=0;n<num;n++){
                        move = move.next;
                    }
                }
            }
        } else {
            for(int i = 0; i<k; i++) {
                result[i] = root;
            }
        }
        return result;
    }
	
	 public String addBinary(String a, String b) {
        String c = Integer.parseInt(a) + Integer.parseInt(b) + "";
        char[] arr = c.toCharArray();
        int carry =0;
        for (int i = arr.length-1; i>=0;i--){
        	System.out.println(arr[i]);
            arr[i] += carry;
            System.out.println(arr[i]);
            System.out.println(arr[i] - 2);
            if(arr[i] > 2){
                arr[i]-=2;
                carry = 1;
            } else {
                carry = 0;
            }
        }
        String result="";
        if(carry >0)
        {
            result="1"+String.valueOf(arr);
        } else {
            result=String.valueOf(arr);
        }
        return result;        
        
    }
	 
	 public static void main(String[] args) {
		System.out.println(new SplitListToParts().addBinary("0", "0"));
	}
}
