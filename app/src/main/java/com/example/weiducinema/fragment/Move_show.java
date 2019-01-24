package com.example.weiducinema.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.example.weiducinema.adapter.Recycle1Adapter;
import com.example.weiducinema.adapter.Recycle2Adapter;
import com.example.weiducinema.adapter.Recycle3Adapter;
import com.example.weiducinema.bean.PopularBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.core.base.BaseFragment;
import com.example.weiducinema.precener.PopulPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class Move_show extends BaseFragment {
    PopulPresenter populPresenter;
    PopulPresenter populPresenter2;
    PopulPresenter populPresenter3;
    RecyclerView recyclerView1,recyclerView2,recyclerView3;
    Recycle1Adapter adapter1;
    Recycle2Adapter adapter2;
    Recycle3Adapter adapter3;
    RelativeLayout remen,zhengzai,jijiang;

    FragmentManager manager;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_move_show;
    }

    @Override
    protected void initView(View view) {
        recyclerView1 = view.findViewById(R.id.recy1);
        recyclerView2 = view.findViewById(R.id.recy2);
        recyclerView3 = view.findViewById(R.id.recy3);
        // recyclerCoverFlow = view.findViewById(R.id.rcf_cinema_flow);
        populPresenter = new PopulPresenter(new PopulData());
        populPresenter2 = new PopulPresenter(new PopulData2());
        populPresenter3 = new PopulPresenter(new PopulData3());
        populPresenter.request("1","10");
        populPresenter2.request("1","10");
        populPresenter3.request("1","10");
        remen = view.findViewById(R.id.remen);
        zhengzai = view.findViewById(R.id.zhengzai);
        jijiang = view.findViewById(R.id.jijiang);
        initData();
    }

    private void initData() {
        adapter1 = new Recycle1Adapter(getActivity());
        adapter2 = new Recycle2Adapter(getActivity());
        adapter3 = new Recycle3Adapter(getActivity());
        remen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frag_moves,new Filmfragment()).commit();

                EventBus.getDefault().postSticky("1");
            }
        });
        zhengzai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frag_moves,new Filmfragment()).commit();

                EventBus.getDefault().postSticky("2");
            }
        });
        jijiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frag_moves,new Filmfragment()).commit();

                EventBus.getDefault().postSticky("3");
            }
        });
    }
    private class PopulData implements Consumer<Result<List<PopularBean>>> {
        @Override
        public void accept(Result<List<PopularBean>> listResult) throws Exception {
            if (listResult.getStatus().equals("0000")){
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setAdapter(adapter1);
                adapter1.setList(listResult.getResult());
            }
        }
    }

    private class PopulData2 implements Consumer<Result<List<PopularBean>>>  {
        @Override
        public void accept(Result<List<PopularBean>> listResult) throws Exception {
            if (listResult.getStatus().equals("0000")){
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView2.setLayoutManager(layoutManager);
                recyclerView2.setAdapter(adapter2);
                adapter2.setList(listResult.getResult());
            }
        }
    }

    private class PopulData3 implements Consumer<Result<List<PopularBean>>>  {
        @Override
        public void accept(Result<List<PopularBean>> listResult) throws Exception {
            if (listResult.getStatus().equals("0000")){
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView3.setLayoutManager(layoutManager);
                recyclerView3.setAdapter(adapter3);
                adapter3.setList(listResult.getResult());
            }
        }
    }

}
