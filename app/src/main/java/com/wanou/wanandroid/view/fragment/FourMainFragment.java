package com.wanou.wanandroid.view.fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wanou.framelibrary.base.BaseActivity;
import com.wanou.framelibrary.base.BaseMvpFragment;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.SystemBean;
import com.wanou.wanandroid.bean.SystemInfoBean;
import com.wanou.wanandroid.constant.UrlConstant;
import com.wanou.wanandroid.presenter.FourPresenterImpl;
import com.wanou.wanandroid.view.adapter.SystemListAdapter;

import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public class FourMainFragment extends BaseMvpFragment<FourPresenterImpl> {
    private FrameLayout mFlLeft;
    private RecyclerView mRvList;
    private SystemListAdapter systemListAdapter;
    private LeftFragment leftFragment;
    private DrawerLayout mDlLayout;
    private Toolbar mToolbar;
    private TextView mTvTitle;

    @Override
    protected int getResId() {
        return R.layout.fragment_four_main;
    }

    @Override
    protected void initView(View view) {
        mFlLeft = view.findViewById(R.id.fl_left);
        mRvList = view.findViewById(R.id.rv_list);
        mDlLayout = view.findViewById(R.id.dl_layout);
        mToolbar = view.findViewById(R.id.toolbar);
        mTvTitle = view.findViewById(R.id.tv_title);

    }

    @Override
    protected void initData() {
        initToolBar();
        mToolbar.setVisibility(View.GONE);
        systemListAdapter = new SystemListAdapter(getActivity());
        mRvList.setAdapter(systemListAdapter);
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

    private void initToolBar() {
        ((BaseActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar supportActionBar = ((BaseActivity) getActivity()).getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeButtonEnabled(true);
        supportActionBar.setTitle("");
        setHasOptionsMenu(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDlLayout, mToolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.unfold);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDlLayout.isDrawerOpen(GravityCompat.START)) {
                    mDlLayout.closeDrawer(GravityCompat.START, true);
                } else {
                    mDlLayout.openDrawer(GravityCompat.START, true);
                }
            }
        });
        mDlLayout.addDrawerListener(actionBarDrawerToggle);
    }

    @Override
    protected FourPresenterImpl getPresenter() {
        return new FourPresenterImpl();
    }

    public void setSuccessData(List<SystemBean> systemBeanList) {
        leftFragment.setSystemBeanList(systemBeanList);
        leftFragment.notifyData();
    }

    public void setSystemData(SystemInfoBean systemInfoBean) {
        int curPage = systemInfoBean.getCurPage();

        List<SystemInfoBean.DatasBean> datas = systemInfoBean.getDatas();
        systemListAdapter.setDatas(datas);
        systemListAdapter.notifyDataSetChanged();
    }
}
