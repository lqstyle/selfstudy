package com.example.demo1.algorithm.order.list;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author liangqing
 * @since 2020/11/11 17:49
 * <p>
 * <p>
 * o(1)的时间复杂度 查询和插入都是o（1）
 * map 查询快，   链表 插入快
 * <p>
 * 操作的都是node节点 ，key(用于hash  索引 ) value（存储实际的值）  next（下一节点） pre（前一节点）
 */
public class MyLru {

    //hash散列 查找复杂度O(1)
    private Map<Integer, Node<Integer, Integer>> map;
    //双向链表 增删改的复杂度是O(1)
    private DoubleLinkedList doubleLinkedList;
    //缓存大小
    private Integer capacity;

    public MyLru(Integer size) {
        map = new HashMap();
        doubleLinkedList = new DoubleLinkedList();
        capacity = size;
    }

    //查找方法
    public Integer get(Node<Integer, Integer> node) {
        if (!map.containsKey(node.key)) {
            return -1;
        }
        Node<Integer, Integer> integerNode = map.get(node.key);
        doubleLinkedList.removeNode(integerNode);
        doubleLinkedList.addHead(integerNode);
        return integerNode.value;
    }

    //添加方法
    public void put(Node<Integer, Integer> node) {
        if (map.containsKey(node.key)) {
            Node<Integer, Integer> integerIntegerNode = map.get(node.key);
            integerIntegerNode.value = node.value;

            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        } else {
            if (capacity.equals(map.size())) {
                //判断是否满了，若满了，则获取尾节点删除
                Node temp = doubleLinkedList.getLast();
                doubleLinkedList.removeNode(temp);
                map.remove(temp.key);
            }
            doubleLinkedList.addHead(node);
            map.put(node.key, node);

        }
    }

    /**
     * node 节点， 散列和双向链表都存储的元素
     * <p>
     * 散列进行 get 索引，方便查找
     * 双向链表用于插入  方便插入，删除，修改操作
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> {

        K key;
        V value;
        Node<K, V> next;
        Node<K, V> pre;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            next = pre = null;
        }

        public Node() {

        }

    }


    static class DoubleLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;


        /*
        虚拟的双向链表 ，头节点和为节点初始化都为不存储值的Node节点
        指定前后关系，真实存在的节点，不是同一个
         */
        public DoubleLinkedList() {
            head = new Node<K, V>();
            tail = new Node<K, V>();
            head.next = tail;
            tail.pre = head;
        }

        //添加到头节点方法
        public void addHead(Node<K, V> node) {
            node.pre = head;
            node.next = head.next;
            //原先头节点的下一节点的pre先指向当前node,然后再将头节点的next指向node
            head.next.pre = node;
            head.next = node;
        }

        //删除节点
        public void removeNode(Node<K, V> node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.next = null;
            node.pre = null;
        }

        //先操作自己 的两个节点 pre指向head  next指向 head.pre  然后再操作头节点的head和 Node.next
       /* public void addHead(Node<K,V> node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }

        //删除节点
        public void removeNode(Node<K,V> node) {
            node.next.pre = node.pre;
            node.pre.next = node.next;
            node.next = null;
            node.pre = null;
        }
*/
        //获取最后一个节点,用于缓存满了删除使用
        public Node getLast() {
            return tail.pre;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            Node node = head;
            while(node != null){
                sb.append(String.format("%s:%s ", node.key,node.value));
                node = node.next;
            }

            return sb.toString();
        }

    }

    public static void main(String[] args) {
        MyLru myLru = new MyLru(2);
        Node node = new Node(1, 1);
        myLru.put(node);
        System.out.println(myLru.doubleLinkedList.toString());

        Node node1 = new Node(2, 2);
        myLru.put(node1);
        System.out.println(myLru.doubleLinkedList.toString());

        Node node2 = new Node(3, 3);
        myLru.put(node2);
        System.out.println(myLru.doubleLinkedList.toString());

        Node node3 = new Node(4, 4);
        myLru.put(node3);
        System.out.println(myLru.doubleLinkedList.toString());
    }



}
