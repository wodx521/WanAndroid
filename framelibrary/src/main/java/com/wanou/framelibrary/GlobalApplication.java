package com.wanou.framelibrary;

import android.app.Application;

import com.wanou.framelibrary.okgoutil.OkGoUtils;
import com.wanou.framelibrary.utils.LogUtils;

/**
 * Author by wodx521
 * Date on 2018/11/7.
 */
public class GlobalApplication extends Application {
    private static GlobalApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        OkGoUtils.initOkGo(this);
        LogUtils.initLogUtils(this);
    }

    public static GlobalApplication getContext() {
        return context;
    }
}