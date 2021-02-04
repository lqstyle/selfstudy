package com.example.demo1.dataStructure;

/**
 * @author liangqing
 * @since 2021/2/4 13:45
 */
public class DoubleLinkedList {

    //头节点 用于遍历使用
    private final Node head = new Node("", "", null);

    public static void main(String[] args) {
        Node node = new Node("lq", "lqq", 0);
        Node node1 = new Node("ls", "lss", 1);
        Node node2 = new Node("lx", "lxx", 2);
        Node node3 = new Node("lc", "lcc", 3);

        //添加节点
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addNode(node);
        doubleLinkedList.addNode(node1);
        doubleLinkedList.addNode(node2);
        doubleLinkedList.addNode(node3);

        //输出节点
        doubleLinkedList.listNode();

        //删除节点
        Node deleteNode = new Node("", "", 2);
        doubleLinkedList.deleteNode(deleteNode);
        //输出节点
        doubleLinkedList.listNode();

        //修改节点
        Node updateNode = new Node("rrr", "rrr", 3);
        doubleLinkedList.updateNode(updateNode);
        //输出节点
        doubleLinkedList.listNode();

    }

    //遍历链表  和单链表遍历相同
    public void listNode() {
        Node temp = head.next;

        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //修改链表，和单链表相同
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

    //新增 ，默认在尾部新增
    //往双向链表中添加元素,尾部添加
    public void addNode(Node node) {
        Node temp = head;
        //遍历
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = node;
        node.pre = temp;
    }

    /*
    删除,单链表需要  依赖节点的前一个节点
    双链表在当前节点删除即可
     */
    public void deleteNode(Node node) {
        Node temp = head.next;

        if (null == temp) {
            System.out.println("当前链表为空");
        }

        boolean flag = false;

        while (temp != null) {
            if (temp.no.equals(node.no)) {
                flag = true;
                break;
            }
            temp = temp.next;
        }


        if (flag) {
            //判断是否是尾节点
            if (temp.next == null) {
                temp.pre.next = null;
            } else {
                temp.pre.next = temp.next;
                temp.next.pre = temp.pre;
            }

        } else {
            System.out.printf("删除的节点不存在%d", node.no);
        }
    }


    //双向链表节点
    static class Node {

        private String name;

        private String nickName;

        private Integer no;

        private Node pre;

        private Node next;

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

        public void setName(String name) {
            this.name = name;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Integer getNo() {
            return no;
        }

        public void setNo(Integer no) {
            this.no = no;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
