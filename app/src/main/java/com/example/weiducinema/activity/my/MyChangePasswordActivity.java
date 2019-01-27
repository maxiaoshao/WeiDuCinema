package com.example.weiducinema.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.base.WDBaseActivity;

public class MyChangePasswordActivity extends WDBaseActivity implements View.OnClickListener {


    private TextView user_older_pwd;
    private TextView user_new_pwd;
    private TextView user_agin_new_pwd;
    private ImageView img_back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdchange_password;

    }

    @Override
    protected void initView() {
        user_older_pwd = findViewById(R.id.user_older_pwd);
        user_new_pwd = findViewById(R.id.user_new_pwd);
        user_agin_new_pwd = findViewById(R.id.user_agin_new_pwd);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(this);

    }

    @Override
    protected void destoryData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }
}
