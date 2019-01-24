package com.example.weiducinema.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.adapter.ShowMoiverAdapter;
import com.example.weiducinema.app.SpacesItemDecoration;
import com.example.weiducinema.bean.PopularBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.core.base.BaseActivity;
import com.example.weiducinema.precener.PopulPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * created by fxb
 * 2019/1/24 10:32
 */
public class Show_moiver extends BaseActivity {
    XRecyclerView xRecyclerView;
    String type ;
    RadioButton yi,er,san;
    RadioGroup group;
    ShowMoiverAdapter adapter;
    PopulPresenter populPresenter;
    PopulPresenter populPresenter2;
    PopulPresenter populPresenter3;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_move;
    }

    @Override
    protected void initView() {
        yi = findViewById(R.id.yi);
        er = findViewById(R.id.er);
        san = findViewById(R.id.san);
        group = findViewById(R.id.group);
        populPresenter = new PopulPresenter(new PopulData());
        populPresenter2 = new PopulPresenter(new PopulData2());
        populPresenter3 = new PopulPresenter(new PopulData3());
        EventBus.getDefault().register(this);
        xRecyclerView = findViewById(R.id.xrecy);
        adapter = new ShowMoiverAdapter(this);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.yi:
                        populPresenter.request("1", "10");
                        break;
                    case R.id.er:
                        populPresenter2.request("1", "10");
                        break;
                    case R.id.san:
                        populPresenter3.request("1", "10");
                        break;
                }
            }
        });
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        xRecyclerView.addItemDecoration(new SpacesItemDecoration(20));
        xRecyclerView.setAdapter(adapter);


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
            populPresenter.request("1","10");
        }else if (type.equals("2")){
            yi.setChecked(false);
            er.setChecked(true);
            san.setChecked(false);
            populPresenter2.request("1","10");
        }else{
            yi.setChecked(false);
            er.setChecked(false);
            san.setChecked(true);
            populPresenter3.request("1","10");
        }
    }



    private class PopulData implements Consumer<Result<List<PopularBean>>> {
        @Override
        public void accept(Result<List<PopularBean>> listResult) throws Exception {
            if (listResult.getStatus().equals("0000")){

                adapter.setList(listResult.getResult());
                adapter.notifyDataSetChanged();
            }
        }
    }

    private class PopulData2 implements Consumer<Result<List<PopularBean>>>  {
        @Override
        public void accept(Result<List<PopularBean>> listResult) throws Exception {
            if (listResult.getStatus().equals("0000")){

                adapter.setList(listResult.getResult());
                adapter.notifyDataSetChanged();
            }
        }
    }

    private class PopulData3 implements Consumer<Result<List<PopularBean>>>  {
        @Override
        public void accept(Result<List<PopularBean>> listResult) throws Exception {
            if (listResult.getStatus().equals("0000")){
                adapter.setList(listResult.getResult());
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
