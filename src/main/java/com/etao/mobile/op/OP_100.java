package com.etao.mobile.op;

import com.etao.mobile.client.BroadCastMessage;
import com.etao.mobile.client.SingleMessager;
import com.etao.mobile.glutton.GlutonChatMap;
import com.etao.mobile.glutton.GlutonSnack;
import com.etao.mobile.websocket.STimer;
import com.etao.mobile.websocket.WebSocketServerHandler;
import net.sf.json.JSONObject;

import java.util.Random;

public class OP_100  extends OPStreategyEX{

    public GlutonSnack snack;
    public JSONObject tempJson;
    public static final Random random = new Random();
    public OP_100(WebSocketServerHandler handler) {
        super(handler);

    }

    @Override
    public void subOP(int subCode) {
        //System.out.println("100 op ->"+subCode);
        switch (subCode)
        {
            case 0:
              //  System.out.println("请求分配的ID");

              GlutonSnack sk =  GlutonChatMap.addNewSnack(handler);

                SingleMessager.send(this.handler,100,0,sk.getSnackBaseInfo());

                BroadCastMessage.broadCast(100,1, GlutonChatMap.getAllSnack());

                BroadCastMessage.broadCast(100,2, GlutonChatMap.getAllSnackPosition());
                break;

            case 100:
                System.out.println(opObject.toString());
                if (opObject.get("message").toString().equals("lsjsc")){

                    STimer.exacute();
                   // RandomCubeTimer.exacute();
                }

                break;

            case 202:
                snack = GlutonChatMap.getSnack(handlerID);

                if(snack!= null){
                    tempJson = JSONObject.fromObject(opObject.get("message").toString());
                   // System.out.println(tempJson.toString());
                    snack.updateDir(tempJson);

//                    for (WebSocketServerHandler handler: ClientMap.handlerMap.values()
//                    ) {
//                        //SingleMessager.send(handler,100,201,ob);
//                        SingleMessager.send(handler,100,203, GlutonChatMap.getAllSnackPosition());
//
//
//
//                    }
//                    if ()
                }else
                {
                   // System.out.println("该snack为空");
                }
                break;

            case 204:
                System.out.println(opObject.get("message").toString());

                JSONObject userJson = JSONObject.fromObject(opObject.get("message").toString());
                snack = GlutonChatMap.getSnack(userJson.get("snackID").toString());
                snack.count++;
                tempJson = new JSONObject();
                tempJson.put("snackID",userJson.get("snackID").toString());
                tempJson.put("count",snack.count);

                //tempJson = JSONObject.fromObject(opObject.get("message").toString());

                BroadCastMessage.broadCast(100,204,tempJson);
                break;

            case 205:

                break;

            default:

                break;

        }
    }
}
