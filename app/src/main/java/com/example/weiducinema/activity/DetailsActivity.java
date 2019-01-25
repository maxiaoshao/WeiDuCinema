package com.example.weiducinema.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.telecom.Call;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.base.BaseActivity;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.bean.DetailsBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.custom.SelectPicPopupWindow;
import com.example.weiducinema.precener.DetailsPrencenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * created by fxb
 * 2019/1/25 08:47
 */
public class DetailsActivity extends BaseActivity {
    ImageView fanhui,goupiao;
    TextView mover_name;
    DetailsPrencenter detailsPrencenter;
    SimpleDraweeView movier_sim;
    SelectPicPopupWindow menuWindow;
    RadioGroup group;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        goupiao = findViewById(R.id.goupiao);
        group = findViewById(R.id.groups);
        mover_name = findViewById(R.id.movier_name);
        movier_sim = findViewById(R.id.movier_sim);
        detailsPrencenter = new DetailsPrencenter(new Details());
        Intent intent = getIntent();
        String moverid = intent.getStringExtra("mid");
        detailsPrencenter.reqeust(moverid);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       goupiao.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               menuWindow = new SelectPicPopupWindow(DetailsActivity.this, itemsOnClick);
               //显示窗口
               //设置layout在PopupWindow中显示的位置
               menuWindow.showAtLocation(DetailsActivity.this.findViewById(R.id.rela_my_info), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

       }
       });
        Log.d("asd","asd");
        Log.d("sdc","aqw");

    }

    @Override
    protected void destoryData() {

    }

    private class Details implements DataCall<Result<DetailsBean>> {


        @Override
        public void success(Result<DetailsBean> data) {
            movier_sim.setImageURI(data.getResult().getImageUrl());
            mover_name.setText(data.getResult().getName());
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                default:
                    break;
            }
        }
    };

    }
