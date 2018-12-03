package com.wanou.wanandroid.view.fragment;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.TextView;

import com.wanou.framelibrary.base.BaseMvpFragment;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.SystemBean;
import com.wanou.wanandroid.constant.UrlConstant;
import com.wanou.wanandroid.presenter.LeftFragmentPresenterImpl;
import com.wanou.wanandroid.weight.FlowLayout;

import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/22.
 */
public class LeftFragment extends BaseMvpFragment<LeftFragmentPresenterImpl> {
    private FlowLayout mFlLayout1;
    private FlowLayout mFlLayout2;

    @Override
    protected LeftFragmentPresenterImpl getPresenter() {
        return new LeftFragmentPresenterImpl();
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_left;
    }

    @Override
    protected void initView(View view) {
        mFlLayout1 = view.findViewById(R.id.fl_layout1);
        mFlLayout2 = view.findViewById(R.id.fl_layout2);
    }

    @Override
    protected void initData() {
        String url = UrlConstant.BASEURL + "/tree/json";
        mPresenter.getSystemInfo(url);
    }

    @Override
    protected void isHiddenListener(boolean hidden) {

    }

    public void setSuccessData(List<SystemBean> systemBeanList) {
        notifyData(systemBeanList);
    }

    void notifyData(List<SystemBean> systemBeanList) {
        // 处理数据显示，使用自定义FlowLayout来处理分类的展示和选择，定义点击事件选择不同的分类，
        // 展示对应的内容
        if (systemBeanList != null && systemBeanList.size() > 0) {
            for (SystemBean systemBean : systemBeanList) {
                TextView textView1 = new TextView(getActivity());
                textView1.setText(systemBean.getName());
                textView1.setBackgroundResource(R.drawable.selector_checked_bg);
                textView1.setClickable(true);
                textView1.setTextColor(UiTools.getColor(R.color.blue_color));
                textView1.setPadding(UiTools.dip2px(10), UiTools.dip2px(5), UiTools.dip2px(10), UiTools.dip2px(5));
                textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFlLayout2.removeAllViews();
                        v.setSelected(true);
                        v.setClickable(false);
                        ((TextView) v).setTextColor(UiTools.getColor(R.color.white_color));
                        String checkText = UiTools.getText((TextView) v);
                        for (int i = 0; i < mFlLayout1.getChildCount(); i++) {
                            TextView childAt = (TextView) mFlLayout1.getChildAt(i);
                            if (UiTools.getText(childAt).equals(checkText)) {
                                SystemBean systemBean1 = systemBeanList.get(i);
                                List<SystemBean.ChildrenBean> children = systemBean1.getChildren();
                                for (SystemBean.ChildrenBean child : children) {
                                    TextView textView2 = new TextView(getActivity());
                                    textView2.setText(child.getName());
                                    textView2.setBackgroundResource(R.drawable.selector_checked_bg);
                                    textView2.setClickable(true);
                                    textView2.setTextColor(UiTools.getColor(R.color.blue_color));
                                    textView2.setPadding(UiTools.dip2px(10), UiTools.dip2px(5), UiTools.dip2px(10), UiTools.dip2px(5));
                                    textView2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            v.setSelected(true);
                                            v.setClickable(false);
                                            ((TextView) v).setTextColor(UiTools.getColor(R.color.white_color));
                                            for (int i1 = 0; i1 < mFlLayout2.getChildCount(); i1++) {
                                                TextView childAt1 = (TextView) mFlLayout2.getChildAt(i1);
                                                if (UiTools.getText(childAt1).equals(UiTools.getText((TextView) v))) {
                                                    int id = children.get(i1).getId();
                                                    // 将文章id传递到父fragment中，在父fragment中访问网络获取体系列表
                                                    FourMainFragment fourMainFragment = (FourMainFragment) getParentFragment();
                                                    if (fourMainFragment != null) {
                                                        fourMainFragment.getSystemList(id);
                                                        if (fourMainFragment.getView() != null) {
                                                            DrawerLayout mDlLayout = fourMainFragment.getView().findViewById(R.id.dl_layout);
                                                            mDlLayout.closeDrawers();
                                                        }
                                                    }
                                                    // 跳过循环，不设置子View的选中状态
                                                    continue;
                                                }
                                                childAt1.setSelected(false);
                                                childAt1.setClickable(true);
                                                childAt1.setTextColor(UiTools.getColor(R.color.blue_color));
                                            }
                                        }
                                    });
                                    mFlLayout2.addView(textView2);
                                }
                                // 跳过循环，不设置子View的选中状态
                                continue;
                            }
                            childAt.setSelected(false);
                            childAt.setClickable(true);
                            childAt.setTextColor(UiTools.getColor(R.color.blue_color));
                        }
                        // 默认点击二级分类第一个
                        if (mFlLayout2.getChildCount() > 0) {
                            mFlLayout2.getChildAt(0).performClick();
                        }
                    }
                });
                mFlLayout1.addView(textView1);
            }
            // 默认点击一级分类第一个
            if (mFlLayout1.getChildCount() > 0) {
                mFlLayout1.getChildAt(0).performClick();
            }
        }
    }


}
