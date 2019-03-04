package com.cc.leetCode;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * 合并k个排序链表
 */
public class MergeKList23 {
    public static ListNode mergeKLists(ListNode[] lists) {
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 0; i < lists.length; i++) {
            ListNode list = lists[i];
            while (list != null){
                arrayList.add(list.val);
                list = list.next;
            }
        }
        Object[] objects = arrayList.toArray();
        Arrays.sort(objects);
        ListNode listNode = new ListNode(0);
        ListNode headNode = listNode;

        for (Object object : objects) {
            ListNode listNode1 = new ListNode((Integer) object);
            listNode.next = listNode1;
            listNode = listNode1;
        }
        return headNode.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[1];
        ListNode node1 = new ListNode(1);
        lists[0] = node1;
        mergeKLists(lists);
    }
}
