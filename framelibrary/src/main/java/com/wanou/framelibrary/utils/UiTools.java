package com.wanou.framelibrary.utils;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.wanou.framelibrary.GlobalApplication;

import java.text.DecimalFormat;

/**
 * Author by wodx521
 * Date on 2018/11/5.
 */
public class UiTools {
    private static Toast toast;
    @SuppressLint("StaticFieldLeak")
    public static Context context = GlobalApplication.getContext();
    private static DecimalFormat decimalFormat = new DecimalFormat();
    private static Resources resources = context.getResources();

    /**
     * 获取屏幕宽
     *
     * @param activity 当前页面的activity
     * @return 当前页面宽
     */
    public static int getDeviceWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        return p.x;
    }

    /**
     * 获取屏幕的高
     *
     * @param activity 当前页面的activity
     * @return 当前页面高
     */
    public static int getDeviceHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        return p.y;
    }

    /**
     * 判断传入的字符串是否为空
     *
     * @param strings 传入的字符串数组
     * @return 全部非空返回true 否则返回false
     */
    public static boolean noEmpty(String... strings) {
        if (strings != null && strings.length > 0) {
            for (String string : strings) {
                if (TextUtils.isEmpty(string)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 要获取内容的TextView
     *
     * @param textView TextView
     * @return TextView内容
     */
    public static String getText(TextView textView) {
        return textView.getText().toString().trim();
    }

    /**
     * 格式化数字为指定格式
     *
     * @param number  需要格式化的数字
     * @param pattern 指定格式
     * @return 格式化完成后的字符串
     */
    public static String formatNumber(Object number, String pattern) {
        try {
            decimalFormat.applyPattern(pattern);
            if (number instanceof Double) {
                return decimalFormat.format((double) number);
            } else if (number instanceof Long) {
                return decimalFormat.format((long) number);
            } else if (number instanceof String) {
                if (noEmpty((String) number)) {
                    if (((String) number).contains(".")) {
                        Double aDouble = Double.valueOf((String) number);
                        return formatNumber(aDouble, pattern);
                    } else {
                        Long aLong = Long.valueOf((String) number);
                        return formatNumber(aLong, pattern);
                    }
                }
                return "0";
            } else {
                return decimalFormat.format(number);
            }
        } catch (Exception e) {
            return "0";
        }
    }

    public static void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();

    }

    public static void showToast(@StringRes int stringRes) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(stringRes);
        toast.show();

    }

    /**
     * 获取资源id对应的字符串
     *
     * @param resId 资源ID
     * @return 返回对应字符串
     */
    public static String getString(int resId) {
        return resources.getString(resId);
    }

    /**
     * 获取资源id对应的字符串数组
     *
     * @param resId 资源ID
     * @return 返回对应字符串数组
     */
    public static String[] getStringArray(int resId) {
        return resources.getStringArray(resId);
    }

    /**
     * 获取资源id对应的颜色值
     *
     * @param resId 资源ID
     * @return 返回对应颜色值
     */
    public static int getColor(int resId) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? resources.getColor(resId, null) : resources.getColor(resId);
    }

    public static Animation getAnimation(int resId) {
        return AnimationUtils.loadAnimation(context, resId);
    }

    public static Animator getAnimator(int resId) {
        return AnimatorInflater.loadAnimator(context, resId);
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     *
     * @param dip 需要转换的dp值
     * @return 返回转换后的值
     */
    public static int dip2px(float dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }


    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     *
     * @param px 需要转换的dp值
     * @return 返回转换后的值
     */
    public static int px2dip(int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static View parseLayout(int resLayout, ViewGroup root) {
        LayoutInflater from = LayoutInflater.from(context);
        return from.inflate(resLayout, root, false);
    }

}
