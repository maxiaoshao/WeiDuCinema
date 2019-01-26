package com.example.weiducinema.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.adapter.AppropriateAdapter;
import com.example.weiducinema.app.SpacesItemDecoration;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.QueryBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.precener.QuerePrencenter;

import java.util.List;

/**
 * created by fxb
 * 2019/1/26 15:33
 */
public class AppropriateActivity extends WDBaseActivity {
    TextView tname;
    RecyclerView recyclerView;
    AppropriateAdapter appropriateAdapter;
    QuerePrencenter querePrencenter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_appropriate;
    }

    @Override
    protected void initView() {
        tname = findViewById(R.id.name);
        recyclerView = findViewById(R.id.recy);
        initData();
    }

    private void initData() {
        querePrencenter = new QuerePrencenter(new Query());
        final Intent intent = getIntent();
        final String movieid = intent.getStringExtra("movieid");
        String moviename = intent.getStringExtra("moviename");
        final String imageuri = intent.getStringExtra("imageuri");
        tname.setText(moviename);
        querePrencenter.reqeust(movieid);
        appropriateAdapter = new AppropriateAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        recyclerView.setAdapter(appropriateAdapter);
        appropriateAdapter.setOnItemClick(new AppropriateAdapter.onItemClick() {
            @Override
            public void tiao(String json, String adress,String id) {
                Intent intent1 = new Intent(AppropriateActivity.this,SchedulingActivity.class);
                intent1.putExtra("name",json);
                intent1.putExtra("adress",adress);
                intent1.putExtra("movieid",movieid);
                intent1.putExtra("imageuri",imageuri);
                intent1.putExtra("yingid",id);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void destoryData() {

    }

    private class Query implements DataCall<Result<List<QueryBean>>> {


        @Override
        public void success(Result<List<QueryBean>> data) {
            appropriateAdapter.setData(data.getResult());
            appropriateAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
