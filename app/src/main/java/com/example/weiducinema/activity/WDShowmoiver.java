package com.example.weiducinema.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.bw.movie.R;
import com.example.weiducinema.adapter.ShowMoiverAdapter;
import com.example.weiducinema.app.SpacesItemDecoration;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.bean.PopularBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.encrypt.FindUserBean;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.db.DBManager;
import com.example.weiducinema.precener.GuanPwdPersent;
import com.example.weiducinema.precener.HotFilmPopulPresenter;
import com.example.weiducinema.precener.HotShowPopulPresenter;
import com.example.weiducinema.precener.QGuanPwdPersent;
import com.example.weiducinema.precener.UpComePopulPresenter;
import com.j256.ormlite.dao.Dao;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.SQLException;
import java.util.List;

/**
 * created by fxb
 * 2019/1/24 10:32
 */
public class WDShowmoiver extends WDBaseActivity {
    XRecyclerView xRecyclerView;
    String type ;
    RadioButton yi,er,san;
    RadioGroup group;
    ImageView fanhui;
    ShowMoiverAdapter adapter;
    Dao<UserInfo, String> userDao;
    List<UserInfo> student;
    HotFilmPopulPresenter mHotFilmPopulPresenter;
    HotShowPopulPresenter mHotFilmPopulPresenter2;
    UpComePopulPresenter mHotFilmPopulPresenter3;
    GuanPwdPersent guanPwdPersent;
    QGuanPwdPersent qGuanPwdPersent;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_move;
    }

    @Override
    protected void initView() {
        yi = findViewById(R.id.yi);
        er = findViewById(R.id.er);
        san = findViewById(R.id.san);
        fanhui = findViewById(R.id.fanhui);
        group = findViewById(R.id.group);
        mHotFilmPopulPresenter = new HotFilmPopulPresenter(new PopulData());
        mHotFilmPopulPresenter2 = new HotShowPopulPresenter(new PopulData2());
        mHotFilmPopulPresenter3 = new UpComePopulPresenter(new PopulData3());
        guanPwdPersent = new GuanPwdPersent(new Guanp());
        EventBus.getDefault().register(this);
        xRecyclerView = findViewById(R.id.xrecy);
        qGuanPwdPersent = new QGuanPwdPersent(new Qguan());
        adapter = new ShowMoiverAdapter(this);
        adapter.setOnItemClick(new ShowMoiverAdapter.onItemClick() {
            @Override
            public void tiao(String json,String guan) {
                Intent intent = new Intent(WDShowmoiver.this,WDDetailsActivity.class);
                intent .putExtra("mid",json);
                intent.putExtra("guan",guan);
                startActivity(intent);
            }


            @Override
            public void guan(int position, String id) {
                userDao = DBManager.getInstance(WDShowmoiver.this).getUserDao();
                try {
                    student = userDao.queryForAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (student.size()==0){

                    startActivity(new Intent(WDShowmoiver.this,WDLoginActivity.class));
                }else {

                    if (id.equals("2")){
                        guanPwdPersent.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"",position+"");

                    }else{
                        qGuanPwdPersent.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"",position+"");

                    }
                }

            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.yi:
                            yi.setTextColor(Color.WHITE);
                            er.setTextColor(Color.BLACK);
                            san.setTextColor(Color.BLACK);
                            mHotFilmPopulPresenter.reqeust(0+"","","1", "10");


                        break;
                    case R.id.er:
                            yi.setTextColor(Color.BLACK);
                            er.setTextColor(Color.WHITE);
                            san.setTextColor(Color.BLACK);
                            mHotFilmPopulPresenter2.reqeust(0+"","","1", "10");


                        break;
                    case R.id.san:
                            yi.setTextColor(Color.BLACK);
                            er.setTextColor(Color.BLACK);
                            san.setTextColor(Color.WHITE);
                            mHotFilmPopulPresenter3.reqeust(0+"","","1", "10");


                        break;
                }
            }
        });
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        xRecyclerView.addItemDecoration(new SpacesItemDecoration(20));
        xRecyclerView.setAdapter(adapter);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void destoryData() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveSoundRecongnizedmsg(String insType) {
        type = insType;
        if (type.equals("1")){
            Toast.makeText(this,"热门电影",Toast.LENGTH_LONG).show();
            yi.setChecked(true);
            er.setChecked(false);
            san.setChecked(false);
            yi.setTextColor(Color.WHITE);
            er.setTextColor(Color.BLACK);
            san.setTextColor(Color.BLACK);
            mHotFilmPopulPresenter.reqeust(0+"","","1","10");
        }else if (type.equals("2")){
            yi.setChecked(false);
            er.setChecked(true);
            san.setChecked(false);
            yi.setTextColor(Color.BLACK);
            er.setTextColor(Color.WHITE);
            san.setTextColor(Color.BLACK);
            mHotFilmPopulPresenter2.reqeust(0+"","","1","10");
        }else{
            yi.setChecked(false);
            er.setChecked(false);
            san.setChecked(true);
            yi.setTextColor(Color.BLACK);
            er.setTextColor(Color.BLACK);
            san.setTextColor(Color.WHITE);

            mHotFilmPopulPresenter3.reqeust(0+"","","1","10");
        }
    }



    private class PopulData implements DataCall<Result<List<PopularBean>>> {

        @Override
        public void success(Result<List<PopularBean>> data) {
            if (data.getStatus().equals("0000")){

                adapter.setList(data.getResult());
                adapter.notifyDataSetChanged();
            }else{
                for (int i=0;i<student.size();i++){
                    try {
                        userDao.delete(student.get(i));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class PopulData2 implements DataCall<Result<List<PopularBean>>> {


        @Override
        public void success(Result<List<PopularBean>> data) {
            if (data.getStatus().equals("0000")){

                adapter.setList(data.getResult());
                adapter.notifyDataSetChanged();
            }else {
                for (int i=0;i<student.size();i++){
                    try {
                        userDao.delete(student.get(i));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class PopulData3 implements DataCall<Result<List<PopularBean>>>  {

        @Override
        public void success(Result<List<PopularBean>> data) {
            if (data.getStatus().equals("0000")){
                adapter.setList(data.getResult());
                adapter.notifyDataSetChanged();
            }else{
                for (int i=0;i<student.size();i++){
                    try {
                        userDao.delete(student.get(i));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private class Guanp implements DataCall<Result> {
        @Override
        public void success(Result data){
            if (data.getStatus().equals("0000")){
                Toast.makeText(WDShowmoiver.this,data.getMessage(),Toast.LENGTH_LONG).show();
                mHotFilmPopulPresenter.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"","1", "10");
            }else{
                for (int i=0;i<student.size();i++){
                    try {
                        userDao.delete(student.get(i));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getBaseContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WDShowmoiver.this,WDLoginActivity.class));
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class Qguan implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")){
                Toast.makeText(WDShowmoiver.this,data.getMessage(),Toast.LENGTH_LONG).show();
                mHotFilmPopulPresenter.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"","1", "10");
            }else{
                for (int i=0;i<student.size();i++){
                    try {
                        userDao.delete(student.get(i));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getBaseContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WDShowmoiver.this,WDLoginActivity.class));
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
