package com.example.weiducinema.core.http;

import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.PopularBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IRequest {
    @GET("movieApi/movie/v1/findHotMovieList")
    Observable<Result<List<PopularBean>>> getPopul(@Query("page") String page , @Query("count") String count);
    @GET("movieApi/movie/v1/findReleaseMovieList")
    Observable<Result<List<PopularBean>>> getPopul2(@Query("page") String page , @Query("count") String count);
    @GET("movieApi/movie/v1/findComingSoonMovieList")
    Observable<Result<List<PopularBean>>> getPopul3(@Query("page") String page , @Query("count") String count);

}
