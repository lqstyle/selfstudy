package com.example.demo1.algorithm.order;


/*
快速排序
三要素： 1.基准值 一般选取数组的最左侧或者最右侧
2. 最左面的指针left，查找比基准值小的，当遇到比基准值小的，则停止前行
3. 最右面的指针right，查找比基准值大的，当遇到比基准值大的，则停止前行

当left 和 right 都停止时，则交换两个数组的值
当left和ritht相遇时，则替换相遇位置的值与基准值
相遇后，最左面的数都小于基准值，最右面的数都大于基准值
然后分别对基准值的左面和右面分别递归调用
 */
public class FastOrder {

  public static void main(String[] args) {
    int[] array = new int[]{2, 3, 5, 89, 22, 33, 11};
    fast(array, 0, array.length - 1);
    for (int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    }
  }

  private static void fast(int[] array, int left, int right) {
    if (left > right) {
      return;
    }

    int base = array[left];
    int i = left;
    int j = right;

    while (i != j) {

      while (array[j] >= base && i < j) {
        j--;
      }

      while (array[i] <= base && i < j) {
        i++;
      }

      int temp = array[j];
      array[i] = array[j];
      array[j] = temp;
    }

    //替换base和相遇位置的值
    array[left] = array[i];
    array[i] = base;

    //递归调用
    fast(array, left, i - 1);
    fast(array, i + 1, right);
  }

}
