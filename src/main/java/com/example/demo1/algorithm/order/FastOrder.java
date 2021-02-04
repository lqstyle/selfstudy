package com.example.demo1.algorithm.order;


/*
快速排序
三要素： 1.基准值 一般选取数组的最左侧或者最右侧
2. 最左面的指针left，查找比基准值大的，当遇到比基准值大的，则停止前行
3. 最右面的指针right，查找比基准值小的，当遇到比基准值小的，则停止前行

当left 和 right 都停止时，则交换两个数组的值
当left和ritht相遇时，则替换相遇位置的值与基准值
相遇后，最左面的数都小于基准值，最右面的数都大于基准值
然后分别对基准值的左面和右面分别递归调用
 */
public class FastOrder {

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 5, 89, 22, 33, 11};
        //fast(array, 0, array.length - 1);
        fastSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    /**
     * 快排
     *
     * @param array
     * @param l
     * @param r
     */
    private static void fastSort(int[] array, final int l, final int r) {
        //两个关键时间点：快慢指针都暂停    快慢指针相遇
        if (l > r) {
            return;
        }
        //基准数
        int i = l;
        int j = r;
        int base = array[l];
        //当左右指针不相等，则执行移动,从最右侧开始移动
        while (i != j) {
            while (array[j] >= base && i < j) {
                j--;
            }
            while (array[i] <= base && i < j) {
                i++;
            }

            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
        //当左右指针相等，则交换base和相遇位置的数
        array[l] = array[i];
        array[i] = base;

        fastSort(array, l, i - 1);
        fastSort(array, i + 1, r);
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
