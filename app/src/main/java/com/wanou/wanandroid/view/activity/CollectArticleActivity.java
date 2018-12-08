package com.wanou.wanandroid.view.activity;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wanou.framelibrary.base.BaseMvpActivity;
import com.wanou.framelibrary.base.BaseRecycleViewAdapter;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.ArticleListBean;
import com.wanou.wanandroid.bean.DatasBean;
import com.wanou.wanandroid.constant.UrlConstant;
import com.wanou.wanandroid.presenter.CollectArticlePresenterImpl;
import com.wanou.wanandroid.view.adapter.CollectArticleAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/12/3.
 */
public class CollectArticleActivity extends BaseMvpActivity<CollectArticlePresenterImpl> {
    private RecyclerView mRvCollectArticle;
    private SmartRefreshLayout mSrlRefresh;
    private CollectArticleAdapter collectArticleAdapter;
    private List<DatasBean> tempDataLists = new ArrayList<>();
    private int page = 0;
    private HttpParams httpParams = new HttpParams();
    private Bundle bundle = new Bundle();

    @Override
    protected int getResId() {
        return R.layout.activity_collect_article;
    }

    @Override
    protected void initView() {
        mRvCollectArticle = findViewById(R.id.rv_collect_article);
        mSrlRefresh = findViewById(R.id.srl_refresh);
    }

    @Override
    protected CollectArticlePresenterImpl getPresenter() {
        return new CollectArticlePresenterImpl();
    }

    @Override
    protected void initData() {
        collectArticleAdapter = new CollectArticleAdapter(CollectArticleActivity.this);
        mRvCollectArticle.setAdapter(collectArticleAdapter);
        page = 0;
        String url = UrlConstant.BASEURL + "/lg/collect/list/" + page + "/json";
        mPresenter.getCollectArticle(url);
    }

    public void setCollectArticle(ArticleListBean articleListBean) {
        int curPage = articleListBean.getCurPage();
        int pageCount = articleListBean.getPageCount();
        mSrlRefresh.setEnableLoadMore(curPage < pageCount);
        List<DatasBean> datas = articleListBean.getDatas();
        tempDataLists.addAll(datas);
        collectArticleAdapter.setCollectData(tempDataLists);

        mSrlRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page += 1;
                String url = UrlConstant.BASEURL + "/lg/collect/list/" + page + "/json";
                mPresenter.getCollectArticle(url);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 0;
                tempDataLists.clear();
                String url = UrlConstant.BASEURL + "/lg/collect/list/" + page + "/json";
                mPresenter.getCollectArticle(url);
            }
        });

        collectArticleAdapter.setCancelCollectListener(new CollectArticleAdapter.onCancelCollectListener() {
            @Override
            public void onCancelCollect(int id, int originId) {
                String url = UrlConstant.BASEURL + "/lg/uncollect/" + id + "/json";
                httpParams.put("originId", originId);
                mPresenter.setCancelCollect(url, httpParams, id);
            }
        });

        collectArticleAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if (tempDataLists.size() > 0) {
                    bundle.clear();
                    DatasBean datasBean = tempDataLists.get(position);
                    String link = datasBean.getLink();
                    bundle.putString("bannerUrl", link);
//                    BannerDetailActivity.startActivity(CollectArticleActivity.this, bundle, BannerDetailActivity.class);

                    ActivityOptionsCompat activityOptionsCompat =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(CollectArticleActivity.this, view, getString(R.string.WebView));
                    BannerDetailActivity.compatStartActivity(CollectArticleActivity.this, bundle, activityOptionsCompat.toBundle(), BannerDetailActivity.class);
                }
            }
        });
    }

    public void setCancelSuccess(int id) {
        UiTools.showToast("取消收藏");
        Iterator<DatasBean> iterator = tempDataLists.iterator();
        while (iterator.hasNext()) {
            DatasBean next = iterator.next();
            if (next.getId() == id) {
                iterator.remove();
            }
        }
        collectArticleAdapter.setCollectData(tempDataLists);
    }
}
