package com.example.weiducinema.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.bw.movie.R;
import com.example.weiducinema.base.WDBaseActivity;

import crossoverone.statuslib.StatusUtil;

public class WDWelComeActivity extends AppCompatActivity {

    private int count = 3;

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setBackgroundResource(R.drawable.rectangle);
        setContentView(R.layout.activity_main);
        setStatusColor();
        setSystemInvadeBlack();
        handler  = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                count--;
                if (count==0){
                    startActivity(new Intent(WDWelComeActivity.this, WDScountActivity.class));
                    finish();
                }else{//消息不能复用，必须新建
                    handler.sendEmptyMessageDelayed(1,1000);
                }
            }
        };
        handler.sendEmptyMessageDelayed(1,1000);


    }

    protected void setStatusColor() {
        StatusUtil.setUseStatusBarColor(this, Color.parseColor("#00000000"));
    }

    protected void setSystemInvadeBlack() {
        // 第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtil.setSystemStatus(this, true, true);
    }




}
