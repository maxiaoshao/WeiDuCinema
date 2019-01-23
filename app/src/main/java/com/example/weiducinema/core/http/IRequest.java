package com.example.weiducinema.core.http;

import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.PopularBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IRequest {
    @GET("movie/v1/findHotMovieList")
    Observable<Result<List<PopularBean>>> getPopul();

}
