package com.wanou.wanandroid.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanou.framelibrary.base.BaseRecycleViewAdapter;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.DatasBean;

import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/12/3.
 */
public class CollectArticleAdapter extends BaseRecycleViewAdapter {
    private List<DatasBean> mDatas;

    public CollectArticleAdapter(Context context) {
        super(context);
    }

    public void setCollectData(List<DatasBean> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_tab_info;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new CollectArticleViewHolder(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        CollectArticleViewHolder collectArticleViewHolder = (CollectArticleViewHolder) viewHolder;
        DatasBean datasBean = mDatas.get(position);

        collectArticleViewHolder.mTvAuthor.setText(datasBean.getAuthor());
        collectArticleViewHolder.mTvContentTitle.setText(datasBean.getTitle());
        collectArticleViewHolder.mTvPublishTime.setText(datasBean.getNiceDate());
        collectArticleViewHolder.mTvClassification.setText(datasBean.getChapterName());
        collectArticleViewHolder.mTvType.setText(R.string.cancel_collect);
        collectArticleViewHolder.mTvIsCollect.setVisibility(View.GONE);
        collectArticleViewHolder.mTvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelCollectListener != null) {
                    cancelCollectListener.onCancelCollect(datasBean.getId(), datasBean.getOriginId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDatas != null && mDatas.size() > 0) {
            return mDatas.size();
        }
        return 0;
    }

    static class CollectArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvAuthor;
        private TextView mTvPublishTime;
        private TextView mTvContentTitle;
        private TextView mTvClassification;
        private TextView mTvType;
        private ImageView mTvIsCollect;

        CollectArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvAuthor = itemView.findViewById(R.id.tv_author);
            mTvPublishTime = itemView.findViewById(R.id.tv_publish_time);
            mTvContentTitle = itemView.findViewById(R.id.tv_content_title);
            mTvClassification = itemView.findViewById(R.id.tv_classification);
            mTvType = itemView.findViewById(R.id.tv_type);
            mTvIsCollect = itemView.findViewById(R.id.tv_is_collect);
        }
    }

    public interface onCancelCollectListener {
        void onCancelCollect(int id, int originId);
    }

    private onCancelCollectListener cancelCollectListener;

    public void setCancelCollectListener(onCancelCollectListener cancelCollectListener) {
        this.cancelCollectListener = cancelCollectListener;
    }
}
