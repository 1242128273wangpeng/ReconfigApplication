package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.RegisterBaseSettingContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.model.RegisterBaseSettingModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/18 0018.
 */
@Module
public class RegisterBaseSettingModule {
    private RegisterBaseSettingContract.View view;

    public RegisterBaseSettingModule(RegisterBaseSettingContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public RegisterBaseSettingContract.View provideRegisterBaseSettingView() {
        return view;
    }

    @Provides
    @ActivityScope
    public RegisterBaseSettingContract.Model provideBaseSettingContractModel(RegisterBaseSettingModel registerBaseSettingModel) {
        return registerBaseSettingModel;
    }
}
