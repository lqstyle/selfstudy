package com.example.demo1.dataStructure;

import java.util.Objects;
import java.util.Stack;

/**
 * @author liangqing
 * @since 2021/2/2 14:27
 */
public class SingleLinkedListInterview {


    public static void main(String[] args) {
//        SingleLinkedList singleLinkedList = organizationLinkedList();
//        //获取链表长度
//        System.out.println(getEffectiveNodeNum(singleLinkedList.getHead()));
//        //查找单链表中的倒数第 k 个结点
//        System.out.println(getNodeByNo(singleLinkedList.getHead(), 1));
//
//        System.out.println("逆序输出前");
//        singleLinkedList.listNode();
//        //链表逆序
//        reverse(singleLinkedList.getHead());
//        System.out.println("逆序输出后");
//        singleLinkedList.listNode();
//
//        //链表逆序输出
//        reverseNodeOut(singleLinkedList.getHead());

        //合并两个有序的单链表，合并之后的链表依然有序
        SingleLinkedList singleLinkedList1 = organizationLinkedList();
        SingleLinkedList singleLinkedList2 = organizationLinkedListTwo();
        final SingleLinkedList singleLinkedList = mergeLinkedList(singleLinkedList1, singleLinkedList2);
        if (Objects.isNull(singleLinkedList)) {
            return;
        }
        singleLinkedList.listNode();
    }

    private static SingleLinkedList organizationLinkedList() {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList.Node node = new SingleLinkedList.Node("lq", "lqqq", 1);
        SingleLinkedList.Node node1 = new SingleLinkedList.Node("qa", "qaaaa", 5);
        SingleLinkedList.Node node2 = new SingleLinkedList.Node("ws", "wsss", 6);

        singleLinkedList.addByOrder(node);
        singleLinkedList.addByOrder(node1);
        singleLinkedList.addByOrder(node2);
        return singleLinkedList;
    }

    private static SingleLinkedList organizationLinkedListTwo() {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList.Node node = new SingleLinkedList.Node("fd", "qweqweqwe", 2);
        SingleLinkedList.Node node1 = new SingleLinkedList.Node("e3w", "re", 3);
        SingleLinkedList.Node node2 = new SingleLinkedList.Node("qw", "qwqw", 4);

        singleLinkedList.addByOrder(node);
        singleLinkedList.addByOrder(node1);
        singleLinkedList.addByOrder(node2);
        return singleLinkedList;
    }

    /*
    求单链表中有效节点的个数
     */
    private static int getEffectiveNodeNum(SingleLinkedList.Node head) {
        SingleLinkedList.Node temp = head;
        //判断链表是否为空
        if (temp.next == null) {
            return 0;
        }
        int count = 0;
        while (temp.getNext() != null) {
            count++;
            temp = temp.getNext();
        }
        System.out.printf("当前链表的节点数量是 %d  如下", count);
        return count;
    }

    /*
   查找单链表中的倒数第 k 个结点
    */
    private static SingleLinkedList.Node getNodeByNo(SingleLinkedList.Node head, int index) {
        SingleLinkedList.Node temp = head;
        //判断链表是否为空
        if (temp.next == null) {
            System.out.println("当前节点为空");
            return null;
        }
        int count = getEffectiveNodeNum(head);

        int position = count - index;
        for (int i = 0; i <= position; i++) {
            temp = temp.getNext();
        }
        //返回链表
        return temp;
    }

    /*
    单链表的反转
     */
    private static void reverse(SingleLinkedList.Node head) {
        //辅助指针
        SingleLinkedList.Node temp = head.next;
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        if (temp.next == null) {
            System.out.println("当前链表只有一个节点");
            return;
        }
        //定义新的头节点
        SingleLinkedList.Node newHead = new SingleLinkedList.Node("", "", null);
        //当前节点的下一个节点，用于暂存，因为当前节点指针变化，保存下一个节点的位置
        SingleLinkedList.Node next = null;
        while (temp != null) {
            //暂存当前节点
            next = temp.next;
            //当前节点的下一个指针指向新节点的头节点的下一个，首节点的话，此处为空
            temp.next = newHead.next;
            //新节点的头节点的下一个节点指向当前节点
            newHead.next = temp;
            //当前节点指向暂存的节点
            temp = next;

        }
        //最后，将原头节点的next指向新节点的next
        head.next = newHead.next;

    }

    /**
     * 从尾到头打印单链表
     * 方式1：反向遍历
     * 方式 2：Stack 栈
     *
     * @param head
     */
    private static void reverseNodeOut(SingleLinkedList.Node head) {
        //使用栈FILO 先进后出的特性实现

        SingleLinkedList.Node temp = head.next;
        if (temp == null) {
            System.out.println("当前链表为空");
        }

        final Stack<SingleLinkedList.Node> stack = new Stack<>();

        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }

    /*
    合并两个有序的单链表，合并之后的链表依然有序
     */
    private static SingleLinkedList mergeLinkedList(SingleLinkedList first, SingleLinkedList second) {

        final SingleLinkedList.Node firstHead = first.getHead();

        final SingleLinkedList.Node secondHead = second.getHead();

        if (firstHead.next == null && secondHead.next == null) {
            return null;
        }
        if (firstHead.next == null) {
            return second;
        }
        if (secondHead.next == null) {
            return first;
        }

        SingleLinkedList.Node firstTemp = firstHead;
        SingleLinkedList.Node secondTemp = secondHead;

        while (firstTemp.next != null) {

            while (secondTemp.next != null) {

                if (firstTemp.next.no >= secondTemp.next.no) {
                    secondTemp.next = firstTemp.next;
                    firstTemp.next = secondTemp;
                } else {
                    break;
                }

                secondTemp = secondTemp.next;
            }
            firstTemp = firstTemp.next;

        }

        return first;

    }
}
