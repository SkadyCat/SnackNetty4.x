package com.etao.mobile.op;

import com.etao.mobile.chat.ChatForGORC;
import com.etao.mobile.client.BroadCastMessage;
import com.etao.mobile.client.SingleMessager;
import com.etao.mobile.websocket.WebSocketServerHandler;
import net.sf.json.JSONObject;

public class OP_101 extends OPStreategyEX {
    public OP_101(WebSocketServerHandler handler) {
        super(handler);
    }
    JSONObject tempJson;
    @Override
    public void subOP(int subCode) {

        switch (subCode)
        {
            //连接
            case 0:
                System.out.println(opObject.toString());

                tempJson = JSONObject.fromObject(opObject.get("message").toString());

                System.out.println(tempJson.get("type").toString());
                if (tempJson.get("type").toString().equals("service") ){

                    ChatForGORC.superPower = this.handler;
                    System.out.println("该用户是一个superMan");
                }else {

                    System.out.println("该用户是一个客户");

                }
                break;

            case 1:

               String id = ChatForGORC.addChat(this.handler);

               SingleMessager.send(this.handler,101,1,id);

                SingleMessager.send(ChatForGORC.superPower,101,1,id);
                break;

            case 2:
                tempJson = JSONObject.fromObject(opObject.get("message").toString());

                String chatID = tempJson.get("chatID").toString();
                String info = tempJson.get("info").toString();
                ChatForGORC chat = ChatForGORC.getFromChatMap(chatID);
                BroadCastMessage.broadCast(chat.userList,101,2,info);
                break;

            default:

                break;


        }

    }
}
