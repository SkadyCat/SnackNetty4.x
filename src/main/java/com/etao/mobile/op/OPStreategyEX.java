package com.etao.mobile.op;

import com.etao.mobile.websocket.WebSocketServerHandler;
import net.sf.json.JSONObject;

public abstract class OPStreategyEX extends  OPStrategy {


    public WebSocketServerHandler handler;
    public String handlerID;
    public  int mainCode;
    public  int subCode;
    public  String message;
    public OPStreategyEX(WebSocketServerHandler handler){

        this.handler = handler;
        this.handlerID = handler.ID;


    }


    @Override
    public void doSomething(JSONObject jobj) {
        this.opObject = jobj;
        mainCode = new Integer(this.opObject.get("main_code").toString());
        subCode = new Integer(this.opObject .get("sub_code").toString());
        subOP(subCode);
    }

    public abstract void  subOP(int subCode);



}
