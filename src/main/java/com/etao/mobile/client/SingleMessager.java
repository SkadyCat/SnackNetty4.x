package com.etao.mobile.client;

import com.etao.mobile.websocket.WebSocketServerHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.sf.json.JSONObject;

import java.util.Date;

public class SingleMessager {

    public static Date lastTime = new Date();
    public static float tempDate  = 0;
    public static void send(WebSocketServerHandler handler,int mainCode,int subCode, Object object){


            JSONObject so = new JSONObject();
            so.put("main_code",mainCode);
            so.put("sub_code",subCode);
            so.put("message",object);

            handler.context.writeAndFlush(new TextWebSocketFrame(so.toString()));







    }

    public static void sendJSONValue(WebSocketServerHandler handler, String object){
        handler.context.writeAndFlush(new TextWebSocketFrame(object));


    }
}
