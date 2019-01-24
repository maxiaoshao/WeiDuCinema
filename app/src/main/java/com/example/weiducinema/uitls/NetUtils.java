package com.example.weiducinema.uitls;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2018/12/28
 */
public class NetUtils {
    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetAvailable(Context context) {
        boolean isAvailable = false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            isAvailable = networkInfo.isAvailable();
        }
        return isAvailable;
    }
}
