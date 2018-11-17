package com.wanou.wanandroid.view.fragment;

import android.view.View;

import com.wanou.framelibrary.base.BaseFragment;
import com.wanou.framelibrary.utils.LogUtils;
import com.wanou.wanandroid.R;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public class SecondMainFragment extends BaseFragment {
    @Override
    protected int getResId() {
        return R.layout.fragment_second_main;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        LogUtils.e("创建了第二个fragment");
    }

    @Override
    protected void isHiddenListener(boolean hidden) {
        if (hidden) {
            LogUtils.e("第二个fragment隐藏了");
        }else{
            LogUtils.e("第二个fragment显示了");
        }
    }
}
