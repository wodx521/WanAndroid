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
import com.wanou.wanandroid.bean.SystemInfoBean;
import com.wanou.wanandroid.view.fragment.FourMainFragment;

/**
 * Author by wodx521
 * Date on 2018/11/13.
 */
public class FourPresenterImpl extends BasePresenterImpl<FourMainFragment> {

    private SmartRefreshLayout mSrlRefresh;

    public void getSystemContentList(String url, int id) {
        OkGoUtils.getRequest(url, "system_info", null, new CustomizeStringCallback() {
            @Override
            public GeneralResult getGeneralResult(String result) {
                return GsonUtils.fromJson(result, new TypeToken<GeneralResult<SystemInfoBean>>() {
                }.getType());
            }

            @Override
            public void onRequestSuccess(SimpleResponse simpleResponse, GeneralResult generalResult) {
                if (generalResult != null) {
                    SystemInfoBean systemInfoBean = (SystemInfoBean) generalResult.data;
                    mPresenterView.setSystemData(systemInfoBean, id);
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
                    mSrlRefresh = mPresenterView.getView().findViewById(R.id.srl_refresh);

                }
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
