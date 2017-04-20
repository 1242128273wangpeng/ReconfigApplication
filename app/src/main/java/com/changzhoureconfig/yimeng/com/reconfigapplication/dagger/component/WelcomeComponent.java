package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.WelcomeModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity.WelcomeActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
@ActivityScope
@Component(modules = WelcomeModule.class, dependencies = AppComponent.class)
public interface WelcomeComponent {
    void inject(WelcomeActivity activity);
}
