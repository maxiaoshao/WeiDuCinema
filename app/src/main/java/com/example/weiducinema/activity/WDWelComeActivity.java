package com.example.weiducinema.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;


import com.bw.movie.R;
import com.example.weiducinema.base.WDBaseActivity;

public class WDWelComeActivity extends WDBaseActivity {

    private int count = 3;

    private Handler handler;
    @Override
    protected int getLayoutId() {
        getWindow().getDecorView().setBackgroundResource(R.drawable.rectangle);
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
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

    @Override
    protected void destoryData() {

    }

}
