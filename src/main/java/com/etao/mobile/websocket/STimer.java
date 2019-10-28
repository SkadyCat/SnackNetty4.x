package com.etao.mobile.websocket;

import com.etao.mobile.client.ClientMap;
import com.etao.mobile.client.SingleMessager;
import com.etao.mobile.glutton.GlutonChatMap;
import com.etao.mobile.glutton.MapInfo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class STimer extends TimerTask {

    Date lastDate;
    float midDate;

    static float timeCounter = 0;
    @Override
    public void run() {

       // System.out.println("hello world");
       // handler.context.
        timeCounter+= 1;
        if (timeCounter>100){
            timeCounter = 0;
            try{


                Object ob = MapInfo.insRandomPoint();
                for (WebSocketServerHandler handler: ClientMap.handlerMap.values()
                ) {
                    SingleMessager.send(handler,100,201,ob);
                    //  SingleMessager.send(handler,100,203, GlutonChatMap.getAllSnackPosition());
                }
            }catch (Exception e){

            }


        }
        try {
           // Object ob = MapInfo.insRandomPoint();
            for (WebSocketServerHandler handler: ClientMap.handlerMap.values()
            ) {
                //SingleMessager.send(handler,100,201,ob);
                SingleMessager.send(handler,100,203, GlutonChatMap.getAllSnackPosition());

            }

        }catch (Exception es){

            System.out.println("TimerException..................");
        }




    }

    public static void exacute() {

        Timer timer = new Timer("我的定时器");           // 创建一个定时器
        STimer myTimerTask = new STimer();
        timer.schedule(myTimerTask, 100, 20);    //10秒后执行，周期为2秒
      //  handler.chan
    }
}
