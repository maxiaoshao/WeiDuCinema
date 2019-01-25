package com.example.weiducinema.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bw.movie.R;
import com.example.weiducinema.activity.LoginActivity;
import com.example.weiducinema.activity.my.My_Attention_Activity;
import com.example.weiducinema.activity.my.My_Message_Activity;
import com.example.weiducinema.activity.my.My_New_Versions_Activity;
import com.example.weiducinema.activity.my.My_Opinion_Activity;
import com.example.weiducinema.activity.my.My_Tracker_Activity;
import com.example.weiducinema.base.BaseFragment;

import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.bean.encrypt.UserInfoBean;
import com.example.weiducinema.db.DBManager;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
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
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private View diogView;
    private Button btn_paizhao;
    private Button btn_xiangce;
    private String path = Environment.getExternalStorageDirectory() + "/head.jpg";

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
        //添加点击事件
        my_pic.setOnClickListener(this);
        my_message.setOnClickListener(this);
        my_attention.setOnClickListener(this);
        my_goupiao.setOnClickListener(this);
        my_tickling.setOnClickListener(this);
        my_new_versions.setOnClickListener(this);
        my_finish.setOnClickListener(this);
        //数据库查询
        try {
            manager = new DBManager(getActivity());
            student = manager.getStudent();
            if(student.size()==0) {
             Toast.makeText(getActivity(),"没有信息"+student,Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getActivity(),"有信息"+student,Toast.LENGTH_SHORT).show();
                System.out.println("有信息"+student);
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
        switch (v.getId()) {
            case R.id.my_pic:
                if (student.size() == 0) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.my_message:
                    startActivity(new Intent(getActivity(), My_Message_Activity.class));
                break;
            case R.id.my_attention://我的关注
                startActivity(new Intent(getActivity(), My_Attention_Activity.class));
                break;
            case R.id.my_goupiao:
                startActivity(new Intent(getActivity(), My_Tracker_Activity.class));
                break;
            case R.id.my_tickling:
                startActivity(new Intent(getActivity(), My_Opinion_Activity.class));
                break;
            case R.id.my_new_versions:
                startActivity(new Intent(getActivity(), My_New_Versions_Activity.class));
                break;
            case R.id.my_finish:
                if (student.size()!=0){
                    for(int i = 0;i<student.size();i++){
                        try {
                            manager.deleteStudent(student.get(i));
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    Intent intent5 = new Intent(getActivity(), LoginActivity.class);
                    intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent5);//跳转登录页
                    getActivity().finish();//本页面关闭

                }

                break;

        }
    }
}
