package com.example.weiducinema.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bw.movie.R;
import com.example.weiducinema.activity.LoginActivity;
import com.example.weiducinema.base.BaseFragment;

import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.bean.encrypt.UserInfoBean;
import com.example.weiducinema.db.DBManager;
import com.facebook.drawee.view.SimpleDraweeView;

import java.sql.SQLException;
import java.util.List;

import static com.baidu.location.d.a.e;
import static com.baidu.location.d.a.m;
import static com.baidu.location.d.a.s;


/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/23
 */
public class My_Fragment extends BaseFragment implements View.OnClickListener {
    private SimpleDraweeView my_pic;
    private TextView my_name;
    private Button my_sign;
    private LinearLayout my_message;
    private LinearLayout my_attention;
    private LinearLayout my_goupiao;
    private LinearLayout my_tickling;
    private LinearLayout my_new_versions;
    private LinearLayout my_finish;
    private DBManager manager;
    private List<UserInfo> student;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_my_layout;
    }

    @Override
    protected void initView(View view) {
        my_pic = view.findViewById(R.id.my_pic);
        my_name = view.findViewById(R.id.my_name);
        my_sign = view.findViewById(R.id.my_sign);
        my_message = view.findViewById(R.id.my_message);
        my_attention = view.findViewById(R.id.my_attention);
        my_goupiao = view.findViewById(R.id.my_goupiao);
        my_tickling = view.findViewById(R.id.my_tickling);
        my_new_versions = view.findViewById(R.id.my_new_versions);
        my_finish = view.findViewById(R.id.my_finish);
        my_pic.setOnClickListener(this);

        try {
            manager = new DBManager(getActivity());
            student = manager.getStudent();
            if(student.size()==0){

                UserInfo userInfo = student.get(0);
                UserInfoBean userInfo1 = userInfo.getUserInfo();
                String pic = userInfo1.getHeadPic();
                my_pic.setImageURI(pic);
                String nickName = userInfo1.getNickName();
                my_name.setText(nickName);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_pic:
                if(student.size()==0) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else {
                    Toast.makeText(getActivity(),"更换头像",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
