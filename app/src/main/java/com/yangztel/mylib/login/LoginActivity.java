package com.yangztel.mylib.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yangztel.lbase.base.BaseActivity;
import com.yangztel.mylib.R;
import com.yangztel.mylib.util.EventHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

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

}
