package com.etao.mobile.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by bruce on 19/3/30 -- 10:35
 */
//初始化器，channel注册以后，会执行里面相应的初始化的方法
public class WSServerInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        //对大数据流的处理
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        //===================以上的都是为了支持http协议=====================================
        /**
         * 添加对websocket的支持,用于指定给客户端的连接访问的路由 ：/ws
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //添加自定义的ws处理的handler
        WebSocketServerHandler hl =  new WebSocketServerHandler();
        hl.setID();
        pipeline.addLast(hl);
    }
}