package com.etao.mobile.email;

import com.etao.mobile.client.SingleMessager;
import net.sf.json.JSONObject;

import java.util.HashMap;

public class EmailCache {
    public static HashMap<String,EmailHouse> emailCache = new HashMap<String, EmailHouse>();

    public JSONObject onConnect(){


        return new JSONObject();

    }

    public static EmailHouse  getEmailHouse(String receiverName){
        if(emailCache.containsKey(receiverName) == false){

            EmailHouse house = new EmailHouse();

            emailCache.put(receiverName,house);

            return  house;

        }else {

            return emailCache.get(receiverName);
        }

    }


    public static void feedEmail(EmailItem item){

        System.out.println(item.getReceiver());
        EmailHouse house = getEmailHouse(item.getReceiver());

        if (house!= null){

            house.itemList.add(item);

            if (house.handler!= null){
                SingleMessager.send(house.handler,102,3,item.emailJSON);
            }
        }

    }
}
