package com.cc.leetCode;

/**
 * 合并两个有序链表
 */
public class MergeTwoList21 {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode resultHead = null;
        if (l1 == null&& l2 ==null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val > l2.val) {
            result = l2;
            resultHead = result;
            l2 = l2.next;
        } else {
            result = l1;
            resultHead = result;
            l1 = l1.next;
        }
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                result.next = l2;
                l2 = l2.next;
            } else {
                result.next = l1;
                l1 = l1.next;
            }
            result = result.next;
        }
        if (l1 != null) {
            result.next = l1;
        } else  {
            result.next = l2;
        }
        return resultHead;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new  ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        ListNode l1istNode = new  ListNode(1);
        ListNode l1istNode1 = new ListNode(2);
        ListNode l1istNode2 = new ListNode(3);
        ListNode l1istNode3 = new ListNode(4);
        ListNode l1istNode4 = new ListNode(5);
        l1istNode.next = l1istNode1;
        l1istNode1.next = l1istNode2;
        l1istNode2.next = l1istNode3;
        l1istNode3.next = l1istNode4;
        ListNode listNode5 = mergeTwoLists(listNode,l1istNode);
        System.out.println("");
    }
}
