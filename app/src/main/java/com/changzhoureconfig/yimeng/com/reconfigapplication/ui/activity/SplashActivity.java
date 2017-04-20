package com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginResultData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.AppComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.DaggerSplashComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.SplashModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.SplashContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.presenter.SplashPresenter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.adapter.ImagePaperAdapter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.common.WEActivity;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class SplashActivity extends WEActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.mySplash)
    ViewPager mySplash;
    @BindView(R.id.tvIntentTime)
    TextView tvIntentTime;
    @BindView(R.id.wel_splash)
    RelativeLayout welSplash;

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

    }

    @Override
    public void killMyself() {

    }

    @Override
    public void animateBackgroundImage(Animation animation) {
        welSplash.startAnimation(animation);
    }

    @Override
    public void toLogin() {
        Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void toMain() {
        Intent loginIntent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void toBaseSetting(LoginResultData.user user) {
        Intent intent = new Intent(SplashActivity.this, RegisterBaseSettingActivity.class);
        intent.putExtra("nickname", user.getNickname());
        startActivity(intent);
    }

    @Override
    public void setBannerAdapter(ImagePaperAdapter pagerAdapter) {
        mySplash.setAdapter(pagerAdapter);
        mySplash.setCurrentItem(0);
    }

    @Override
    public void setAutoInterval(int currentPage) {
        mySplash.setCurrentItem(currentPage);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerSplashComponent.builder().appComponent(appComponent).splashModule(new SplashModule(this)).build().inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_splash, null, false);
    }

    @Override
    protected void initData() {
        mPresenter.StartBackgroundImageAnimationRequestAdvert(this);
    }

    @OnClick({R.id.tvIntentTime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvIntentTime:
                welSplash.clearAnimation();
                mPresenter.requestLoginOrToLogin();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
