package com.example.weiducinema.activity.my;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.bw.movie.R;
import com.example.weiducinema.activity.WDLoginActivity;
import com.example.weiducinema.adapter.UserAttenAdaptet;
import com.example.weiducinema.adapter.UserAttenChangAdaptet;
import com.example.weiducinema.app.SpacesItemDecoration;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.UserAttenBean;
import com.example.weiducinema.bean.UserAttentionCheng;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.db.DBManager;
import com.example.weiducinema.precener.UserAttenChengPrencenter;
import com.example.weiducinema.precener.UserAttenPrencenter;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MyAttentionActivity extends WDBaseActivity {
    RecyclerView recy,cecy;
    Button pai,yuan;
    private Dao<UserInfo,String> userDao;
    UserAttenChengPrencenter userAttenChengPrencenter;
    private List<UserInfo> student;
    UserAttenPrencenter userAttenPrencenter;
    UserAttenAdaptet attenAdaptet;
    UserAttenChangAdaptet attenAdaptets;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my__attention_;
    }

    @Override
    protected void initView() {
        recy = findViewById(R.id.recy);
        pai = findViewById(R.id.bt_pian);
        yuan = findViewById(R.id.bt_cheng);
        cecy = findViewById(R.id.crcy);
        recy = findViewById(R.id.ercy);
        userAttenPrencenter = new UserAttenPrencenter(new UserAtten());
        userAttenChengPrencenter = new UserAttenChengPrencenter(new UserAttenChang());
        pai.setBackgroundResource(R.drawable.btn_gradient);
        pai.setTextColor(Color.WHITE);
        yuan.setBackgroundResource(R.drawable.btn_wu);
        yuan.setTextColor(Color.BLACK);
        try {
           userDao= DBManager.getInstance(this).getUserDao();
            student = userDao.queryForAll();
            if (student.size()==0){
                startActivity(new Intent(MyAttentionActivity.this,WDLoginActivity.class));
            }else{

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        pai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pai.setBackgroundResource(R.drawable.btn_gradient);
                pai.setTextColor(Color.WHITE);
                yuan.setBackgroundResource(R.drawable.btn_wu);
                yuan.setTextColor(Color.BLACK);
                userAttenPrencenter.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"","1","10");
            }
        });
        yuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yuan.setBackgroundResource(R.drawable.btn_gradient);
                yuan.setTextColor(Color.WHITE);
                pai.setBackgroundResource(R.drawable.btn_wu);
                pai.setTextColor(Color.BLACK);
                userAttenChengPrencenter.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"","1","10");

            }
        });
            userAttenPrencenter.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"","1","10");
            attenAdaptet = new UserAttenAdaptet(this);
            attenAdaptets = new UserAttenChangAdaptet(this);



    }

    @Override
    protected void destoryData() {

    }

    private class UserAtten implements DataCall<Result<List<UserAttenBean>>> {
        @Override
        public void success(Result<List<UserAttenBean>> data) {
            if (data.getStatus().equals("0000")){
                attenAdaptet.setData(data.getResult());
                attenAdaptet.notifyDataSetChanged();
                recy.addItemDecoration(new SpacesItemDecoration(20));
                recy.setLayoutManager(new LinearLayoutManager(MyAttentionActivity.this));
                recy.setAdapter(attenAdaptet);
                cecy.setVisibility(View.GONE);
                recy.setVisibility(View.VISIBLE);
            }else{
                for(int i = 0;i<student.size();i++){
                    try {
                       userDao.delete(student.get(i));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                startActivity(new Intent(MyAttentionActivity.this,WDLoginActivity.class));

            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class UserAttenChang implements DataCall<Result<List<UserAttentionCheng>>> {


        @Override
        public void success(Result<List<UserAttentionCheng>> data) {
            if (data.getStatus().equals("0000")){
                attenAdaptets.setData(data.getResult());
                attenAdaptets.notifyDataSetChanged();
                cecy.addItemDecoration(new SpacesItemDecoration(20));
                cecy.setLayoutManager(new LinearLayoutManager(MyAttentionActivity.this));
                cecy.setAdapter(attenAdaptets);
                recy.setVisibility(View.GONE);
                cecy.setVisibility(View.VISIBLE);
            }else{
                for(int i = 0;i<student.size();i++){
                    try {
                        userDao.delete(student.get(i));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                startActivity(new Intent(MyAttentionActivity.this,WDLoginActivity.class));

            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
