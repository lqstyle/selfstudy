package com.example.netty.nio.chat;

import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author liangqing
 * @since 2020/12/15 11:12
 * <p>
 * 客户端的主要功能
 * <p>
 * 像服务端发送消息，
 * 接收服务端返回的消息
 */
public class ChatClient {

    private Selector selector;

    private SocketChannel socketChannel;

    private final String ip = "127.0.0.1";

    private final int port = 6667;

    private String username;

    public ChatClient() throws IOException {
        //获取监听器
        selector = Selector.open();

        //创建socket
        socketChannel = SocketChannel.open(new InetSocketAddress(ip, port));

        //配置非阻塞
        socketChannel.configureBlocking(false);

        //注册到 selector ，注意，此处注册事件为读取
        socketChannel.register(selector, SelectionKey.OP_READ);

        username = socketChannel.getRemoteAddress() + " ";

        System.out.println(username + " is ok...");
    }

    /**
     * 像服务端发送消息
     * @param s
     * @throws IOException
     */
    public void sendMsg(String s) throws IOException {
        String msg = "hello，大家好，我是" + username + "   " + s;

        //将消息通过bytebuffer写入到channel
        socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
    }

    /**
     * 从服务端接收消息
     * @throws IOException
     */
    public void receiveMsg() throws IOException {
        //查看是否有可用的事件
        final int select = selector.select();
        if (select > 0) {
            final Set<SelectionKey> selectionKeys = selector.selectedKeys();
            if (CollectionUtils.isEmpty(selectionKeys)) {
                return;
            }
            final Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                final SelectionKey selectionKey = iterator.next();
                //事件感兴趣的是读取事件
                if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //通过buffer读取channel中的数据，不能直接操作channel读写数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel.read(buffer);
                    System.out.println(new String(buffer.array()));
                }
                //需要移除
                iterator.remove();
            }

        } else {
            System.out.println("没有可用的通道");
        }
    }

    public static void main(String[] args) throws IOException {

        ChatClient chatClient = new ChatClient();
        System.out.println("selector地址    "+   chatClient.selector);
        //定义一个线程，每隔三秒读取从服务器返回的信息
        new Thread(() -> {
            try {
                chatClient.receiveMsg();
                TimeUnit.SECONDS.sleep(3000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //从控制台输入
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            final String s = scanner.nextLine();
            chatClient.sendMsg(s);
        }
    }


}
