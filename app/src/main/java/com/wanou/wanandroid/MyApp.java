package com.wanou.wanandroid;

import android.content.Context;

import com.wanou.framelibrary.GlobalApplication;

/**
 * Author by wodx521
 * Date on 2018/11/16.
 */
public class MyApp extends GlobalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected Context getAppContext() {
        return this;
    }

}
