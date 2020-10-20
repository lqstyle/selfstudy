package com.example.demo1.leetcode.array;

/*
网页链接 ：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/

思路变化：当时拿到这个题目 ,就想着单链表，不知道链表的长度，不好通过长度控制循环
其实，可以把单链表遍历一遍，tail节点的next为null 为止 ，
就可以获得链表长度（遍历链表得到长度后，走n-k步，就可以得到倒数第k位的数据）

单链表只有next指针，没有prev指针，不好进行向后查找，所以暂时没有思路

看了下评论，讲到快慢指针，快指针先K步，然后快慢指针同时走，直到快指针走到尾部，慢指针就是需要的倒是第k个节点

结论：
 1. 快慢指针思路
 2.先遍历统计链表长度，记为 nn ；
设置一个指针走 (n-k)(n−k) 步，即可找到链表倒数第 kk 个节点。
 */
class ListNode {

  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class ArrowOffer22 {

  public ListNode getKthFromEnd(ListNode head, int k) {
    int i = 1;
    ListNode fast = head;
    ListNode slow = head;
    while (i < k) {
      if (fast.next != null) {
        fast = fast.next;
      }
      i++;
    }
    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }
    return slow;
  }

}
