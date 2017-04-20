package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.presenter;

import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BasePresenter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.WelcomeContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity.SplashActivity;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.adapter.FragmentPagerAdapter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.UiUtils;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
@ActivityScope
public class WelcomePresenter extends BasePresenter<WelcomeContract.Model, WelcomeContract.View> {
    private FragmentPagerAdapter pagerAdapter;

    @Inject
    public WelcomePresenter(WelcomeContract.Model model,WelcomeContract.View view) {
        super(model, view);
    }

    public void initFragmentView(FragmentManager fm) {
        pagerAdapter = new FragmentPagerAdapter(fm, mModel.initFragment());
        if (mModel.getIsFirst()) {
            mRootView.launchActivity();
        } else {
            mRootView.setViewAdapter(pagerAdapter);
        }
    }

}
