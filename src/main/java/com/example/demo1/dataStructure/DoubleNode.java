package com.example.demo1.dataStructure;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/*
双链表 前驱节点 后继节点
 */
public class DoubleNode {

  static Node head;
  static Node tail;
  static AtomicInteger size = new AtomicInteger(0);

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("请选择操作\n1.插入节点\n2.删除节点\n3.查找结点");
    Integer integer = Integer.valueOf(scanner.nextLine());

    //初始化链表
    initDoubleNode();

    switch (integer) {
      case 1:
        addDoubleNode(scanner);
        break;
      case 2:
        deleteDoubleNode(scanner);
        break;
      case 3:
        queryDoubleNode(scanner);
        break;
      default:
        break;
    }

  }

  private static void initDoubleNode() {
    Node node = new Node(1);
    size.incrementAndGet();
    Node node2 = new Node(2);
    size.incrementAndGet();
    Node node3 = new Node(3);
    size.incrementAndGet();

    head = node;
    node.prev = null;
    node.next = node2;

    node2.prev = node;
    node2.next = node3;

    node3.prev = node2;
    node3.next = null;
    tail = node3;

    System.out.println("当前初始化的链表如下" + node.toString());

  }

  /**
   * 查询
   *
   * @param scanner
   */
  private static void queryDoubleNode(Scanner scanner) {

  }

  /**
   * 删除双链表
   *
   * @param scanner
   */
  private static void deleteDoubleNode(Scanner scanner) {
  }

  /**
   * 添加节点
   *
   * @param scanner
   */
  private static void addDoubleNode(Scanner scanner) {
    System.out.println("请输入插入数据的位置");
    Integer position = Integer.valueOf(scanner.nextLine());

    Node node = new Node(4);

    if (position > size.get()) {
      return;
    }

    if (position == 0) {
      /*
      头部插入
      1. 将新加入的节点的next指向head
         新加入的节点的prev为null

      2. 原先头节点的prev指向 新加入节点
      3. 将head 指向新节点
       */

      Node headNode = head;
      node.next = headNode;
      node.prev = null;

      headNode.prev = node;

      head = node;
    } else if (position == size.get()) {
      /*
      尾部插入
      1. 将新节点的next=null
         新节点的prev指向原先的tail
      2. 尾节点的next指向新节点
      3. 尾节点的指向新节点
       */
      Node tailNode = tail;

      node.prev = tailNode;
      node.next = null;

      tailNode.next = node;

      tail = node;
    } else {
      /*
      中间插入
      获取当前位置的节点
      前驱节点
      后继节点

      新节点的prev指向前驱节点
      新节点的next指向当前节点

      前驱节点的next 指向当前节点
      当前节点的prev 指向当前节点

       */

      int i = 0;
      Node current = null;
      while (i < position) {
        i++;
        if (current == null) {
          current = head.next;
        } else {
          current = current.next;
        }
      }
      Node prev = current.prev;

      node.next = current;
      node.prev = prev;

      prev.next = node;

      current.prev = node;

    }

    size.getAndIncrement();
    System.out.println(head.toString());

  }

  private static class Node {

    Node prev;
    Node next;
    Integer o;

    public Node(Integer o) {
      prev = null;
      next = null;
      this.o = o;
    }

    @Override
    public String toString() {
      return "Node{" +
          ", o=" + o +
          '}';
    }
  }

}
