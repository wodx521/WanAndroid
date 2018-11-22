package com.wanou.wanandroid.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wanou.framelibrary.base.BaseRecycleViewAdapter;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.SystemBean;

import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/22.
 */
public class ClassificationAdapter1 extends BaseRecycleViewAdapter {
    private List<SystemBean> mSystemBeanList;

    public ClassificationAdapter1(Context context) {
        super(context);
    }

    public void setData(List<SystemBean> systemBeanList) {
        this.mSystemBeanList = systemBeanList;
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_text;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new ClassificationViewHolder1(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        ClassificationViewHolder1 classificationViewHolder = (ClassificationViewHolder1) viewHolder;
        SystemBean systemBean = mSystemBeanList.get(position);
        classificationViewHolder.mTvClassificationContent.setText(systemBean.getName());
    }

    @Override
    public int getItemCount() {
        if (mSystemBeanList != null && mSystemBeanList.size() > 0) {
            return mSystemBeanList.size();
        }
        return 0;
    }

    static class ClassificationViewHolder1 extends RecyclerView.ViewHolder {
        private TextView mTvClassificationContent;

        ClassificationViewHolder1(@NonNull View itemView) {
            super(itemView);
            mTvClassificationContent = itemView.findViewById(R.id.tv_classification_content);
        }
    }
}
