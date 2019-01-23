package com.example.weiducinema.precener;

import com.example.weiducinema.bean.Result;
import com.example.weiducinema.core.DataCall;
import com.example.weiducinema.core.exception.CustomException;
import com.example.weiducinema.core.exception.ResponseTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * created by fxb
 * 2019/1/23 14:27
 */
public abstract class BasePrecenter {
    private DataCall dataCall;

    private boolean running;
    private Observable observable;

    public BasePrecenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }

    protected abstract Observable observable(Object... args);

    public void reqeust(Object... args) {
        if (running) {
            return;
        }

        running = true;
        observable = observable(args);
        observable.compose(ResponseTransformer.handleResult())//添加了一个全局的异常-观察者
                .compose(new ObservableTransformer() {
                    @Override
                    public ObservableSource apply(Observable upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
//                .subscribeOn(Schedulers.newThread())//将请求调度到子线程上
//                .observeOn(AndroidSchedulers.mainThread())//观察响应结果，把响应结果调度到主线程中处理
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        running = false;
//                        if (result.getStatus().equals("1001")){
//                            Dialog dialog = new AlertDialog.Builder().setMessage("").set.create().sh;
//                        }else {
                        dataCall.success(result);
//                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        running = false;
                        // 处理异常
//                        UIUtils.showToastSafe("请求失败");
                        //通过异常工具类封装成自定义的ApiException
                        dataCall.fail(CustomException.handleException(throwable));
                    }
                });

    }

    public void cancelRequest() {
        if (observable!=null){
            observable.unsubscribeOn(Schedulers.io());
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void unBind() {
        dataCall = null;
    }
}
