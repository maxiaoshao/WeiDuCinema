package com.example.weiducinema.core.http;

import com.example.weiducinema.bean.ChangeMessageBean;
import com.example.weiducinema.bean.CommentBean;
import com.example.weiducinema.bean.FilmTimeBean;
import com.example.weiducinema.bean.DetailsBean;
import com.example.weiducinema.bean.PayBean;
import com.example.weiducinema.bean.QueryBean;
import com.example.weiducinema.bean.QueryPaiBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.PopularBean;
import com.example.weiducinema.bean.ScheduleBean;
import com.example.weiducinema.bean.SystemMassage;
import com.example.weiducinema.bean.TicketBean;
import com.example.weiducinema.bean.UserAttenBean;
import com.example.weiducinema.bean.YuantuiBean;
import com.example.weiducinema.bean.encrypt.FindUserBean;
import com.example.weiducinema.bean.encrypt.UserInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
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
     * @param page
     * @param count
     * @return
     */
    @GET("movieApi/movie/v1/findHotMovieList")
    Observable<Result<List<PopularBean>>> getPopul(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId,
            @Query("page") String page,
            @Query("count") String count);

    /**
     * @param page
     * @param count
     * @return
     */
    @GET("movieApi/movie/v1/findReleaseMovieList")
    Observable<Result<List<PopularBean>>> getPopul2(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId,
            @Query("page") String page,
            @Query("count") String count);

    /**
     * @param page
     * @param count
     * @return
     */
    @GET("movieApi/movie/v1/findComingSoonMovieList")
    Observable<Result<List<PopularBean>>> getPopul3(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId,
            @Query("page") String page,
            @Query("count") String count);

    /**
     * 登录
     *
     * @param phone
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("movieApi/user/v1/login")
    Observable<Result<UserInfo>> getLogin(@Field("phone") String phone,
                                          @Field("pwd") String pwd);

    /**
     * 注册
     *
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

    @POST("movieApi/user/v1/registerUser")

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
     *
     * @param page
     * @param count
     * @return
     */

    @GET("movieApi/cinema/v1/findRecommendCinemas")
    Observable<Result<List<YuantuiBean>>> getTui(
            @Header("userId") String userid,
            @Header("sessionId") String sessid,
            @Query("page") String page,
            @Query("count") String count);

    /**
     * 附近影院
     *
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
            @Query("longitude") String longitude,
            @Query("latitude") String latitude,
            @Query("page") String page,
            @Query("count") String count);

    /**
     * 查询影院
     * @param movieId
     * @return
     */

    /**
     * 影片详情
     *
     * @param movieId
     * @return
     */
    @GET("movieApi/movie/v1/findMoviesDetail")
    Observable<Result<DetailsBean>> getDetails(
            @Query("movieId") String movieId);

    /**
     * 根据用户Id查询用户信息
     */
    @GET("movieApi/user/v1/verify/getUserInfoByUserId")
    Observable<Result<FindUserBean>> getFindUser(
            @Header("userId") int userid,
            @Header("sessionId") String sessid
    );

    /**
     * 影院排期
     */
    @GET("movieApi/movie/v1/findMovieListByCinemaId")
    Observable<Result<List<ScheduleBean>>> getSchedule(
            @Query("cinemaId") String cinemaId
    );

    /**
     * 电影时间排期
     */
    @GET("movieApi/movie/v1/findMovieScheduleList")
    Observable<Result<List<FilmTimeBean>>> getFilmTime(
            @Query("cinemasId") String cinemasId,
            @Query("movieId") int movieId
    );

    /**
     * 根据影片查询影院
     *
     * @param movieId
     * @return
     */
    @GET("movieApi/movie/v1/findCinemasListByMovieId")
    Observable<Result<List<QueryBean>>> getQuery(
            @Query("movieId") String movieId);

    /**
     * 根据电影和影城查询排期
     *
     * @param cinemasId
     * @param movieId
     * @return
     */
    @GET("movieApi/movie/v1/findMovieScheduleList")
    Observable<Result<List<QueryPaiBean>>> getQueryPai(
            @Query("cinemasId") String cinemasId,
            @Query("movieId") String movieId);

    /**
     * 根据用户关注影片，进行查询
     *
     * @param userId
     * @param sessionId
     * @param page
     * @param count
     * @return
     */
    @GET("movieApi/movie/v1/verify/findMoviePageList")
    Observable<Result<List<UserAttenBean>>> getUserPai(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId,
            @Query("page") String page,
            @Query("count") String count);

    /**
     * 根据用户关注影城，进行查询
     *
     * @param userId
     * @param sessionId
     * @param page
     * @param count
     * @return
     */
    @GET("movieApi/cinema/v1/verify/findCinemaPageList")
    Observable<Result<List<UserAttenBean>>> getUserChang(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId,
            @Query("page") String page,
            @Query("count") String count);

    /**
     * 修改密码
     *
     * @param userId
     * @param sessionId
     * @return
     */
    @POST("movieApi/user/v1/verify/modifyUserPwd")
    Observable<Result> getChangePwd(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId,
            @Field("oldPwd") String oldPwd,
            @Field("newPwd") String newPwd,
            @Field("newPwd2") String newPwd2
    );

    /**
     * 下单接口
     *
     * @param userId
     * @param sessionId
     * @param scheduleId
     * @param amount
     * @param sign
     * @return
     */
    @POST("movieApi/movie/v1/verify/buyMovieTicket")
    @FormUrlEncoded
    Observable<Result> getOrder(@Header("userId") String userId,
                                @Header("sessionId") String sessionId,
                                @Field("scheduleId") String scheduleId,
                                @Field("amount") String amount,
                                @Field("sign") String sign);

    /**
     * 支付接口
     *
     * @param userId
     * @param sessionId
     * @param payType
     * @param orderId
     * @return
     */
    @POST("movieApi/movie/v1/verify/pay")
    @FormUrlEncoded
    Observable<PayBean> pay(@Header("userId") String userId,
                            @Header("sessionId") String sessionId,
                            @Field("payType") String payType,
                            @Field("orderId") String orderId);

    /**
     * 微信登录
     *
     * @param code
     * @return
     */

    @POST("movieApi/user/v1/weChatBindingLogin")
    @FormUrlEncoded
    Observable<Result<UserInfo>> getWxLogin(
            @Field("code") String code);

    /**
     * 切换头像
     */
    @POST("movieApi/user/v1/verify/uploadHeadPic")
    Observable<Result> headPic(@Header("userId") String userId,
                               @Header("sessionId") String sessionId,
                               @Body MultipartBody image);

    /**
     * movieApi/user/v1/verify/findUserBuyTicketRecordList
     * 查询购票记录
     */
    @GET("movieApi/user/v1/verify/findUserBuyTicketRecordList")
    Observable<Result<List<TicketBean>>> getTicketBean(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId,
            @Query("page") int page,
            @Query("count") int count,
            @Query("status") int status);

    /**
     * 查看评论接口
     *
     * @param movieId
     * @param page
     * @param count
     * @return
     */
    @GET("movieApi/movie/v1/findAllMovieComment")
    Observable<Result<List<CommentBean>>> getcomment(
            @Query("movieId") String movieId,
            @Query("page") String page,
            @Query("count") String count);

    /**
     * 关注影片
     *
     * @param userId
     * @param sessionId
     * @param movieId
     * @return
     */
    @GET("movieApi/movie/v1/verify/followMovie")
    Observable<Result> getGuan(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId,
            @Query("movieId") String movieId);

    /**
     * 取消关注
     *
     * @param userId
     * @param sessionId
     * @param movieId
     * @return
     */
    @GET("movieApi/movie/v1/verify/cancelFollowMovie")
    Observable<Result> getQGuan(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId,
            @Query("movieId") String movieId);

    /**
     *
     */
    @GET("movieApi/user/v1/verify/userSignIn")
    Observable<Result> getSign(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId);

    /**
     * 系统消息
     */
    @GET("movieApi/tool/v1/verify/findAllSysMsgList")
    Observable<Result<List<SystemMassage>>> getSystem(
            @Header("userId") int userId,
            @Header("sessionId") String sessionId,
            @Query("page") int page,
            @Query("count") int count
            );

    /**
     * 修改信息
     */
    @POST("movieApi/user/v1/verify/modifyUserInfo")
    Observable<Result<ChangeMessageBean>> getChangeMessage(
            @Header("userId") int userId,
            @Header("sessionId") String sessionId,
            @Field("nickName") String nickName,
            @Field("sex") int sex,
            @Field("email") String email
    );
}