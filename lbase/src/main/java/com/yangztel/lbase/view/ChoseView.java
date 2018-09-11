package com.yangztel.lbase.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yangzteL on 2018/9/10 0010.
 */

public class ChoseView extends View {
    private Paint paint;
    private int ringProgress;//圆环进度

    public ChoseView(Context context) {
        super(context);
        init(context);
    }

    public ChoseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChoseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(0,0,20,20);
        canvas.drawArc(rectF,180,getRingProgress(),false,paint);


    }

    private int getRingProgress() {
        return ringProgress;
    }

    private void setRingProgress(int ringProgress) {
        //动画执行的时候，会调用setter
        //这里我们可以将动画生成的数值记录下来,用变量存起来，在ondraw的时候用
        this.ringProgress = ringProgress;
        //记得重绘
        postInvalidate();
    }

}
