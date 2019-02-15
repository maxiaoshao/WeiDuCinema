package com.example.weiducinema.fragment;


import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.bw.movie.R;
import com.example.weiducinema.activity.WDDetailsActivity;
import com.example.weiducinema.activity.WDShowmoiver;
import com.example.weiducinema.adapter.RecycleHotAdapter;
import com.example.weiducinema.adapter.RecycleHotShowAdapter;
import com.example.weiducinema.adapter.RecycleUpComeAdapter;
import com.example.weiducinema.adapter.RecycleFilmDetailsAdapter;
import com.example.weiducinema.app.WifiUtils;
import com.example.weiducinema.base.WDBaseFragment;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.bean.PopularBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.core.exception.FileUtils;
import com.example.weiducinema.precener.HotFilmPopulPresenter;
import com.example.weiducinema.precener.HotShowPopulPresenter;
import com.example.weiducinema.precener.UpComePopulPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
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
    RecyclerView recyclerView1, recyclerView2, recyclerView3;
    RecycleHotAdapter adapter1;
    RecycleHotShowAdapter adapter2;
    RecycleUpComeAdapter adapter3;
    RecycleFilmDetailsAdapter adapter4;
    RecyclerCoverFlow rcf_cinema_flow;
    RelativeLayout remen, zhengzai, jijiang;
    TextView movie_text_xian, movie_text_dong,text1;
    FragmentManager manager;
    int mWidth, mItemCount, mCoun;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        SDKInitializer.initialize(getActivity().getApplicationContext());
        return R.layout.fragment_filmfragment;
    }

    @Override
    protected void initView(View view) {

        text1 = view.findViewById(R.id.text10);
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
        mHotFilmPopulPresenter.reqeust(0 + "", "", "1", "10");
        mHotShowPopulPresenter.reqeust(0 + "", "", "1", "10");
        mUpComePopulPresenter.reqeust(0 + "", "", "1", "10");
        remen = view.findViewById(R.id.remen);
        zhengzai = view.findViewById(R.id.zhengzai);
        jijiang = view.findViewById(R.id.jijiang);
        remen.setOnClickListener(this);
        zhengzai.setOnClickListener(this);
        jijiang.setOnClickListener(this);
        initData();
    }

    private void initData() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE
                }, 100);
            } else {
                mLocationClient = new LocationClient(getContext());
                //声明LocationClient类
                mLocationClient.registerLocationListener(myListener);
                //注册监听函数
                LocationClientOption option = new LocationClientOption();
                option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
                //可选，是否需要位置描述信息，默认为不需要，即参数为false
                //如果开发者需要获得当前点的位置信息，此处必须为true
                option.setIsNeedLocationDescribe(true);
                //可选，设置是否需要地址信息，默认不需要
                option.setIsNeedAddress(true);
                //可选，默认false,设置是否使用gps
                option.setOpenGps(true);
                //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
                option.setLocationNotify(true);
                mLocationClient.setLocOption(option);
                mLocationClient.start();
            }
        }
        adapter1 = new RecycleHotAdapter(getActivity());
        adapter2 = new RecycleHotShowAdapter(getActivity());
        adapter3 = new RecycleUpComeAdapter(getActivity());
        adapter4 = new RecycleFilmDetailsAdapter(getActivity());
        adapter1.setOnItemClick(new RecycleHotAdapter.onItemClick() {
            @Override
            public void tiao(String json) {
                Intent intent = new Intent(getActivity(), WDDetailsActivity.class);
                intent.putExtra("mid", json);
                startActivity(intent);

            }
        });
        adapter2.setOnItemClick(new RecycleHotShowAdapter.onItemClick() {
            @Override
            public void tiao(String json) {
                Intent intent = new Intent(getActivity(), WDDetailsActivity.class);
                intent.putExtra("mid", json);
                startActivity(intent);

            }
        });
        adapter3.setOnItemClick(new RecycleUpComeAdapter.onItemClick() {
            @Override
            public void tiao(String json) {
                Intent intent = new Intent(getActivity(), WDDetailsActivity.class);
                intent.putExtra("mid", json);
                startActivity(intent);

            }
        });
        adapter4.setOnItemClick(new RecycleFilmDetailsAdapter.onItemClick() {
            @Override
            public void tiao(String json) {
                Intent intent = new Intent(getActivity(), WDDetailsActivity.class);
                intent.putExtra("mid", json);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            if (data.getStatus().equals("0000")) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setAdapter(adapter1);

                String json = new Gson().toJson(data.getResult());
                FileUtils.saveDataToFile(getContext(), json, "shouye");
                String shouye = FileUtils.loadDataFromFile(getContext(), "shouye");
                Type types = new TypeToken<List<PopularBean>>() {}.getType();

                List<PopularBean> li = new Gson().fromJson(shouye,types);
                adapter4.setList(li);
                adapter1.setList(li);
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
            int type = WifiUtils.getInstance(getActivity()).getNetype();
            if (type == -1) {

                String shouye = FileUtils.loadDataFromFile(getContext(), "shouye");
                Type types = new TypeToken<List<PopularBean>>() {}.getType();

                List<PopularBean> li = new Gson().fromJson(shouye,types);
                adapter4.setList(li);
                adapter1.setList(li);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setAdapter(adapter1);
                adapter4.notifyDataSetChanged();
            }
        }
    }

    private class PopulData2 implements DataCall<Result<List<PopularBean>>> {


        @Override
        public void success(Result<List<PopularBean>> data) {
            if (data.getStatus().equals("0000")) {
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

    private class PopulData3 implements DataCall<Result<List<PopularBean>>> {


        @Override
        public void success(Result<List<PopularBean>> data) {
            if (data.getStatus().equals("0000")) {
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
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            String locationDescribe = location.getLocationDescribe();    //获取位置描述信息
            String addr = location.getCity();    //获取详细地址信息
            if (addr != null && addr != "") {
                text1.setText(addr);
            }

        }

    }


}
