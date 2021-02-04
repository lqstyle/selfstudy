package com.example.demo1.algorithm.order;

/**
 * @author liangqing
 * @since 2020/12/10 14:37
 */

/*
2，参照双指针解决
这里可以参照双指针的思路解决，指针j是一直往后移动的，如果指向的值不等于0才对他进行操作。而i统计的是前面0的个数，我们可以把j-i看做另一个指针，
它是指向前面第一个0的位置，然后我们让j指向的值和j-i指向的值交换


    public void moveZeroes(int[] nums) {
        int i = 0;//统计前面0的个数
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == 0) {//如果当前数字是0就不操作
                i++;
            } else if (i != 0) {
                //否则，把当前数字放到最前面那个0的位置，然后再把
                //当前位置设为0
                nums[j - i] = nums[j];
                nums[j] = 0;
            }
        }
    }
看一下运行结果




如果觉得绕，还可以换种写法


    public void moveZeroes(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            //只要不为0就往前挪
            if (nums[j] != 0) {
                //i指向的值和j指向的值交换
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i++;
            }
        }
    }
看下运行结果


上面的解法都不难，如果还理解不了，我给你讲个故事吧。有两个人A和B，其中A有特异功能，水路，陆路他都能走，而B只能走陆路，不能走水路。题中数组为0的我们把它看做是水路，不为0的我们可以把它看做是陆路，A和B同时出发，走的时候，无论是水路还是陆路，A都会往前走一步。而B只能遇到陆路才能走，遇到水路就掉到水里，走不动了，这个时候A可以继续走水路，当A往前走找到陆路的时候就把陆路踢到B的面前，然后B就可以继续走了。



这里就以示例 [0,1,0,3,12]为例，来看下A和B的对话。


初始状态：咱哥俩一起走


第1步

B：兄弟救我，我掉进水里了。
A：兄弟别急，我也在水里，我到前面找块陆地给你
第1步之后数组的值是[0,1,0,3,12]



第2步

A：兄弟，我找到陆地1了，踢给你，你接着（把1踢给B）
B：好嘞
第2步之后数组的值是[1,0,0,3,12]



第3步

B：兄弟，我又掉进水里了
A：别急，我也在水了，我在到前面看看有没有陆地，找块给你
第3步之后数组的值是[1,0,0,3,12]



第4步

A：兄弟，我找到陆地3了，踢给你，你接着（把3踢给B）
B：好嘞
第4步之后数组的值是[1,3,0,0,12]



第5步

B：兄弟，我又掉水里了，快来救救我
A：我现在在陆地12上面，我把它踢给你吧
B：好嘞，谢了，兄弟
第5步之后数组的值是[1,3,12,0,0]

作者：sdwwld
链接：https://leetcode-cn.com/problems/move-zeroes/solution/san-chong-fang-shi-jie-jue-du-ji-bai-liao-100de-yo/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class ArrayMove {
    public static void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=0){
                nums[j]=nums[i];
                j++;
            }
        }
        while(j<nums.length){
            nums[j++]=0;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
