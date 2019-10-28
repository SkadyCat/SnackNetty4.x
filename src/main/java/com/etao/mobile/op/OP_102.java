package com.etao.mobile.op;

import com.etao.mobile.email.EmailCache;
import com.etao.mobile.email.EmailHouse;
import com.etao.mobile.email.EmailItem;
import com.etao.mobile.websocket.WebSocketServerHandler;
import net.sf.json.JSONObject;

public class OP_102 extends OPStreategyEX {


    public EmailHouse emailHouse;
    public EmailItem tempEmail;

    public JSONObject tempJSON ;


    public OP_102(WebSocketServerHandler handler) {
        super(handler);


    }

    @Override
    public void subOP(int subCode) {

        switch (subCode)
        {

            case 0:
                String user_acc = opObject.get("message").toString();
                System.out.println(user_acc);

                emailHouse = EmailCache.getEmailHouse(user_acc);
                emailHouse.handler = this.handler;

                if (emailHouse!= null){

                    emailHouse.flushToHandler();

                }

                break;
            case 1:

                tempJSON = JSONObject.fromObject(opObject.get("message"));
                tempEmail = new EmailItem(tempJSON);

                System.out.println(tempEmail.emailJSON.toString());
                EmailCache.feedEmail(tempEmail);



                break;

            case 2:

                break;

        }

    }
}
