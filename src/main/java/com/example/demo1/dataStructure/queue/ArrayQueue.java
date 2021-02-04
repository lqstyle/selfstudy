package com.example.demo1.dataStructure.queue;

import java.util.Scanner;

/**
 * @author liangqing
 * @since 2021/2/1 17:55
 */
public class ArrayQueue {

    private int maxSize;  //队列最大容量
    private int front;    //队列头部  指向前一个位置       取元素递增
    private int rear;     //队列尾部  指向尾部的后一个位置  添加元素递增
    private int[] table;  //队列底层数据存放


    //队列构造函数
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = -1;
        this.rear = -1;
        this.table = new int[maxSize];
    }

    //判断队列是否为空，当两个位置相等，则为空
    public Boolean isEmpty() {
        return this.front == this.rear;
    }

    //判断队列是否满了
    public Boolean isFull() {
        return rear == this.maxSize - 1;
    }

    /**
     * 遍历当前队列元素
     */
    public void showQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        System.out.println("遍历输出当前队列");
        for (int i : table) {
            System.out.println(i + "\n");
        }
    }

    //获取队首元素
    public int getHead() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("当前队列为空");
        }
        return table[front + 1];
    }

    //往队列中添加元素
    public void addQueue(int i) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        table[++rear] = i;
    }

    //取元素
    public int getQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("队列为空");
        }
        return table[++front];
    }


    public static void main(String[] args) {
//        test();
        //创建队列
        ArrayQueue arrayQueue = new ArrayQueue(3);

        Scanner scanner = new Scanner(System.in);

        //接收用户输入
        char key;

        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符

            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入需要入队的数据");
                    final int i = scanner.nextInt();
                    arrayQueue.addQueue(i);
                    break;
                case 'g':
                    arrayQueue.getQueue();
                    break;
                case 'h':
                    int h = arrayQueue.getHead();
                    System.out.println(" " + h);
                    break;
                default:
                    break;

            }
        }

    }

    private static void test() {
        int i = 0;
        int j = 0;
        int[] table = {1, 2, 3};
        System.out.println(table[i++]);
        System.out.println(table[++j]);

        i = 0;
        j = 0;
        for (int k = 0; k < 3; k++) {
            System.out.println(table[i++]);
        }

        for (int k = 0; k < 3; k++) {
            System.out.println(table[++j]);
        }
    }
}
