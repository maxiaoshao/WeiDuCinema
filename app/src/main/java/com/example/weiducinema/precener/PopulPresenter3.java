package com.example.weiducinema.precener;

import com.example.weiducinema.core.http.IRequest;
import com.example.weiducinema.core.http.NetworkManager;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author dingtao
 * @date 2018/12/28 11:23
 * qq:1940870847
 */
public class PopulPresenter3 extends BasePresenter {


    public PopulPresenter3(Consumer consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetworkManager.instance().create(IRequest.class);
        return iRequest.getPopul3((String) args[0], (String) args[1]);
    }


}
