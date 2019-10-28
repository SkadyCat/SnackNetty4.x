package com.etao.mobile.glutton;

import com.etao.mobile.templates.Vector3;

import java.util.Random;

public class MapInfo {
   public static Random rd = new Random();
    public static String insRandomPoint(){

        Vector3 vec = new Vector3();
        int tx = rd.nextInt();
        int ty = rd.nextInt();
        vec.x = rd.nextFloat();
        vec.y = rd.nextFloat();

        if(tx%2 == 0&&ty%2 == 0)
        {


        }
        if(tx%2 == 0&&ty%2 == 1)
        {
            vec.y = - vec.y;

        }
        if(tx%2 == 1&&ty%2 == 0)
        {
            vec.x = - vec.x;

        }
        if(tx%2 == 1&&ty%2 == 1)
        {
            vec.x = - vec.x;
            vec.y = - vec.y;
        }


        return vec.toJsonValue();
    }
}
