package com.example.weiducinema.activity.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.functions.Consumer;
import com.bw.movie.R;
import com.example.weiducinema.activity.SeatTable;
import com.example.weiducinema.activity.WDLoginActivity;
import com.example.weiducinema.activity.my.MyAttentionActivity;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.bean.MessageBean;
import com.example.weiducinema.bean.PayBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.core.http.IRequest;
import com.example.weiducinema.core.http.NetworkManager;
import com.example.weiducinema.core.md5.MD5Utils;
import com.example.weiducinema.db.DBManager;
import com.example.weiducinema.precener.OrderPersent;
import com.example.weiducinema.precener.UserAttenChengPrencenter;
import com.j256.ormlite.dao.Dao;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CinemaChooseActivity extends WDBaseActivity {


    private SeatTable seat_view;
    private TextView txt_jiesuan;
    private TextView txt_fuhao;
    private TextView txt_choose_price;
    private ImageView img_confirm;
    private ImageView img_abandon;
    private String title;
    private int num = 0;
    private Dao<UserInfo,String> userDao;
    private List<UserInfo> student;
    private int price;

    private IWXAPI api;
    private String paiid;
    OrderPersent orderPersent;
    public CinemaChooseActivity() {
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_cinema_choose;
    }


    @Override
    protected void initView() {
        api = WXAPIFactory.createWXAPI(this, "wxb3852e6a6b7d9516");//第二个参数为APPID
        api.registerApp("wxb3852e6a6b7d9516");
        seat_view = findViewById(R.id.seat_view);
        txt_choose_price = findViewById(R.id.txt_choose_price);
        seat_view.setMaxSelected(3);//设置最多选中
        img_confirm = findViewById(R.id.img_confirm);
            orderPersent = new OrderPersent(new OrderData());
        initChooseMessage();
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
                changePriceWithSelected();
//                price++;
//               txt_choose_price.setText(price);
            }

            @Override
            public void unCheck(int row, int column) {
                changePriceWithUnSelected();
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
        img_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    userDao = DBManager.getInstance(getBaseContext()).getUserDao();
                    student = userDao.queryForAll();
                }catch (Exception e){

                }
                if (student.size()==0){
                    startActivity(new Intent(CinemaChooseActivity.this,WDLoginActivity.class));
                }else{
                    String md = student.get(0).getUserId()+""+paiid+"1"+"movie";
                    String s = MD5Utils.md5Password(md);
                    orderPersent.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"",paiid,"1",s);
                }

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void chuan(MessageBean messageBean){
        title = messageBean.getTitle();
        price = messageBean.getPrice();
        paiid = messageBean.getPid();
        seat_view.setScreenName(title);//设置屏幕名称

        txt_choose_price.setText(price);
    }

    @Override
    protected void destoryData() {
      EventBus.getDefault().unregister(this);
    }
    private void initChooseMessage() {
        String mPrice = "0.1";
        mPriceWithCalculate = new BigDecimal(mPrice);
    }

    private BigDecimal mPriceWithCalculate;
    private int selectedTableCount = 0;
    private void changePriceWithSelected() {
        selectedTableCount++;
        String currentPrice = mPriceWithCalculate.multiply(new BigDecimal(String.valueOf(selectedTableCount))).toString();
        SpannableString spannableString = changTVsize(currentPrice);
        txt_choose_price.setText(spannableString);
        //计算机：处理浮点数是不精确的1.2 - 02   = 1   =》    0.9999999999
    }
    private void changePriceWithUnSelected() {
        selectedTableCount--;
        String currentPrice = mPriceWithCalculate.multiply(new BigDecimal(String.valueOf(selectedTableCount))).toString();
        SpannableString spannableString = changTVsize(currentPrice);
        txt_choose_price.setText(spannableString);
    }
    public static SpannableString changTVsize(String value) {
        SpannableString spannableString = new SpannableString(value);
        if (value.contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), value.indexOf("."), value.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    private class OrderData implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")){
                Toast.makeText(CinemaChooseActivity.this,"创建订单成功",Toast.LENGTH_LONG).show();
                String orderId = data.getOrderId();
                IRequest interfacea = NetworkManager.instance().create(IRequest.class);
                interfacea.pay(student.get(0).getUserId()+"",student.get(0).getSessionId()+"","1",orderId).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( new Consumer<PayBean>() {
                            @Override
                            public void accept(PayBean payBean) throws Exception {
                                PayReq req = new PayReq();
                                req.appId = payBean.getAppId();
                                req.partnerId = payBean.getPartnerId();
                                req.prepayId = payBean.getPrepayId();
                                req.nonceStr = payBean.getNonceStr();
                                req.timeStamp = payBean.getTimeStamp();
                                req.packageValue = payBean.getPackageValue();
                                req.sign = payBean.getSign();
                                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                                //3.调用微信支付sdk支付方法
                                api.sendReq(req);
                            }
                        });
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
