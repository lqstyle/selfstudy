package com.example.demo1.dataStructure.linkedList;

/**
 * @author liangqing
 * @since 2021/2/4 14:46
 * 约瑟夫  约瑟夫环问题
 * <p>
 * 分析 ： 基于环形单链表实现
 * 1. 构造环形单链表
 * 分如下情况
 * first 节点不可以动
 * 1.1 当前节点是第一个节点
 * first=child
 * first.next = first
 * curChild = first
 * 1.2 当前节点不是第一个节点
 * first.next = child;
 * child.next=first
 * curChild = child;
 * <p>
 * 2 遍历环形单链表
 * 当且仅当，当前节点的next=first 遍历结束
 * <p>
 * <p>
 * 执行约瑟夫 环操作
 * 定义两个节点  一个first  一个 helper 辅助节点
 * 1. 校验 相关属性
 * 2. 将helper定位到first节点的头节点
 * 3. helper节点和first节点分别移动 k-1个位置， 也就是开始位置
 * 4. 报数的个数，比如m 则first和helper分别移动m-1次
 * 此处的节点，就是需要删除的节点  输出
 * 5. 当且仅当 helper=first 时候，只有一个节点  ，输出即可
 */
public class JosePhu {

    //此节点不可移动，类似头节点
    private Child first = null;

    public static void main(String[] args) {

        JosePhu josePhu = new JosePhu();
        josePhu.buildCircleLinkedList(100);
//        josePhu.listCircleLinkedLiest(josePhu.first);
        josePhu.solveJoseHuCircle(1, 3, 100);
    }

    //编写约瑟夫问题实现
    public void solveJoseHuCircle(final Integer k, final Integer m, final Integer childNum) {
        //校验参数是否合法
        if (k < 0 || m > childNum || m < 0 || first == null) {
            System.out.println("参数错误,请检查");
            return;
        }
        //定义辅助指针，指向first的前一位
        Child helper = first;
        while (helper.next != first) {
            helper = helper.next;
        }

        //first 和  helper分别移动 k-1次
        for (int i = 0; i < k - 1; i++) {
            first = first.next;
            helper = helper.next;
        }

        // 执行数数
        while (helper != first) {
            for (int i = 0; i < m - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            System.out.println("当前节点出队" + first);
            //找到后，需要把节点删除
            first = first.next;
            helper.next = first;
        }
        System.out.println("当前节点出队" + helper);

    }


    //构造环形链表
    public void buildCircleLinkedList(Integer num) {
        if (num == null || num < 0) {
            System.out.println("参数错误，请检查");
            return;
        }

        //辅助节点
        Child curChild = null;
        for (int i = 1; i <= num; i++) {
            Child child = new Child(i);
            if (i == 1) {
                first = child;
                first.next = first;
                curChild = first;
            } else {
                curChild.next = child;
                child.next = first;
                curChild = child;
            }
        }
    }


    //遍历当前环形链表
    public void listCircleLinkedLiest(Child first) {

        if (first == null) {
            System.out.println("当前链表为空");
            return;
        }
        Child curChild = first;

        while (curChild.next != first) {
            System.out.println("节点如下" + curChild);
            curChild = curChild.next;
        }
        System.out.println("最后节点是" + curChild);
    }


    //小孩实体类
    static class Child {
        private Integer num;
        private Child next;

        public Child(Integer num) {
            this.num = num;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public Child getNext() {
            return next;
        }

        public void setNext(Child next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Child{" +
                    "num=" + num +
                    '}';
        }
    }

}
