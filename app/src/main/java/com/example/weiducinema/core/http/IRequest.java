package com.example.weiducinema.core.http;

import com.example.weiducinema.bean.Login_Bean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.PopularBean;
import com.example.weiducinema.bean.YuantuiBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IRequest {
    /**
     *
     * @param page
     * @param count
     * @return
     */
    @GET("movieApi/movie/v1/findHotMovieList")
    Observable<Result<List<PopularBean>>> getPopul(
            @Query("page") String page ,
            @Query("count") String count);
    /**
     *
     * @param page
     * @param count
     * @return
     */
    @GET("movieApi/movie/v1/findReleaseMovieList")
    Observable<Result<List<PopularBean>>> getPopul2(
            @Query("page") String page ,
            @Query("count") String count);
    /**
     *
     * @param page
     * @param count
     * @return
     */
    @GET("movieApi/movie/v1/findComingSoonMovieList")
    Observable<Result<List<PopularBean>>> getPopul3(
            @Query("page") String page ,
            @Query("count") String count);

    /**
     * 登录
     * @param phone
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("movieApi/user/v1/login")
    Observable<Result<Login_Bean>> getLogin(@Field("phone") String phone,
                             @Field("pwd") String pwd);

    /**
     * 注册
     * @param nickName
     * @param phone
     * @param pwd
     * @param pwd2
     * @param sex
     * @param birthday
     * @param imei
     * @param ua
     * @param screenSize
     * @param os
     * @param email
     * @return
     */
    @POST("movieApi/user1/registerUser")

    @FormUrlEncoded
    Observable<Result> getRegiest(@Field("nickName") String nickName,
                               @Field("phone") String phone,
                               @Field("pwd") String pwd,
                               @Field("pwd2") String pwd2,
                               @Field("sex") int sex,
                               @Field("birthday") String birthday,
                               @Field("imei") String imei,
                               @Field("ua") String ua,
                               @Field("screenSize") String screenSize,
                               @Field("os") String os,
                               @Field("email") String email);

    /**
     * 推荐影院
     * @param page
     * @param count
     * @return
     */

    @GET("movieApi/cinema/v1/findRecommendCinemas")
    Observable<Result<List<YuantuiBean>>> getTui(
            @Header("userId") String userid,
            @Header("sessionId") String sessid,
            @Query("page") String page ,
            @Query("count") String count);

    /**
     * 附近影院
     * @param userid
     * @param sessid
     * @param page
     * @param count
     * @return
     */
    @GET("movieApi/cinema/v1/findNearbyCinemas")
    Observable<Result<List<YuantuiBean>>> getFu(
            @Header("userId") String userid,
            @Header("sessionId") String sessid,
            @Query("longitude") String longitude ,
            @Query("latitude") String latitude,
            @Query("page") String page ,
            @Query("count") String count);
}
