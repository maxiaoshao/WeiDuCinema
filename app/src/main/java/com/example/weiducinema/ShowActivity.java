package com.example.weiducinema;


import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.weiducinema.core.base.BaseActivity;

public class ShowActivity extends BaseActivity {


    private FrameLayout frag;
    private ImageView pageactivity_imagefilm;
    private ImageView pageactivity_imagecinema;
    private ImageView pageactivity_imagemy;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        frag = findViewById(R.id.frag);
        pageactivity_imagefilm = findViewById(R.id.pageactivity_imagefilm);
        pageactivity_imagecinema = findViewById(R.id.pageactivity_imagecinema);
        pageactivity_imagemy = findViewById(R.id.pageactivity_imagemy);

    }

    @Override
    protected void destoryData() {

    }

}
