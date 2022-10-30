package com.ulp.inmobiliariafpestchanker.request;

import android.content.Context;
import android.content.SharedPreferences;

public class Token {


    public static String ObtenerToken(Context context){
        SharedPreferences sp= context.getSharedPreferences("token",0);
        String token=sp.getString("token","-1");
        return token;

    };
}
