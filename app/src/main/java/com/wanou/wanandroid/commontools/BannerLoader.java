package com.wanou.wanandroid.commontools;

import android.content.Context;
import android.widget.ImageView;

import com.wanou.framelibrary.glidetools.GlideApp;
import com.youth.banner.loader.ImageLoader;

/**
 * Author by wodx521
 * Date on 2018/11/19.
 */
public class BannerLoader extends ImageLoader {
    @Override
    public ImageView createImageView(Context context) {
        return super.createImageView(context);
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        GlideApp.with(context)
                .load((String) path)
                .into(imageView);
    }
}
