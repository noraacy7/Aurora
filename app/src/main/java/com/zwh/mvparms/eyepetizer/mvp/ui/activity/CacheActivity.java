package com.zwh.mvparms.eyepetizer.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.zwh.annotation.apt.Router;
import com.zwh.mvparms.eyepetizer.R;
import com.zwh.mvparms.eyepetizer.app.constants.Constants;
import com.zwh.mvparms.eyepetizer.app.utils.helper.CacheFragmentAdapter;
import com.zwh.mvparms.eyepetizer.mvp.ui.fragment.CacheFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

@Router(Constants.CACHE)
public class CacheActivity extends BaseActivity implements IView{


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tl_tabs)
    TabLayout mTlTabs;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private AppComponent mAppComponent;
    private List<CacheFragment> fragments = new ArrayList<>();

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_cache;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initToolBar();
        List<String> list = new ArrayList<>();
        list.add("已缓存");
        list.add("正在缓存");
        Observable.fromIterable(list)
                .map(new Function<String, CacheFragment>() {
                    @Override
                    public CacheFragment apply(@NonNull String category) throws Exception {
                        CacheFragment fragment = CacheFragment.newInstance(category);
                        fragments.add(fragment);
                        return fragment;
                    }
                })
                .toList()
                .map(fragments -> CacheFragmentAdapter.newInstance(getSupportFragmentManager(), fragments, list))
                .subscribe(mFragmentAdapter -> mViewpager.setAdapter(mFragmentAdapter));
        mTlTabs.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(0);
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }
}