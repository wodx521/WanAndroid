package com.wanou.wanandroid.view.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.wanou.framelibrary.base.BaseActivity;
import com.wanou.wanandroid.R;

/**
 * Author by wodx521
 * Date on 2018/11/19.
 */
public class BannerDetailActivity extends BaseActivity {
    private WebView mWvBannerDetail;
    private ProgressBar mProgressbar;

    @Override
    protected int getResId() {
        return R.layout.activity_banner_detail;
    }

    @Override
    protected void initView() {
        mProgressbar = findViewById(R.id.progressbar);
        mWvBannerDetail = findViewById(R.id.wv_banner_detail);
        initData();
    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void initData() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        String bannerUrl = bundle.getString("bannerUrl");

        mWvBannerDetail.loadUrl(bannerUrl);
        mWvBannerDetail.addJavascriptInterface(this, "android");
        mWvBannerDetail.setWebChromeClient(webChromeClient);
        mWvBannerDetail.setWebViewClient(webViewClient);
        WebSettings settings = mWvBannerDetail.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
    }

    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient = new WebViewClient() {

        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
            mProgressbar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
            mProgressbar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient = new WebChromeClient() {
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定", null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressbar.setProgress(newProgress);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //点击返回按钮的时候判断有没有上一页
        if (mWvBannerDetail.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            // goBack()表示返回webView的上一页面
            mWvBannerDetail.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mWvBannerDetail.destroy();
        mWvBannerDetail = null;
    }
}
