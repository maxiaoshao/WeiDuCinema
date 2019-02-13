package com.example.weiducinema.activity.my;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.adapter.SysAdapter;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.SystemMassage;
import com.example.weiducinema.bean.TicketBean;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.db.DBManager;
import com.example.weiducinema.precener.SystemPersent;
import com.j256.ormlite.dao.Dao;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.sql.SQLException;
import java.util.List;

public class SystemsActivity extends WDBaseActivity implements View.OnClickListener {


    private XRecyclerView sys_recycle;
    private ImageView sys_back;
    private SystemPersent persent;
    private Dao<UserInfo, String> userDao;
    private List<UserInfo> student;
    private SysAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_systems;
    }

    @Override
    protected void initView() {
        sys_recycle = findViewById(R.id.sys_recycle);
        sys_back = findViewById(R.id.sys_back);
        sys_back.setOnClickListener(this);

        try {
            userDao = DBManager.getInstance(this).getUserDao();
            student = userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapter = new SysAdapter(getBaseContext());

        persent = new SystemPersent(new SystemCall());
        persent.reqeust(student.get(0).getUserId(),student.get(0).getSessionId());
    }

    @Override
    protected void destoryData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sys_back:
                finish();
                break;
        }
    }

    class SystemCall implements DataCall<Result<List<SystemMassage>>> {

        @Override
        public void success(Result<List<SystemMassage>> result) {
            if (result.getStatus().equals("0000")) {
                Toast.makeText(SystemsActivity.this, result.getMessage() + "成功", Toast.LENGTH_SHORT).show();
                List<SystemMassage> result1 = result.getResult();
                adapter.adAll(result1);
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(SystemsActivity.this, e.getMessage() + "失败", Toast.LENGTH_SHORT).show();
        }
    }
}
