package com.example.demo1.dataStructure.linkedList;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/*
双端链表  有头节点和尾节点的引用
单链表  只有头节点，为节点为空
 */
public class SingleNode {

  static Node head;
  static Node tail;

  static AtomicInteger size = new AtomicInteger(0);

  public static void main(String[] args) {
    Scanner sanner = new Scanner(System.in);
    System.out.println("请选择操作\n1.插入节点\n2.删除节点\n3.查找结点");
    int operate = Integer.parseInt(sanner.nextLine());
    //初始化节点1->2->3
    initNode();
    switch (operate) {
      case 1:
        addnoo(sanner);
        break;
      case 2:
        deletenoo(sanner);
        break;
      case 3:
        Node n = querynoo(sanner);
        System.out.println(n.toString());
        break;
      default:
        break;
    }
    sanner.close();

  }

  private static Node querynoo(Scanner scanner) {
    System.out.println("请输入查找元素的位置：");
    int position = Integer.parseInt(scanner.nextLine());
    Node node = null;
    if (position == 0) {
      node = head;
    }
    for (int i = 0; i < position; i++) {
      if (node == null) {
        node = head.next;
      } else {
        node = node.next;
      }
    }
    return node;
  }

  private static void deletenoo(Scanner scanner) {
    System.out.println("请输入删除元素的位置：");
    int position = Integer.parseInt(scanner.nextLine());
    if (position == size.get()) {
      throw new RuntimeException("越界");
    }
    if (position == 0) {
      //删除头部元素
      Node node = head;
      head = node.next;
      node.next = null;
      System.out.println(head.toString());
    } else {
      //删除其他元素
      Node node = null;
      for (int i = 0; i < position; i++) {
        if (node == null) {
          node = head;
        } else {
          node = node.next;
        }
      }

      Node preNode = node;
      Node current = node.next;
      preNode.next = current.next;
      current.next = null;

      size.decrementAndGet();
      System.out.println(head.toString() + "数组大小为" + size);
    }
  }

  private static void addnoo(Scanner scanner) {
    System.out.println("请输入插入的位置");
    int position = Integer.parseInt(scanner.nextLine());
    if (position > size.get()) {
      return;
    }
    Node node = new Node(4);
    addNode(position, node);
    System.out.println(head.toString());
  }

  private static void initNode() {
    Node initNode = new Node(1);
    size.incrementAndGet();

    SingleNode.head = initNode;

    Node no1 = new Node(2);
    size.incrementAndGet();

    initNode.next = no1;
    Node no2 = new Node(3);
    size.incrementAndGet();
    no1.next = no2;

    SingleNode.tail = no2;
  }

  private static void addNode(int position, Node node) {
    if (position == 0) {
      /*
      在头部插入:
      1.先将node节点的next指向head节点指向的头结点
      2.将head节点指向node节点
       */
      node.next = head;
      head = node;
      size.incrementAndGet();
    } else if (position == size.get()) {
      /*
       * 在尾部插入:
       * 1.判断尾部节点是否为空，若为空，则直接将tail指向node
       * 若不为空，则将tail的next指向node，再将tail指向node
       * */
      if (tail == null) {
        tail = node;
      } else {
        tail.next = node;
        tail = node;
      }
    } else if (position < size.get() && position > 0) {
      /*
      在中间位置插入：
      1.先循环找到节点index所在的位置
      然后，找到该位置的下一节点，将node的next指向下一节点
      将该位置上的节点的next指向node；
       */
      Node currentPositionNode = null;
      for (int i = 0; i < position - 1; i++) {
        if (currentPositionNode != null) {
          currentPositionNode = currentPositionNode.next;
        }

        if (currentPositionNode == null) {
          currentPositionNode = head.next;
        }
      }
      if (position == 1) {
        currentPositionNode = head;
      }
      if (currentPositionNode == null) {
        throw new RuntimeException("节点不存在异常");
      }
      //将新加入的node节点的next指向下一节点，再将当前节点的next指向node
      node.next = currentPositionNode.next;
      currentPositionNode.next = node;

      System.out.println("添加成功");
    }
  }


  static class Node {

    //单链表的下一个指针
    private Node next;

    //当前节点的值
    private Integer val;

    public Node(Integer val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return "Node{" +
          "next=" + next +
          ", val=" + val +
          '}';
    }
  }

  public Integer getSize() {
    return size.get();
  }

  public static Node getHead() {
    return head;
  }

  public static void setHead(Node head) {
    SingleNode.head = head;
  }

  public static Node getTail() {
    return tail;
  }

  public static void setTail(Node tail) {
    SingleNode.tail = tail;
  }

  public static void setSize(AtomicInteger size) {
    SingleNode.size = size;
  }


}
