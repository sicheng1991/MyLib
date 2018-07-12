package com.yangztel.lbase.net;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Rx线程转换
 *
 * Created by yangzteL on 2018/7/11 0011.
 */

public class RxSchedulers {
    public static <T> ObservableTransformer<T, T> io_main() {
        return upstream -> upstream.subscribeOn(Schedulers.newThread()).unsubscribeOn(Schedulers.newThread()
        ).observeOn(AndroidSchedulers.mainThread());
    }
}
