package com.example.demo1.dataStructure;

import com.google.common.collect.Lists;
import java.util.LinkedList;
import org.springframework.util.CollectionUtils;

public class TwoTreeIter<T> {

  public static void main(String[] args) {
    LinkedList<Integer> tree = Lists.newLinkedList();
    tree.add(10);
    tree.add(2);
    tree.add(9);
    tree.add(7);
    tree.add(5);
    tree.add(6);
    tree.add(4);
    TwoTreeIter<Integer> twoTreeIter = new TwoTreeIter();
    TreeNode<Integer> treeNode = twoTreeIter.creatBinaryPre(tree);
    twoTreeIter.printBinaryTreeMidRecur(treeNode);
  }

  /*
   * 中序遍历二叉树（递归）
   */
  public void printBinaryTreeMidRecur(TreeNode<T> root) {
    if (root != null) {
      printBinaryTreeMidRecur(root.left);
      System.out.print(root.data);
      printBinaryTreeMidRecur(root.right);
    }
  }

  public TreeNode<T> creatBinaryPre(LinkedList<T> treeData) {
    TreeNode<T> root = null;
    if (CollectionUtils.isEmpty(treeData)) {
      return null;
    }
    T data = treeData.removeFirst();
    if (data != null) {
      root = new TreeNode<T>(data, null, null);
      root.left = creatBinaryPre(treeData);
      root.right = creatBinaryPre(treeData);
    }
    return root;
  }

  static class TreeNode<T> {

    public T data;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }


}
