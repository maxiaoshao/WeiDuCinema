package com.example.weiducinema.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.encrypt.EncryptUtil;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.precener.RegionPersent;


public class WDRegionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edittext_name;
    private EditText edittext_gender;
    private EditText edittext_data;
    private EditText edittext_phone;
    private EditText edittext_email;
    private EditText edittext_login;
    private Button button_register;
    private RegionPersent persent;
    private int sexNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        initView();

    }

    private void initView() {
        edittext_name = (EditText) findViewById(R.id.edittext_name);
        edittext_gender = (EditText) findViewById(R.id.edittext_gender);
        edittext_data = (EditText) findViewById(R.id.edittext_data);
        edittext_phone = (EditText) findViewById(R.id.edittext_phone);
        edittext_email = (EditText) findViewById(R.id.edittext_email);
        edittext_login = (EditText) findViewById(R.id.edittext_login);
        button_register = (Button) findViewById(R.id.button_register);

        button_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register:
               submit();
                break;
        }
    }

    private void submit() {
        // validate
        String name = edittext_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "昵称", Toast.LENGTH_SHORT).show();
            return;
        }

        String gender = edittext_gender.getText().toString().trim();
        if (TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "性别", Toast.LENGTH_SHORT).show();
            return;
        }

        String data = edittext_data.getText().toString().trim();
        if (TextUtils.isEmpty(data)) {
            Toast.makeText(this, "出生日期", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = edittext_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = edittext_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        String login = edittext_login.getText().toString().trim();
        if (TextUtils.isEmpty(login)) {
            Toast.makeText(this, "登陆密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(gender.equals("男")){
            sexNum = 1;
        }else if(gender.equals("女")){
            sexNum = 2;
        }

        String s = EncryptUtil.encrypt(login);
        // TODO validate success, do something
        persent = new RegionPersent(new RegionCall());
        persent.reqeust(name,phone,s,s,sexNum,data,"123456","小米/红米","5.0","android",email);

    }
    //获取数据
    class RegionCall implements DataCall<Result> {

        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")) {
                finish();
            }
            Toast.makeText(getBaseContext(),data.getMessage(),Toast.LENGTH_SHORT).show();
        }
        @Override
        public void fail(ApiException e) {
            Toast.makeText(getBaseContext(),"异常",Toast.LENGTH_SHORT).show();

        }
    }
}