package com.yangztel.mylib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import android.widget.Button;
import android.widget.TextView;

import com.yangztel.lbase.view.PrizeView;
import com.yangztel.mylib.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 抽奖
 * Created by yangzteL on 2018/7/12 0012.
 */

public class PrizeDrawActivity extends FragmentActivity{
    private PrizeView pv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_drize_draw);

        pv = findViewById(R.id.pv_prize);

        Button button = findViewById(R.id.btn_commit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pv.prize();
                pv.prize1();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        List<TextView> list= new ArrayList<>();
        for(int i = 0;i < 8;i++){
            TextView tv = new TextView(this);
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.b,0,0);
            tv.setText("奖品" + (i + 1));
            list.add(tv);
        }

        pv.initPrize(list);


    }
}
