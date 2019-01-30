package com.bw.movie.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.activity.WDShowActivity;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.db.DBManager;
import com.example.weiducinema.fragment.WDMyFragment;
import com.example.weiducinema.precener.WXLoginPersent;
import com.j256.ormlite.dao.Dao;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.sql.SQLException;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/28
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    private WXLoginPersent wxLoginPersent  = new WXLoginPersent(new WXCall());;
    private DBManager manager;
    private Dao<UserInfo, String> userDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.wx_login_layout);
        // 注册API
        api = WXAPIFactory.createWXAPI(this, "wxb3852e6a6b7d9516");
        api.handleIntent(getIntent(), this);
        userDao = DBManager.getInstance(this).getUserDao();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        finish();
    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Log.e("flag", "-----code:ok");
                if (resp instanceof SendAuth.Resp) {
                    SendAuth.Resp sendAuthResp = (SendAuth.Resp) resp;
                    String code = sendAuthResp.code;
                    wxLoginPersent.reqeust(code);
                    // 发起登录请求
                    Log.e("flag", "-----code:" + sendAuthResp.code);
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (resp instanceof SendAuth.Resp) {}
                Log.e("flag", "-----授权取消:");
                Toast.makeText(this, "授权取消:", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                if (resp instanceof SendAuth.Resp) {}
                Log.e("flag", "-----授权失败:");
                Toast.makeText(this, "授权失败:", Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
                break;
        }
    }


    //获取数据
    class WXCall implements DataCall<Result<UserInfo>> {



        @Override
        public void success(Result<UserInfo> data) {
            UserInfo result = data.getResult();
                try {
                    userDao.createOrUpdate(result);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }

        @Override
        public void fail(ApiException e) {

            Toast.makeText(getBaseContext(), "异常", Toast.LENGTH_SHORT).show();
        }
    }


}
