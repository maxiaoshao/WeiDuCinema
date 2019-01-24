package com.example.weiducinema;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;


import com.bw.movie.R;
import com.example.weiducinema.activity.ScountActivity;
import com.example.weiducinema.core.base.BaseActivity;

public class WelComeActivity extends BaseActivity {

    private int count = 3;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            count--;
            if (count==0){
                startActivity(new Intent(WelComeActivity.this, ScountActivity.class));
                finish();
            }else{//消息不能复用，必须新建
                handler.sendEmptyMessageDelayed(1,1000);
            }
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        handler.sendEmptyMessageDelayed(1,1000);

    }

    @Override
    protected void destoryData() {

    }

}
