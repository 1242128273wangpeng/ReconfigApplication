package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.LoginContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.model.LoginModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
@Module
public class LoginModule {
    private LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LoginContract.View provideLoginView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LoginContract.Model provideLoginModel(LoginModel loginModel) {
        return loginModel;
    }
}
