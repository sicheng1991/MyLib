package com.yangztel.mylib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yangztel.lbase.mvp.RxBus;
import com.yangztel.lbase.mvp.RxManager;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {
    RxManager rxManager = new RxManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxManager.registerBus("tag", (Consumer<String>) s -> {
            Log.d("taggggg", "onCreate: " + s);
        });
        Disposable disposable = Observable.just("sss").subscribe(s -> {

        });
        RxBus.getInstance().post("tag","send message");

    }

    @Override
    protected void onResume() {
        super.onResume();
        RxBus.getInstance().post("tag","send onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        RxBus.getInstance().post("tag","send onPause");
        getWindow().getDecorView();
        rxManager.clear();
    }

    @Override
    protected void onStop() {
        super.onStop();
        RxBus.getInstance().post("tag","send onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().post("tag","send onDestroy");
    }
}
