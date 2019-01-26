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
public class FilmTimePersent extends BasePresenter {


    public FilmTimePersent(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetworkManager.instance().create(IRequest.class);
        return iRequest.getFilmTime((String)args[0],(int)args[1]);
    }
}
