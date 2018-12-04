package com.wanou.wanandroid.view.fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wanou.framelibrary.base.BaseActivity;
import com.wanou.framelibrary.base.BaseMvpFragment;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.DatasBean;
import com.wanou.wanandroid.bean.SystemInfoBean;
import com.wanou.wanandroid.constant.UrlConstant;
import com.wanou.wanandroid.presenter.FourPresenterImpl;
import com.wanou.wanandroid.view.adapter.TabListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public class FourMainFragment extends BaseMvpFragment<FourPresenterImpl> {
    private RecyclerView mRvList;
    private TabListAdapter tabListAdapter;
    private DrawerLayout mDlLayout;
    private Toolbar mToolbar;
    private TextView mTvTitle;
    private SmartRefreshLayout mSrlRefresh;
    private int page = 0;
    private List<DatasBean> tempData = new ArrayList<>();

    @Override
    protected int getResId() {
        return R.layout.fragment_four_main;
    }

    @Override
    protected void initView(View view) {
        mRvList = view.findViewById(R.id.rv_list);
        mDlLayout = view.findViewById(R.id.dl_layout);
        mToolbar = view.findViewById(R.id.toolbar);
        mTvTitle = view.findViewById(R.id.tv_title);
        mSrlRefresh = view.findViewById(R.id.srl_refresh);

    }

    @Override
    protected void initData() {
        initToolBar();
        mToolbar.setVisibility(View.GONE);
        tabListAdapter = new TabListAdapter(getActivity());
        mRvList.setAdapter(tabListAdapter);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        LeftFragment leftFragment = new LeftFragment();
        fragmentTransaction.add(R.id.fl_left, leftFragment, "left_fragment");
        fragmentTransaction.show(leftFragment);
        fragmentTransaction.commit();

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

    // 侧边fragment中获取选择的id，访问网络获取知识体系列表
    public void getSystemList(int id) {
        tempData.clear();
        page = 0;
        String url = UrlConstant.BASEURL + "/article/list/" + page + "/json?cid=" + id;
        mPresenter.getSystemContentList(url, id);
    }

    // 处理结果，
    public void setSystemData(SystemInfoBean systemInfoBean, int id) {
        int curPage = systemInfoBean.getCurPage();
        int pageCount = systemInfoBean.getPageCount();
        List<DatasBean> datas = systemInfoBean.getDatas();
        tempData.addAll(datas);
        tabListAdapter.setDatas(tempData, 0, false);

        mSrlRefresh.setEnableLoadMore(curPage < pageCount);
        mSrlRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page += 1;
                String url = UrlConstant.BASEURL + "/article/list/" + page + "/json?cid=" + id;
                mPresenter.getSystemContentList(url, id);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 0;
                tempData.clear();
                String url = UrlConstant.BASEURL + "/article/list/" + page + "/json?cid=" + id;
                mPresenter.getSystemContentList(url, id);
            }
        });
        tabListAdapter.setCollectArticleListener(new TabListAdapter.CollectArticleListener() {
            @Override
            public void onCollectArticleListener(int position, int id) {
                if (tempData.size() > 0) {
                    DatasBean datasBean = tempData.get(position);
                    String url;
                    if (datasBean.isCollect()) {
                        url = UrlConstant.BASEURL + "/lg/uncollect_originId/" + id + "/json";
                        mPresenter.setCollect(url, position, datasBean.isCollect());
                    } else {
                        url = UrlConstant.BASEURL + "/lg/collect/" + id + "/json";
                        mPresenter.setCollect(url, position, datasBean.isCollect());
                    }
                }
            }
        });
    }

    public void setCollectListener(int position, boolean isCollect) {
        if (isCollect) {
            tempData.get(position).setCollect(false);
            UiTools.showToast("取消收藏");
        } else {
            tempData.get(position).setCollect(true);
            UiTools.showToast("收藏成功");
        }
        tabListAdapter.setDatas(tempData, 0, false);
    }
}
