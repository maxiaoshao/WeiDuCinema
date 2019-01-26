package com.example.weiducinema.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.adapter.QueryPaiAdapter;
import com.example.weiducinema.app.SpacesItemDecoration;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.QueryPaiBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.precener.QueryPaiPrencenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * created by fxb
 * 2019/1/26 19:43
 */
public class SchedulingActivity extends WDBaseActivity {
    TextView name,adress,imaguri,txt_title,txt_site;
    SimpleDraweeView sim;
    QueryPaiPrencenter queryPaiPrencenter;
    QueryPaiAdapter queryPaiAdapter;
    RecyclerView recyclerView;
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
        queryPaiPrencenter.reqeust(yingid,movieid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        recyclerView.setAdapter(queryPaiAdapter);
    }

    @Override
    protected void destoryData() {

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
}
