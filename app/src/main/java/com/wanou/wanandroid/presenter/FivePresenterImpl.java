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
import com.wanou.wanandroid.view.fragment.FiveMainFragment;

/**
 * Author by wodx521
 * Date on 2018/11/13.
 */
public class FivePresenterImpl extends BasePresenterImpl<FiveMainFragment> {

    public void exitLogout(String url) {
        OkGoUtils.getRequest(url, "exit_log", null, new CustomizeStringCallback() {
            @Override
            public GeneralResult getGeneralResult(String result) {
                return GsonUtils.fromJson(result, new TypeToken<GeneralResult<Void>>() {
                }.getType());
            }

            @Override
            public void onRequestSuccess(SimpleResponse simpleResponse, GeneralResult generalResult) {
                if (generalResult != null) {
                    mPresenterView.setLogoutSuccess();
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
