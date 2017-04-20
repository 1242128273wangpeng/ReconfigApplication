package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.SplashModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity.SplashActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity activity);

}
