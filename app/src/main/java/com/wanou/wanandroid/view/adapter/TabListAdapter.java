package com.wanou.wanandroid.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.wanou.framelibrary.base.BaseRecycleViewAdapter;
import com.wanou.wanandroid.R;

/**
 * Author by wodx521
 * Date on 2018/11/20.
 */
public class TabListAdapter extends BaseRecycleViewAdapter {

    public TabListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_tab_info;
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
