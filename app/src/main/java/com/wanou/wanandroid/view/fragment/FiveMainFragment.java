package com.wanou.wanandroid.view.fragment;

import android.view.View;

import com.wanou.framelibrary.base.BaseMvpFragment;
import com.wanou.framelibrary.utils.LogUtils;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.presenter.FivePresenterImpl;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public class FiveMainFragment extends BaseMvpFragment<FivePresenterImpl> {
    @Override
    protected int getResId() {
        return R.layout.fragment_five_main;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        LogUtils.e("创建了第五个fragment");
    }


    @Override
    protected void isHiddenListener(boolean hidden) {
        if (hidden) {
            LogUtils.e("第五个fragment隐藏了");
        }else{
            LogUtils.e("第五个fragment显示了");
        }
    }

    @Override
    protected FivePresenterImpl getPresenter() {
        return new FivePresenterImpl();
    }
}
