package com.example.demo1.leetcode.array;

/*
网页链接
 */
public class ArrowOffer24 {

  public ListNode reverseList(ListNode head) {

    ListNode pre = head;
    ListNode cur = null;
    ListNode node = null;
    while (pre != null) {
      node = pre.next;

      pre.next = cur;

      cur = pre;
      pre = node;
    }
    return cur;
  }
}
