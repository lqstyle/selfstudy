package com.example.netty.bio;

/**
 * @author liangqing
 * @since 2020/12/14 14:01
 */

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1 使用bio模型 编写一个服务器，监听6666 端口，当客户端连接时，就启动一个线程池通讯
 * 服务端接受客户端连接，使用telnet方式即可
 *
 * IO模型中的BIO模型 (同步阻塞io，适合线程数较少，长连接)
 *
 * socket.accept和 inputstream.read是阻塞的
 *
 * 每次连接都需要重新启动一个线程进行操作，父线程负责接入，
 * 后续的处理，需要单独的线程处理
 * 多线程编程中，需要用while 防止虚假唤醒
 *
 * 对于socketinputstream 里面的数据需要创建一个字节数组存储
 *
 */
public class BioDemoOne {

    public static void main(String[] args) throws Exception {

        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);

        //对于新建连接，不需要开启线程池，获得连接的后续处理需要线程池去处理
        System.out.println("当前负责连接的线程是:"+Thread.currentThread()+"****"+Thread.currentThread().getName());

        while (true) {
            //此处获取一个socket连接 ，此处是阻塞的
            System.out.println("等待客户端接入:"+Thread.currentThread()+"****"+Thread.currentThread().getName());
            final Socket socket = serverSocket.accept();
            System.out.println("有客户端接入:"+Thread.currentThread()+"****"+Thread.currentThread().getName());

            executorService.execute(() -> {
                try {
                    handler(socket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }


    }

    public static void handler(Socket socket) throws Exception {

        //获取 socket中的输入流
        InputStream inputStream = socket.getInputStream();

        //创建一个字节数组
        byte[] bytes = new byte[1024];

        System.out.println("当前读取数据的线程是:"+Thread.currentThread()+"****"+Thread.currentThread().getName());
        while(true){
            System.out.println("等待读取字节:"+Thread.currentThread()+"****"+Thread.currentThread().getName());
            //此处是阻塞的，客户端已经接入，等待继续读取字节
            //read一次读取所有字节，若输入的字节较大，则存在粘包拆包的问题
            int read = inputStream.read(bytes);
            System.out.println("读取到字节:"+Thread.currentThread()+"****"+Thread.currentThread().getName());
            if (read != -1) {
                System.out.println(new String(bytes));
            }else{
                break;
            }
        }


    }
}
