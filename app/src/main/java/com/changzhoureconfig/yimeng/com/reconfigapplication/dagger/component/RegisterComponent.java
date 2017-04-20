package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.RegisterModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity.RegisterActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = RegisterModule.class)
public interface RegisterComponent {
    void inject(RegisterActivity activity);
}
