package com.wanou.framelibrary.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public abstract class BaseFragment extends Fragment implements BaseView{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getResId() > 0) {
            return inflater.inflate(getResId(), container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initData();
    }

    protected abstract int getResId();

    protected abstract void initView(View view);

    protected abstract void initData();

    @Override
    public void onHiddenChanged(boolean hidden) {
        isHiddenListener(hidden);
    }

    protected abstract void isHiddenListener(boolean hidden);
}
