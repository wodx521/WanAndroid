package com.wanou.wanandroid.view.fragment;

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
    private List<SystemBean> systemBeanList;
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

    public void setSystemBeanList(List<SystemBean> systemBeanList) {
        this.systemBeanList = systemBeanList;
    }

    @Override
    protected void initView(View view) {
        mFlLayout1 = view.findViewById(R.id.fl_layout1);
        mFlLayout2 = view.findViewById(R.id.fl_layout2);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void isHiddenListener(boolean hidden) {

    }

    void notifyData() {
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
                        for (int i = 0; i < mFlLayout1.getChildCount(); i++) {
                            TextView childAt = (TextView) mFlLayout1.getChildAt(i);
                            if (childAt.getText().equals(((TextView) v).getText().toString())) {
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
                                                if (childAt1.getText().equals(((TextView) v).getText().toString())) {
                                                    // TODO: 2018/11/22 这里添加具体列表的代码
                                                    UiTools.showToast("charat" + childAt.getText().toString() + "~~~~charat1" + childAt1.getText().toString());
//                                                    http://www.wanandroid.com/article/list/0/json?cid=60
                                                    int id = children.get(i1).getId();
                                                    String url = UrlConstant.BASEURL + "/article/list/" + 0 + "/json?cid=" + id;
                                                    mPresenter.getSystemContentList(url);
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
                                continue;
                            }
                            childAt.setSelected(false);
                            childAt.setClickable(true);
                            childAt.setTextColor(UiTools.getColor(R.color.blue_color));
                        }
                    }
                });
                mFlLayout1.addView(textView1);
            }
        }
    }


}
