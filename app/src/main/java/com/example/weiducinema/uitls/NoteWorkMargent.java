package com.example.weiducinema.uitls;



import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2018/12/28
 */
public class NoteWorkMargent {
    private static NoteWorkMargent mNoteWorkMargent;
    private  Retrofit mRetrofit;
    private final static String Mall_Api="http://172.17.8.100/movieApi/";
    private  OkHttpClient mClient;
    //双重锁
    public static NoteWorkMargent getInsert(){
              if (mNoteWorkMargent == null){
                mNoteWorkMargent = new NoteWorkMargent();
              }

        return mNoteWorkMargent;
    }
    private NoteWorkMargent(){
        // 初始化okhttp
          init();

    }

    private void init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        mClient = new OkHttpClient.Builder()

                .addInterceptor(logging)

                .build();
        mRetrofit = new Retrofit.Builder()
                .client(mClient)
                .baseUrl(Mall_Api)//"http://mobile.bwstudent.com/small/"
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    //把接口的注解翻译为OKhttp请求
    public <T> T create(final Class<T> service) {
        return mRetrofit.create(service);
    }
}


