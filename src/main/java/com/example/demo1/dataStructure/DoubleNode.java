package com.example.demo1.dataStructure;

public class DoubleNode {
  DoubleNode head;

  public static void main(String[] args) {
    Node node = new Node();


  }

  private static class Node {

    Node prev;
    Node next;
    Integer o;

    public Node(Node prev, Node next, Integer o) {
      this.prev = prev;
      this.next = next;
      this.o = o;
    }

    public Node() {
    }

    @Override
    public String toString() {
      return "Node{" +
          "prev=" + prev +
          ", next=" + next +
          ", o=" + o +
          '}';
    }

    public Node getPrev() {
      return prev;
    }

    public void setPrev(Node prev) {
      this.prev = prev;
    }

    public Node getNext() {
      return next;
    }

    public void setNext(Node next) {
      this.next = next;
    }

    public Integer getO() {
      return o;
    }

    public void setO(Integer o) {
      this.o = o;
    }
  }

}
