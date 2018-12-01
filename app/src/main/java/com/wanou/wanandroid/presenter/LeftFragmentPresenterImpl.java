package com.wanou.wanandroid.presenter;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.request.base.Request;
import com.wanou.framelibrary.base.BasePresenterImpl;
import com.wanou.framelibrary.bean.GeneralResult;
import com.wanou.framelibrary.bean.SimpleResponse;
import com.wanou.framelibrary.okgoutil.CustomizeStringCallback;
import com.wanou.framelibrary.okgoutil.OkGoUtils;
import com.wanou.framelibrary.utils.GsonUtils;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.bean.SystemInfoBean;
import com.wanou.wanandroid.view.fragment.FourMainFragment;
import com.wanou.wanandroid.view.fragment.LeftFragment;

/**
 * Author by wodx521
 * Date on 2018/11/23.
 */
public class LeftFragmentPresenterImpl extends BasePresenterImpl<LeftFragment> {
    public void getSystemContentList(String url) {
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
                    FourMainFragment fourMainFragment = (FourMainFragment) mPresenterView.getParentFragment();
                    fourMainFragment.setSystemData(systemInfoBean);
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
