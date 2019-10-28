package com.etao.mobile.email;

import com.etao.mobile.client.SingleMessager;
import com.etao.mobile.websocket.WebSocketServerHandler;
import net.sf.json.JSONArray;

import java.util.ArrayList;

public class EmailHouse {

    public ArrayList<EmailItem> itemList = new ArrayList<EmailItem>();
    public WebSocketServerHandler handler;

    public void flushToHandler(){

        SingleMessager.send(handler,102,4,getAllEmail());

    }

    public JSONArray getAllEmail(){

        JSONArray jsonArray = new JSONArray();
      for (EmailItem item : itemList){

          jsonArray.add(item.emailJSON);
      }
      return  jsonArray;
    }

}
