package com.rvg.idmsample;

import android.content.Intent;

import com.konylabs.android.KonyMain;

public class IDMWrapper {
public static void idmWrapper(){
    try {
        KonyMain context = KonyMain.getActivityContext();

    Intent i = new Intent(context,MainActivity.class);
context.startActivity(i);
    }
    catch (Exception e){
e.printStackTrace();
    }

}


}
