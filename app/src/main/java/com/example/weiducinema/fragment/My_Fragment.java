package com.example.weiducinema.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bw.movie.R;
import com.example.weiducinema.activity.LoginActivity;
import com.example.weiducinema.base.BaseFragment;


/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/23
 */
public class My_Fragment extends BaseFragment implements View.OnClickListener {
    private ImageView my_pic;
    private TextView my_name;
    private Button my_sign;
    private LinearLayout my_message;
    private LinearLayout my_attention;
    private LinearLayout my_goupiao;
    private LinearLayout my_tickling;
    private LinearLayout my_new_versions;
    private LinearLayout my_finish;

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
//        my_message = view.findViewById(R.id.my_message);
//        my_attention = view.findViewById(R.id.my_attention);
//        my_goupiao = view.findViewById(R.id.my_goupiao);
//        my_tickling = view.findViewById(R.id.my_tickling);
//        my_new_versions = view.findViewById(R.id.my_new_versions);
//        my_finish = view.findViewById(R.id.my_finish);
        my_pic.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_pic:
                startActivity(new Intent(getActivity(),LoginActivity.class));
                break;
        }
    }
}
