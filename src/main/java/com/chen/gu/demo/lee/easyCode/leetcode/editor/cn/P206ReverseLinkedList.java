//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表

package com.chen.gu.demo.lee.easyCode.leetcode.editor.cn;

import com.chen.gu.demo.lee.easyCode.leetcode.editor.cn.pojo.ListNode;

//java:反转链表
public class P206ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new P206ReverseLinkedList().new Solution();

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        ListNode listNode = solution.reverseList1(node);
        System.out.println(listNode);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode cur = head;
            ListNode pre = null;

            while (cur != null){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }

        public ListNode reverseList1(ListNode head) {
            if (head == null || head.next == null){
                return head;
            }

            ListNode newHead = reverseList1(head.next);
            head.next.next = head;

            head.next = null;

            return newHead;
        }


    }
    //leetcode submit region end(Prohibit modification and deletion)

}