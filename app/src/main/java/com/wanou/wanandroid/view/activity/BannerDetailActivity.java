package com.wanou.wanandroid.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.wanou.framelibrary.base.BaseActivity;
import com.wanou.wanandroid.R;

/**
 * Author by wodx521
 * Date on 2018/11/19.
 */
@SuppressLint({"SetJavaScriptEnabled","JavascriptInterface"})
public class BannerDetailActivity extends BaseActivity {

    private FrameLayout mFlWebContainer;
    private AgentWeb mAgentWeb;

    @Override
    protected int getResId() {
        return R.layout.activity_banner_detail;
    }

    @Override
    protected void initView() {
        mFlWebContainer = findViewById(R.id.fl_web_container);
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        String bannerUrl = bundle.getString("bannerUrl");
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mFlWebContainer, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .interceptUnkownUrl() //拦截找不到相关页面
                .createAgentWeb()
                .ready()
                .go(bannerUrl);

        WebSettings settings = mAgentWeb.getWebCreator().getWebView().getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        settings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        settings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }


    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
