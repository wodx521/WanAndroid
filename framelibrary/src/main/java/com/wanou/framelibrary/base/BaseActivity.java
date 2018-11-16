package com.wanou.framelibrary.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wanou.framelibrary.manager.ActivityManage;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    public static void startActivity(Context context, Bundle bundle, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        context.startActivity(intent);
    }

    public static void startActivityForResult(Context context, Bundle bundle, int requestCode, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActivityManage.getInstance().addActivity(this);
        setContentView(getResId());
        initView();
    }

    //设置布局id
    protected abstract int getResId();

    //初始化view
    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManage.getInstance().removeActivity(this);
    }
}
