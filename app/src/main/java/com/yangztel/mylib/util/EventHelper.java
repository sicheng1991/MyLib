package com.yangztel.mylib.util;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by yangzteL on 2018/7/5 0005.
 */

public class EventHelper {
    public static void click(Activity activity, View... views) {
        if (!(activity instanceof View.OnClickListener)) return;
        if (views == null || views.length == 0) return;
        for (View v : views) {
            v.setOnClickListener((View.OnClickListener) activity);
        }
    }

    public static void click(Fragment fragment, View... views) {
        if (!(fragment instanceof View.OnClickListener)) return;
        if (views == null || views.length == 0) return;
        for (View v : views) {
            v.setOnClickListener((View.OnClickListener) fragment);
        }
    }

}
