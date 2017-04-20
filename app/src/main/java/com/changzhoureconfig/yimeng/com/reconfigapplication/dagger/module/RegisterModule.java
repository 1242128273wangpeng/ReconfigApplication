package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.RegisterContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.model.RegisterModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
@Module
public class RegisterModule {
    private RegisterContract.View view;

    public RegisterModule(RegisterContract.View view) {
        this.view = view;
    }

    @ActivityScope //注入Activity(View)，同时规定Activity所对应的域是@ActivityScope
    @Provides
    RegisterContract.View provideRegisterView() {
        return this.view;
    }

    @ActivityScope  //注入(Model)，同时规定Activity所对应的域是@ActivityScope
    @Provides
    RegisterContract.Model provideRegisterModel(RegisterModel registerModel) {
        return registerModel;
    }
}
