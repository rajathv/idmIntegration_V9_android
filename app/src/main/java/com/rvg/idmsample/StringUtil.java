package com.rvg.idmsample;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class StringUtil {
    public static final boolean DEBUG_MODE = true;

    public static boolean isEmpty(String source) {
        return (null == source || source.trim().equals("")) ? true : false;
    }

    public static void printLogInDebugMode(String title, String log) {
        if (DEBUG_MODE) {
            Log.d("SDK", "::::::: " + title + " :::::::");
            Log.d("SDK", log);
            Log.d("SDK", "--------------");
        }
    }

    public static String encodeBitmapTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 95, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public static String getApplicationVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    "com.idmission.imageprocessingdemo", 0);
            versionName = info.versionName;
        } catch (Exception e) {
            versionName = BuildConfig.VERSION_NAME;
        }
        return versionName;
    }
}
