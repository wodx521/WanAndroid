package com.wanou.wanandroid.view.activity;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.wanou.wanandroid.bean.HotKeyBean;
import com.wanou.wanandroid.constant.UrlConstant;
import com.wanou.wanandroid.presenter.SearchPresenterImpl;
import com.wanou.wanandroid.view.adapter.TabListAdapter;
import com.wanou.wanandroid.weight.AutoChangeRowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/12/8.
 */
public class SearchActivity extends BaseMvpActivity<SearchPresenterImpl> implements View.OnClickListener {
    private EditText mEtSearchContent;
    private ImageView mIvClear;
    private RecyclerView mRvSearchResult;
    private SmartRefreshLayout mSrlRefresh;
    private HttpParams httpParams = new HttpParams();
    private TabListAdapter tabListAdapter;
    private List<DatasBean> tempDataLists = new ArrayList<>();
    private Bundle bundle = new Bundle();
    private AutoChangeRowLayout mAcrl;
    private LinearLayout mLlHot;
    private int page = 0;

    @Override
    protected SearchPresenterImpl getPresenter() {
        return new SearchPresenterImpl();
    }

    @Override
    protected int getResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mEtSearchContent = findViewById(R.id.et_search_content);
        mIvClear = findViewById(R.id.iv_clear);
        mRvSearchResult = findViewById(R.id.rv_search_result);
        mSrlRefresh = findViewById(R.id.srl_refresh);
        mAcrl = findViewById(R.id.acrl);
        mLlHot = findViewById(R.id.ll_hot);

        viewInvisible(mIvClear);
        mIvClear.setClickable(false);
        mIvClear.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String url = UrlConstant.BASEURL + "//hotkey/json";
        mPresenter.getHotContent(url);
        tabListAdapter = new TabListAdapter(SearchActivity.this);
        mRvSearchResult.setAdapter(tabListAdapter);
        mEtSearchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tempDataLists.clear();
                String content = s.toString();
                String url = UrlConstant.BASEURL + "/article/query/0/json";
                httpParams.put("k", content);
                mPresenter.getSearchList(url, httpParams, content);
                if (UiTools.noEmpty(content)) {
                    viewVisible(mIvClear);
                    mIvClear.setClickable(true);
                } else {
                    viewInvisible(mIvClear);
                    mIvClear.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void setSearchList(ArticleListBean articleListBean, String content) {
        int curPage = articleListBean.getCurPage();
        int pageCount = articleListBean.getPageCount();
        List<DatasBean> datas = articleListBean.getDatas();
        tempDataLists.addAll(datas);
        tabListAdapter.setDatas(tempDataLists, 0, false);
        if (tempDataLists != null && tempDataLists.size() > 0) {
            viewGone(mLlHot);
        } else {
            viewVisible(mLlHot);
        }
        mSrlRefresh.setEnableLoadMore(curPage < pageCount);

        mSrlRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                String url = UrlConstant.BASEURL + "/article/query/" + page + "/json";
                httpParams.put("k", content);
                mPresenter.getSearchList(url, httpParams, content);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                String url = UrlConstant.BASEURL + "/article/query/" + page + "/json";
                httpParams.put("k", content);
                mPresenter.getSearchList(url, httpParams, content);
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
                            ActivityOptionsCompat.makeSceneTransitionAnimation(SearchActivity.this, view, getString(R.string.WebView));
                    BannerDetailActivity.compatStartActivity(SearchActivity.this, bundle, activityOptionsCompat.toBundle(), BannerDetailActivity.class);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_clear:
                mEtSearchContent.setText("");
                break;
            default:
        }
    }

    public void setCollectListener(int position, boolean isCollect) {
        if (isCollect) {
            tempDataLists.get(position).setCollect(false);
            UiTools.showToast("取消收藏");
        } else {
            tempDataLists.get(position).setCollect(true);
            UiTools.showToast("收藏成功");
        }
        tabListAdapter.setDatas(tempDataLists, 0, false);
    }

    public void setHotKey(List<HotKeyBean> hotKeyBeanList) {
        for (HotKeyBean hotKeyBean : hotKeyBeanList) {
            String name = hotKeyBean.getName();
            TextView textView = new TextView(SearchActivity.this);
            textView.setText(name);
            textView.setTextColor(UiTools.getColor(R.color.white_color));
            textView.setBackgroundResource(R.drawable.shape_blue_bg_round20);
            textView.setPadding(15, 5, 15, 5);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEtSearchContent.setText(UiTools.getText((TextView) v));
                }
            });
            mAcrl.addView(textView);
        }
    }
}
