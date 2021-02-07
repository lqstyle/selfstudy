package com.example.demo1.dataStructure.recursive;

/**
 * @author liangqing
 * @since 2021/2/6 10:25
 */
public class MiGong {

    public static void main(String[] args) {
        /*
        假定：1 不可走  0 还未走  2 已走，可走  3. 已走 不可走
        第一步，构造迷宫 迷宫8行7列
         最上和最下都是 1
         最左和最右都是1
         4行2列为1  4行3列为1
         */
        int[][] m = new int[8][7];
        //最上和最下都是 1
        for (int i = 0; i < 7; i++) {
            m[0][i] = 1;
            m[7][i] = 1;
        }
        //最左和最右都是1
        for (int i = 0; i < 8; i++) {
            m[i][0] = 1;
            m[i][6] = 1;
        }
        m[3][1] = 1;
        m[3][2] = 1;

        //输出m
        for (int[] ints : m) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        setWay(m, 1, 1);
        System.out.println("找路之后");
        //输出m
        for (int[] ints : m) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

    }

    /**
     * @param m 迷宫
     * @param i 行坐标
     * @param j 列坐标
     *          我们定义
     *          0 是没有走并且可走的
     *          1 是不可以走的
     *          2 已经走过并可以走的
     *          3 已经走过，并且不可以走的
     *          定义走的策略
     *          下 右 上 左
     */
    public static Boolean setWay(int[][] m, int i, int j) {
        if (m[6][5] == 2) {
            return Boolean.TRUE;
        } else {
            if (m[i][j] == 0) {
                m[i][j] = 2;
                //向下走
                if (setWay(m, i + 1, j)) {
                    return Boolean.TRUE;
                } else if (setWay(m, i, j + 1)) {
                    //向右走
                    return Boolean.TRUE;
                } else if (setWay(m, i - 1, j)) {
                    //向上走
                    return Boolean.TRUE;
                } else if (setWay(m, i, j - 1)) {
                    //向左走
                    return Boolean.TRUE;
                } else {
                    //说明该点是走不通，是死路
                    m[i][j] = 3;
                    return false;
                }

            } else {
                //1,2,3  直接返回false
                return Boolean.FALSE;
            }
        }

    }

}
