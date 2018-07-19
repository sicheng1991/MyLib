package com.yangztel.mylib.ui.login;

import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                break;
        }
    }




    @Override
    protected void initEventAndData() {
        EventHelper.click(this,button);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
