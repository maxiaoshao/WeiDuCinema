package com.example.weiducinema.base;

import com.example.weiducinema.bean.Result;
import com.example.weiducinema.core.exception.CustomException;
import com.example.weiducinema.uitls.ResponseTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2018/12/29
 */
public abstract class BasePresenter {

        private DataCall dataCall;

        private boolean running;


        public BasePresenter(DataCall dataCall) {

            this.dataCall = dataCall;

        }



        protected abstract Observable observable(Object... args);



        public void reqeust(Object... args) {
            if (running) {
                return;
            }
            running = true;
             observable(args)

                    .compose(ResponseTransformer.handleResult())

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
                            dataCall.success(result);

                        }

                    }, new Consumer<Throwable>() {

                        @Override

                        public void accept(Throwable throwable) throws Exception {
                            running = false;
                            // 处理异常

//                        UIUtils.showToastSafe("请求失败");

                            dataCall.fail(CustomException.handleException(throwable));

                        }

                    });



        }



        public void unBind() {

            dataCall = null;

        }


    public boolean isRunning() {
        return running;
    }
}
