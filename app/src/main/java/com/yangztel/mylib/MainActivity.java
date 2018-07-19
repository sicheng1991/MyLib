package com.yangztel.mylib;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yangztel.lbase.mvp.RxBus;
import com.yangztel.lbase.mvp.RxManager;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {
    RxManager rxManager = new RxManager();
    private WebView wvHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wvHelp = findViewById(R.id.wv_w);

        initWebView("http://182.150.20.24:10087/ZHHWeb/H5/about/about.html");

        rxbus();
    }
    @SuppressLint("JavascriptInterface")
    public void initWebView(String url) {
        WebSettings settings = wvHelp.getSettings();
        settings.setJavaScriptEnabled(true);

//        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //google调试
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            WebView.setWebContentsDebuggingEnabled(true);
        }

        wvHelp.addJavascriptInterface(this, "local_obj");
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        wvHelp.requestFocus();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(false);//是否使用缓存
        wvHelp.loadUrl(url);
        wvHelp.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                return true;
            }
        });
        wvHelp.setWebViewClient(new WebViewClient() {

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e("当前url：:", url);
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });

    }

    private void rxbus() {
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
