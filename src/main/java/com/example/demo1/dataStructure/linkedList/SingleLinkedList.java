package com.example.demo1.dataStructure.linkedList;

/**
 * @author liangqing
 * @since 2021/2/2 11:21
 */
public class SingleLinkedList {

    //头节点 用于遍历使用
    private final Node head = new Node("", "", null);

    //第一种方式：往单链表中添加元素,尾部添加
    public void addNode(Node node) {
        Node temp = head;

        //遍历
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    //第二种方式：通过no顺序添加
    public void addByOrder(Node node) {
        Node temp = head;

        //1. 遍历链表，定位到相应的位置 2 直接添加操作
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no.equals(node.no)) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", node.no);
        } else {
            node.next = temp.next;
            temp.next = node;
        }

    }

    //遍历链表
    public void listNode() {
        Node temp = head.next;

        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }

    }

    //修改节点
    public void updateNode(Node node) {

        //判断是否空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        Node temp = head.next;

        boolean flag = false;
        while (temp != null) {

            if (temp.no.equals(node.no)) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = node.name;
            temp.nickName = node.nickName;
        } else {
            System.out.println("不存在要修改的元素");
        }
    }

    /*
    删除节点
    不管如何，先定义辅助操作节点
    1. 定位到当前元素的上一个元素
    2.调整上一个元素的下个指针指向下下个元素
    3.当前节点没有指针指向，根据gc可达性分析，垃圾对象，被回收
     */
    public void deleteNode(Node node) {
        Node temp = head;

        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.no.equals(node.no)) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("删除的节点不存在%d", node.no);
        }
    }

    public static void main(String[] args) {
        //第一种方式遍历，尾部添加
        addOne();
        //第二种方式遍历，按照no添加
        addTwo();


    }

    private static void addOne() {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        Node node = new Node("lq", "lqqq", 1);
        Node node1 = new Node("qa", "qaaaa", 2);
        Node node2 = new Node("ws", "wsss", 3);

        singleLinkedList.addNode(node);
        singleLinkedList.addNode(node1);
        singleLinkedList.addNode(node2);

        singleLinkedList.listNode();
    }

    private static void addTwo() {
        SingleLinkedList singleLinked1List = new SingleLinkedList();
        Node node4 = new Node("lqq", "lqqq", 5);
        Node node5 = new Node("qaa", "qaaaa", 4);
        Node node6 = new Node("wss", "wsss", 2);
        singleLinked1List.addByOrder(node4);
        singleLinked1List.addByOrder(node5);
        singleLinked1List.addByOrder(node6);
        System.out.println("修改前");
        singleLinked1List.listNode();

        //修改节点
        singleLinked1List.updateNode(new Node("update", "update111", 4));
        System.out.println("修改后");
        singleLinked1List.listNode();
        System.out.println("删除后的节点");
        singleLinked1List.deleteNode(new Node("update", "update111", 4));
        singleLinked1List.listNode();
    }

    //单链表节点
    static class Node {

        public String name;

        public String nickName;

        public Integer no;

        public Node next;

        public Node(String name, String nickName, Integer no) {
            this.name = name;
            this.nickName = nickName;
            this.no = no;
        }


        //重写toString
        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", no=" + no +
                    '}';
        }

        public String getName() {
            return name;
        }

        public String getNickName() {
            return nickName;
        }

        public Integer getNo() {
            return no;
        }

        public Node getNext() {
            return next;
        }
    }

    public Node getHead() {
        return head;
    }

}


