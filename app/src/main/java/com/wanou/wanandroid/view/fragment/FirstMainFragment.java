package com.wanou.wanandroid.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wanou.framelibrary.base.BaseMvpFragment;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.BannerBean;
import com.wanou.wanandroid.bean.DatasBean;
import com.wanou.wanandroid.bean.TabListBean;
import com.wanou.wanandroid.commontools.BannerLoader;
import com.wanou.wanandroid.constant.UrlConstant;
import com.wanou.wanandroid.presenter.FirstPresenterImpl;
import com.wanou.wanandroid.view.activity.BannerDetailActivity;
import com.wanou.wanandroid.view.adapter.TabListAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public class FirstMainFragment extends BaseMvpFragment<FirstPresenterImpl> implements TabLayout.OnTabSelectedListener {
    private Banner mBanner;
    private List<String> imageUrl = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private Bundle bundle = new Bundle();
    private TabLayout mTlbHomeTab;
    private SmartRefreshLayout mSrlRefresh;
    private RecyclerView mRvHomeList;
    private String[] homeTab = UiTools.getStringArray(R.array.home_tab);
    private List<DatasBean> tempDataLists = new ArrayList<>();
    private TabListAdapter tabListAdapter;
    private int page = 0;

    @Override
    protected int getResId() {
        return R.layout.fragment_first_main;
    }

    @Override
    protected void initView(View view) {
        mBanner = view.findViewById(R.id.banner);
        mTlbHomeTab = view.findViewById(R.id.tlb_home_tab);
        mSrlRefresh = view.findViewById(R.id.srl_refresh);
        mRvHomeList = view.findViewById(R.id.rv_home_list);
    }

    @Override
    protected void initData() {
        initBanner();
        mPresenter.getBannerInfo(UrlConstant.BASEURL + "/banner/json");
        for (String tabContent : homeTab) {
            mTlbHomeTab.addTab(mTlbHomeTab.newTab().setText(tabContent));
        }
        mTlbHomeTab.addOnTabSelectedListener(this);
        mTlbHomeTab.getTabAt(0).select();

        tabListAdapter = new TabListAdapter(getActivity());
        mRvHomeList.setAdapter(tabListAdapter);
    }

    @Override
    protected void isHiddenListener(boolean hidden) {
        if (hidden) {
            mBanner.stopAutoPlay();
        } else {
            mBanner.startAutoPlay();
        }
    }

    @Override
    protected FirstPresenterImpl getPresenter() {
        return new FirstPresenterImpl();
    }

    public void setBannerList(List<BannerBean> bannerBeanList) {
        imageUrl.clear();
        titles.clear();
        if (bannerBeanList != null && bannerBeanList.size() > 0) {
            for (BannerBean bannerBean : bannerBeanList) {
                imageUrl.add(bannerBean.getImagePath());
                titles.add(bannerBean.getTitle());
            }
            mBanner.setImages(imageUrl);
            mBanner.setBannerTitles(titles);
            mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    bundle.clear();
                    BannerBean bannerBean = bannerBeanList.get(position);
                    String bundleUrl = bannerBean.getUrl();
                    bundle.putString("bannerUrl", bundleUrl);
                    BannerDetailActivity.startActivity(getActivity(), bundle, BannerDetailActivity.class);
                }
            });
            mBanner.start();
        }
    }

    public void initBanner() {
        mBanner.setImageLoader(new BannerLoader());
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tabSelect(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        tabSelect(tab);
    }


    public void setTabSuccess(TabListBean tabListBean) {
        int selectedTabPosition = mTlbHomeTab.getSelectedTabPosition();
        List<DatasBean> datas = tabListBean.getDatas();
        tempDataLists.addAll(datas);
        tabListAdapter.setDatas(tempDataLists, selectedTabPosition);
        if (tabListBean.getCurPage() == tabListBean.getPageCount()) {
            mSrlRefresh.setEnableLoadMore(false);
        } else {
            mSrlRefresh.setEnableLoadMore(true);
        }
        mSrlRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page += 1;
                String url;
                switch (mTlbHomeTab.getSelectedTabPosition()) {
                    case 0:
                        url = UrlConstant.BASEURL + "/article/list/" + page + "/json";
                        break;
                    case 1:
                    default:
                        url = UrlConstant.BASEURL + "/article/listproject/" + page + "/json";
                        break;
                }
                mPresenter.getTabListInfo(url);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 0;
                tempDataLists.clear();
                String url;
                switch (mTlbHomeTab.getSelectedTabPosition()) {
                    case 0:
                        url = UrlConstant.BASEURL + "/article/list/" + page + "/json";
                        break;
                    case 1:
                    default:
                        url = UrlConstant.BASEURL + "/article/listproject/" + page + "/json";
                        break;
                }
                mPresenter.getTabListInfo(url);
            }
        });

        tabListAdapter.setCollectArticleListener(new TabListAdapter.CollectArticleListener() {
            @Override
            public void onCollectArticleListener(int position, int id) {
                if (tempDataLists.size()>0) {
                    DatasBean datasBean = tempDataLists.get(position);
                    String url;
                    if (datasBean.isCollect()) {
                        url = UrlConstant.BASEURL + "/lg/uncollect_originId/" + id + "/json";
                        mPresenter.setCollect(url);
                    } else {
                        url = UrlConstant.BASEURL + "/lg/collect/" + id + "/json";
                        mPresenter.setCollect(url);
                    }
                }
            }
        });

    }

    private void tabSelect(TabLayout.Tab tab) {
        String url;
        tempDataLists.clear();
        if (tab.getPosition() == 0) {
            url = UrlConstant.BASEURL + "/article/list/0/json";
        } else {
            url = UrlConstant.BASEURL + "/article/listproject/0/json";
        }
        mPresenter.getTabListInfo(url);
    }

    public void setCollectListener() {

    }
}
