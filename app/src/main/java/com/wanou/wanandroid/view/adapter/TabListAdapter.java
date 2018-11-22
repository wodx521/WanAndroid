package com.wanou.wanandroid.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wanou.framelibrary.base.BaseRecycleViewAdapter;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.TabListBean;

import java.io.File;
import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/20.
 */
public class TabListAdapter extends BaseRecycleViewAdapter {
    private List<TabListBean.DatasBean> datas;
    private int selectedTabPosition;

    public TabListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_tab_info;
    }

    public void setDatas(List<TabListBean.DatasBean> datas, int selectedTabPosition) {
        this.datas = datas;
        this.selectedTabPosition = selectedTabPosition;
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        TabListViewHolder tabListViewHolder = (TabListViewHolder) viewHolder;
        TabListBean.DatasBean datasBean = datas.get(position);
        int chapterId = datasBean.getChapterId();
        List<TabListBean.DatasBean.TagsBean> tags = datasBean.getTags();

        tabListViewHolder.mTvContentTitle.setText(datasBean.getTitle());
        tabListViewHolder.mTvAuthor.setText(datasBean.getAuthor());
        tabListViewHolder.mTvClassification.setText(datasBean.getSuperChapterName()
                + File.separator + datasBean.getChapterName());
        tabListViewHolder.mTvPublishTime.setText(datasBean.getNiceDate());

        if (selectedTabPosition == 0) {
            if (tags.size() > 0) {
                tabListViewHolder.mTvType.setVisibility(View.VISIBLE);
                tabListViewHolder.mTvType.setText(tags.get(0).getName());
                tabListViewHolder.mTvType.setBackgroundResource(R.drawable.shape_frame_green);
            } else {
                tabListViewHolder.mTvType.setVisibility(View.GONE);
            }
        } else {
            tabListViewHolder.mTvType.setVisibility(View.GONE);
        }
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

        public TabListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvContentTitle = itemView.findViewById(R.id.tv_content_title);
            mTvType = itemView.findViewById(R.id.tv_type);
            mTvAuthor = itemView.findViewById(R.id.tv_author);
            mTvClassification = itemView.findViewById(R.id.tv_classification);
            mTvPublishTime = itemView.findViewById(R.id.tv_publish_time);
        }
    }


}
