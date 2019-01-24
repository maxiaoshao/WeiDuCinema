package com.example.weiducinema.precener;



import com.example.weiducinema.core.http.IRequest;
import com.example.weiducinema.base.BasePresenter;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.uitls.NoteWorkMargent;


import io.reactivex.Observable;


/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/24
 */
public class LoginPersent extends BasePresenter {


    public LoginPersent(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NoteWorkMargent.getInsert().create(IRequest.class);
        return iRequest.getLogin((String)args[0],(String)args[1]);
    }
}
