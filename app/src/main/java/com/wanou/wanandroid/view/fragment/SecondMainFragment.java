package com.wanou.wanandroid.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wanou.framelibrary.base.BaseMvpFragment;
import com.wanou.framelibrary.base.BaseRecycleViewAdapter;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.ArticleListBean;
import com.wanou.wanandroid.bean.ChapterListBean;
import com.wanou.wanandroid.bean.DatasBean;
import com.wanou.wanandroid.constant.UrlConstant;
import com.wanou.wanandroid.presenter.SecondPresenterImpl;
import com.wanou.wanandroid.view.activity.BannerDetailActivity;
import com.wanou.wanandroid.view.adapter.TabListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public class SecondMainFragment extends BaseMvpFragment<SecondPresenterImpl> implements TabLayout.OnTabSelectedListener {
    private TabLayout mTlThird;
    private SmartRefreshLayout mSrlRefresh;
    private RecyclerView mRvThirdList;
    private List<ChapterListBean> tempChapterList = new ArrayList<>();
    private List<DatasBean> tempDataLists = new ArrayList<>();
    private int page = 1;
    private TabListAdapter tabListAdapter;
    private Bundle bundle = new Bundle();

    @Override
    protected int getResId() {
        return R.layout.fragment_third_main;
    }

    @Override
    protected void initView(View view) {
        mTlThird = view.findViewById(R.id.tl_third);
        mSrlRefresh = view.findViewById(R.id.srl_refresh);
        mRvThirdList = view.findViewById(R.id.rv_third_list);

    }

    @Override
    protected void initData() {
        tabListAdapter = new TabListAdapter(getActivity());
        mRvThirdList.setAdapter(tabListAdapter);
        String url = "http://www.wanandroid.com/project/tree/json";
        mPresenter.getTabList(url);
    }

    @Override
    protected void isHiddenListener(boolean hidden) {
    }

    @Override
    protected SecondPresenterImpl getPresenter() {
        return new SecondPresenterImpl();
    }

    public void setTabList(List<ChapterListBean> chapterListBeanList) {
        tempChapterList.clear();
        tempChapterList.addAll(chapterListBeanList);
        if (tempChapterList.size() > 0) {
            for (ChapterListBean chapterListBean : tempChapterList) {
                mTlThird.addTab(mTlThird.newTab().setText(chapterListBean.getName()));
            }
            mTlThird.addOnTabSelectedListener(this);
            TabLayout.Tab tabAt = mTlThird.getTabAt(0);
            if (tabAt != null) {
                tabAt.select();
            }
        }
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

    private void tabSelect(TabLayout.Tab tab) {
        mRvThirdList.scrollToPosition(0);
        int position = tab.getPosition();
        page = 1;
        tempDataLists.clear();
        ChapterListBean chapterListBean = tempChapterList.get(position);
        int id = chapterListBean.getId();
        String url = UrlConstant.BASEURL + "/wxarticle/list/" + id + "/" + page + "/json";
        mPresenter.getProjectList(url, id);
    }

    public void setArticleList(ArticleListBean articleListBean, int id) {
        int curPage = articleListBean.getCurPage();
        int pageCount = articleListBean.getPageCount();
        List<DatasBean> datas = articleListBean.getDatas();
        tempDataLists.addAll(datas);
        tabListAdapter.setDatas(tempDataLists, 0, false);
        mSrlRefresh.setEnableLoadMore(curPage < pageCount);

        mSrlRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page += 1;
                String url = UrlConstant.BASEURL + "/wxarticle/list/" + id + "/" + page + "/json";
                mPresenter.getProjectList(url, id);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 1;
                tempDataLists.clear();
                String url = UrlConstant.BASEURL + "/wxarticle/list/" + id + "/" + page + "/json";
                mPresenter.getProjectList(url, id);
            }
        });

        tabListAdapter.setCollectArticleListener(new TabListAdapter.CollectArticleListener() {
            @Override
            public void onCollectArticleListener(int position, int id) {
                if (tempDataLists.size() > 0) {
                    DatasBean datasBean = tempDataLists.get(position);
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

        tabListAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if (tempDataLists.size() > 0) {
                    bundle.clear();
                    DatasBean datasBean = tempDataLists.get(position);
                    String link = datasBean.getLink();
                    bundle.putString("bannerUrl", link);

                    ActivityOptionsCompat activityOptionsCompat =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, getString(R.string.WebView));
                    BannerDetailActivity.compatStartActivity(getActivity(), bundle, activityOptionsCompat.toBundle(), BannerDetailActivity.class);
                }
            }
        });
    }

    public void setCollectListener(int position, boolean isCollect) {
        if (isCollect) {
            tempDataLists.get(position).setCollect(false);
            UiTools.showToast("取消收藏");
        } else {
            tempDataLists.get(position).setCollect(true);
            UiTools.showToast("收藏成功");
        }
        tabListAdapter.setDatas(tempDataLists, mTlThird.getSelectedTabPosition(), false);
    }
}
