package com.yangztel.mylib.login;

import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbiz.JumpToBizProfile;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yangztel.lbase.mvp.BaseActivity;
import com.yangztel.mylib.R;
import com.yangztel.mylib.util.EventHelper;

import butterknife.BindView;

/**
 * Created by yangzteL on 2018/7/5 0005.
 */

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.button)
    Button button;
    private String TAG = "msgggggg";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginContract.Presenter initPresent() {
        return new LoginPresenter();
    }

    @Override
    protected void processClick(View v) {
        switch (v.getId()){
            case R.id.button:
                mPresenter.login(editText.getText().toString(),editText2.getText().toString());
//                wechat();
//                getWechatApi();

                break;
        }
    }

    private void wechat() {
        //                IWXAPI api = WXAPIFactory.createWXAPI(this, "wx31ba40c5ec92014e", false);
//
//                if (api.isWXAppInstalled()) {
//                    JumpToBizProfile.Req req = new JumpToBizProfile.Req();
//                    req.toUserName = "gh_333e0108f987"; // 公众号原始ID
//                    req.extMsg = "hello";
//                    req.profileType = JumpToBizProfile.JUMP_TO_NORMAL_BIZ_PROFILE; // 普通公众号
//                    api.sendReq(req);
//                } else {
//                }
//                getWechatApi();
    }

    /**
     * 跳转到微信
     */
    private void getWechatApi(){
        ClipboardManager tvCopy = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        tvCopy.setText("yangzteL");
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // TODO: handle exception
            Toast.makeText(this, "检查到您手机没有安装微信，请安装后使用该功能", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initEventAndData() {
        EventHelper.click(this,button);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        setMouseClick(800,100);
    }
    public void setMouseClick(int x, int y){
        MotionEvent evenDownt = MotionEvent.obtain(System.currentTimeMillis(),
                System.currentTimeMillis() + 100, MotionEvent.ACTION_DOWN, x, y, 0);
        dispatchTouchEvent(evenDownt);
        MotionEvent eventUp = MotionEvent.obtain(System.currentTimeMillis(),
                System.currentTimeMillis() + 100, MotionEvent.ACTION_UP, x, y, 0);
        dispatchTouchEvent(eventUp);
        evenDownt.recycle();
        eventUp.recycle();

        Log.e(TAG, "setMouseClick: 模拟了点击");
    }
}
