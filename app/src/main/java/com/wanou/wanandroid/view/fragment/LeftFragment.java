package com.wanou.wanandroid.view.fragment;

import android.view.View;
import android.widget.TextView;

import com.wanou.framelibrary.base.BaseFragment;
import com.wanou.framelibrary.utils.UiTools;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.bean.SystemBean;
import com.wanou.wanandroid.weight.FlowLayout;

import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/22.
 */
public class LeftFragment extends BaseFragment {
    private List<SystemBean> systemBeanList;
    //    private ChipGroup mCgGroup1;
//    private ChipGroup mCgGroup2;
    private FlowLayout mFlLayout1;
    private FlowLayout mFlLayout2;

    @Override
    protected int getResId() {
        return R.layout.fragment_left;
    }

    public void setSystemBeanList(List<SystemBean> systemBeanList) {
        this.systemBeanList = systemBeanList;
    }

    @Override
    protected void initView(View view) {
//        mCgGroup1 = view.findViewById(R.id.cg_group1);
//        mCgGroup2 = view.findViewById(R.id.cg_group2);
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

            for (int i = 0; i < mFlLayout1.getChildCount(); i++) {
                View childAt = mFlLayout1.getChildAt(i);
                if (childAt.isSelected()) {
                    SystemBean systemBean = systemBeanList.get(i);
                    List<SystemBean.ChildrenBean> children = systemBean.getChildren();

                }
            }

//            for (SystemBean systemBean : systemBeanList) {
//                Chip chip = new Chip(getActivity());
//                chip.setText(systemBean.getName());
//                chip.setTextColor(UiTools.getColor(R.color.blue_color));
//                ChipDrawable chipDrawable = (ChipDrawable) chip.getChipDrawable();
//                chipDrawable.setCheckable(false);
//                chipDrawable.setCheckedIconVisible(false);
//                chip.setChipDrawable(chipDrawable);
//                mCgGroup1.addView(chip);
//            }
//
//            mCgGroup1.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(ChipGroup chipGroup, int i) {
//                    if (i != -1) {
//                        for (int i1 = 0; i1 < mCgGroup1.getChildCount(); i1++) {
//                            mCgGroup1.getChildAt(i1).setSelected(false);
//                        }
//                        Chip chip1 = (Chip) chipGroup.getChildAt(i - 1);
//                        chip1.setSelected(true);
//
//                        UiTools.showToast("选中改变了" + chipGroup.getCheckedChipId() + "~~~~~" + i);
//                        mCgGroup2.removeAllViews();
//                        SystemBean systemBean = systemBeanList.get(i - 1);
//                        List<SystemBean.ChildrenBean> children = systemBean.getChildren();
//                        for (SystemBean.ChildrenBean child : children) {
//                            Chip chip = new Chip(getActivity());
//                            chip.setText(child.getName());
//                            chip.setTextColor(UiTools.getColor(R.color.blue_color));
//                            ChipDrawable chipDrawable = (ChipDrawable) chip.getChipDrawable();
//                            chipDrawable.setCheckable(true);
//                            chipDrawable.setCheckedIconVisible(false);
//                            chipDrawable.setCloseIconVisible(false);
//                            chip.setChipDrawable(chipDrawable);
//                            mCgGroup2.addView(chip);
//                        }
//
//                        mCgGroup2.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
//                            @Override
//                            public void onCheckedChanged(ChipGroup chipGroup1, int i1) {
////                                if (i != -1) {
////                                    UiTools.showToast(systemBean.getName() + children.get(i - systemBeanList.size() - 1).getName());
////                                }
////                                i-systemBeanList.size()
//                                LogUtils.e("chipgroup" + chipGroup1.getCheckedChipId() + "~~~~i1" + i1);
//                            }
//                        });
//                    }
//                }
//            });
//
//            mCgGroup1.check(mCgGroup1.getChildAt(0).getId());
        }
    }
}
