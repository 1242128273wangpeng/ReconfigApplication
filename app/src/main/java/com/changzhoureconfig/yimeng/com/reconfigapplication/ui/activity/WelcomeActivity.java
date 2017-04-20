package com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.AppComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.DaggerWelcomeComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.WelcomeModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.WelcomeContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.presenter.WelcomePresenter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.adapter.FragmentPagerAdapter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.common.WEActivity;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public class WelcomeActivity extends WEActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private MyPageTransformer pageTransformer;


    @Override
    public void showLoading(String message) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showWarn(String message) {

    }

    @Override
    public void launchActivity() {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void setViewAdapter(final FragmentPagerAdapter fragmentPagerAdapter) {
        viewpager.setAdapter(fragmentPagerAdapter);
        viewpager.setPageTransformer(true, pageTransformer);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerWelcomeComponent.builder().appComponent(appComponent).welcomeModule(new WelcomeModule(this)).build().inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_guide, null, false);
    }

    @Override
    protected void initData() {
        pageTransformer = new MyPageTransformer();
        mPresenter.initFragmentView(getSupportFragmentManager());
    }

    class MyPageTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View view, float position) {
            // 缩放
            view.setScaleX(Math.max(0.8f, (1 - Math.abs(position))));
            view.setScaleY(Math.max(0.8f, (1 - Math.abs(position))));
            //外翻转
            view.setPivotX(position < 0f ? view.getWidth() : 0f);
            view.setPivotY(view.getHeight() * 0.5f);
            view.setRotationY(position * 90f);
        }
    }

    @Override
    protected void onResume() {
        JPushInterface.onResume(WelcomeActivity.this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        JPushInterface.onPause(WelcomeActivity.this);
        super.onPause();
    }

}
