package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.ChangePassWordContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.model.ChangePassWordModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/15 0015.
 */
@Module
public class ChangePassWordModule {
    private ChangePassWordContract.View view;

    public ChangePassWordModule(ChangePassWordContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public ChangePassWordContract.View provideChangePassWordView() {
        return this.view;
    }

    @Provides
    @ActivityScope
    public ChangePassWordContract.Model provideChangePassWordModel(ChangePassWordModel changePassWordModel) {
        return changePassWordModel;
    }
}
