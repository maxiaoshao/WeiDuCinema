package com.example.weiducinema.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.bw.movie.R;
import com.example.weiducinema.base.WDBaseActivity;

public class WDWelComeActivity extends AppCompatActivity {

    private int count = 3;

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setBackgroundResource(R.drawable.rectangle);
        setContentView(R.layout.activity_main);

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






}
