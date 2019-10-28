package com.etao.mobile.glutton;

import com.etao.mobile.websocket.STimer;
import com.etao.mobile.websocket.WebSocketServerHandler;
import net.sf.json.JSONObject;

import java.util.HashMap;

public class GlutonChatMap {

    public static HashMap<String,GlutonSnack> snackMap = new
            HashMap<String, GlutonSnack>();

    public static GlutonSnack addNewSnack(WebSocketServerHandler han){

        GlutonSnack snack = new GlutonSnack(han);
        snackMap.put(snack.snackID,snack);

        return snack;


    }

    public static void  removeSnack(WebSocketServerHandler han){
        snackMap.remove(han.ID);

    }

    public static GlutonSnack getSnack(String ID){

        if (snackMap.keySet().contains(ID)){

            return  snackMap.get(ID);
        }
        return  null;
    }
    public static JSONObject getAllSnack(){

        JSONObject reObj = new JSONObject();

        int index = 0;

        for (GlutonSnack jb :snackMap.values()){

            JSONObject skObj = new JSONObject();
            skObj.put("snackID",jb.snackID);
            skObj.put("color",jb.color);
            skObj.put("count",jb.count);

            reObj.put(index,skObj);

            index++;
        }

        return  reObj;

    }


    public static JSONObject getAllSnackPosition() {

        JSONObject reobj = new JSONObject();
        int index = 0;
        for (GlutonSnack snack : snackMap.values()) {
            reobj.put(index,snack.toVecJSON());
            index++;
        }
       // System.out.println(reobj.toString());
        return reobj;
    }

    public static boolean timeBegin = false;


    public static void runTime(){

        STimer.exacute();
        RandomCubeTimer.exacute();


    }
}
