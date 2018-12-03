package com.wanou.wanandroid.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wanou.framelibrary.base.BaseMvpFragment;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.ArticleListBean;
import com.wanou.wanandroid.bean.ChapterListBean;
import com.wanou.wanandroid.bean.DatasBean;
import com.wanou.wanandroid.constant.UrlConstant;
import com.wanou.wanandroid.presenter.ThirdPresenterImpl;
import com.wanou.wanandroid.view.adapter.TabListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public class ThirdMainFragment extends BaseMvpFragment<ThirdPresenterImpl> implements TabLayout.OnTabSelectedListener {
    private TabLayout mTlThird;
    private SmartRefreshLayout mSrlRefresh;
    private RecyclerView mRvThirdList;
    private List<ChapterListBean> tempChapterList = new ArrayList<>();
    private TabListAdapter wxArticleAdapter;
    private int page = 0;
    private List<DatasBean> tempDatas = new ArrayList<>();

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
        String chapterListUrl = "http://wanandroid.com/wxarticle/chapters/json";
        mPresenter.getWxArticle(chapterListUrl);

        wxArticleAdapter = new TabListAdapter(getActivity());
        mRvThirdList.setAdapter(wxArticleAdapter);
    }


    @Override
    protected void isHiddenListener(boolean hidden) {
    }

    @Override
    protected ThirdPresenterImpl getPresenter() {
        return new ThirdPresenterImpl();
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
        page = 0;
        tempDatas.clear();
        ChapterListBean chapterListBean = tempChapterList.get(position);
        int id = chapterListBean.getId();
        String url = UrlConstant.BASEURL + "/wxarticle/list/" + id + "/" + page + "/json";
        mPresenter.getArticleList(url, id);
    }

    public void setArticleList(ArticleListBean articleListBean, int id) {
        int curPage = articleListBean.getCurPage();
        int pageCount = articleListBean.getPageCount();
        List<DatasBean> datas = articleListBean.getDatas();
        tempDatas.addAll(datas);
        wxArticleAdapter.setDatas(tempDatas, 0);
        mSrlRefresh.setEnableLoadMore(curPage < pageCount);

        mSrlRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page += 1;
                String url = UrlConstant.BASEURL + "/wxarticle/list/" + id + "/" + page + "/json";
                mPresenter.getArticleList(url, id);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 0;
                tempDatas.clear();
                String url = UrlConstant.BASEURL + "/wxarticle/list/" + id + "/" + page + "/json";
                mPresenter.getArticleList(url, id);
            }
        });
    }
}
