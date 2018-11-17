package com.wanou.wanandroid.view.fragment;

import android.view.View;

import com.wanou.framelibrary.base.BaseMvpFragment;
import com.wanou.framelibrary.utils.LogUtils;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.LoginBean;
import com.wanou.wanandroid.presenter.FirstPresenterImpl;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public class FirstMainFragment extends BaseMvpFragment<FirstPresenterImpl> {


    @Override
    protected int getResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void isHiddenListener(boolean hidden) {
    }

    @Override
    protected FirstPresenterImpl getPresenter() {
        return new FirstPresenterImpl();
    }

    public void setLoginSuccess(LoginBean data) {
        LogUtils.e(data.getToken());
    }
}
