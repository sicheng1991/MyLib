package com.yangztel.lbase.base;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


/**
 * Created by yangzteL on 2018/7/4 0004.
 */

public class RxBus {
    static RxBus mRxBus;
    private ConcurrentHashMap<String,List<Subject>> busMap = new ConcurrentHashMap<>();
    public static RxBus getInstance() {
        if (mRxBus == null) {
            synchronized (RxBus.class) {
                if (mRxBus == null) {
                    mRxBus = new RxBus();
                }
            }
        }
        return mRxBus;
    }
    private RxBus() {}

    private static boolean isEmpty(Collection<Subject> collection) {
        return null == collection || collection.isEmpty();
    }
    public <T> Observable<T> register(@NonNull String tag) {
        List<Subject> subjectList = busMap.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            busMap.put(tag, subjectList);
        }
        Subject<T> subject;
        subjectList.add(subject = PublishSubject.create());
        return subject;
    }
    public void  unregister(@NonNull Object tag, Observable observable) {
        List<Subject> subjects = busMap.get(tag);
        if (null != subjects) {
            subjects.remove(observable);
            if (isEmpty(subjects)) {
                busMap.remove(tag);
            }
        }
    }
    public void post(@NonNull Object tag, @NonNull Object content) {
        List<Subject> subjectList = busMap.get(tag);
        if (!isEmpty(subjectList)) {
            for (Subject subject : subjectList) {
                subject.onNext(content);
            }
        }
    }

}
