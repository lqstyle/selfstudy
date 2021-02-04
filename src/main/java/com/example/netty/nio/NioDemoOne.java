package com.example.netty.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liangqing
 * @since 2020/12/14 14:58
 *
 * channel 应用实例
 * 使用ButyBuffer和 channel 将字符串写入文件
 *
 *数据从通道读取到buffer  channel.read(buffer)
 * 数据从buffer写入到通道  channel.write(buffer)
 *
 * 对于文件的写入，如果从inputstream直接获取channel，讲buffer写入channel 失败
 *
 * 因为是只读的
 *
 * 需要通过RandomAccessFile 来操作
 *
 * buffer写入后，需要flip转换 再读取
 *
 * position limit  capacity mark
 *
 *
 * 通常我们使用以下解决方案避免这种问题：
 *
 * 为SIGBUS信号建立信号处理程序
 * 当遇到SIGBUS信号时，信号处理程序简单地返回，write系统调用在被中断之前会返回已经写入的字节数，
 * 并且errno会被设置成success,但是这是一种糟糕的处理办法，因为你并没有解决问题的实质核心。
 * 使用文件租借锁
 * 通常我们使用这种方法，在文件描述符上使用租借锁，我们为文件向内核申请一个租借锁，
 * 当其它进程想要截断这个文件时，内核会向我们发送一个实时的RT_SIGNAL_LEASE信号，
 * 告诉我们内核正在破坏你加持在文件上的读写锁。这样在程序访问非法内存并且被SIGBUS杀死之前，你的write系统调用会被中断
 * 。write会返回已经写入的字节数，并且置errno为success。
 *
 * 作者：卡巴拉的树
 * 链接：https://juejin.cn/post/6844903556345135118
 * 来源：掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class NioDemoOne {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:\\mavenproject\\demo1\\file\\1.txt","rw");
        String input = "hello,我的netty";
        //获取包装的fileChannel
        FileChannel channel = file.getChannel();

        //定义ByteBuffer，进行数据的存储
        ByteBuffer bytebuffer =  ByteBuffer.allocate(1024);
        bytebuffer.put(input.getBytes());
        //注意，此处将ByteBuffer的数据写入通道之前，需要先flip，讲写模式切入到读取模式
        bytebuffer.flip();
        //将数据写入到channel
        channel.write(bytebuffer);
        file.close();
    }
}
