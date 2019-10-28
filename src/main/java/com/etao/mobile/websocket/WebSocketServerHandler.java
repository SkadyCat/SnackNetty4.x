package com.etao.mobile.websocket;

import com.etao.mobile.client.BroadCastMessage;
import com.etao.mobile.client.ClientMap;
import com.etao.mobile.glutton.GlutonChatMap;
import com.etao.mobile.op.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import net.sf.json.JSONObject;

import java.util.Random;

/**
 * Created by bruce on 19/3/30 -- 9:52
 * 自定义的助手类
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static volatile int clientCount = 0;
    public ChannelHandlerContext context;
    public String ID;

    public String setID(){

        Random random = new Random();

        String value = "";


        for (int i =0;i<10;i++)
        {
            value += ""+Math.abs( random.nextInt()%10);

        }
        this.ID = value;
        return  this.ID;
    }


    //用户记录和管理所有的客户端的channel
    private static ChannelGroup clientList = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                TextWebSocketFrame msg) throws Exception {
//        //获取客户端发送来的消息
//        String content = msg.text();
//        System.out.println("接收到的消息是:" + content);
//        System.out.println("在线人数是:" + clientCount);
//
//
////        群发
//        clientList.writeAndFlush(new TextWebSocketFrame("发送的消息是" + content + ">>>在线人数是: " + clientCount + "\n"));
//
        String getValue = msg.text();
        //System.out.println(getValue);
        // System.out.println(getValue);
        //System.out.println(getValue);
        OPStrategy opStrategy = null;
        JSONObject object = JSONObject.fromObject(getValue);
        //System.out.println("JSONd对象 = "+object.toString());

        switch (new Integer(object.get("main_code").toString())) {
            case 0:
                opStrategy = new OP_0(this);
                break;

            case 100:
                opStrategy = new OP_100(this);
                break;

            case 101:
                opStrategy = new OP_101(this);
                break;

            case 102:
                opStrategy = new OP_102(this);
                break;
            default:
                break;
        }
        if(opStrategy!= null){
            //  System.out.println(new Integer(object.get("main_code").toString()));
            opStrategy.doSomething(object);
        }



    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        this.context = ctx;
        ClientMap.addClient(this);
        System.out.println("有一个链接进来 ：");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ClientMap.removeClient(this.ID);
        //channel.currentWriteBuffer
        // ctx.getChannel().
        //ctx.getChannel().
        GlutonChatMap.removeSnack(this);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user",ID);
        GlutonChatMap.removeSnack(this);
        BroadCastMessage.broadCast(100,233,jsonObject);

        System.out.println("失去连接 +"+ID);

        //channel.isOpen() && channel.isConnected()
        ctx.close();

    }
}
