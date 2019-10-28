package com.etao.mobile.chat;

import com.etao.mobile.websocket.WebSocketServerHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ChatForGORC {

    public static HashMap<String,ChatForGORC> chatMap = new HashMap<String, ChatForGORC>();
    public static WebSocketServerHandler superPower;


    public List<WebSocketServerHandler> userList = new ArrayList<WebSocketServerHandler>();


    public static String addChat(WebSocketServerHandler client)
    {
        UUID uuid = UUID.randomUUID();
        ChatForGORC ct = new ChatForGORC();
        ct.userList.add(superPower);
        ct.userList.add(client);
        chatMap.put(uuid.toString(),ct);
        return  uuid.toString();
    }

    public static ChatForGORC getFromChatMap(String id)
    {
        if(chatMap.containsKey(id))
        {

            return chatMap.get(id);
        }
        return  null;

    }






}
