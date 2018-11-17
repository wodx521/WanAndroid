package com.wanou.wanandroid.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.wanou.framelibrary.base.BaseMvpActivity;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.presenter.MainPresenterImpl;

/**
 * Author by wodx521
 * Date on 2018/11/16.
 */
public class MainActivity extends BaseMvpActivity<MainPresenterImpl> {
    private Toolbar mToolbar;
    private FrameLayout mFlContainer;
    private BottomNavigationView mNavigation;

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
        mToolbar = findViewById(R.id.toolbar);
        mFlContainer = findViewById(R.id.fl_container);
        mNavigation = findViewById(R.id.navigation);

        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                return false;
            }
        });
    }

    @Override
    protected void initData() {

    }
}
