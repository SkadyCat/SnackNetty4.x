package com.etao.mobile.glutton;

import com.etao.mobile.templates.Vector3;
import com.etao.mobile.websocket.WebSocketServerHandler;
import net.sf.json.JSONObject;

import java.util.Random;

public class GlutonSnack {

    public static final Random random = new Random();


    public WebSocketServerHandler handler;

    Vector3 dir;
    Vector3 position;
    String snackID;
    String bodyMessage = "";
    Vector3 color;
   public int count;
    public GlutonSnack(WebSocketServerHandler han){

        System.out.println("构造的ID = "+han.ID);
        snackID = han.ID;
        position = new Vector3();
        dir = new Vector3();
        position = new Vector3();

        color = new Vector3();
        color.x =Math.abs(random.nextInt()%255);
        color.y = Math.abs(random.nextInt()%255);
        color.z = Math.abs(random.nextInt()%255);



    }

    public  void  updateDir(JSONObject posObj) {


        if (position.x>28||position.x<-28){

            float tdir =(position.x/Math.abs(position.x));
            int x = (int)(position.x);
            position.x = tdir*x;
            dir.x = 0;

        }else {
            dir.x = new Float(posObj.get("x").toString());

        }
        if (position.y>28||position.y<-28){

            dir.y = 0;
            float tdir =(position.y/Math.abs(position.y));
            int y = (int)(position.y);
            position.y = tdir*y;
        }else {
            dir.y = new Float(posObj.get("y").toString());

        }
//        dir.y = new Float(posObj.get("y").toString());
        dir.z = new Float(posObj.get("z").toString());

        bodyMessage = posObj.get("bodyMsg").toString();

       // System.out.println(dir.toJsonValue());


    }
    public void updatePosition(){


        position.x += dir.x;
        position.y += dir.y;
        position.z += dir.z;


        //System.out.println("输出 = "+bodyMessage);

    }

    public JSONObject toVecJSON(){

        updatePosition();
        JSONObject reObj = new JSONObject();
        reObj.put("snackID",snackID);
        reObj.put("vec",position.toJson());
        reObj.put("bodyMsg",bodyMessage);
        return reObj;
    }

    public JSONObject getSnackBaseInfo(){
        JSONObject skObj = new JSONObject();
        skObj.put("snackID",this.snackID);
        skObj.put("color",this.color);
        skObj.put("count",this.count);


        return skObj;

    }


}
