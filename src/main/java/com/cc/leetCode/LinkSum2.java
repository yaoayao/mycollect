package com.cc.leetCode;

/**
 * 第二题 两数相加
 *
 */
public class LinkSum2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int i = 0;
        ListNode now = null;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + i;
            i = sum / 10;
            sum = sum % 10;
            ListNode node = new ListNode(sum);
            if (now == null) {
                now = node;
            }
            node.next = now.next;
            now.next = node;
            now = node;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + i;
            i = sum / 10;
            sum = sum % 10;
            ListNode node = new ListNode(sum);

            if (now == null) {
                now = node;
            }
            node.next = now.next;
            now.next = node;
            now = node;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + i;
            i = sum / 10;
            sum = sum % 10;
            ListNode node = new ListNode(sum);
            if (now == null) {
                now = node;
            }
            node.next = now.next;
            now.next = node;
            now = node;
            l2 = l2.next;
        }
        if (i == 1) {
            ListNode last = new ListNode(1);
            l1 = now.next;
            now.next = last;
            return l1;
        } else {
            l1 = now.next;
            now.next = null;
            return l1;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
