package com.example.demo1.dataStructure.array;


import java.io.*;

/**
 * @author liangqing
 * @since 2021/2/1 10:06
 * 稀疏数组  用于多数数据都是0 或者多数数据相同的情况
 * <p>
 * 用于数据压缩  减少空间利用率
 *
 * Producer 事务
 * 为了实现跨分区跨会话的事务，需要引入一个全局唯一的 Transaction ID，并将 Producer
 * 获得的PID 和Transaction ID 绑定。这样当Producer 重启后就可以通过正在进行的 Transaction
 * ID 获得原来的 PID。
 * 为了管理 Transaction，Kafka 引入了一个新的组件 Transaction Coordinator。Producer 就
 * 是通过和 Transaction Coordinator 交互获得 Transaction ID 对应的任务状态。Transaction
 * Coordinator 还负责将事务所有写入 Kafka 的一个内部 Topic，这样即使整个服务重启，由于
 * 事务状态得到保存，进行中的事务状态可以得到恢复，从而继续进行。
 * 3.6.2 Consumer 事务
 * 上述事务机制主要是从 Producer 方面考虑，对于 Consumer 而言，事务的保证就会相对
 * 较弱，尤其时无法保证 Commit 的信息被精确消费。这是由于 Consumer 可以通过 offset 访
 * 问任意信息，而且不同的 Segment File 生命周期不同，同一事务的消息可能会出现重启后被
 * 删除的情况
 */
public class SparseArray {

    public static void main(String[] args) throws IOException {
        //1.创建一个二维数组
        int[][] array = new int[11][11];
        array[1][2] = 1;
        array[2][3] = 2;
        array[3][4] = 3;

        System.out.println("数组初始化完成");
        for (int[] row : array) {
            for (int i : row) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }
        /*
        2.转换成稀疏数组
        1. 获取有效数据个数，遍历原有二维数组
        2. 创建稀疏数组
        3. 遍历获取有效数据存入稀疏数组
         */

        //获取有效数据的个数
        int sum = 0;
        int row = array.length;
        int column = 0;
        for (int i = 0; i < array.length; i++) {
            if (column == 0) {
                column = array[i].length;
            }
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    sum++;
                }
            }
        }
        //创建稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = row;
        sparseArray[0][1] = column;
        sparseArray[0][2] = sum;

        //遍历原有数组，为稀疏数组赋值

        int index = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    //自增，每次有一个数据，则加1
                    index++;
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = array[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println("如下是稀疏数组");
        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }

        /*
        稀疏数组转换成二维数组
        1. 遍历稀疏数组，创建二维数组
        2. 塞值
         */
        int[][] original = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            original[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        System.out.println("恢复后的数组为：");
        for (int[] ints : original) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }

        writeSparseToFile(sparseArray);
        readSparseFromFile("1.txt");
    }


    /**
     * @Description: 将稀疏数组写入文件
     * @Param: [sparseArray]
     * @return: java.lang.String
     * @Author: luasliang
     * @Date: 2021/2/1 11:00
     */
    private static String writeSparseToFile(int[][] sparseArray) throws IOException {
        //文件操作,将数组写入文件
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("1.txt"));
        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                bufferedWriter.write(anInt + "\t");
            }
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        System.out.println("文件写入成功");
        return "1.txt";
    }

    /**
     * @Description: 从文件读出稀疏数组
     * @Param: [s]
     * @return: void
     * @Author: luasliang
     * @Date: 2021/2/1 11:00
     */
    private static void readSparseFromFile(String s) throws IOException {
        //文件操作,将数组写入文件
        //读取两次，一次记录行数总大小，也就是稀疏数组的行数
        BufferedReader bufferedWriter = new BufferedReader(new FileReader(s));
        //第二个buffer 用于生成稀疏数组使用
        BufferedReader bufferedWriter1 = new BufferedReader(new FileReader(s));

        //记录多少行
        int count = 0;
        while (bufferedWriter.readLine() != null) {
            count++;
        }

        //创建稀疏数组
        int[][] originalSparseArray = new int[count][3];

        int row = 0;
        String line;
        while ((line = bufferedWriter1.readLine()) != null) {
            final String[] split = line.split("\t");
            for (int i = 0; i < split.length; i++) {
                originalSparseArray[row][i] = Integer.parseInt(split[i]);
            }
            row++;
        }

        System.out.println("从文件读取的稀疏数组");
        for (int[] rows : originalSparseArray) {
            for (int data : rows) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        bufferedWriter.close();
        bufferedWriter1.close();
        System.out.println("文件写出成功");
    }


}
