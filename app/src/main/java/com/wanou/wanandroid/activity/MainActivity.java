package com.wanou.wanandroid.activity;

import com.wanou.framelibrary.base.BaseMvpActivity;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.presenter.MainPresenterImpl;

/**
 * Author by wodx521
 * Date on 2018/11/16.
 */
public class MainActivity extends BaseMvpActivity<MainPresenterImpl> {

    @Override
    protected MainPresenterImpl getPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected int getResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
