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
public class CommentPrencenter extends BasePresenter {


    public CommentPrencenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetworkManager.instance().create(IRequest.class);
        return iRequest.getcomment((String)args[0],(String)args[1],(String)args[2]);
    }
}
