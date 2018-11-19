package com.wanou.wanandroid.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.wanou.framelibrary.base.BaseMvpActivity;
import com.wanou.wanandroid.R;
import com.wanou.wanandroid.fragmentfactory.MainFragmentFactory;
import com.wanou.wanandroid.presenter.MainPresenterImpl;

import java.util.List;

/**
 * Author by wodx521
 * Date on 2018/11/16.
 */
public class MainActivity extends BaseMvpActivity<MainPresenterImpl> {
    private Toolbar mToolbar;
    private FrameLayout mFlContainer;
    private BottomNavigationView mNavigation;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected MainPresenterImpl getPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected int getResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mFlContainer = findViewById(R.id.fl_container);
        mNavigation = findViewById(R.id.navigation);

        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                String title = menuItem.getTitle().toString();
                switch (itemId) {
                    case R.id.navigation_home:
                        addFragment(0,title);
                        break;
                    case R.id.navigation_project:
                        addFragment(1,title);
                        break;
                    case R.id.navigation_no_public:
                        addFragment(2,title);
                        break;
                    case R.id.navigation_system:
                        addFragment(3,title);
                        break;
                    case R.id.navigation_me:
                    default:
                        addFragment(4,title);
                        break;
                }
                return true;
            }
        });
        mNavigation.setSelectedItemId(R.id.navigation_home);
    }

    private void addFragment(int position, String title) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(title);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            fragmentTransaction.hide(fragment);
        }
        if (fragmentByTag != null) {
            fragmentTransaction.show(fragmentByTag);
        } else {
            fragmentTransaction.add(R.id.fl_container, MainFragmentFactory.getFragment(position), title);
            fragmentTransaction.show(MainFragmentFactory.getFragment(position));
        }
        fragmentTransaction.commit();
    }


    @Override
    protected void initData() {

    }
}
