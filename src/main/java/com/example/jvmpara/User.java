package com.example.jvmpara;

/**
 * @author liangqing
 * @since 2020/12/21 11:33
 * <p>
 * 开启了 指针压缩
 * 对象头 12字节
 * 未开启16 字节
 */
public class User {


    int x;
    int y;

    public User(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void execute() {
        System.out.println("   ");
    }
}
