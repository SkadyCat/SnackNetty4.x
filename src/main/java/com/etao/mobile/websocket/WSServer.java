package com.etao.mobile.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by bruce on 19/3/30 -- 10:46
 */
public class WSServer {
    public static void main(String[] args) throws Exception {
        /**
         * 1、定义一对线程组
         */
        //主线程组（线程池）,用于接受客户端的连接，但是不做任何事情，把任务交给从线程组去做
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //从线程组（线程池）,把工作交给下面的线程去处理
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            /**
             *2、启动类
             */
            ServerBootstrap bootstrap = new ServerBootstrap();
            //把线程组交给启动类来启动
            bootstrap.group(bossGroup, workGroup);
            //
            bootstrap.channel(NioServerSocketChannel.class)//nio的双向通道
//                    .childHandler(new HttpServerInit());//子处理器，用于处理workGroup交过来的任务
                    .childHandler(new WSServerInit());//子处理器，用于处理workGroup交过来的任务
            //绑定端口,同步启动server

            System.out.println("9001。。。。。。");
            ChannelFuture channelFuture = bootstrap.bind(9001).sync();
            //监听关闭的channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();
        } finally {
            //最后优雅的关闭
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
