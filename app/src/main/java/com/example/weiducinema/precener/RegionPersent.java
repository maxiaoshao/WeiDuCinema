package com.example.weiducinema.precener;

import com.example.weiducinema.base.BasePresenter;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.core.http.IRequest;
import com.example.weiducinema.core.http.NetworkManager;

import io.reactivex.Observable;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/24
 */
public class RegionPersent extends BasePresenter {


    public RegionPersent(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetworkManager.instance().create(IRequest.class);
        return iRequest.getRegiest((String)args[0],
                (String)args[1],(String)args[2],(String)args[3],
                (int)args[4],(String)args[5],(String)args[6],
                (String)args[7],(String)args[8],(String)args[9],
                (String)args[10]);
    }
}
