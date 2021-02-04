package com.example.demo1.dataStructure.stack;

/**
 * @author liangqing
 * @since 2021/2/4 16:08
 * <p>
 * 基于数组实现栈
 * 要素
 * 1.定义栈顶指针 top 默认 -1
 * 先进后出
 */
public class ArrayStack {

    public static void main(String[] args) {
        AStack<Integer> aStack = new AStack<>(5);
        for (int i = 0; i < 6; i++) {
            aStack.pushStack(i);
        }

        for (int i = 0; i < 7; i++) {
            System.out.println(aStack.popStack());
        }

    }

    static class AStack<V> {

        private int pop = -1;

        private final V[] table;

        private final int maxSize;

        public AStack(int maxSize) {
            this.maxSize = maxSize;
            table = (V[]) new Object[maxSize];
        }

        //栈空间是否满了
        private Boolean isFull() {
            return pop == maxSize - 1;
        }

        //栈空间是否为空
        private Boolean isEmpty() {
            return pop == -1;
        }

        //入栈
        private void pushStack(V v) {
            if (isFull()) {
                System.out.println("当前栈已经满了");
                return;
            }
            pop++;
            table[pop] = v;
        }

        //出栈
        private V popStack() {
            if (isEmpty()) {
                System.out.println("当前栈为空");
                return null;
            }
            V v = table[pop];
            pop--;
            return v;
        }

        //读取，不弹出
        private V pickStack() {
            return table[pop];
        }

    }


}
