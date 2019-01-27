package com.example.weiducinema.activity.cinema;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.activity.SeatTable;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.MessageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CinemaChooseActivity extends WDBaseActivity {


    private SeatTable seat_view;
    private TextView txt_jiesuan;
    private TextView txt_fuhao;
    private TextView txt_choose_price;
    private ImageView img_confirm;
    private ImageView img_abandon;
    private String title;
    private int num = 0;
    private int price;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_cinema_choose;
    }


    @Override
    protected void initView() {
        seat_view = findViewById(R.id.seat_view);
        txt_choose_price = findViewById(R.id.txt_choose_price);
        seat_view.setMaxSelected(3);//设置最多选中
        seat_view.setSeatChecker(new SeatTable.SeatChecker() {
            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==6&&column==6){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
                System.out.println("选中了");
//                price++;
//               txt_choose_price.setText(price);
            }

            @Override
            public void unCheck(int row, int column) {
                System.out.println("没选中");
//                if (num!=0) {
//                    num--;
//                }
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seat_view.setData(10,15);


        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void chuan(MessageBean messageBean){
        title = messageBean.getTitle();
        price = messageBean.getPrice();
        seat_view.setScreenName(title);//设置屏幕名称

        txt_choose_price.setText(price);
    }

    @Override
    protected void destoryData() {
      EventBus.getDefault().unregister(this);
    }



}
