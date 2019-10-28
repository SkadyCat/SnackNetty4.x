package com.etao.mobile.op;


import net.sf.json.JSONObject;

public abstract class OPStrategy {

    public JSONObject opObject;
    public abstract void doSomething(JSONObject jobj);




}
