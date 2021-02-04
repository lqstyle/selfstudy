package com.example.netty.timer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liangqing
 * @since 2020/12/16 13:51
 */
public class TimerServer {

    public static void main(String[] args) throws InterruptedException {
        //定义bossgroup和workgroup
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup();

        try {
            //启动辅助配置
            ServerBootstrap serverBootStrap = new ServerBootstrap();

            serverBootStrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TimeServerInitializer())  //此处需要配置处理器
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            //启动客户端
            final ChannelFuture channelFuture = serverBootStrap.bind(6669).sync();

            //等待连接关闭
            channelFuture.channel().closeFuture().sync();

        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }


}
