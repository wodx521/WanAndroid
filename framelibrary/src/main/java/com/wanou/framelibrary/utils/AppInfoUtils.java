package com.wanou.framelibrary.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.wanou.framelibrary.GlobalApplication;

public class AppInfoUtils {

    private static int getLocalVersion() {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = GlobalApplication.getContext().getPackageManager()
                    .getPackageInfo(GlobalApplication.getContext().getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            LogUtils.d("本软件的版本号--" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName() {
        String localVersionName = "";
        try {
            PackageInfo packageInfo = GlobalApplication.getContext().getPackageManager()
                    .getPackageInfo(GlobalApplication.getContext().getPackageName(), 0);
            localVersionName = packageInfo.versionName;
            LogUtils.d("本软件的版本号--" + localVersionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersionName;
    }

    public static boolean isUpdate(int newVersion) {
        return newVersion > getLocalVersion();
    }
}
