package com.wanou.wanandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wanou.framelibrary.base.BaseMvpActivity;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.CollectArticleBean;
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
    private List<CollectArticleBean.DatasBean> tempDatas = new ArrayList<>();
    private int page = 0;
    private HttpParams httpParams = new HttpParams();

    public static void startActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, CollectArticleActivity.class);
        if (bundle != null && bundle.size() > 0) {
            intent.putExtra("bundle", bundle);
        }
        context.startActivity(intent);
    }

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

    public void setCollectArticle(CollectArticleBean collectArticleBean) {
        int curPage = collectArticleBean.getCurPage();
        int pageCount = collectArticleBean.getPageCount();
        mSrlRefresh.setEnableLoadMore(curPage < pageCount);
        List<CollectArticleBean.DatasBean> datas = collectArticleBean.getDatas();
        tempDatas.addAll(datas);
        collectArticleAdapter.setCollectData(tempDatas);

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
                tempDatas.clear();
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
    }

    public void setCancelSuccess(int id) {
        UiTools.showToast("取消收藏");
        Iterator<CollectArticleBean.DatasBean> iterator = tempDatas.iterator();
        while (iterator.hasNext()) {
            CollectArticleBean.DatasBean next = iterator.next();
            if (next.getId() == id) {
                iterator.remove();
            }
        }
        collectArticleAdapter.setCollectData(tempDatas);
    }
}
