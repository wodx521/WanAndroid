package com.wanou.wanandroid.fragmentfactory;

import com.wanou.framelibrary.base.BaseFragment;
import com.wanou.wanandroid.view.fragment.FirstMainFragment;
import com.wanou.wanandroid.view.fragment.FiveMainFragment;
import com.wanou.wanandroid.view.fragment.FourMainFragment;
import com.wanou.wanandroid.view.fragment.SecondMainFragment;
import com.wanou.wanandroid.view.fragment.ThirdMainFragment;

import java.util.HashMap;

/**
 * @author wodx521
 * @date on 2018/8/17
 */
public class MainFragmentFactory {
    public static HashMap<Integer, BaseFragment> fragmentMainMap = new HashMap<>();

    public static BaseFragment getFragment(int position) {
        BaseFragment baseFragment = null;
        //尝试从内存中读取需要的对象
        baseFragment = fragmentMainMap.get(position);
        if (baseFragment != null) {
            return baseFragment;
        } else {
            switch (position) {
                case 0:
                    baseFragment = new FirstMainFragment();
                    break;
                case 1:
                    baseFragment = new SecondMainFragment();
                    break;
                case 2:
                    baseFragment = new ThirdMainFragment();
                    break;
                case 3:
                    baseFragment = new FourMainFragment();
                    break;
                case 4:
                default:
                    baseFragment = new FiveMainFragment();
                    break;
            }
            fragmentMainMap.put(position, baseFragment);
            return baseFragment;
        }
    }
}
