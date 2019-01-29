package com.example.weiducinema.activity.my;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.activity.WDLoginActivity;
import com.example.weiducinema.adapter.MyAdapter;
import com.example.weiducinema.app.SpacesItemDecoration;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.TicketBean;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.bean.encrypt.UserInfoBean;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.db.DBManager;
import com.example.weiducinema.precener.BuyTicketMoviePersent;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MyTrackerActivity extends WDBaseActivity implements View.OnClickListener {

    private Button ticket_wait_money;
    private Button ticket_finish;
    private RecyclerView ticket_recy;
    private ImageView img_back;
    private BuyTicketMoviePersent moviePersent;
    private MyAdapter adapter;
    private int id;
    private Dao<UserInfo, String> userDao;
    private List<UserInfo> stedent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my__tracker_;
    }

    @Override
    protected void initView() {
        ticket_wait_money = findViewById(R.id.ticket_wait_money);
        ticket_finish = findViewById(R.id.ticket_finish);
        ticket_recy = findViewById(R.id.ticket_recy);
        img_back = findViewById(R.id.img_back);

        ticket_wait_money.setOnClickListener(this);
        ticket_finish.setOnClickListener(this);
        img_back.setOnClickListener(this);

        ticket_wait_money.setBackgroundResource(R.drawable.btn_gradient);
        ticket_wait_money.setTextColor(Color.WHITE);
        ticket_finish.setBackgroundResource(R.drawable.btn_wu);
        ticket_finish.setTextColor(Color.BLACK);
        try {
            userDao = DBManager.getInstance(this).getUserDao();
            stedent = userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (stedent.size()==0){
            startActivity(new Intent(MyTrackerActivity.this,WDLoginActivity.class));
        }else {
            moviePersent = new BuyTicketMoviePersent(new MyCall());
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            ticket_recy.setLayoutManager(manager);
            adapter = new MyAdapter(this);
            ticket_recy.setAdapter(adapter);
            moviePersent.reqeust(stedent.get(0).getUserId() + "", stedent.get(0).getSessionId() + "", 1, 5, 1);
            ticket_recy.addItemDecoration(new SpacesItemDecoration(20));

        }

    }

    @Override
    protected void destoryData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ticket_wait_money:
                id = 1;
                adapter.clearIteam();
                ticket_wait_money.setBackgroundResource(R.drawable.btn_gradient);
                ticket_wait_money.setTextColor(Color.WHITE);
                ticket_finish.setBackgroundResource(R.drawable.btn_wu);
                ticket_finish.setTextColor(Color.BLACK);
                moviePersent.reqeust(stedent.get(0).getUserId()+"", stedent.get(0).getSessionId()+"", 1, 5, id);
                adapter.notifyDataSetChanged();
                break;
            case R.id.ticket_finish:
                id = 2;
                adapter.clearIteam();
                moviePersent.reqeust(stedent.get(0).getUserId()+"", stedent.get(0).getSessionId()+"", 1, 5, id);
                ticket_finish.setBackgroundResource(R.drawable.btn_gradient);
                ticket_finish.setTextColor(Color.WHITE);
                ticket_wait_money.setBackgroundResource(R.drawable.btn_wu);
                ticket_wait_money.setTextColor(Color.BLACK);
                adapter.notifyDataSetChanged();
                break;
        }

    }
    class MyCall implements DataCall<Result<List<TicketBean>>> {

        @Override
        public void success(Result<List<TicketBean>> result) {
            if (result.getStatus().equals("0000")) {
                Log.d("qqq", "success: " + result.toString());
                Toast.makeText(MyTrackerActivity.this, result.getMessage() + "成功", Toast.LENGTH_SHORT).show();
                List<TicketBean> result1 = result.getResult();
                adapter.addList(result1);
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(MyTrackerActivity.this, e.getMessage() + "失败", Toast.LENGTH_SHORT).show();
        }
    }
}
