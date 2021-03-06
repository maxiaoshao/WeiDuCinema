package com.example.weiducinema.activity.my;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.activity.WDLoginActivity;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.encrypt.FindUserBean;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.db.DBManager;
import com.example.weiducinema.precener.FindUserPersent;
import com.example.weiducinema.precener.HeardPicPersent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.j256.ormlite.dao.Dao;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class My_Message_Activity extends WDBaseActivity implements View.OnClickListener {

    private SimpleDraweeView img_pic;
    private TextView user_name;
    private TextView user_sex;
    private TextView user_data;
    private TextView user_phone;
    private TextView user_email;
    private ImageView img_reset;
    private DBManager manager;
    private List<UserInfo> student;
    private FindUserPersent persent;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private View diogView;
    private Button btn_paizhao;
    private Button btn_xiangce;
    private String path = Environment.getExternalStorageDirectory() + "/head.jpg";
    private ImageView img_back;
    private Dao<UserInfo, String> userDao;
    private HeardPicPersent heardPicPersent;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my__message_;
    }

    @Override
    protected void initView() {
        img_pic = (SimpleDraweeView) findViewById(R.id.img_pic);
        user_name = (TextView) findViewById(R.id.user_name);
        user_sex = (TextView) findViewById(R.id.user_sex);
        user_data = (TextView) findViewById(R.id.user_data);
        user_phone = (TextView) findViewById(R.id.user_phone);
        user_email = (TextView) findViewById(R.id.user_email);
        img_reset = (ImageView) findViewById(R.id.img_reset);
        img_back = (ImageView) findViewById(R.id.img_back);
        persent = new FindUserPersent(new FindUserCall());
        img_back.setOnClickListener(this);
        img_pic.setOnClickListener(this);
        img_reset.setOnClickListener(this);

        try {
            userDao = DBManager.getInstance(this).getUserDao();
            student = userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onResume() {
        super.onResume();
        try {
             userDao = DBManager.getInstance(this).getUserDao();
             student = userDao.queryForAll();
            if (student.size()!=0) {
                UserInfo info = student.get(0);
                int userId = info.getUserId();
                String sessionId = info.getSessionId();
                persent.reqeust(userId, sessionId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.img_reset:
                if (student.size()!=0){
                    startActivity(new Intent(this,MyChangePasswordActivity.class));
                }else {
                    Toast.makeText(this,"请先登录",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,WDLoginActivity.class));
            }

                break;
        }
    }

    //获取数据
    class FindUserCall implements DataCall<Result<FindUserBean>> {

        @Override
        public void success(Result<FindUserBean> data) {
            if (data.getStatus().equals("0000")) {
                FindUserBean result = data.getResult();
                img_pic.setImageURI(Uri.parse(result.getHeadPic()));
                user_name.setText(result.getNickName());
                user_phone.setText(result.getPhone());
                user_data.setText("1998-02-09");
                if (result.getSex()==1) {
                    user_sex.setText("男");
                }else {
                    user_sex.setText("女");
                }
            } else {
                Toast.makeText(getBaseContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(),WDLoginActivity.class));
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getBaseContext(), "异常", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void destoryData() {

    }

}
