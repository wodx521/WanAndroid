package com.wanou.wanandroid.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.okgo.model.HttpParams;
import com.wanou.framelibrary.base.BaseMvpActivity;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.constant.UrlConstant;
import com.wanou.wanandroid.presenter.LoginPresenterImpl;

/**
 * Author by wodx521
 * Date on 2018/11/16.
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenterImpl> implements View.OnClickListener {
    private EditText mEtUsername, mEtPassword;
    private Button mBtLogin, mBtRegister;
    private HttpParams httpParams = new HttpParams();

    @Override
    protected LoginPresenterImpl getPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    protected int getResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mEtUsername = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);
        mBtLogin = findViewById(R.id.bt_login);
        mBtRegister = findViewById(R.id.bt_register);

        mBtLogin.setOnClickListener(this);
        mBtRegister.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        String userName;
        String password;
        switch (v.getId()) {
            case R.id.bt_login:
                userName = UiTools.getText(mEtUsername);
                password = UiTools.getText(mEtPassword);
                if (UiTools.noEmpty(userName, password)) {
                    httpParams.clear();
                    httpParams.put("username", userName);
                    httpParams.put("password", password);
                    mPresenter.loginOperate1(UrlConstant.BASEURL + "/user/login", httpParams);
                } else {
                    if (!UiTools.noEmpty(userName)) {
                        UiTools.showToast("请输入用户名");
                    } else {
                        UiTools.showToast("请输入密码");
                    }
                }
                break;
            case R.id.bt_register:
            default:
                UiTools.showToast("稍后注册");
                break;
        }
    }

    public void setLoginSuccess() {
        startActivity(this, null, MainActivity.class);
    }
}
