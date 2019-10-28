package com.etao.mobile.op;


import com.etao.mobile.websocket.WebSocketServerHandler;

public class OP_0  extends OPStreategyEX{

    public OP_0(WebSocketServerHandler handler) {
        super(handler);

    }

    @Override
    public void subOP(int subCode) {
        switch (subCode){
            case 0:
                System.out.println("子操作...");

                break;

        }
    }
}
