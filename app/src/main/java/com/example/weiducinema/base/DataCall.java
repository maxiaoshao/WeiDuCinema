package com.example.weiducinema.base;

import com.example.weiducinema.core.exception.ApiException;


/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/3
 */

public interface DataCall<T> {


    void success(T data);

    void fail(ApiException e);



}
