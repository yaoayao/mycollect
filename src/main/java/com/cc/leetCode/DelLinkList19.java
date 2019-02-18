package com.cc.leetCode;

/**
 * 删除倒数第n个节点 并返回头结点
 */
public class DelLinkList19 {
    /**
     * 双遍历
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode headReserve = new ListNode(head.val);
        //reserve
        while (head.next != null) {
            ListNode newNode = new ListNode(head.next.val);
            newNode.next = headReserve;
            headReserve = newNode;
            head = head.next;
        }
        if (n == 1) {
            headReserve = headReserve.next;
        }
        if (headReserve == null) return null;
        ListNode result = new ListNode(headReserve.val);
        while (headReserve.next != null) {
            if (n != 2) {
                ListNode newNode = new ListNode(headReserve.next.val);
                newNode.next = result;
                result = newNode;
            }
            headReserve = headReserve.next;
            n--;
        }
        return result;


    }

    /**
     * 遍历一遍
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;
        while (n > 0 && second != null){
            second = second.next;
            n--;
        }
        //连表长度等于给定数量
        if (second == null && n == 0){
            return head.next;
        }else if (second == null && n > 0){
            return head;
        }
        while (second.next != null){
            first = first.next;
            second = second.next;
        }
        first.next = first.next.next;
        return head;
    }

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);
//        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        ListNode listNode5 = removeNthFromEnd1(listNode, 1);
        System.out.println("");
    }
}
