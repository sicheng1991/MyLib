package com.yangztel.lbase.mvp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by yangzteL on 2018/7/4 0004.
 */

public class RxManager {
    public RxBus mRxBus = RxBus.getInstance();
    private Map<String, Observable> busObservables = new HashMap<>();
    private CompositeDisposable mDisposables = new CompositeDisposable();

    /**
     * RxBus注入监听
     *
     * @param eventName
     * @param consumer   #rx2
     */
    public <T> void registerBus(String eventName, Consumer<T> consumer) {
        Observable<T> mObservable = mRxBus.register(eventName);
        busObservables.put(eventName,mObservable);
        /*订阅管理*/
        mDisposables.add(mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                throwable.printStackTrace();
            }
        }));
    }
    public void post(Object tag, Object content) {
        mRxBus.post(tag, content);
    }

    public void add(Disposable disposable) {
        /*订阅管理*/
        mDisposables.add(disposable);
    }

    public void clear (){
        mDisposables.clear();
        Set<Map.Entry<String,Observable>> entrySet = busObservables.entrySet();
        for(Map.Entry<String,Observable> entry : entrySet){
            mRxBus.unregister(entry.getKey(),entry.getValue());
        }
    }
}
