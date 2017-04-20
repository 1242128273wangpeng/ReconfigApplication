package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.RegisterBaseSettingModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity.RegisterBaseSettingActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/18 0018.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = RegisterBaseSettingModule.class)
public interface RegisterBaseSettingComponent {
    void inject(RegisterBaseSettingActivity activity);
}
