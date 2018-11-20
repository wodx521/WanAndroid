package com.wanou.framelibrary.okgoutil;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.wanou.framelibrary.R;
import com.wanou.framelibrary.bean.GeneralResult;
import com.wanou.framelibrary.bean.SimpleResponse;
import com.wanou.framelibrary.utils.GsonUtils;
import com.wanou.framelibrary.utils.UiTools;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Author by wodx521
 * Date on 2018/11/12.
 * @author Administrator
 */
public abstract class CustomizeStringCallback extends StringCallback {

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        onRequestStart(request);
    }

    @Override
    public void onSuccess(Response<String> response) {
        String body = response.body();
        if (body == null) {
            return;
        }
        try {
            SimpleResponse simpleResponse = GsonUtils.fromJson(body, SimpleResponse.class);
            int resultCode = simpleResponse.code;
            GeneralResult generalResult = null;
            if (resultCode == 0) {
                generalResult = getGeneralResult(body);
                simpleResponse = null;
            }
            onRequestSuccess(simpleResponse, generalResult);
        } catch (Exception e) {
            response.setException(e);
            onError(response);
        }
    }

    @Override
    public void onError(Response<String> response) {
        Throwable exception = response.getException();
        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            UiTools.showToast(UiTools.getString(R.string.connect_fail));
        } else if (exception instanceof SocketTimeoutException) {
            UiTools.showToast(UiTools.getString(R.string.connect_out_time));
        } else {
            UiTools.showToast(UiTools.getString(R.string.server_error));
        }
        exception.printStackTrace();
        onRequestError(exception);
    }

    @Override
    public void onFinish() {
        onRequestFinish();
    }

    /**
     * 返回解析对象
     *
     * @param result 结果string
     * @return 返回解析对象
     */
    public abstract GeneralResult getGeneralResult(String result);

    public abstract void onRequestSuccess(SimpleResponse simpleResponse, GeneralResult generalResult);

    public abstract void onRequestError(Throwable exception);

    public abstract void onRequestStart(Request<String, ? extends Request> request);

    public abstract void onRequestFinish();
}
