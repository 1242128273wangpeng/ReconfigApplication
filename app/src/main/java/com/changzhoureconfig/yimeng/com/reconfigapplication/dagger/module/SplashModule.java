package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.manage.ServiceManage;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.SplashContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.model.SplashModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
@Module
public class SplashModule {
    private SplashContract.View view;

    /**
     * 构建SplashModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SplashModule(SplashContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SplashContract.View provideSplashView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SplashContract.Model provideSplashModel(ServiceManage serviceManage) {
        return new SplashModel(serviceManage);
    }
}
