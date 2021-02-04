package com.example.netty.nio.chat;

import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liangqing
 * @since 2020/12/15 9:52
 * <p>
 * break 会跳出当前while循环
 */

public class ChatServer {
    /*
    定义基本信息
     */

    //监听器
    private Selector selector;

    //服务端socket
    private ServerSocketChannel serverSocketChannel;

    //服务端端口
    private final int port = 6667;

    //构造基础数据
    public ChatServer() throws IOException {
        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();
        //非阻塞
        serverSocketChannel.configureBlocking(false);
        //注册到selector，监听接入事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
    }

    public void listen() throws IOException {

        System.out.println("服务端监听客户端接入");
        while (true) {
            //此处阻塞
            System.out.println("等待客户端连接中");
            final int select = selector.select();
            //表示有客户端接入
            if (select > 0) {
                //获取到有效的事件，与keys不同，keys是所有注册在selector上的事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                if (CollectionUtils.isEmpty(selectionKeys)) {
                    return;
                }
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    //接入
                    if (selectionKey.isAcceptable()) {
                        //serverSocketChannel 负责接入
                        SocketChannel channel = serverSocketChannel.accept();
                        //设置非阻塞
                        channel.configureBlocking(false);
                        //将channel 注册到selector上，并写明事件
                        channel.register(selector, SelectionKey.OP_READ);
                        System.out.println(channel.getRemoteAddress() + "    上线啦");
                    }
                    //可读
                    if (selectionKey.isReadable()) {
                        SocketChannel sc = (SocketChannel) selectionKey.channel();

                        //此需要设置非阻塞，负责会抛出异常 非法的阻塞异常
                        sc.configureBlocking(false);
                        //定义bytebuffer
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //将通道的数据写入bytebuffer
                        final int read = sc.read(byteBuffer);
                        String msg = new String(byteBuffer.array());
                        if (read != -1) {
                            System.out.println(sc.getRemoteAddress() + "  " + msg);
                        }
                        //分发通知
                        dispatcher(msg, sc);
                    }
                    //注意，此处需要做移除处理，避免重复执行
                    iterator.remove();
                }
            } else {
                System.out.println("等待客户端接入....");
                //此处不应该是break，不应该跳出当前循环，等待中
            }
        }


    }

    public void dispatcher(String msg, SocketChannel self) throws IOException {

        System.out.println("服务器转发消息中...");

        //获取所有注册到selector上的key
        final Set<SelectionKey> keys = selector.keys();
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        final Iterator<SelectionKey> iterator = keys.iterator();
        while (iterator.hasNext()) {
            /**
             * 此处需要注意， keys 不只是socketchannel  还有serversocketchannel
             * 所以此处需要做类型匹配，不能一刀切
             *
             * 不能移除
             *
             * selectedKeys 是可以移除的
             */
            final SelectionKey selectionKey = iterator.next();
            Channel channel = selectionKey.channel();
            if (channel instanceof SocketChannel && channel != self) {
                //获取msg字节流，写入 socketchannel
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                SocketChannel socketChannel = (SocketChannel) channel;
                socketChannel.write(buffer);
            }

//            iterator.remove();
        }
    }

    public static void main(String[] args) throws IOException {
        ChatServer chatServer = new ChatServer();
        System.out.println("selector地址          " + chatServer.selector);
        chatServer.listen();

    }


}
