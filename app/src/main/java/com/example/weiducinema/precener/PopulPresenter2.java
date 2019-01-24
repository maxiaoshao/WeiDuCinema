package com.example.weiducinema.precener;

import com.example.weiducinema.base.BasePresenter;
import com.example.weiducinema.base.DataCall;

import com.example.weiducinema.core.http.IRequest;
import com.example.weiducinema.core.http.NetworkManager;

import io.reactivex.Observable;

/**
 * @author dingtao
 * @date 2018/12/28 11:23
 * qq:1940870847
 */
public class PopulPresenter2 extends BasePresenter {


    public PopulPresenter2(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetworkManager.instance().create(IRequest.class);
        return iRequest.getPopul2((String) args[0], (String) args[1]);
    }


}
