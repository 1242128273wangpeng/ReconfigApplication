package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.LoginModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
