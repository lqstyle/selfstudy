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
    System.out.println("请输入查询的节点");
    int position = Integer.parseInt(scanner.nextLine());
    if(position>=size.get()){
      System.out.println("输入节点大于总大小，请检查");
      position=Integer.parseInt(scanner.nextLine());
    }
    int i =0;
    Node node = null;
    while(i<=position){
      i++;
      if(node == null){
        node = head;
      }else{
        node =node.next;
      }
    }
    System.out.println(node);
  }

  /**
   * 删除双链表
   *
   * @param scanner
   */
  private static void deleteDoubleNode(Scanner scanner) {
    System.out.println("请输入刪除数据的位置");
    Integer position = Integer.valueOf(scanner.nextLine());
    if (position >= size.get()) {
      throw new RuntimeException("删除数据位置异常");
    }
    if (position == 0) {
      //头部刪除
      /*
        head指向 头部的next
       新的头部元素的pre为null
      头部元素的next指向null
       size-1
       */

      Node temp = head;
      Node node = temp.next;
      node.prev = null;
      temp.next = null;
      head = node;
      size.decrementAndGet();
    } else if (position == size.get()-1) {
      //尾部删除
      /*
       新的尾部元素的next指向null
      尾部元素的pre指向null
      tail指向新的尾部元素
      size--
       */
      Node temp = tail;
      Node node = temp.prev;
      node.next = null;
      temp.prev = null;

      tail = node;

      size.decrementAndGet();
    } else {
      //中间删除
      /*
      定位到节点的位置
      获取当前位置的节点元素
      获取下一位置的节点元素
      前一位置的节点元素
      将前一位置的节点元素的next指向下一位置
      下一位置的prev指向前一位置
      当前节点的next指向null prev指向null
       */
      Node node = null;
      AtomicInteger i = new AtomicInteger();
      while (i.get() <= position) {
        if (node == null) {
          node = head;
        } else {
          node = node.next;
        }
        i.incrementAndGet();
      }
      Node prev = node.prev;
      Node next = node.next;
      prev.next = next;
      next.prev = prev;

      node.next = null;
      node.prev = null;
      size.decrementAndGet();
    }
    System.out.println(head.toString());
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

      //初始化
      if (tail == null) {
        head = tail = node;
      } else {

        Node tailNode = tail;

        node.prev = tailNode;
        node.next = null;

        tailNode.next = node;
        tail = node;
      }
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
          "o=" + o +
          '}';
    }
  }

}
