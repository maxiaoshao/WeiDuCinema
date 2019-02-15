package com.example.weiducinema.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.activity.WDLoginActivity;
import com.example.weiducinema.activity.WDShowmoiver;
import com.example.weiducinema.activity.cinema.CinemaDetailsActivity;
import com.example.weiducinema.adapter.YuanTuiAdaptr;
import com.example.weiducinema.app.SpacesItemDecoration;
import com.example.weiducinema.base.WDBaseFragment;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.bean.EventMessage;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.YuantuiBean;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.db.DBManager;
import com.example.weiducinema.precener.GuanCinmearPersent;
import com.example.weiducinema.precener.YuanCinemaFuPersent;
import com.example.weiducinema.precener.YuanCinemaPersent;
import com.j256.ormlite.dao.Dao;

import org.greenrobot.eventbus.EventBus;

import java.sql.SQLException;
import java.util.List;

import static com.bw.movie.R.drawable.btn_gradient;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/23
 */
public class WDCinemaFragment extends WDBaseFragment implements YuanTuiAdaptr.onItemClick {
    RecyclerView recy_tui;
    Button tui,fu;
    Dao<UserInfo, String> userDao;
    List<UserInfo> student;
    YuanTuiAdaptr adaptr;
    GuanCinmearPersent guanCinmearPersent;
    YuanCinemaFuPersent yuanCinemaFuPersent;
    YuanCinemaPersent yuanCinemaPersent;
    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_cinema_layout;
    }

    @Override
    protected void initView(View view) {
        userDao = DBManager.getInstance(getActivity()).getUserDao();
        try {
            student = userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tui = view.findViewById(R.id.tui);
        fu = view.findViewById(R.id.fu);
        adaptr = new YuanTuiAdaptr(getActivity());
        yuanCinemaPersent = new YuanCinemaPersent(new Tui());
        yuanCinemaFuPersent = new YuanCinemaFuPersent(new Fu());
        yuanCinemaPersent.reqeust("0","","1","10");
        recy_tui = view.findViewById(R.id.recy_tui);
        recy_tui.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_tui.addItemDecoration(new SpacesItemDecoration(20));
        recy_tui.setAdapter(adaptr);
        adaptr.setOnItemClick(this);
        guanCinmearPersent = new GuanCinmearPersent(new Guan());
        fu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (student.size()==0){
                    yuanCinemaFuPersent.reqeust("0","","40.0481292144","116.3065021771","1","10");
                }else{
                    yuanCinemaFuPersent.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"","40.0481292144","116.3065021771","1","10");
                }

                fu.setBackgroundResource(R.drawable.btn_gradient);
                fu.setTextColor(Color.WHITE);
                tui.setBackgroundResource(R.drawable.btn_wu);
                tui.setTextColor(Color.BLACK);

            }
        });
        tui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (student.size()==0){
                    yuanCinemaPersent.reqeust("0","","1","10");
                }

                tui.setBackgroundResource(R.drawable.btn_gradient);
                tui.setTextColor(Color.WHITE);
                fu.setBackgroundResource(R.drawable.btn_wu);
                fu.setTextColor(Color.BLACK);

            }
        });
    }

    @Override
    public void tiao(String cinameId, String imgPic, String cinameName, String address) {
        System.out.println("XXX"+cinameId+imgPic+cinameName+address);
        startActivity(new Intent(getActivity(),CinemaDetailsActivity.class));
        EventBus.getDefault().postSticky(new EventMessage(cinameId,imgPic,cinameName,address));
    }

    @Override
    public void guan(int position, String tr) {

        if (student.size()==0){

            startActivity(new Intent(getActivity(), WDLoginActivity.class));
        }else {

            if (tr.equals("1")){
                guanCinmearPersent.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"",position+"");

            }else{
                //qGuanPwdPersent.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"",position+"");

            }
        }
    }


    private class Tui implements DataCall<Result<List<YuantuiBean>>> {
        @Override
        public void success(Result<List<YuantuiBean>> data) {
            adaptr.setData(data.getResult());
            adaptr.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class Fu implements DataCall<Result<List<YuantuiBean>>>  {
        @Override
        public void success(Result<List<YuantuiBean>> data) {
            adaptr.setData(data.getResult());
            adaptr.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class Guan implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")){
                Toast.makeText(getActivity(),"关注成功",Toast.LENGTH_LONG).show();
                yuanCinemaFuPersent.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"","40.0481292144","116.3065021771","1","10");
            }else{
                for (int i=0;i<student.size();i++){
                    try {
                        userDao.delete(student.get(i));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),WDLoginActivity.class));
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
