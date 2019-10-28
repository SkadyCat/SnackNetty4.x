package com.etao.mobile.client;

import com.etao.mobile.websocket.WebSocketServerHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public class BroadCastMessage {

    public static void broadCast(int mainCode, int subCode, Object object){
        JSONObject so = new JSONObject();
        so.put("main_code", mainCode);
        so.put("sub_code", subCode);
        so.put("message", object);
        for (WebSocketServerHandler handler : ClientMap.handlerMap.values()) {

                handler.context.writeAndFlush(new TextWebSocketFrame(so.toString()));

        }
    }

    public static void broadCast(List<WebSocketServerHandler> list, int mainCode, int subCode, Object object){
        JSONObject so = new JSONObject();
        so.put("main_code", mainCode);
        so.put("sub_code", subCode);
        so.put("message", object);
        for (WebSocketServerHandler handler : ClientMap.handlerMap.values()) {


            handler.context.writeAndFlush(new TextWebSocketFrame(so.toString()));


        }
    }

    public static void broadCast(Map<String,WebSocketServerHandler> list, int mainCode, int subCode, Object object){
        JSONObject so = new JSONObject();
        so.put("main_code", mainCode);
        so.put("sub_code", subCode);
        so.put("message", object);
        for (WebSocketServerHandler handler : list.values()) {

            handler.context.writeAndFlush(new TextWebSocketFrame(so.toString()));


        }
    }
}
