package com.example.weiducinema.precener;

import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.bean.Result;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author dingtao
 * @date 2018/12/28 11:30
 * qq:1940870847
 */
public abstract class BasePresenter {
    private DataCall dataCall;
    private  boolean running;
   // public BasePresenter(DataCall dataCall) {
//        this.dataCall = dataCall;
//    }
   private Consumer consumer;

    public BasePresenter(Consumer consumer) {
        this.consumer = consumer;
    }
    protected abstract Observable<Result> observable(Object... args);
    public void request(Object...args) {
        observable(args).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
//    public void reqeust(Object... args) {
//        if (running){
//            return;
//        }
//        running = true;
//        observable(args)
//                .compose(ResponseTransformer.handleResult())
//                .compose(new ObservableTransformer() {
//                    @Override
//                    public ObservableSource apply(Observable upstream) {
//                        running = false;
//                        return upstream.subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread());
//                    }
//                })
////                .subscribeOn(Schedulers.newThread())//将请求调度到子线程上
////                .observeOn(AndroidSchedulers.mainThread())//观察响应结果，把响应结果调度到主线程中处理
//                .subscribe(new Consumer<Result>() {
//                    @Override
//                    public void accept(Result result) throws Exception {
//                        running = false;
//                        dataCall.success(result);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        // 处理异常
////                        UIUtils.showToastSafe("请求失败");
//                        dataCall.fail(CustomException.handleException(throwable));
//                    }
//                });
//
//    }

//    public void unBind() {
//        dataCall = null;
//    }
    }
}
