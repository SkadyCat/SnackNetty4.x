package com.etao.mobile.templates;

import net.sf.json.JSONObject;

public class TempBase  {


    public String toJsonValue(){

        return JSONObject.fromObject(this).toString();
    }
    public JSONObject toJson(){

        return JSONObject.fromObject(this);
    }
}
