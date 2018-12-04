package com.wanou.wanandroid.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wanou.framelibrary.base.BaseRecycleViewAdapter;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.DatasBean;

import java.io.File;
import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/20.
 */
public class TabListAdapter extends BaseRecycleViewAdapter {
    private List<DatasBean> datas;
    private int selectedTabPosition;
    private boolean isHome;

    public TabListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_tab_info;
    }

    public void setDatas(List<DatasBean> datas, int selectedTabPosition, boolean isHome) {
        this.datas = datas;
        this.selectedTabPosition = selectedTabPosition;
        this.isHome = isHome;
        notifyDataSetChanged();
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        TabListViewHolder tabListViewHolder = (TabListViewHolder) viewHolder;
        DatasBean datasBean = datas.get(position);
        int id = datasBean.getId();
        List<DatasBean.TagsBean> tags = datasBean.getTags();

        tabListViewHolder.mTvContentTitle.setText(datasBean.getTitle());
        tabListViewHolder.mTvAuthor.setText(datasBean.getAuthor());
        tabListViewHolder.mTvClassification.setText(datasBean.getSuperChapterName() + File.separator +
                datasBean.getChapterName());
        tabListViewHolder.mTvPublishTime.setText(datasBean.getNiceDate());
        tabListViewHolder.mTvIsCollect.setVisibility(View.VISIBLE);
        tabListViewHolder.mTvIsCollect.setSelected(datasBean.isCollect());

        if (selectedTabPosition == 0) {
            if (isHome && tags.size() > 0) {
                tabListViewHolder.mTvType.setVisibility(View.VISIBLE);
                tabListViewHolder.mTvType.setText(tags.get(0).getName());
                tabListViewHolder.mTvType.setBackgroundResource(R.drawable.shape_frame_green);
            } else {
                tabListViewHolder.mTvType.setVisibility(View.GONE);
            }
        } else {
            tabListViewHolder.mTvType.setVisibility(View.GONE);
        }

        tabListViewHolder.mTvIsCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (collectArticleListener != null) {
                    collectArticleListener.onCollectArticleListener(position, id);
                }
            }
        });

    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new TabListViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (datas != null && datas.size() > 0) {
            return datas.size();
        }
        return 0;
    }

    static class TabListViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvContentTitle, mTvType, mTvAuthor, mTvClassification,
                mTvPublishTime;
        private TextView mTvIsCollect;

        TabListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvContentTitle = itemView.findViewById(R.id.tv_content_title);
            mTvType = itemView.findViewById(R.id.tv_type);
            mTvAuthor = itemView.findViewById(R.id.tv_author);
            mTvClassification = itemView.findViewById(R.id.tv_classification);
            mTvPublishTime = itemView.findViewById(R.id.tv_publish_time);
            mTvIsCollect = itemView.findViewById(R.id.tv_is_collect);
        }
    }

    public interface CollectArticleListener {
        void onCollectArticleListener(int position, int id);
    }

    private CollectArticleListener collectArticleListener;

    public void setCollectArticleListener(CollectArticleListener collectArticleListener) {
        this.collectArticleListener = collectArticleListener;
    }
}
