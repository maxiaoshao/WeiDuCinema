package com.example.weiducinema.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import com.example.weiducinema.R;
import com.example.weiducinema.adapter.RecycleAdapter;
import com.example.weiducinema.bean.PopularBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.core.DataCall;
import com.example.weiducinema.core.base.BaseFragment;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.precener.PopulPresenter;

import java.util.List;

import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * A simple {@link Fragment} subclass.
 */
public class Filmfragment extends BaseFragment {
    RecyclerCoverFlow recyclerCoverFlow;
    PopulPresenter populPresenter;
   RecycleAdapter adapter;
    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_filmfragment;
    }

    @Override
    protected void initView(View view) {
        adapter = new RecycleAdapter(getActivity());

        recyclerCoverFlow = view.findViewById(R.id.rcf_cinema_flow);
        populPresenter = new PopulPresenter(new PopulData());
        initData();
    }

    private void initData() {
        recyclerCoverFlow.setAlphaItem(true); //设置半透渐变
        //平面滚动


        recyclerCoverFlow.setAdapter(adapter);
        recyclerCoverFlow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {//滑动监听
            @Override
            public void onItemSelected(int position) {

            }
        });
    }

    private class PopulData implements DataCall<Result<List<PopularBean>>> {
        @Override
        public void success(Result<List<PopularBean>> data) {

            adapter.setList(data.getResult());
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
