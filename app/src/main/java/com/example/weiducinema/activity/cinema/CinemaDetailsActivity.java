package com.example.weiducinema.activity.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.adapter.CinemaTimeAdapter;
import com.example.weiducinema.adapter.RecycleCinemaDetailsAdapter;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.EventMessage;
import com.example.weiducinema.bean.FilmTimeBean;
import com.example.weiducinema.bean.MessageBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.ScheduleBean;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.precener.FilmTimePersent;
import com.example.weiducinema.precener.SchedulePersent;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

public class CinemaDetailsActivity extends WDBaseActivity implements RecycleCinemaDetailsAdapter.ByValue, CinemaTimeAdapter.onItemClick {

    private SimpleDraweeView sdv_cinema_head;
    private TextView txt_title;
    private TextView txt_site;
    private RecyclerCoverFlow recy_carousel;
    private RecyclerView rv_time;
    private ImageView img_return;
    private SchedulePersent persent;
    private RecycleCinemaDetailsAdapter cinemaDetailsAdapter;
    private String cinemaId;
    private String img_pic;
    private String ciname_name;
    private String address;
    private FilmTimePersent filmTimePersent;
    private CinemaTimeAdapter timeAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_cinema_details;
    }

    @Override
    protected void initView() {
        sdv_cinema_head = (SimpleDraweeView) findViewById(R.id.sdv_cinema_head);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_site = (TextView) findViewById(R.id.txt_site);
        recy_carousel = (RecyclerCoverFlow) findViewById(R.id.recy_carousel);
        rv_time = (RecyclerView) findViewById(R.id.rv_time);
        img_return = (ImageView) findViewById(R.id.img_return);
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void cinemaId(EventMessage message){
        cinemaId = message.getCinemaId();
        img_pic = message.getImg_pic();
        ciname_name = message.getCiname_name();
        address = message.getAddress();
    }





    public void initData(){
         sdv_cinema_head.setImageURI(img_pic);
         txt_title.setText(ciname_name);
         txt_site.setText(address);
         persent = new SchedulePersent(new ScheduleCall());
         cinemaDetailsAdapter = new RecycleCinemaDetailsAdapter(this);
         cinemaDetailsAdapter.setByvalue(this);
         persent.reqeust(cinemaId);
         filmTimePersent = new FilmTimePersent(new FilmTimeCall());
         timeAdapter = new CinemaTimeAdapter(this);
         timeAdapter.setOnItemClick(this);
         rv_time.setAdapter(timeAdapter);
         rv_time.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public void Cid(int id) {
        System.out.println("XXXXXXX"+id);
        filmTimePersent.reqeust(cinemaId,id);
    }

    @Override
    public void byValue(String name,int price) {
        EventBus.getDefault().postSticky(new MessageBean(name,price));
        startActivity(new Intent(this,CinemaChooseActivity.class));
    }


    //获取数据
    class ScheduleCall implements DataCall<Result<List<ScheduleBean>>> {

        @Override
        public void success(Result<List<ScheduleBean>> data) {
            if (data.getStatus().equals("0000")) {
                final List<ScheduleBean> result = data.getResult();
                cinemaDetailsAdapter.setList(result);
                recy_carousel.setAdapter(cinemaDetailsAdapter);
            } else {
                Toast.makeText(getBaseContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getBaseContext(), "异常", Toast.LENGTH_SHORT).show();
        }
    }

    //获取数据
    class FilmTimeCall implements DataCall<Result<List<FilmTimeBean>>> {

        @Override
        public void success(Result<List<FilmTimeBean>> data) {
            if (data.getStatus().equals("0000")) {
                List<FilmTimeBean> result = data.getResult();
                timeAdapter.clien();
                timeAdapter.addAll(result);
                timeAdapter.notifyDataSetChanged();

            } else {
                Toast.makeText(getBaseContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getBaseContext(), "异常", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void destoryData() {
        EventBus.getDefault().unregister(this);
    }
}
