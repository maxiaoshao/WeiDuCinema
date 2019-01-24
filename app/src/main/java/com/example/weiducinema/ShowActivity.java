package com.example.weiducinema;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bw.movie.R;
import com.example.weiducinema.core.base.BaseActivity;
import com.example.weiducinema.fragment.Cinema_Fragment;
import com.example.weiducinema.fragment.Filmfragment;
import com.example.weiducinema.fragment.My_Fragment;

public class ShowActivity extends BaseActivity implements View.OnClickListener {


    private FrameLayout frag;
    private ImageView pageactivity_imagefilm;
    private ImageView pageactivity_imagecinema;
    private ImageView pageactivity_imagemy;
    private Filmfragment filmfragment;
    private Cinema_Fragment cinema_fragment;
    private My_Fragment my_fragment;
    FragmentManager manager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        frag = findViewById(R.id.frag);
        pageactivity_imagefilm = findViewById(R.id.pageactivity_imagefilm);
        pageactivity_imagecinema = findViewById(R.id.pageactivity_imagecinema);
        pageactivity_imagemy = findViewById(R.id.pageactivity_imagemy);

        pageactivity_imagefilm.setOnClickListener(this);
        pageactivity_imagecinema.setOnClickListener(this);
        pageactivity_imagemy.setOnClickListener(this);
        //设置点击事件
        initdata();
    }

    public void initdata(){


        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frag,new Filmfragment()).commit();
        //变大
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator o1 = ObjectAnimator.ofFloat(pageactivity_imagefilm, "scaleX",1.17f);
        ObjectAnimator o2 = ObjectAnimator.ofFloat(pageactivity_imagecinema, "scaleX",1.0f );
        ObjectAnimator o3 = ObjectAnimator.ofFloat(pageactivity_imagemy, "scaleX",1.0f );

        ObjectAnimator o4 = ObjectAnimator.ofFloat(pageactivity_imagefilm,"scaleY",1.17f);
        ObjectAnimator o5 = ObjectAnimator.ofFloat(pageactivity_imagecinema,"scaleY",1.0f);
        ObjectAnimator o6 = ObjectAnimator.ofFloat(pageactivity_imagemy,"scaleY",1.0f);

        //存入集合
        set.playTogether(o1,o2,o3,o4,o5,o6);
        //开始执行
        set.start();

    }
    @Override
    protected void destoryData() {

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
             case R.id.pageactivity_imagefilm:
                 manager = getSupportFragmentManager();
                 manager.beginTransaction().replace(R.id.frag,new Filmfragment()).commit();
                 //属性动画改变图片大小
                 AnimatorSet set = new AnimatorSet();
                 ObjectAnimator o1 = ObjectAnimator.ofFloat(pageactivity_imagefilm, "scaleX",1.17f);
                 ObjectAnimator o2 = ObjectAnimator.ofFloat(pageactivity_imagecinema, "scaleX",1.0f );
                 ObjectAnimator o3 = ObjectAnimator.ofFloat(pageactivity_imagemy, "scaleX",1.0f );

                 ObjectAnimator o4 = ObjectAnimator.ofFloat(pageactivity_imagefilm,"scaleY",1.17f);
                 ObjectAnimator o5 = ObjectAnimator.ofFloat(pageactivity_imagecinema,"scaleY",1.0f);
                 ObjectAnimator o6 = ObjectAnimator.ofFloat(pageactivity_imagemy,"scaleY",1.0f);

                 //存入集合
                 set.playTogether(o1,o2,o3,o4,o5,o6);
                 //开始执行
                 set.start();
                 break;
             case R.id.pageactivity_imagecinema:
                 manager = getSupportFragmentManager();
                 manager.beginTransaction().replace(R.id.frag,new Cinema_Fragment()).commit();
                 //属性动画改变图片大小
                 AnimatorSet set1 = new AnimatorSet();
                 ObjectAnimator o11 = ObjectAnimator.ofFloat(pageactivity_imagefilm, "scaleX",1.0f);
                 ObjectAnimator o21 = ObjectAnimator.ofFloat(pageactivity_imagecinema, "scaleX",1.17f );
                 ObjectAnimator o31 = ObjectAnimator.ofFloat(pageactivity_imagemy, "scaleX",1.0f );

                 ObjectAnimator o41 = ObjectAnimator.ofFloat(pageactivity_imagefilm,"scaleY",1.0f);
                 ObjectAnimator o51 = ObjectAnimator.ofFloat(pageactivity_imagecinema,"scaleY",1.17f);
                 ObjectAnimator o61 = ObjectAnimator.ofFloat(pageactivity_imagemy,"scaleY",1.0f);

                 //存入集合
                 set1.playTogether(o11,o21,o31,o41,o51,o61);
                 //开始执行
                 set1.start();
                 break;
             case R.id.pageactivity_imagemy:
                 manager = getSupportFragmentManager();
                 manager.beginTransaction().replace(R.id.frag,new My_Fragment()).commit();
                 //属性动画改变图片大小
                 AnimatorSet set2 = new AnimatorSet();
                 ObjectAnimator o12 = ObjectAnimator.ofFloat(pageactivity_imagefilm, "scaleX",1.0f);
                 ObjectAnimator o22 = ObjectAnimator.ofFloat(pageactivity_imagecinema, "scaleX",1.0f );
                 ObjectAnimator o32 = ObjectAnimator.ofFloat(pageactivity_imagemy, "scaleX",1.17f );

                 ObjectAnimator o42 = ObjectAnimator.ofFloat(pageactivity_imagefilm,"scaleY",1.0f);
                 ObjectAnimator o52 = ObjectAnimator.ofFloat(pageactivity_imagecinema,"scaleY",1.0f);
                 ObjectAnimator o62 = ObjectAnimator.ofFloat(pageactivity_imagemy,"scaleY",1.17f);

                 //存入集合
                 set2.playTogether(o12,o22,o32,o42,o52,o62);
                 //开始执行
                 set2.start();
                 break;
         }
    }
}
