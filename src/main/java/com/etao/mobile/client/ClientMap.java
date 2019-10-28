package com.etao.mobile.client;

import com.etao.mobile.websocket.WebSocketServerHandler;
import net.sf.json.JSONObject;

import java.util.HashMap;

public class ClientMap {
    public static HashMap<String, WebSocketServerHandler> handlerMap = new HashMap<String, WebSocketServerHandler>();


    public static void addClient(WebSocketServerHandler handler){
        handlerMap.put(handler.ID,handler);
    }

    public static void  removeClient(String ID){

        handlerMap.remove(ID);
    }

    public static JSONObject getAllUser(){
        JSONObject reObj = new JSONObject();

        int index = 0;

        for (WebSocketServerHandler jb :handlerMap.values()){

            reObj.put(index,jb.ID);
            index++;
        }

        return  reObj;

    }





}
