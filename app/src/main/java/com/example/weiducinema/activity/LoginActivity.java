package com.example.weiducinema.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bw.movie.R;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.encrypt.EncryptUtil;
import com.example.weiducinema.base.BaseActivity;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.core.exception.ApiException;

import com.example.weiducinema.db.DBManager;
import com.example.weiducinema.precener.LoginPersent;

import java.sql.SQLException;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText edittext_phone;
    private EditText edittext_pwd;
    private CheckBox check_remember;
    private CheckBox check_autologin;
    private TextView textview_register;
    private Button button_login;
    private ImageView login_weixin;
    private LoginPersent persent;
    private ImageView imageview_click;
    private DBManager manager;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        edittext_phone = (EditText) findViewById(R.id.edittext_phone);
        edittext_pwd = (EditText) findViewById(R.id.edittext_pwd);
        check_remember = (CheckBox) findViewById(R.id.check_remember);
        check_autologin = (CheckBox) findViewById(R.id.check_autologin);
        textview_register = (TextView) findViewById(R.id.textview_register);
        button_login = (Button) findViewById(R.id.button_login);
        login_weixin = (ImageView) findViewById(R.id.login_weixin);
        imageview_click = findViewById(R.id.imageview_click);
        button_login.setOnClickListener(this);
        textview_register.setOnClickListener(this);
        imageview_click.setOnClickListener(this);
        try {
            manager = new DBManager(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                submit();
                break;
            case R.id.textview_register:
                startActivity(new Intent(getBaseContext(), RegionActivity.class));
                break;
            case R.id.imageview_click:
                if (!edittext_pwd.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    edittext_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    edittext_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

                break;
        }
    }

    private void submit() {
        // validate
        String phone = edittext_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }


        String pwd = edittext_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "登陆密码", Toast.LENGTH_SHORT).show();
            return;
        }
        String s = EncryptUtil.encrypt(pwd);
        // TODO validate success, do something
        persent = new LoginPersent(new LoginCall());
        persent.reqeust(phone, s);


    }

    @Override
    protected void destoryData() {

    }

    //获取数据
    class LoginCall implements DataCall<Result<UserInfo>> {

        @Override
        public void success(Result<UserInfo> data) {
            UserInfo result = data.getResult();
            if (data.getStatus().equals("0000")) {
                try {

                    manager.insertStudent(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
            } else {
                Toast.makeText(getBaseContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getBaseContext(), "异常", Toast.LENGTH_SHORT).show();
        }
    }
}
