package com.yangztel.mylib.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.yangztel.mylib.R;
import com.yangztel.mylib.ui.adapter.PickerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择器
 * Created by yangzteL on 2018/7/16 0016.
 */

public class PickerActivity extends FragmentActivity {

    private RecyclerView recyclerView;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_picker);

        recyclerView = findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        List<String> list = new ArrayList<>();
        list.add("就");
        list.add("选");
        list.add("垒");
        list.add("特");
        list.add("恩");
        list.add("就1");
        list.add("选1");
        list.add("垒1");
        list.add("特1");
        list.add("恩1");
        recyclerView.setAdapter(new PickerAdapter(this,list));
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);//到底无动画
        initEvent();
    }
    private void initEvent(){
     recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
         @Override
         public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
             super.onScrollStateChanged(recyclerView, newState);
//             if (mShouldScroll) {
//                 mShouldScroll = false;
//                 smoothMoveToPosition(recyclerView, mToPosition);
//             }
         }

         @Override
         public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//             super.onScrolled(recyclerView, dx, dy);
             int x = dy % 40;
             x = x > 20 ? 40 - x:-x;
//             Log.e("msggggg", "onScrolled: " + dy + ":" + x);
//             recyclerView.setScrollY(x);
             int y = dy / 40;
             position = position + y;
             Log.e("msggggg", "position: " + y);
             recyclerView.smoothScrollToPosition(position);
         }
     });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //目标项是否在最后一个可见项之后
    private boolean mShouldScroll;
    //记录目标项位置
    private int mToPosition;
    /**
     * 滑动到指定位置
     */
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }
}
