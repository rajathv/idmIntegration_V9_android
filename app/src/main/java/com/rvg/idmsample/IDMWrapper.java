package com.rvg.idmsample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.konylabs.android.KonyMain;
import com.konylabs.vm.Function;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class IDMWrapper {
    public  Function mCallback = null;
    public static void idmWrapper(Function pCallback) {
        try {
            Context context = KonyMain.getAppContext();

            MainActivity.mCallback = pCallback;
            Intent i = new Intent(context,MainActivity.class);

            i.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
         //   i.putExtra("callBack",pCallback);

//            MainActivity main = new MainActivity();
//             main.mCallback = pCallback;
//            Log.d("IDMKONY", "MCALLBACK DATA INSIDE WRappper" + main.mCallback);
//         final   Intent i = new Intent(context, MainActivity.class);
////            final Intent i = new Intent(this.mContext, (Class)CaptureActivity.class);
//
//
//
//            Log.d("IDMKONY", "Intent value inside wrappers method" +i);
//            context.startActivityForResult(i,2);
//            Log.d("IDMKONY", "After calling mainactivity");

Log.d("IDMKONY", "After IDM wrapper function finishes execution");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("IDMKONY", "After IDM wrapper function finishes execution2");

    }
}
