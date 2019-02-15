package com.example.weiducinema.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bw.movie.R;
import com.example.weiducinema.activity.WDLoginActivity;
import com.example.weiducinema.activity.cinema.CinemaDetailsActivity;
import com.example.weiducinema.adapter.YuanTuiAdaptr;
import com.example.weiducinema.app.SpacesItemDecoration;
import com.example.weiducinema.base.WDBaseFragment;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.base.WDBaseFragment;
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

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/23
 */
public class WDCinemaFragment extends WDBaseFragment implements YuanTuiAdaptr.onItemClick, View.OnClickListener {
    RecyclerView recy_tui;
    Button tui, fu;
    Dao<UserInfo, String> userDao;
    List<UserInfo> student;
    YuanTuiAdaptr adaptr;
    TextView text11;
    GuanCinmearPersent guanCinmearPersent;
    YuanCinemaFuPersent yuanCinemaFuPersent;
    YuanCinemaPersent yuanCinemaPersent;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    //private SearchView cinema_search;

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
text11 = view.findViewById(R.id.text111);
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
                if (student.size() == 0) {
                    yuanCinemaFuPersent.reqeust("0", "", "40.0481292144", "116.3065021771", "1", "10");
                } else {
                    yuanCinemaFuPersent.reqeust(student.get(0).getUserId() + "", student.get(0).getSessionId() + "", "40.0481292144", "116.3065021771", "1", "10");
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
                if (student.size() == 0) {
                    yuanCinemaPersent.reqeust("0", "", "1", "10");
                }

                tui.setBackgroundResource(R.drawable.btn_gradient);
                tui.setTextColor(Color.WHITE);
                fu.setBackgroundResource(R.drawable.btn_wu);
                fu.setTextColor(Color.BLACK);

            }
        });
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
    }

    @Override
    public void tiao(String cinameId, String imgPic, String cinameName, String address) {
        System.out.println("XXX" + cinameId + imgPic + cinameName + address);
        startActivity(new Intent(getActivity(), CinemaDetailsActivity.class));
        EventBus.getDefault().postSticky(new EventMessage(cinameId, imgPic, cinameName, address));
    }

    @Override
    public void guan(int position, String tr) {

        if (student.size() == 0) {

            startActivity(new Intent(getActivity(), WDLoginActivity.class));
        } else {

            if (tr.equals("1")) {
                guanCinmearPersent.reqeust(student.get(0).getUserId() + "", student.get(0).getSessionId() + "", position + "");

            } else {
                //qGuanPwdPersent.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"",position+"");

            }
        }
    }



    @Override
    public void onClick(View v) {

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

    private class Fu implements DataCall<Result<List<YuantuiBean>>> {
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
            if (data.getStatus().equals("0000")) {
                Toast.makeText(getActivity(), "关注成功", Toast.LENGTH_LONG).show();
                yuanCinemaFuPersent.reqeust(student.get(0).getUserId() + "", student.get(0).getSessionId() + "", "40.0481292144", "116.3065021771", "1", "10");
            } else {
                for (int i = 0; i < student.size(); i++) {
                    try {
                        userDao.delete(student.get(i));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), WDLoginActivity.class));
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
                text11.setText(addr);
            }

        }

    }
}
