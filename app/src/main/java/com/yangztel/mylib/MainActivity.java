package com.yangztel.mylib;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.Gesture;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.yangztel.lbase.mvp.RxBus;
import com.yangztel.lbase.mvp.RxManager;
import com.yangztel.mylib.util.StatusBarCompat;
import com.yangztel.myutils.utils.ToastUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;



public class MainActivity extends FragmentActivity {
    RxManager rxManager = new RxManager();
    private WebView wvHelp;
    private TextView tv;
    private static  final String TAG = "msggggg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        StatusBarUtil.setTranslucent(this,0);
        StatusBarUtil.setLightMode(this);

        wvHelp = findViewById(R.id.wv_w);
        tv = findViewById(R.id.tv_content);
        Log.e("TAG", "onCreate: " + ViewConfiguration.get(this).getScaledDoubleTapSlop());
//        initWebView("http://182.150.20.24:10087/ZHHWeb/H5/about/about.html");


        tv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int left = tv.getLeft();
                int right = tv.getRight();
                int top  = tv.getTop();
                int bottom = tv.getBottom();

                Log.e(TAG, "onCreate: " + left + ":" + right + ":" + top + ":" + bottom);

            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(ObjectAnimator.ofFloat(tv,"translationX",0,1000)
//        ,ObjectAnimator.ofInt(tv,"textColor",0xFFFF8080,0xFF8080FF));//同时开始动画
        animatorSet.playSequentially(ObjectAnimator.ofFloat(tv,"translationX",0,1000)
                ,ObjectAnimator.ofInt(tv,"textColor",0xFFFF8080,0xFF8080FF));//前一个完了开始下一个
        animatorSet.setDuration(3 * 1000);
        animatorSet.start();



        setWindow();
        rxbus();
    }


    /**
     * window
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setWindow() {
        TextView button=new Button(this);
        button.setText("点我");
        WindowManager wmManager=(WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams lp=new WindowManager.LayoutParams();
        lp.flags= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                |WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

//        lp.gravity= Gravity.TOP;
        lp.x = 200;
        lp.y = 200;
        lp.width = 300;
        lp.height = 200;
        wmManager.addView(button,lp);

//        lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
       button.setOnTouchListener((v, event) -> {
           int x = (int) event.getRawX();
           int y = (int) event.getRawY();

           switch (event.getAction()){
               case MotionEvent.ACTION_MOVE:
                   lp.x = x;
                   lp.y = y;
                   Log.e(TAG, "setWindow: " + x  + " : " + y);
                   wmManager.updateViewLayout(v,lp);
                   break;
           }


           return false;
       });
    }

//    private int lastX;
//    private int lastY;
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
////        VelocityTracker vt = VelocityTracker.obtain();
////        vt.addMovement(event);
////        vt.computeCurrentVelocity(1000);
////        float x = vt.getXVelocity();
////        float y = vt.getYVelocity();
////        Log.e(TAG, "onTouchEvent: " + x + ":" + y);
////        vt.clear();
////        vt.recycle();
//        int x = (int) event.getRawX();
//        int y = (int) event.getRawY();
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int dx = x - lastX;
//                int dy = y - lastY;
//                int tx = (int) (tv.getTranslationX() + dx);
//                int ty = (int) (tv.getTranslationY() + dy);
//                Log.e(TAG, "onTouchEvent: " + x + ":" + y + ":" + tx + ":" + ty );
//                tv.setTranslationX(tx);
//                tv.setTranslationY(ty);
//                break;
//        }
//        lastX = x;
//        lastY = y;
//        return super.onTouchEvent(event);
//    }

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
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
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
