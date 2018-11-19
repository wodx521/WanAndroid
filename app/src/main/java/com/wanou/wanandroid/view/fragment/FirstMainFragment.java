package com.wanou.wanandroid.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.wanou.framelibrary.base.BaseMvpFragment;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.BannerBean;
import com.wanou.wanandroid.commontools.BannerLoader;
import com.wanou.wanandroid.constant.UrlConstant;
import com.wanou.wanandroid.presenter.FirstPresenterImpl;
import com.wanou.wanandroid.view.activity.BannerDetailActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/10.
 */
public class FirstMainFragment extends BaseMvpFragment<FirstPresenterImpl> {
    private Banner mBanner;
    private List<String> imageUrl = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private Bundle bundle = new Bundle();

    @Override
    protected int getResId() {
        return R.layout.fragment_first_main;
    }

    @Override
    protected void initView(View view) {
        mBanner = view.findViewById(R.id.banner);

    }

    @Override
    protected void initData() {
        initBanner();
        mPresenter.getBannerInfo(UrlConstant.BASEURL + "/banner/json", null);
    }

    @Override
    protected void isHiddenListener(boolean hidden) {
        if (hidden) {
            mBanner.stopAutoPlay();
        } else {
            mBanner.startAutoPlay();
        }
    }

    @Override
    protected FirstPresenterImpl getPresenter() {
        return new FirstPresenterImpl();
    }

    public void setBannerList(List<BannerBean> bannerBeanList) {
        imageUrl.clear();
        if (bannerBeanList != null && bannerBeanList.size() > 0) {
            for (BannerBean bannerBean : bannerBeanList) {
                imageUrl.add(bannerBean.getImagePath());
                titles.add(bannerBean.getTitle());
            }
            mBanner.setImages(imageUrl);
            mBanner.setBannerTitles(titles);
            mBanner.start();

            mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    bundle.clear();
                    BannerBean bannerBean = bannerBeanList.get(position);
                    String bundleUrl = bannerBean.getUrl();
                    bundle.putString("bannerUrl", bundleUrl);
                    BannerDetailActivity.startActivity(getActivity(), bundle, BannerDetailActivity.class);
                }
            });
        }
    }

    public void initBanner() {
        mBanner.setImageLoader(new BannerLoader());
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }
}
