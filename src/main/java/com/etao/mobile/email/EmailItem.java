package com.etao.mobile.email;

import net.sf.json.JSONObject;

public class EmailItem {

    public EmailItem(JSONObject jobk){

        this.emailJSON = jobk;

    }
    public String getReceiver(){

        return this.emailJSON.get("email_receiver").toString();
    }
    public String emailID;

    public String receiver;
    public String sendMaster;
    public String head;

    public JSONObject emailJSON;

}
