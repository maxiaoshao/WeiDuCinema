package com.example.weiducinema.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class WifiUtils {
    private static volatile WifiUtils instance;
    private Context context;

    private WifiUtils(Context context) {
        this.context = context;
    }

    public static WifiUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (WifiUtils.class) {
                if (instance == null) {
                    instance = new WifiUtils(context);
                }
            }
        }
        return instance;
    }

    public int getNetype() {
        int netType = -1;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        //无网络
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        //手机网络
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            netType = 2;
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            //wifi网络
            netType = 1;
        }
        //返回
        return netType;
    }
}


