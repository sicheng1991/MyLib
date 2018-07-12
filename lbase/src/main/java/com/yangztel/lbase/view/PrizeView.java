package com.yangztel.lbase.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yangztel.lbase.R;

import java.util.List;

import static android.content.ContentValues.TAG;
import static android.view.animation.Animation.RELATIVE_TO_PARENT;

/**
 * Created by yangzteL on 2018/7/12 0012.
 */

public class PrizeView extends RelativeLayout{
    private View mView;
    private RelativeLayout rlOut;
    private RelativeLayout rlCenter;
    private ImageView ivArrow;
    private int prizeSize;
    private final  String TAG = "PrizeView";
    private Context context;
    private List<TextView> texts;


    public PrizeView(Context context) {
        super(context);
    }

    public PrizeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public PrizeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.context = context;
        mView = LayoutInflater.from(context).inflate(R.layout.view_drize, this, true);
        rlOut = mView.findViewById(R.id.rl_out);
        rlCenter = mView.findViewById(R.id.rl_center);
        ivArrow = mView.findViewById(R.id.img_arrow);




        rlOut.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获取width和height

                //注意取消监听，因为该方法会调用多次！
                rlOut.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public void prize(){
        //        RotateAnimation animation = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.rotate_anim);
        int degrees = randomDegress();
        Log.e(TAG, "degrees" + degrees);
        RotateAnimation animation = new RotateAnimation( 0,degrees + 1800,RELATIVE_TO_PARENT,0.5f,RELATIVE_TO_PARENT,0.5f);
        animation.setFillBefore(false);
        animation.setFillAfter(true);
        animation.setDuration(3000);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        ivArrow.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int index =  (degrees) / (360 / texts.size()) ;
                Toast.makeText(context, "恭喜你抽到了:" + texts.get(index).getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 奖品动
     */
    public void prize1(){
        int degrees = randomDegress();
        Log.e(TAG, "degrees" + degrees);
        RotateAnimation animation = new RotateAnimation( 0,degrees + 1800,RELATIVE_TO_PARENT,0.5f,RELATIVE_TO_PARENT,0.5f);
        animation.setFillBefore(false);
        animation.setFillAfter(true);
        animation.setDuration(3000);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        rlOut.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int index =  (texts.size() - (degrees) / (360 / texts.size())) % texts.size();
                Toast.makeText(context, "恭喜你抽到了:"+ texts.get(index).getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @SuppressLint("ResourceAsColor")
    public void initPrize(List<TextView> textViews){
        this.texts = textViews;
        if(textViews == null || textViews.size() < 2 ){
            Log.e(TAG, "initPrize: Prize size must more than 2");
            return;
        }
        prizeSize = textViews.size();
        int angle = 360 / prizeSize;
        for(int i = 0; i < prizeSize;i++){
            ImageView imageView = new ImageView(context);
            Drawable drawable = context.getResources().getDrawable(R.drawable.img_spliter_black);
            imageView.setImageDrawable(drawable);
//            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.a));
            imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,540));
            imageView.setPadding(0,10,0,0);
           imageView.setPivotX(540);
           imageView.setPivotY(540);
           imageView.setRotation(i * angle + 180 / textViews.size());
            rlOut.addView(imageView);


            TextView tv = textViews.get(i);
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(lp);
            tv.setPadding(160 * 3,5,0,0);
            tv.setPivotX(540);
            tv.setPivotY(540);
            tv.setRotation(i * angle);
            rlOut.addView(tv);
        }

    }


    private int randomDegress(){
        return ((int)( Math.random() * texts.size())) * (360 / texts.size());
    }


}
