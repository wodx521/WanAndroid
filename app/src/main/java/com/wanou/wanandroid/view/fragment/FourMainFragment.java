package com.wanou.wanandroid.view.fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.wanou.framelibrary.base.BaseMvpFragment;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.SystemBean;
import com.wanou.wanandroid.constant.UrlConstant;
import com.wanou.wanandroid.presenter.FourPresenterImpl;
import com.wanou.wanandroid.view.adapter.TabListAdapter;

import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public class FourMainFragment extends BaseMvpFragment<FourPresenterImpl> {
    private FrameLayout mFlLeft;
    private RecyclerView mRvList;
    private TabListAdapter tabListAdapter;
    private LeftFragment leftFragment;
    private DrawerLayout mDlLayout;

    @Override
    protected int getResId() {
        return R.layout.fragment_four_main;
    }

    @Override
    protected void initView(View view) {
        mFlLeft = view.findViewById(R.id.fl_left);
        mRvList = view.findViewById(R.id.rv_list);
        mDlLayout = view.findViewById(R.id.dl_layout);

    }

    @Override
    protected void initData() {
        tabListAdapter = new TabListAdapter(getActivity());
        mRvList.setAdapter(tabListAdapter);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        leftFragment = new LeftFragment();
        fragmentTransaction.add(R.id.fl_left, leftFragment, "left_fragment");
        fragmentTransaction.show(leftFragment);
        fragmentTransaction.commit();
        String url = UrlConstant.BASEURL + "/tree/json";
        mPresenter.getSystemInfo(url);
    }

    @Override
    protected void isHiddenListener(boolean hidden) {
    }

    @Override
    protected FourPresenterImpl getPresenter() {
        return new FourPresenterImpl();
    }

    public void setSuccessData(List<SystemBean> systemBeanList) {
        leftFragment.setSystemBeanList(systemBeanList);
        leftFragment.notifyData();
    }
}
