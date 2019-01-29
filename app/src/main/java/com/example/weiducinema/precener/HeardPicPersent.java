package com.example.weiducinema.precener;


import com.example.weiducinema.base.BasePresenter;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.core.http.IRequest;
import com.example.weiducinema.core.http.NetworkManager;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/24
 */
public class HeardPicPersent extends BasePresenter {


    public HeardPicPersent(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetworkManager.instance().create(IRequest.class);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("headPath", (String)args[2]);
        File file = new File((String) args[2]);
        builder.addFormDataPart("image", file.getName(),
                RequestBody.create(MediaType.parse("multipart/octet-stream"),
                        file));


        return iRequest.headPic((String)args[0],(String)args[1],builder.build());
    }
}
