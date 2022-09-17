package com.example.authur.server.template.nowcoder;

import java.util.Stack;


/**
 * 反转链表
 */
public class ReverseLinked {

    public ListNode ReverseList(ListNode head) {
        Stack<ListNode> s = new Stack<>();
        while (head != null) {
            s.push(head);
            head = head.next;
        }
        System.out.println(s);
        if (s.isEmpty()) return null;

        ListNode node = (ListNode)s.pop();
        ListNode temp = node;
        while (!s.isEmpty()) {
            node.next = s.pop();
            node = node.next;
        }

        System.out.println(temp);
        node.next = null;
        return temp;
    }

}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}