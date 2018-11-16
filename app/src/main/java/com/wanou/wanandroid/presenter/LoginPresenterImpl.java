package com.wanou.wanandroid.presenter;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;
import com.wanou.framelibrary.base.BasePresenterImpl;
import com.wanou.framelibrary.bean.GeneralResult;
import com.wanou.framelibrary.bean.SimpleResponse;
import com.wanou.framelibrary.okgoutil.CustomizeStringCallback;
import com.wanou.framelibrary.okgoutil.OkGoUtils;
import com.wanou.framelibrary.utils.GsonUtils;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.activity.LoginActivity;
import com.wanou.wanandroid.bean.LoginBean;

/**
 * Author by wodx521
 * Date on 2018/11/16.
 */
public class LoginPresenterImpl extends BasePresenterImpl<LoginActivity> {

    public void loginOperate(String url, HttpParams httpParams) {
        OkGoUtils.postRequest(url, "login", httpParams, new CustomizeStringCallback() {
            @Override
            public GeneralResult getGeneralResult(String result) {
                return GsonUtils.fromJson(result, new TypeToken<GeneralResult<LoginBean>>() {
                }.getType());
            }

            @Override
            public void onRequestSuccess(SimpleResponse simpleResponse, GeneralResult generalResult) {
                if (generalResult != null) {
                    mPresenterView.setLoginSuccess();
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
