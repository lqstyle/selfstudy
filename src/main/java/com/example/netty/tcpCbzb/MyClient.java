package com.example.netty.tcpCbzb;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author liangqing
 * @since 2020/12/16 10:55
 */
public class MyClient {
    public static void main(String[] args) throws InterruptedException {
        //定义客户端线程池 ,默认cpu核数*2
        final NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        try {
            //定义配置参数
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientInitializer());

            //绑定ip，端口

            final ChannelFuture sync = bootstrap.connect("127.0.0.1", 6668).sync();

            //异步关闭
            sync.channel().closeFuture().sync();
        } finally {
            eventExecutors.shutdownGracefully();
        }

    }
}
