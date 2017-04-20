package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.ChangePassWordModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity.ChangePassWordActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/15 0015.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ChangePassWordModule.class)
public interface ChangePassWordComponent {
    public void inject(ChangePassWordActivity activity);
}
