package com.example.weiducinema.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.core.exception.ApiException;

import com.example.weiducinema.db.DBManager;
import com.example.weiducinema.precener.LoginPersent;
import com.example.weiducinema.uitls.Validator;
import com.example.weiducinema.uitls.WeiXinUtil;
import com.j256.ormlite.dao.Dao;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.utils.ILog;

import java.sql.SQLException;


public class WDLoginActivity extends WDBaseActivity implements View.OnClickListener {

    private EditText edittext_phone;
    private EditText edittext_pwd;
    private CheckBox check_remember;
    private CheckBox check_autologin;
    private TextView textview_register;
    private Button button_login;
    private ImageView login_weixin;
    private LoginPersent persent;
    private ImageView imageview_click;
    private Dao<UserInfo,String> userDao;
    private IWXAPI api;
    private SharedPreferences sp;
    private String phone;
    private String pwd;


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
        login_weixin.setOnClickListener(this);
        userDao = DBManager.getInstance(this).getUserDao();

        //
        sp = getSharedPreferences("sxx", MODE_PRIVATE);
        boolean jz = sp.getBoolean("jz", false);
        //判断是否记住密码
        if (jz == true) {
            String phone_name = sp.getString("phone", "");
            String phone_pwd = sp.getString("pwd", "");
            edittext_phone.setText(phone_name);
            edittext_pwd.setText(phone_pwd);
            check_remember.setChecked(true);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                submit();
                break;
            case R.id.textview_register:
                startActivity(new Intent(getBaseContext(), WDRegionActivity.class));
                break;
            case R.id.imageview_click:
                if (!edittext_pwd.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    edittext_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    edittext_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

                break;
            case R.id.login_weixin:
                if (!WeiXinUtil.success(this)) {
                    Toast.makeText(this, "请先安装应用", Toast.LENGTH_SHORT).show();
                } else {
                    //  验证
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test";
                    WeiXinUtil.reg(this).sendReq(req);

                    finish();
                }

                break;
        }
    }

    private void submit() {
        // validate
        phone = edittext_phone.getText().toString().trim();
        boolean mobile = Validator.isMobile(phone);
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }else if(!mobile){
            Toast.makeText(this, "手机号格式不对", Toast.LENGTH_SHORT).show();
           return;
        }


        pwd = edittext_pwd.getText().toString().trim();
        boolean password = Validator.isPassword(pwd);
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "登陆密码", Toast.LENGTH_SHORT).show();
            return;
        }else if(!password){
            Toast.makeText(this, "密码格式不对", Toast.LENGTH_SHORT).show();
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
            result.getUserInfo().setSign("2");
            if (data.getStatus().equals("0000")) {
                SharedPreferences.Editor editor = sp.edit();
                if (check_remember.isChecked()) {
                    editor.putString("phone", phone);
                    editor.putString("pwd", pwd);
                    editor.putBoolean("jz", true);
                    editor.commit();
                } else {
                    try {
                        editor.putString("phone", phone);
                        editor.putString("pwd", pwd);
                        editor.putBoolean("jz", false);
                        editor.commit();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


                try {
                    userDao.createOrUpdate(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
            }
                Toast.makeText(getBaseContext(), data.getMessage(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getBaseContext(), "请检查信息", Toast.LENGTH_SHORT).show();
        }
    }

}
