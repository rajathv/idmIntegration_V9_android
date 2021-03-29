package com.rvg.idmsample;

import android.content.Intent;

import com.konylabs.android.KonyMain;
import com.konylabs.vm.Function;

public class IDMWrapper {
    public static Function mCallback = null;
    public static void idmWrapper(Function pCallback) {
        try {
            KonyMain context = KonyMain.getActivityContext();
             //MainActivity.mCallback = pCallback;
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
