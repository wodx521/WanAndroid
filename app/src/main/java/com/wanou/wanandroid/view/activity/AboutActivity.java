package com.wanou.wanandroid.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.wanou.framelibrary.base.BaseActivity;
import com.wanou.framelibrary.utils.AppInfoUtils;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.R;

/**
 * Author by wodx521
 * Date on 2018/12/4.
 */
public class AboutActivity extends BaseActivity {
    private TextView mTvAboutUs;
    private TextView mTvVersion;
    private TextView mTvGithubInfo;
    private TextView mTvTitle;
    private Toolbar mToolbar;

    @Override
    protected int getResId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        mTvAboutUs = findViewById(R.id.tv_about_us);
        mTvVersion = findViewById(R.id.tv_version);
        mTvGithubInfo = findViewById(R.id.tv_github_info);
        mTvTitle = findViewById(R.id.tv_title);
        mToolbar = findViewById(R.id.toolbar);
        initData();
    }

    private void initData() {
        mTvTitle.setText("关于软件");
        mTvAboutUs.setText("");
        mTvVersion.setText(AppInfoUtils.getLocalVersionName());
        mTvGithubInfo.setText(Html.fromHtml(UiTools.getString(R.string.info)));

        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle("");
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
