package com.wanou.wanandroid.presenter;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wanou.framelibrary.base.BasePresenterImpl;
import com.wanou.framelibrary.bean.GeneralResult;
import com.wanou.framelibrary.bean.SimpleResponse;
import com.wanou.framelibrary.okgoutil.CustomizeStringCallback;
import com.wanou.framelibrary.okgoutil.OkGoUtils;
import com.wanou.framelibrary.utils.GsonUtils;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.ArticleListBean;
import com.wanou.wanandroid.bean.HotKeyBean;
import com.wanou.wanandroid.view.activity.SearchActivity;

import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/12/8.
 */
public class SearchPresenterImpl extends BasePresenterImpl<SearchActivity> {

    private SmartRefreshLayout mSrlRefresh;

    public void getSearchList(String url, HttpParams httpParams, String content) {
        OkGoUtils.postRequest(url, "search_list", httpParams, new CustomizeStringCallback() {
            @Override
            public GeneralResult getGeneralResult(String result) {
                return GsonUtils.fromJson(result, new TypeToken<GeneralResult<ArticleListBean>>() {
                }.getType());
            }

            @Override
            public void onRequestSuccess(SimpleResponse simpleResponse, GeneralResult generalResult) {
                if (generalResult != null) {
                    ArticleListBean articleListBean = (ArticleListBean) generalResult.data;
                    mPresenterView.setSearchList(articleListBean, content);
                } else {
                    if (UiTools.noEmpty(simpleResponse.msg)) {
                        UiTools.showToast(simpleResponse.msg);
                    }
                }
            }

            @Override
            public void onRequestError(Throwable exception) {

            }

            @Override
            public void onRequestStart(Request<String, ? extends Request> request) {
                mSrlRefresh = mPresenterView.findViewById(R.id.srl_refresh);

            }

            @Override
            public void onRequestFinish() {
                if (mSrlRefresh.isLoading() || mSrlRefresh.isRefreshing()) {
                    mSrlRefresh.finishLoadMore();
                    mSrlRefresh.finishRefresh();
                }
            }
        });
    }

    public void getHotContent(String url) {
        OkGoUtils.getRequest(url, "search_word", null, new CustomizeStringCallback() {
            @Override
            public GeneralResult getGeneralResult(String result) {
                return GsonUtils.fromJson(result, new TypeToken<GeneralResult<List<HotKeyBean>>>() {
                }.getType());
            }

            @Override
            public void onRequestSuccess(SimpleResponse simpleResponse, GeneralResult generalResult) {
                if (generalResult != null) {
                    List<HotKeyBean> hotKeyBeanList = (List<HotKeyBean>) generalResult.data;
                    mPresenterView.setHotKey(hotKeyBeanList);
                } else {
                    if (UiTools.noEmpty(simpleResponse.msg)) {
                        UiTools.showToast(simpleResponse.msg);
                    }
                }
            }

            @Override
            public void onRequestError(Throwable exception) {

            }

            @Override
            public void onRequestStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onRequestFinish() {

            }
        });
    }

    public void setCollect(String url, int position, boolean isCollect) {
        OkGoUtils.postRequest(url, "cancel_collect", null, new CustomizeStringCallback() {
            @Override
            public GeneralResult getGeneralResult(String result) {
                return GsonUtils.fromJson(result, new TypeToken<GeneralResult>() {
                }.getType());
            }

            @Override
            public void onRequestSuccess(SimpleResponse simpleResponse, GeneralResult generalResult) {
                if (generalResult != null) {
                    mPresenterView.setCollectListener(position, isCollect);
                } else {
                    if (UiTools.noEmpty(simpleResponse.msg)) {
                        UiTools.showToast(simpleResponse.msg);
                    }
                }
            }

            @Override
            public void onRequestError(Throwable exception) {

            }

            @Override
            public void onRequestStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onRequestFinish() {

            }
        });
    }
}
