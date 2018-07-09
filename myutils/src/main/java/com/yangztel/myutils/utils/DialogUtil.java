package com.yangztel.myutils.utils;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yangztel.myutils.R;

/**
 * Created by yangzteL on 2018/6/27 0027.
 */

public class DialogUtil {
    private Dialog toastDlg;//呼叫、取消对话框


    /**
     * 提示弹框
     *
     * @param centerView  正文布局
     * @param cancelStr   取消按钮文章，传空不显示
     * @param commitStr 确认按钮提示，传空不显示
     * @param inter  按钮回调
     */
    public void showCommonDig(View centerView, String cancelStr, String commitStr, final DialogInterface inter) {
        if (toastDlg != null && toastDlg.isShowing()) {
            toastDlg.dismiss();
            return;
        }
        View view;
        view = LayoutInflater.from(centerView.getContext()).inflate(R.layout.dialog_common, null);
        toastDlg = new Dialog(centerView.getContext(), R.style.FullHeightDialog);
        toastDlg.setContentView(view);

        /** 设置选择框居中显示 */
        Window window = toastDlg.getWindow();
        window.setGravity(Gravity.CENTER);
        LinearLayout llCenter = view.findViewById(R.id.ll_content);
        llCenter.addView(centerView);

        TextView tvClose = view.findViewById(R.id.btn_cancel);
        if(!TextUtils.isEmpty(cancelStr)){
            tvClose.setText(cancelStr);
            tvClose.setOnClickListener(view12 -> {
                toastDlg.dismiss();
                inter.cancel(null);
            });
        }else{
            tvClose.setVisibility(View.GONE);
        }

        TextView tvCommit = view.findViewById(R.id.btn_commit);
        if(!TextUtils.isEmpty(commitStr)){
            tvCommit.setText(commitStr);
            tvCommit.setOnClickListener(v -> {
                toastDlg.dismiss();
                inter.sure(null);
            });
        }else{
            tvClose.setVisibility(View.GONE);
        }
        toastDlg.setCanceledOnTouchOutside(false);
        toastDlg.setCancelable(true);
        toastDlg.show();
    }
    public interface DialogInterface{
        void cancel(Object object);
        void sure(Object object);
    }
}
