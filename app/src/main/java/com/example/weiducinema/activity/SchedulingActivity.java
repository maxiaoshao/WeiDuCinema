package com.example.weiducinema.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.activity.cinema.CinemaChooseActivity;
import com.example.weiducinema.adapter.CinemaTimeAdapter;
import com.example.weiducinema.adapter.QueryPaiAdapter;
import com.example.weiducinema.app.SpacesItemDecoration;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.EventMessage;
import com.example.weiducinema.bean.FilmTimeBean;
import com.example.weiducinema.bean.MessageBean;
import com.example.weiducinema.bean.QueryPaiBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.precener.FilmTimePersent;
import com.example.weiducinema.precener.QueryPaiPrencenter;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * created by fxb
 * 2019/1/26 19:43
 */
public class SchedulingActivity extends WDBaseActivity implements QueryPaiAdapter.onItemClick {
    TextView name,adress,imaguri,txt_title,txt_site;
    SimpleDraweeView sim;
    QueryPaiPrencenter queryPaiPrencenter;
    QueryPaiAdapter queryPaiAdapter;
    RecyclerView recyclerView;
    private FilmTimePersent filmTimePersent;
    private CinemaTimeAdapter timeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scheduling;
    }

    @Override
    protected void initView() {
        Intent intent  = getIntent();
        String names = intent.getStringExtra("name");
        String adress = intent.getStringExtra("adress");
        String movieid = intent.getStringExtra("movieid");
        String imageuri = intent.getStringExtra("imageuri");
        String yingid = intent.getStringExtra("yingid");
        txt_title = findViewById(R.id.txt_title);
        txt_site = findViewById(R.id.txt_site);
        sim = findViewById(R.id.ying_sim);
        txt_title.setText(names);
        txt_site.setText(adress);
        sim.setImageURI(imageuri);
        recyclerView = findViewById(R.id.ying_recy);
        queryPaiPrencenter = new QueryPaiPrencenter(new Pai());
        queryPaiAdapter = new QueryPaiAdapter(this);
        queryPaiAdapter.setOnItemClick(this);
        queryPaiPrencenter.reqeust(yingid,movieid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        recyclerView.setAdapter(queryPaiAdapter);

        filmTimePersent = new FilmTimePersent(new FilmTimeCall());
        timeAdapter = new CinemaTimeAdapter(this);

    }

    @Override
    protected void destoryData() {

    }


    @Override
    public void byValue(String name, int price,String pid) {
        EventBus.getDefault().postSticky(new MessageBean(name,price,pid));
        startActivity(new Intent(this,CinemaChooseActivity.class));
    }

    private class Pai implements DataCall<Result<List<QueryPaiBean>>> {
        @Override
        public void success(Result<List<QueryPaiBean>> data) {
            queryPaiAdapter.setList(data.getResult());
            queryPaiAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

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
}
