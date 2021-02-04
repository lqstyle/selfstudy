package com.example.netty.tcpCbzb;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liangqing
 * @since 2020/12/16 10:11
 * <p>
 * ByteBuffer和ByteBuf区别
 * ByteBuf 采用读写分离的策略  readindex writeindex
 * 初始都为0 ，随着数据写入 ，大小为 N  数据读取，也会增加读指针
 * 初始默认大小256 ，最大值 Integer.maxValue，操作不需要额外的方法
 * 可以动态扩容
 * <p>
 * ByteBuffer  mark<=position<=limit<=capacity
 * 如果需要切换模式，flip和 rewind  clear 复位，不清除元素
 * 必须指定分配的大小，不允许动态扩容
 * allocate（分配在堆上）  allocateDirect（分配在直接内存）
 * Netty 使用的是ByteBuf
 * jdk  nio使用的是ByteBUffer
 * 所以netty 的ByteBuf提供了转换
 * <p>
 * ByteBuffer nioBuffer()  将ByteBuf转换成ByteBuffer
 */
public class MyServer {

    public static void main(String[] args) throws InterruptedException {
        //netty模型 bossGroup workGroup
        final EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        final NioEventLoopGroup workLoopGroup = new NioEventLoopGroup();

        try {
            //定义配置参数  辅助启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossLoopGroup, workLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);;

            //绑定端口
            final ChannelFuture channelFuture = serverBootstrap.bind(6668).sync();

            //异步关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            //关闭两组线程池
            bossLoopGroup.shutdownGracefully();
            workLoopGroup.shutdownGracefully();
        }

    }
}
