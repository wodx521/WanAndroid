package com.wanou.wanandroid.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wanou.framelibrary.base.BaseMvpFragment;
import com.wanou.framelibrary.manager.ActivityManage;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.constant.UrlConstant;
import com.wanou.wanandroid.presenter.FivePresenterImpl;
import com.wanou.wanandroid.view.activity.CollectArticleActivity;
import com.wanou.wanandroid.view.activity.LoginActivity;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public class FiveMainFragment extends BaseMvpFragment<FivePresenterImpl> implements View.OnClickListener {
    private Bundle bundle = new Bundle();

    @Override
    protected int getResId() {
        return R.layout.fragment_five_main;
    }

    @Override
    protected void initView(View view) {
        TextView mTvCollectArticle = view.findViewById(R.id.tv_collect_article);
        TextView mTvCollectWeb = view.findViewById(R.id.tv_collect_web);
        Button mBtExit = view.findViewById(R.id.bt_exit);

        mTvCollectArticle.setOnClickListener(this);
        mTvCollectWeb.setOnClickListener(this);
        mBtExit.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void isHiddenListener(boolean hidden) {
    }

    @Override
    protected FivePresenterImpl getPresenter() {
        return new FivePresenterImpl();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_collect_article:
                bundle.clear();
                CollectArticleActivity.startActivity(getActivity(), bundle);
                break;
            case R.id.tv_collect_web:

                break;
            case R.id.bt_exit:
                String url = UrlConstant.BASEURL + "/user/logout/json";
                mPresenter.exitLogout(url);
                break;
            default:
        }
    }

    public void setLogoutSuccess() {
        bundle.clear();
        ActivityManage.getInstance().finishAll();
        UiTools.showToast("退出成功");
        LoginActivity.startActivity(getActivity(), bundle, LoginActivity.class);
    }
}
