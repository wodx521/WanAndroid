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
import com.wanou.wanandroid.bean.CollectArticleBean;
import com.wanou.wanandroid.view.activity.CollectArticleActivity;

/**
 * Author by wodx521
 * Date on 2018/12/3.
 */
public class CollectArticlePresenterImpl extends BasePresenterImpl<CollectArticleActivity> {
    private SmartRefreshLayout mSrlRefresh;

    public void getCollectArticle(String url) {
        OkGoUtils.getRequest(url, "collect_article", null, new CustomizeStringCallback() {
            @Override
            public GeneralResult getGeneralResult(String result) {
                return GsonUtils.fromJson(result, new TypeToken<GeneralResult<CollectArticleBean>>() {
                }.getType());
            }

            @Override
            public void onRequestSuccess(SimpleResponse simpleResponse, GeneralResult generalResult) {
                if (generalResult != null) {
                    CollectArticleBean collectArticleBean = (CollectArticleBean) generalResult.data;
                    mPresenterView.setCollectArticle(collectArticleBean);
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
                    mSrlRefresh.finishRefresh();
                    mSrlRefresh.finishLoadMore();
                }
            }
        });
    }

    public void setCancelCollect(String url, HttpParams stringMap, int id) {
        OkGoUtils.postRequest(url, "cancel_collect", stringMap, new CustomizeStringCallback() {
            @Override
            public GeneralResult getGeneralResult(String result) {
                return GsonUtils.fromJson(result, new TypeToken<GeneralResult>() {
                }.getType());
            }

            @Override
            public void onRequestSuccess(SimpleResponse simpleResponse, GeneralResult generalResult) {
                if (generalResult != null) {
                    mPresenterView.setCancelSuccess(id);
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
