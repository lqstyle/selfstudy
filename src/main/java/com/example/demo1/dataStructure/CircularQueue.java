package com.example.demo1.dataStructure;

import java.util.Scanner;

/**
 * @author liangqing
 * @since 2021/2/2 9:55
 */
public class CircularQueue {

    private int maxSize;  //队列最大容量
    private int front;    //队列头部  指向当前位置         取元素递增，后面需要取模处理
    private int rear;     //队列尾部  指向尾部的后一个位置  添加元素递增，后面需要取模处理
    private int[] table;  //队列底层数据存放


    //队列构造函数
    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = 0;
        this.rear = 0;
        this.table = new int[maxSize];
    }

    //判断队列是否为空，当两个位置相等，则为空
    public Boolean isEmpty() {
        return this.front == this.rear;
    }

    //判断队列是否满了,此处已经处理成环形数组，需要考虑取模
    public Boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        for (int i = front; i < front + size(); i++) {
            System.out.printf("table[%d]=%d\n", i % maxSize, table[i % maxSize]);
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //获取队首元素
    public int getHead() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("当前队列为空");
        }

        //此处front就是队列头元素
        return table[front];
    }

    //往队列中添加元素
    public void addQueue(int i) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }

        table[rear] = i;
        rear = (rear + 1) % maxSize;
    }

    //取元素
    /*
    此处取元素，需要先将原有位置的元素暂存，然后把front前移
     */
    public int getQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("队列为空");
        }
        int temp = table[front];
        front = (front + 1) % maxSize;
        return temp;
    }


    public static void main(String[] args) {
        CircularQueue arrayQueue = new CircularQueue(4);

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
}
