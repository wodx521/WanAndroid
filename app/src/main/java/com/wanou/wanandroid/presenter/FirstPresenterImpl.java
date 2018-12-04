package com.wanou.wanandroid.presenter;

import com.google.gson.reflect.TypeToken;
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
import com.wanou.wanandroid.bean.BannerBean;
import com.wanou.wanandroid.bean.TabListBean;
import com.wanou.wanandroid.view.fragment.FirstMainFragment;

import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/13.
 */
public class FirstPresenterImpl extends BasePresenterImpl<FirstMainFragment> {
    private SmartRefreshLayout srlRefresh;

    public void getBannerInfo(String url) {
        OkGoUtils.getRequest(url, "banner", null, new CustomizeStringCallback() {
            @Override
            public GeneralResult getGeneralResult(String result) {
                return GsonUtils.fromJson(result, new TypeToken<GeneralResult<List<BannerBean>>>() {
                }.getType());
            }

            @Override
            public void onRequestSuccess(SimpleResponse simpleResponse, GeneralResult generalResult) {
                if (generalResult != null) {
                    List<BannerBean> bannerBeanList = (List<BannerBean>) generalResult.data;
                    mPresenterView.setBannerList(bannerBeanList);
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

    public void getTabListInfo(String url) {
        OkGoUtils.getRequest(url, "tab_list", null, new CustomizeStringCallback() {
            @Override
            public GeneralResult getGeneralResult(String result) {
                return GsonUtils.fromJson(result, new TypeToken<GeneralResult<TabListBean>>() {
                }.getType());
            }

            @Override
            public void onRequestSuccess(SimpleResponse simpleResponse, GeneralResult generalResult) {
                if (generalResult != null) {
                    TabListBean tabListBean = (TabListBean) generalResult.data;
                    mPresenterView.setTabSuccess(tabListBean);
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
                if (mPresenterView.getView() != null) {
                    srlRefresh = mPresenterView.getView().findViewById(R.id.srl_refresh);
                }
            }

            @Override
            public void onRequestFinish() {
                if (srlRefresh.isRefreshing() || srlRefresh.isLoading()) {
                    srlRefresh.finishRefresh();
                    srlRefresh.finishLoadMore();
                }
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
                    mPresenterView.setCollectListener(position,isCollect);
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
