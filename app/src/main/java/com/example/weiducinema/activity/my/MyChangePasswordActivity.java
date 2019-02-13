package com.example.weiducinema.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.encrypt.EncryptUtil;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.db.DBManager;
import com.example.weiducinema.precener.ChangePwdPersent;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyChangePasswordActivity extends WDBaseActivity implements View.OnClickListener {


    private TextView user_older_pwd;
    private TextView user_new_pwd;
    private TextView user_agin_new_pwd;
    private ImageView img_back;
    private String new_pwd;
    private String agin_new_pwd;
    private ChangePwdPersent pwdPersent;
    private DBManager manager;
    private List<UserInfo> student;
    private String old_pwd;
    private Button btn_ok;
    private Dao<UserInfo, String> userDao;

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
        btn_ok = findViewById(R.id.btn_ok);
        img_back.setOnClickListener(this);
        btn_ok.setOnClickListener(this);

        try {
            userDao = DBManager.getInstance(this).getUserDao();
            student = userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pwdPersent = new ChangePwdPersent(new ChangePwdCall());

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
            case R.id.btn_ok:
                old_pwd = user_older_pwd.getText().toString().trim();
                new_pwd = user_new_pwd.getText().toString().trim();
                agin_new_pwd = user_agin_new_pwd.getText().toString().trim();
                pwdPersent.reqeust(student.get(0).getUserId(), student.get(0).getSessionId(), EncryptUtil.encrypt(old_pwd), EncryptUtil.encrypt(new_pwd), EncryptUtil.encrypt(agin_new_pwd));
                break;
        }
    }
    //获取数据
    class ChangePwdCall implements DataCall<Result> {

        @Override
        public void success(Result data) {

            Toast.makeText(getBaseContext(),data.getMessage(),Toast.LENGTH_SHORT).show();
            if (data.getStatus().equals("0000")) {
                finish();
            }

        }
        @Override
        public void fail(ApiException e) {
            Toast.makeText(getBaseContext(),"异常",Toast.LENGTH_SHORT).show();

        }
    }
}
