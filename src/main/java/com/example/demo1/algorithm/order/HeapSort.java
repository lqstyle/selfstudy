package com.example.demo1.algorithm.order;

/*
堆的特点
1. 完全二叉树
2. 父节点比孩子节点大（最大堆） ，父节点比孩子节点小（最小堆）
3. 插入节点在节点的左孩子，树最后一层的右节点的右孩子为最后一个元素
4.对于任意一个节点n，他的左孩子的下标是2n+1,右孩子的下标是2n+2 父节点是(i-1)/2
底层树数组存储，tree[],tree[n] 就是当前节点的值

下面以最大堆为例，进行堆排序

具体的操作分三步：
1. 对于任意输入的下标节点，需要进行堆化
2. 从倒数第二层开始，对于每一层的节点都进行堆化
3. 遍历堆，交换最右侧节点的值和根节点的值，替换后把尾结点移除，继续堆化，重复操作

 */
public class HeapSort {


  public static void main(String[] args) {
    int[] tree = {2, 3, 4, 7, 1, 9, 5, 6};
    //MaxHeap.sortTree(tree, tree.length);
    minHeap.sortTree(tree, tree.length);
    for (int i = 0; i < tree.length; i++) {
      System.out.println(tree[i]);
    }
  }

  static class MaxHeap {

    /*
  tree 为堆的数组 tree[9,2,8,3,1,5]
  n   数组长度 6
  i 为需要堆化的索引下标
   */
    public static void heapify(int[] tree, int n, int i) {
      if (i >= n) {
        return;
      }

      int max = i;      //假设i索引所在的节点值最大
      int left = 2 * i + 1; //左节点
      int right = 2 * i + 2; // 右节点

      if (left < n && tree[left] > tree[max]) {
        max = left;
      }

      if (right < n && tree[right] > tree[max]) {
        max = right;
      }

      if (max != i) {
        swap(tree, max, i);
        heapify(tree, n, max);
      }
    }

    /*

    从倒数第二层节点开始遍历,倒序遍历，一直遍历到根节点
     */
    public static void buildTree(int[] tree, int n) {
      int last = n - 1;
      int second = (last - 1) / 2;
      for (int i = second; i >= 0; i--) {
        heapify(tree, n, i);
      }
    }

    /*
    堆化后，最大堆的根节点是最大的，需要替换根节点的值与尾结点，然后再树化，最后在依次移除
    此处构建堆只需要第一步构建，后续的堆化都是替换后的堆进行堆化，不需要多次构建堆
     */
    public static void sortTree(int[] tree, int n) {
      buildTree(tree, n);
      for (int i = n - 1; i >= 0; i--) {
        swap(tree, i, 0);
        heapify(tree, i, 0);
      }
    }

    /*
    交换数组中两个元素的值
     */
    private static void swap(int[] tree, int j, int i) {
      int temp = tree[i];
      tree[i] = tree[j];
      tree[j] = temp;
    }
  }


  static class minHeap {

    /*
  tree 为堆的数组 tree[9,2,8,3,1,5]
  n   数组长度 6
  i 为需要堆化的索引下标
   */
    public static void heapify(int[] tree, int n, int i) {
      if (i >= n) {
        return;
      }

      int min = i;      //假设i索引所在的节点值最小
      int left = 2 * i + 1; //左节点
      int right = 2 * i + 2; // 右节点

      if (left < n && tree[left] < tree[min]) {
        min = left;
      }

      if (right < n && tree[right] > tree[min]) {
        min = right;
      }

      if (min != i) {
        swap(tree, min, i);
        heapify(tree, n, min);
      }
    }

    /*

    从倒数第二层节点开始遍历,倒序遍历，一直遍历到根节点
     */
    public static void buildTree(int[] tree, int n) {
      int last = n - 1;
      int second = (last - 1) / 2;
      for (int i = second; i >= 0; i--) {
        heapify(tree, n, i);
      }
    }

    /*
    堆化后，最大堆的根节点是最大的，需要替换根节点的值与尾结点，然后再树化，最后在依次移除
    此处构建堆只需要第一步构建，后续的堆化都是替换后的堆进行堆化，不需要多次构建堆
     */
    public static void sortTree(int[] tree, int n) {
      buildTree(tree, n);
      for (int i = n - 1; i >= 0; i--) {
        swap(tree, i, 0);
        heapify(tree, i, 0);
      }
    }

    /*
    交换数组中两个元素的值
     */
    private static void swap(int[] tree, int j, int i) {
      int temp = tree[i];
      tree[i] = tree[j];
      tree[j] = temp;
    }
  }


}
