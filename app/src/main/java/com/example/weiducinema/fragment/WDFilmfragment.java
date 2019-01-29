package com.example.weiducinema.fragment;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.activity.WDDetailsActivity;
import com.example.weiducinema.activity.WDShowmoiver;
import com.example.weiducinema.adapter.RecycleHotAdapter;
import com.example.weiducinema.adapter.RecycleHotShowAdapter;
import com.example.weiducinema.adapter.RecycleUpComeAdapter;
import com.example.weiducinema.adapter.RecycleFilmDetailsAdapter;
import com.example.weiducinema.base.WDBaseFragment;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.bean.PopularBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.precener.HotFilmPopulPresenter;
import com.example.weiducinema.precener.HotShowPopulPresenter;
import com.example.weiducinema.precener.UpComePopulPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * A simple {@link Fragment} subclass.
 */
public class WDFilmfragment extends WDBaseFragment implements View.OnClickListener {
    HotFilmPopulPresenter mHotFilmPopulPresenter;
    HotShowPopulPresenter mHotShowPopulPresenter;
    UpComePopulPresenter mUpComePopulPresenter;
    RecyclerView recyclerView1,recyclerView2,recyclerView3;
    RecycleHotAdapter adapter1;
    RecycleHotShowAdapter adapter2;
    RecycleUpComeAdapter adapter3;
    RecycleFilmDetailsAdapter adapter4;
    RecyclerCoverFlow rcf_cinema_flow;
    RelativeLayout remen,zhengzai,jijiang;
    TextView movie_text_xian,movie_text_dong;
    FragmentManager manager;
    int mWidth,mItemCount,mCoun;
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
        rcf_cinema_flow = view.findViewById(R.id.rcf_cinema_flow);
        recyclerView1 = view.findViewById(R.id.recy1);
        recyclerView2 = view.findViewById(R.id.recy2);
        recyclerView3 = view.findViewById(R.id.recy3);
        // recyclerCoverFlow = view.findViewById(R.id.rcf_cinema_flow);
        mHotFilmPopulPresenter = new HotFilmPopulPresenter(new PopulData());
        mHotShowPopulPresenter = new HotShowPopulPresenter(new PopulData2());
        mUpComePopulPresenter = new UpComePopulPresenter(new PopulData3());
        movie_text_dong = view.findViewById(R.id.movie_text_dong);
        movie_text_xian = view.findViewById(R.id.movie_text_xian);
        mHotFilmPopulPresenter.reqeust(0+"","","1","10");
        mHotShowPopulPresenter.reqeust(0+"","","1","10");
        mUpComePopulPresenter.reqeust(0+"","","1","10");
        remen = view.findViewById(R.id.remen);
        zhengzai = view.findViewById(R.id.zhengzai);
        jijiang = view.findViewById(R.id.jijiang);
        remen.setOnClickListener(this);
        zhengzai.setOnClickListener(this);
        jijiang.setOnClickListener(this);
        initData();
    }

    private void initData() {
        adapter1 = new RecycleHotAdapter(getActivity());
        adapter2 = new RecycleHotShowAdapter(getActivity());
        adapter3 = new RecycleUpComeAdapter(getActivity());
        adapter4 = new RecycleFilmDetailsAdapter(getActivity());
        adapter1.setOnItemClick(new RecycleHotAdapter.onItemClick() {
            @Override
            public void tiao(String json) {
                Intent intent = new Intent(getActivity(), WDDetailsActivity.class);
                intent.putExtra("mid",json);
                startActivity(intent);

            }
        });
        adapter2.setOnItemClick(new RecycleHotShowAdapter.onItemClick() {
            @Override
            public void tiao(String json) {
                Intent intent = new Intent(getActivity(), WDDetailsActivity.class);
                intent.putExtra("mid",json);
                startActivity(intent);

            }
        });
        adapter3.setOnItemClick(new RecycleUpComeAdapter.onItemClick() {
            @Override
            public void tiao(String json) {
                Intent intent = new Intent(getActivity(), WDDetailsActivity.class);
                intent.putExtra("mid",json);
                startActivity(intent);

            }
        });
        adapter4.setOnItemClick(new RecycleFilmDetailsAdapter.onItemClick() {
            @Override
            public void tiao(String json) {
                Intent intent = new Intent(getActivity(), WDDetailsActivity.class);
                intent.putExtra("mid",json);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.remen:
                startActivity(new Intent(getActivity(), WDShowmoiver.class));
                EventBus.getDefault().postSticky("1");
                break;
            case R.id.zhengzai:
                startActivity(new Intent(getActivity(), WDShowmoiver.class));
                EventBus.getDefault().postSticky("2");
                break;
            case R.id.jijiang:
                startActivity(new Intent(getActivity(), WDShowmoiver.class));
                EventBus.getDefault().postSticky("3");
                break;

        }
    }

    private class PopulData implements DataCall<Result<List<PopularBean>>> {

        @Override
        public void success(Result<List<PopularBean>> data) {
            if (data.getStatus().equals("0000")){
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setAdapter(adapter1);
                adapter4.setList(data.getResult());
                adapter1.setList(data.getResult());
                mWidth = movie_text_xian.getWidth();
                mItemCount = adapter4.getItemCount();
                mCoun = mWidth / mItemCount;
                rcf_cinema_flow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
                    @Override
                    public void onItemSelected(int position) {
                        int selectedPos = rcf_cinema_flow.getSelectedPos();
                        ObjectAnimator animator = ObjectAnimator.ofFloat(movie_text_dong, "translationX", mCoun * (selectedPos));
                        animator.setDuration(500);
                        animator.start();

                    }
                });
                rcf_cinema_flow.setAdapter(adapter4);
                rcf_cinema_flow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class PopulData2 implements DataCall<Result<List<PopularBean>>>  {


        @Override
        public void success(Result<List<PopularBean>> data) {
            if (data.getStatus().equals("0000")){
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView2.setLayoutManager(layoutManager);
                recyclerView2.setAdapter(adapter2);
                adapter2.setList(data.getResult());
                adapter2.notifyDataSetChanged();

            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class PopulData3 implements DataCall<Result<List<PopularBean>>>  {


        @Override
        public void success(Result<List<PopularBean>> data) {
            if (data.getStatus().equals("0000")){
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView3.setLayoutManager(layoutManager);
                recyclerView3.setAdapter(adapter3);
                adapter3.setList(data.getResult());
                adapter3.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

}
