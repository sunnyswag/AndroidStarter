package com.example.kotlinstarter.viewmodel;

public class Algorithm {

    public static class ListNode {

        public ListNode next;
        public int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 两数相加
     两个链表分别表示两个非负的整数，它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

     请你将两个数相加，并以相同形式返回一个表示和的链表。

     你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

     输入：l1 = [2,4,3], l2 = [5,6,4]
     输出：[7,0,8]
     解释：342 + 465 = 807.
     */
    public static ListNode plusTowNum(ListNode list1, ListNode list2) {
        ListNode cur = new ListNode(0);
        ListNode result = cur;

        int sub = 0;

        while (list1 != null || list2 != null) {
            int val1 = list1 == null ? 0 : list1.val;
            int val2 = list2 == null ? 0 : list2.val;

            int total = val1 + val2 + sub;
            sub = total / 10;

            cur.next = new ListNode(total % 10);
            cur = cur.next;

            if (list1 != null) {
                list1 = list1.next;
            }

            if (list2 != null) {
                list2 = list2.next;
            }
        }

        // sub is not zero
        if (sub != 0) {
            cur.next = new ListNode(sub);
        }

        return result.next;
    }
}
