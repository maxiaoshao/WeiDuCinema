package com.example.weiducinema.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.activity.WDLoginActivity;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.db.DBManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MyOpinionActivity extends WDBaseActivity implements View.OnClickListener {


    private EditText user_opinion;
    private Button btn_sent;
    private ImageView img_back;
    private Dao<UserInfo, String> userDao;
    private List<UserInfo> student;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my__opinion_;
    }

    @Override
    protected void initView() {
        user_opinion = findViewById(R.id.user_opinion);
        btn_sent = findViewById(R.id.btn_sent);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(this);
        btn_sent.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        userDao = DBManager.getInstance(this).getUserDao();
        try {
            student = userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initData(){
        String s = user_opinion.getText().toString();
        if (student.size()!=0) {
            if (!TextUtils.isEmpty(s)) {
                startActivity(new Intent(MyOpinionActivity.this, SentSeccussActivity.class));
            } else {
                Toast.makeText(MyOpinionActivity.this, "不能发送空的意见哦!", Toast.LENGTH_SHORT).show();
            }
        }else {
           Toast.makeText(MyOpinionActivity.this,"登陆后才能发表您的评论",Toast.LENGTH_SHORT).show();
           startActivity(new Intent(this,WDLoginActivity.class));
        }
    }
    @Override
    protected void destoryData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sent:
                initData();
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }
}
